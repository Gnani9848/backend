package com.flight.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDetailsDTO {
    private Long id;
    private String flightNumber;
    private String airline;
    private String departureCity;
    private String arrivalCity;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private int totalSeats;
    private int availableSeats;
    private double basePrice;
   
    private boolean active;
	
}
