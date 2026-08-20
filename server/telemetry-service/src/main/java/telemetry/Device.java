package main.java.telemetry;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Device {
    @Id
    private String macAddress;
    private String sharedSecret;

    public Device() {}

    public Device(String macAddress, String sharedSecret) {
        this.macAddress = macAddress;
        this.sharedSecret = sharedSecret;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getSharedSecret() {
        return sharedSecret;
    }

    public void setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
    }
}
