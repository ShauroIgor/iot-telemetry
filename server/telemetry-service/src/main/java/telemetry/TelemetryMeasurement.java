package main.java.telemetry;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TelemetryMeasurement {
    @Id
    private String timestamp;
    private double temperatureValue;

    public TelemetryMeasurement() {}

    public TelemetryMeasurement(String timestamp, double temperatureValue) {
        this.timestamp = timestamp;
        this.temperatureValue = temperatureValue;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(double temperatureValue) {
        this.temperatureValue = temperatureValue;
    }
}