package cl.duoc.mineria.telemetria.service;

import cl.duoc.mineria.telemetria.dto.RegistrarTelemetriaDTO;
import cl.duoc.mineria.telemetria.exception.CamionNotFoundException;
import cl.duoc.mineria.telemetria.model.Telemetria;
import cl.duoc.mineria.telemetria.repository.TelemetriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TelemetriaService {

    private final TelemetriaRepository telemetriaRepository;
    private final ExternalCamionService externalCamionService;

    @Transactional
    public Telemetria registrarLectura(RegistrarTelemetriaDTO dto) {
        // Validación síncrona vía WebClient al puerto 8084
        if (!externalCamionService.verificarCamionExiste(dto.getCamionId())) {
            throw new CamionNotFoundException("El camión con ID " + dto.getCamionId() + " no existe en los registros de la flota.");
        }

        Telemetria telemetria = Telemetria.builder()
                .camionId(dto.getCamionId())
                .latitud(dto.getLatitud())
                .longitud(dto.getLongitud())
                .ultimaSenal(LocalDateTime.now()) // Mapeado a ultimaSenal del diagrama
                .alertaInactividad(dto.getAlertaInactividad())
                .build();

        return telemetriaRepository.save(telemetria);
    }

    @Transactional(readOnly = true)
    public List<Telemetria> listarPorCamion(Long camionId) {
        return telemetriaRepository.findByCamionIdOrderByUltimaSenalDesc(camionId);
    }
}