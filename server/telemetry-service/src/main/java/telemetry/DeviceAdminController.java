package main.java.telemetry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Map;

@RestController
@RequestMapping(value = "/telemetry/devices")
@Tag(name = "Device Admin API", description = "Admin-only endpoints for managing device registration")
public class DeviceAdminController {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceAdminController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Operation(summary = "Approve a registered device")
    @PostMapping("/{macAddress}/approve")
    public ResponseEntity<Map<String, String>> approveDevice(@PathVariable String macAddress) {
        Device device = deviceRepository.findById(macAddress).orElse(null);
        if (device == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Device not found.", "macAddress", macAddress));
        }

        device.setApproved(true);
        deviceRepository.save(device);
        return ResponseEntity.ok(Map.of("status", "approved", "macAddress", macAddress));
    }
}
