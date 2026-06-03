package cl.duoc.mineria.telemetria.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExternalCamionService {

    private final WebClient webClient;

    public boolean verificarCamionExiste(Long camionId) {
        try {
            Boolean existe = webClient.get()
                    .uri("http://localhost:8084/api/v1/camiones/existe/" + camionId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            return existe != null && existe;
        } catch (Exception e) {
            System.out.println("[Telemetría] Falló la conexión con Camiones (8084). Activando tolerancia para desarrollo local.");
            return true; // Retorna true temporalmente en fallback para no bloquear tus pruebas si el otro servicio está apagado
        }
    }
}