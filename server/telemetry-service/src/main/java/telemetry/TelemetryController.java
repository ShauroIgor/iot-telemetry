package main.java.telemetry;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@RestController
@RequestMapping(value = "/telemetry")
@Tag(name = "Telemetry API", description = "API for telemetry and device management")
public class TelemetryController {

    private final TelemetryService telemetryService;
    private final DeviceRepository deviceRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public TelemetryController(TelemetryService telemetryService, DeviceRepository deviceRepository, ObjectMapper objectMapper) {
        this.telemetryService = telemetryService;
        this.deviceRepository = deviceRepository;
        this.objectMapper = objectMapper;
    }

    @Operation(summary = "Register a new device")
    @PostMapping("/register")
    public ResponseEntity<String> registerDevice(@RequestBody Device device) {
        deviceRepository.save(device);
        return ResponseEntity.ok("Device registered successfully with MAC: " + device.getMacAddress());
    }

    @Operation(summary = "Add telemetry measurement")
    @PostMapping({ "", "/" })
    public ResponseEntity<String> addTelemetryMeasurement(
            @RequestBody String rawPayload, 
            @RequestParam String device,
            @RequestHeader(value = "X-Signature", required = false) String signature) {
            
        Device dbDevice = deviceRepository.findById(device).orElse(null);
        if (dbDevice == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Device not registered.");
        }

        if (!dbDevice.isApproved()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Device is registered but pending approval.");
        }

        // HMAC verification
        if (signature == null || !verifyHmac(rawPayload, dbDevice.getSharedSecret(), signature)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing HMAC signature.");
        }

        try {
            TelemetryMeasurement measurement = objectMapper.readValue(rawPayload, TelemetryMeasurement.class);
            telemetryService.addTelemetryMeasurement(measurement, device);
            return ResponseEntity.status(HttpStatus.CREATED).body("Measurement added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid JSON payload.");
        }
    }

    private boolean verifyHmac(String payload, String secret, String providedSignature) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hmacBytes = mac.doFinal(payload.getBytes());
            
            // Convert to hex string
            StringBuilder sb = new StringBuilder();
            for (byte b : hmacBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString().equalsIgnoreCase(providedSignature);
        } catch (Exception e) {
            return false;
        }
    }
}