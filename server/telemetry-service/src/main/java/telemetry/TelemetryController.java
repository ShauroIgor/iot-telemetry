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

@RestController
@RequestMapping(value = "/telemetry")
@Tag(name = "User API", description = "API for managing users")
public class TelemetryController {

    private final TelemetryService telemetryService;

    @Autowired
    public TelemetryController(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "400", description = "Invalid user supplied", content = @Content) })
    @PostMapping({ "", "/" })
    public ResponseEntity<String> addTelemetryMeasurement(@RequestBody TelemetryMeasurement measurement, @RequestParam String device) {
        telemetryService.addTelemetryMeasurement(measurement, device);
        return ResponseEntity.status(HttpStatus.CREATED).body("Measurement added successfully");
    }

    // TODO: Login endpoint
}