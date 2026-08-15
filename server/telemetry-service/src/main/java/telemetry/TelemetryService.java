package main.java.telemetry;

import org.springframework.stereotype.Service;

@Service
public class TelemetryService {
    
    private final DeviceRepository deviceRepository;

    public TelemetryService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public boolean addTelemetryMeasurement(TelemetryMeasurement measurement, String device)
    {
        if (deviceRepository.existsById(device))
        {
            //TODO: add measurement 
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
