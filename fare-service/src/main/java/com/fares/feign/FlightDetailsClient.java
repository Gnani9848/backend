package com.fares.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.fares.dto.FlightDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "flightdetails-service")
public interface FlightDetailsClient {

    @GetMapping("/api/flights/search")
    @CircuitBreaker(name = "flightDetailsCB", fallbackMethod = "searchFlightsFallback")
    List<FlightDTO> searchFlights(
        @RequestParam("departureCity") String departureCity,
        @RequestParam("arrivalCity") String arrivalCity,
        @RequestParam("departureDate") String departureDate
    );

    @GetMapping("/api/flights/{flightNumber}")
    @CircuitBreaker(name = "flightDetailsCB", fallbackMethod = "getFlightByNumberFallback")
    FlightDTO getFlightByNumber(@PathVariable("flightNumber") String flightNumber);

    // Fallback for searchFlights
    default List<FlightDTO> searchFlightsFallback(String departureCity, String arrivalCity, String departureDate, Throwable t) {
        // Return empty list or cached data as fallback
        return Collections.emptyList();
    }

    // Fallback for getFlightByNumber
    default FlightDTO getFlightByNumberFallback(String flightNumber, Throwable t) {
        // Return null or a default FlightDTO as fallback
        return null;
    }
}
