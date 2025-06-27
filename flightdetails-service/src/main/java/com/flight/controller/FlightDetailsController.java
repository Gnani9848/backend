package com.flight.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.flight.dto.FlightDetailsDTO;
import com.flight.service.impl.FlightDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@Tag(name = "Flight Details", description = "Flight Details management APIs")
public class FlightDetailsController {
    
    @Autowired
    private FlightDetailsServiceImpl flightDetailsService;
    
    @PostMapping
    @Operation(
        summary = "Add new flight", 
        description = "Add a new flight to the system (Admin only)",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FlightDetailsDTO> addFlightDetails(@RequestBody FlightDetailsDTO flightDetailsDTO) {
        FlightDetailsDTO savedFlight = flightDetailsService.addFlightDetails(flightDetailsDTO);
        return new ResponseEntity<>(savedFlight, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @Operation(
        summary = "Update flight", 
        description = "Update an existing flight (Admin only)",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FlightDetailsDTO> updateFlightDetails(
            @PathVariable String id, 
            @RequestBody FlightDetailsDTO flightDetailsDTO) {
        FlightDetailsDTO updatedFlight = flightDetailsService.updateFlightDetails(id, flightDetailsDTO);
        return ResponseEntity.ok(updatedFlight);
    }
    
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete flight", 
        description = "Delete a flight by ID (Admin only)",
        security = { @SecurityRequirement(name = "bearerAuth") }
    )
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteFlightDetails(@PathVariable String id) {
        flightDetailsService.deleteFlightDetails(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    @Operation(
        summary = "Get flight by ID", 
        description = "Retrieve a flight by its ID"
    )
    public ResponseEntity<FlightDetailsDTO> getFlightDetailsById(@PathVariable String id) {
        FlightDetailsDTO flightDetails = flightDetailsService.getFlightDetailsById(id);
        return ResponseEntity.ok(flightDetails);
    }
    
    @GetMapping
    @Operation(
        summary = "Get all flights", 
        description = "Retrieve all flights in the system"
    )
    public ResponseEntity<List<FlightDetailsDTO>> getAllFlightDetails() {
        List<FlightDetailsDTO> flightDetailsList = flightDetailsService.getAllFlightDetails();
        return ResponseEntity.ok(flightDetailsList);
    }
    
    @GetMapping("/search")
    @Operation(
        summary = "Search flights", 
        description = "Search flights by departure city, arrival city, and date"
    )
    public ResponseEntity<List<FlightDetailsDTO>> searchFlights(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity,
            @RequestParam String departureDate) {
        
        List<FlightDetailsDTO> flights = flightDetailsService.searchFlights(departureCity, arrivalCity, departureDate);
        return ResponseEntity.ok(flights);
    }
}