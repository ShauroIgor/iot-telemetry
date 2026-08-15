package main.java.telemetry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TelemetryRepository extends JpaRepository<TelemetryMeasurement, Long> {
    boolean existsByDeviceId(String deviceId);
}