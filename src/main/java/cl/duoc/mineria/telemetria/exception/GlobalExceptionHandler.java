package cl.duoc.mineria.telemetria.exception;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors(MethodArgumentNotValidException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Lectura de telemetría con parámetros fuera de rango");
        problem.setTitle("Validation Error");
        problem.setProperty("timestamp", Instant.now());

        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
            .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (e, n) -> e));

        problem.setProperty("errores", errors);
        return problem;
    }

    @ExceptionHandler(CamionNotFoundException.class)
    public ProblemDetail handleCamionNotFound(CamionNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Camion Mismatch");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(ServicioExternoNoDisponibleException.class)
    public ProblemDetail handleServicioNoDisponible(ServicioExternoNoDisponibleException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        problem.setTitle("Servicio Externo No Disponible");
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }
}