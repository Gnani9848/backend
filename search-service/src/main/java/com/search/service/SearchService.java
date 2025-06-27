package com.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.dto.FlightDetailsDTO;
import com.search.dto.SearchCriteriaDTO;
import com.search.dto.SearchResultDTO;
import com.search.dto.fareDetailDTO;
import com.search.feign.FareClient;
import com.search.feign.FlightDetailsClient;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    @Autowired
    private FlightDetailsClient flightDetailsClient;

    @Autowired
    private FareClient fareServiceClient;

    public List<SearchResultDTO> searchFlights(SearchCriteriaDTO searchCriteria) {
        // Fetch flight details
    	LocalDate date=searchCriteria.getDepartureDate();
  
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Convert LocalDate to String
    String  departureDate = date.format(formatter);
    
        List<FlightDetailsDTO> flightDetails = flightDetailsClient.searchFlights(
            searchCriteria.getDepartureCity(), 
            searchCriteria.getArrivalCity(), 
            departureDate);

 
        // Fetch fare details
        List<fareDetailDTO> fareDetails = fareServiceClient.searchFares(
            searchCriteria.getDepartureCity(), 
            searchCriteria.getArrivalCity(), 
             departureDate
        );

        // Combine flight and fare details
        return flightDetails.stream()
            .map(flight -> {
                // Find corresponding fare
                fareDetailDTO fare = fareDetails.stream()
                    .filter(f -> f.getFlightNumber().equals(flight.getFlightNumber()))
                    .findFirst()
                    .orElse(null);

                // Create search result
                SearchResultDTO result = new SearchResultDTO();
                result.setFlightNumber(flight.getFlightNumber());
                result.setAirline(flight.getAirline());
                result.setDepartureCity(flight.getDepartureCity());
                result.setArrivalCity(flight.getArrivalCity());
                result.setDepartureDate(flight.getDepartureDate());
                result.setDepartureTime(flight.getDepartureTime());
                result.setArrivalTime(flight.getArrivalTime());
                result.setAvailableSeats(flight.getAvailableSeats());
                
                // Pricing details
                if (fare != null) {
                    result.setBasePrice(fare.getBasePrice());
                    result.setFinalPrice(fare.getFinalPrice());

                }

                // Calculate flight duration
                result.setDuration(calculateFlightDuration(flight));
                
                // Additional attributes
// Default class

                return result;
            })
            .filter(this::filterFlightResults)
            .collect(Collectors.toList());
    }

    // Filter flights based on additional criteria
    private boolean filterFlightResults(SearchResultDTO flight) {
        // Additional filtering logic
        return flight.getAvailableSeats() > 0 && 
              
               flight.getFinalPrice() > 0;
    }

    // Calculate flight duration
    private int calculateFlightDuration(FlightDetailsDTO flight) {
        return (int) Duration.between(
            flight.getDepartureTime(), 
            flight.getArrivalTime()
        ).toMinutes();
    }

    // Advanced search with more filters
    public List<SearchResultDTO> advancedSearch(
        SearchCriteriaDTO searchCriteria, 
        List<String> airlines, 
        double minPrice, 
        double maxPrice
    ) {
        // Basic search
        List<SearchResultDTO> results = searchFlights(searchCriteria);

        // Apply additional filters
        return results.stream()
            .filter(flight -> 
                (airlines.isEmpty() || airlines.contains(flight.getAirline())) &&
                (flight.getFinalPrice() >= minPrice) &&
                (flight.getFinalPrice() <= maxPrice)
            )
            .collect(Collectors.toList());
    }
}