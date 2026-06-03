package cl.duoc.mineria.telemetria.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "telemetrias")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Telemetria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "camion_id", nullable = false)
    private Long camionId; // Clave foránea lógica al microservicio de Camiones

    @Column(nullable = false)
    private Double latitud;

    @Column(nullable = false)
    private Double longitud;

    @Column(name = "ultima_senal", nullable = false)
    private LocalDateTime ultimaSenal;

    @Column(name = "alerta_inactividad", nullable = false)
    private Boolean alertaInactividad;
}