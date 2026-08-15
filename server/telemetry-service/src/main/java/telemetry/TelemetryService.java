package main.java.telemetry;

import org.springframework.stereotype.Service;

@Service
public class TelemetryService {
    
    private final DeviceRepository deviceRepository;

    public TelemetryService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public bool addTelemetryMeasurement(TelemetryMeasurement measurement, String device)
    {
        if (deviceRepository.contains(device))
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
