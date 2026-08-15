package main.java.telemetry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TelemetryServiceTest {

    @Mock
    private TelemetryRepository telemetryRepository;

    @InjectMocks
    private TelemetryService telemetryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTelemetryMeasurement() {
        String deviceId = "device-123";
        TelemetryMeasurement measurement = new TelemetryMeasurement();
        measurement.setTimestamp("2026-10-05T01:00:00Z");
        measurement.setMetricName("HUMIDITY");
        measurement.setMetricValue(55.4);
        boolean result = telemetryService.addTelemetryMeasurement(measurement, deviceId);
        assertTrue(result, "Adding measurement should return true");
        ArgumentCaptor<TelemetryMeasurement> captor = ArgumentCaptor.forClass(TelemetryMeasurement.class);
        verify(telemetryRepository, times(1)).save(captor.capture());
        TelemetryMeasurement savedMeasurement = captor.getValue();
        assertEquals(deviceId, savedMeasurement.getDeviceId(), "Device ID should be set correctly on the measurement");
        assertEquals("2026-10-05T01:00:00Z", savedMeasurement.getTimestamp(), "Timestamp should remain unchanged");
        assertEquals("HUMIDITY", savedMeasurement.getMetricName(), "Metric name should remain unchanged");
        assertEquals(55.4, savedMeasurement.getMetricValue(), "Metric value should remain unchanged");
    }
}
