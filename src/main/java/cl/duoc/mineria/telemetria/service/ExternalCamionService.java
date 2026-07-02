package cl.duoc.mineria.telemetria.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import cl.duoc.mineria.telemetria.exception.ServicioExternoNoDisponibleException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExternalCamionService {

    private final WebClient webClient;

    public boolean verificarCamionExiste(Long camionId) {
        try {
            Boolean existe = webClient.get()
                    .uri("http://camiones/api/v1/camiones/existe/" + camionId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            return existe != null && existe;
        } catch (WebClientResponseException.NotFound e) {
            return false;
        } catch (Exception e) {
            throw new ServicioExternoNoDisponibleException(
                "No se pudo validar el camión " + camionId + ": " + e.getMessage());
        }
    }
}