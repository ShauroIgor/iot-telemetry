package main.java.telemetry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DeviceRepository extends JpaRepository<TelemetryMeasurement, String> {
}