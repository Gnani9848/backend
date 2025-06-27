package com.fares.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fares.entitty.FareDetail;
import com.fares.service.FareService;

@RestController
@RequestMapping("/api/fares")
@Tag(name = "Fare Service", description = "APIs for fare calculation")
public class FareController {
    
	 @Autowired
	    private FareService fareService;

	    @GetMapping("/search")
	    @Operation(summary = "Search fares for a route and date")
	    public List<FareDetail> searchFares(
	        @RequestParam String departureCity,
	        @RequestParam String arrivalCity,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String departureDate
	    ) {
	        return fareService.searchFares(departureCity, arrivalCity, departureDate);
	    }

	    @GetMapping("/flight")
	    @Operation(summary = "Get fare for a specific flight")
	    public FareDetail getFareForFlight(
	        @RequestParam String flightNumber,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
	        @RequestParam int passengers
	    ) {
	        return fareService.getFareForFlight(flightNumber, departureDate,passengers);
	    }

	    @PostMapping
	    @Operation(summary = "Create a new fare detail")
	    public FareDetail createFareDetail(@RequestBody FareDetail fareDetail) {
	        return fareService.createFareDetail(fareDetail);
	    }

	    @PutMapping("/{id}")
	    @Operation(summary = "Update an existing fare detail")
	    public FareDetail updateFareDetail(
	        @PathVariable String id, 
	        @RequestBody FareDetail fareDetailUpdates
	    ) {
	        return fareService.updateFareDetail(id, fareDetailUpdates);
	    }

	    @DeleteMapping("/{id}")
	    @Operation(summary = "Delete a fare detail")
	    public ResponseEntity<Void> deleteFareDetail(@PathVariable String id) {
	        fareService.deleteFareDetail(id);
	        return ResponseEntity.ok().build();
	    }
}


