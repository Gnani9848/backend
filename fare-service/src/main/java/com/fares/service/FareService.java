package com.fares.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fares.dto.FlightDTO;
import com.fares.entitty.FareDetail;
import com.fares.feign.FlightDetailsClient;
import com.fares.repository.FareDetailRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FareService {
    @Autowired
    private FareDetailRepository fareDetailRepository;

    @Autowired
    private FlightDetailsClient flightDetailsClient;

    // Dynamic Pricing Calculation
    public double calculateDynamicPrice(FlightDTO flight, FareDetail fareDetail) {
        // Base price from flight details service
        double basePrice = flight.getBasePrice();

        // Apply dynamic factors
        double demandFactor = calculateDemandFactor(flight);
        double taxFactor = 1 + (fareDetail.getTaxPercentage() / 100);
        double discountFactor = 1 - (fareDetail.getDiscountPercentage() / 100);

        // Final price calculation
        double finalPrice = basePrice * demandFactor * taxFactor * discountFactor;
        
        // Update fare detail
        fareDetail.setBasePrice(basePrice);
        fareDetail.setFinalPrice(finalPrice);
        fareDetail.setDemandFactor(demandFactor);

        return finalPrice;
    }

    // Demand Factor Calculation
    private double calculateDemandFactor(FlightDTO flight) {
        // Logic to calculate demand factor based on available seats
        double availabilityRatio = (double) flight.getAvailableSeats() / flight.getTotalSeats();
        
        // More demand (fewer available seats) increases price
        return availabilityRatio < 0.2 ? 1.5 : 
               availabilityRatio < 0.5 ? 1.2 : 
               1.0;
    }

    // Search Fares with Dynamic Pricing
    public List<FareDetail> searchFares(String departureCity, String arrivalCity, String departureDate) {
        // Get flight details first
    	
        List<FlightDTO> flights = flightDetailsClient.searchFlights(
            departureCity, arrivalCity, departureDate
        );
    	LocalDate parsedDate = LocalDate.parse(departureDate);
        // Convert and apply dynamic pricing
        return flights.stream().map(flight -> {
            // Find or create fare detail
            FareDetail fareDetail = fareDetailRepository
                .findByFlightNumberAndDepartureDate(flight.getFlightNumber(), parsedDate )
                .orElse(new FareDetail());

            // Set basic details
            fareDetail.setFlightNumber(flight.getFlightNumber());
            fareDetail.setAirline(flight.getAirline());
            fareDetail.setDepartureCity(departureCity);
            fareDetail.setArrivalCity(arrivalCity);
            fareDetail.setDepartureDate(parsedDate );
            fareDetail.setAvailabilityStatus(flight.isActive() );

            // Calculate dynamic price
            double finalPrice = calculateDynamicPrice(flight, fareDetail);
            fareDetail.setFinalPrice(finalPrice);

            // Save and return
            return fareDetailRepository.save(fareDetail);
        }).collect(Collectors.toList());
    }

    // Get Fare for Specific Flight
    public FareDetail getFareForFlight(String flightNumber, LocalDate departureDate,int passengers) {
        // Get flight details
        FlightDTO flight = flightDetailsClient.getFlightByNumber(flightNumber);

        // Find or create fare detail
        FareDetail fareDetail = fareDetailRepository
            .findByFlightNumberAndDepartureDate(flightNumber, departureDate)
            .orElse(new FareDetail());

        // Set basic details
        fareDetail.setFlightNumber(flightNumber);
        fareDetail.setAirline(flight.getAirline());
        fareDetail.setDepartureDate(departureDate);
        fareDetail.setAvailabilityStatus(flight.isActive() );

        // Calculate dynamic price
        double finalPrice = calculateDynamicPrice(flight, fareDetail);
        if(passengers==1||passengers==0) {
        fareDetail.setFinalPrice(finalPrice);
        }else {
        	fareDetail.setFinalPrice(passengers*finalPrice);
        }
        // Save and return
        return fareDetailRepository.save(fareDetail);
    }

    // Additional CRUD methods
    public FareDetail createFareDetail(FareDetail fareDetail) {
        return fareDetailRepository.save(fareDetail);
    }

    public FareDetail updateFareDetail(String id, FareDetail fareDetailUpdates) {
        FareDetail existingFareDetail = fareDetailRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Fare Detail not found"));

        // Update fields
        existingFareDetail.setTaxPercentage(fareDetailUpdates.getTaxPercentage());
        existingFareDetail.setDiscountPercentage(fareDetailUpdates.getDiscountPercentage());
        
        return fareDetailRepository.save(existingFareDetail);
    }

    public void deleteFareDetail(String id) {
        fareDetailRepository.deleteById(id);
    }
}