package cl.duoc.mineria.telemetria.controller;

import cl.duoc.mineria.telemetria.dto.RegistrarTelemetriaDTO;
import cl.duoc.mineria.telemetria.model.Telemetria;
import cl.duoc.mineria.telemetria.service.TelemetriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/telemetria")
@RequiredArgsConstructor
@Tag(name = "Gestión de Telemetría", description = "Operaciones para registrar y consultar datos de telemetría de los equipos.")
public class TelemetriaController {

    private final TelemetriaService telemetriaService;

    @PostMapping("/registrar")
    @Operation(summary = "Registrar una nueva lectura de telemetría", description = "Guarda una nueva entrada de datos telemétricos para un camión específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lectura de telemetría registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    public ResponseEntity<Telemetria> registrarLectura(@Valid @RequestBody RegistrarTelemetriaDTO dto) {
        return new ResponseEntity<>(telemetriaService.registrarLectura(dto), HttpStatus.CREATED);
    }

    @GetMapping("/camion/{camionId}")
    @Operation(summary = "Obtener historial de telemetría por camión", description = "Devuelve una lista de todas las lecturas de telemetría para un ID de camión específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historial obtenido correctamente")
    })
    public ResponseEntity<List<Telemetria>> obtenerHistorialCamion(@PathVariable Long camionId) {
        return ResponseEntity.ok(telemetriaService.listarPorCamion(camionId));
    }
}