package cl.duoc.mineria.telemetria.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegistrarTelemetriaDTO {

    @NotNull(message = "El ID del camión es obligatorio")
    private Long camionId;

    @NotNull(message = "La latitud es obligatoria")
    @Min(value = -90, message = "La latitud debe estar entre -90 y 90")
    @Max(value = 90, message = "La latitud debe estar entre -90 y 90")
    private Double latitud;

    @NotNull(message = "La longitud es obligatoria")
    @Min(value = -180, message = "La longitud debe estar entre -180 y 180")
    @Max(value = 180, message = "La longitud debe estar entre -180 y 180")
    private Double longitud;

    @NotNull(message = "El estado de alerta de inactividad es obligatorio")
    private Boolean alertaInactividad;
}