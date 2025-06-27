package com.search.feign;

import java.util.Collections;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.search.dto.fareDetailDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name = "fare-service")
public interface FareClient {
    
    @GetMapping("/api/fares/search")
    @CircuitBreaker(name = "fareServiceCB", fallbackMethod = "searchFaresFallback")
    List<fareDetailDTO> searchFares(
        @RequestParam("departureCity") String departureCity,
        @RequestParam("arrivalCity") String arrivalCity,
        @RequestParam("departureDate") String departureDate
    );

    // Fallback method for circuit breaker
    default List<fareDetailDTO> searchFaresFallback(String departureCity, String arrivalCity, String departureDate, Throwable t) {
        // Return empty list or cached data as fallback
        return Collections.emptyList();
    }
}