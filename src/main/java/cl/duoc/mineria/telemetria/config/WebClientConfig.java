package cl.duoc.mineria.telemetria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        // .create() levanta el objeto de forma nativa sin pedirle configuraciones a Spring
        return WebClient.create();
    }
}