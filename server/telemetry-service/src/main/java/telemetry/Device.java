package main.java.telemetry;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Device {
    @Id
    @Column(name = "mac_address")
    private String macAddress;
    
    @Column(name = "shared_secret")
    private String sharedSecret;
    
    @Column(name = "is_approved")
    private boolean isApproved = false;

    public Device() {}

    public Device(String macAddress, String sharedSecret) {
        this.macAddress = macAddress;
        this.sharedSecret = sharedSecret;
        this.isApproved = false;
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

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
