package cl.duoc.mineria.telemetria.controller;

import cl.duoc.mineria.telemetria.dto.RegistrarTelemetriaDTO;
import cl.duoc.mineria.telemetria.model.Telemetria;
import cl.duoc.mineria.telemetria.service.TelemetriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/telemetria")
@RequiredArgsConstructor
public class TelemetriaController {

    private final TelemetriaService telemetriaService;

    @PostMapping("/registrar")
    public ResponseEntity<Telemetria> registrarLectura(@Valid @RequestBody RegistrarTelemetriaDTO dto) {
        return new ResponseEntity<>(telemetriaService.registrarLectura(dto), HttpStatus.CREATED);
    }

    @GetMapping("/camion/{camionId}")
    public ResponseEntity<List<Telemetria>> obtenerHistorialCamion(@PathVariable Long camionId) {
        return ResponseEntity.ok(telemetriaService.listarPorCamion(camionId));
    }
}