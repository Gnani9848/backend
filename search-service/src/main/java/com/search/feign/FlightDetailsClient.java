package com.search.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.search.dto.FlightDetailsDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "flightdetails-service")
public interface FlightDetailsClient {
    
    @GetMapping("/api/flights/search")
    @CircuitBreaker(name = "flightDetailsCB", fallbackMethod = "searchFlightsFallback")
    List<FlightDetailsDTO> searchFlights(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity,
            @RequestParam String departureDate);

    // Fallback method for circuit breaker
    default List<FlightDetailsDTO> searchFlightsFallback(String departureCity, String arrivalCity, String departureDate, Throwable t) {
        // Return empty list or cached data as fallback
        return Collections.emptyList();
    }
}