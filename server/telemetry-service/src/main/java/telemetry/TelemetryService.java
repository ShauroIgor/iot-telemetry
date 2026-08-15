package main.java.telemetry;

import org.springframework.stereotype.Service;

@Service
public class TelemetryService {
    
    private final TelemetryRepository telemetryRepository;

    public TelemetryService(TelemetryRepository telemetryRepository) {
        this.telemetryRepository = telemetryRepository;
    }

    public boolean addTelemetryMeasurement(TelemetryMeasurement measurement, String deviceId)
    {
        measurement.setDeviceId(deviceId);
        telemetryRepository.save(measurement);
        return true;
    }
    
}
