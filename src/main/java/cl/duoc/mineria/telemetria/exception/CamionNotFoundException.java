package cl.duoc.mineria.telemetria.exception;

public class CamionNotFoundException extends RuntimeException {
    public CamionNotFoundException(String mensaje) {
        super(mensaje);
    }
}