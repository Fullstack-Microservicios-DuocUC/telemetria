package cl.duoc.mineria.telemetria.repository;

import cl.duoc.mineria.telemetria.model.Telemetria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TelemetriaRepository extends JpaRepository<Telemetria, Long> {
    List<Telemetria> findByCamionIdOrderByUltimaSenalDesc(Long camionId);
}