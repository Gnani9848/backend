package com.flight.service.impl;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.dto.FlightDetailsDTO;
import com.flight.entity.FlightDetails;
import com.flight.exception.FlightNotFoundException;
import com.flight.repository.FlightDetailsRepository;

@Service
public class FlightDetailsServiceImpl  {
    
    @Autowired
    private FlightDetailsRepository flightDetailsRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    public FlightDetailsDTO addFlightDetails(FlightDetailsDTO flightDetailsDTO) {
        FlightDetails flightDetails = modelMapper.map(flightDetailsDTO, FlightDetails.class);
        flightDetails = flightDetailsRepository.save(flightDetails);
        return modelMapper.map(flightDetails, FlightDetailsDTO.class);
    }
    
    public FlightDetailsDTO updateFlightDetails(String id, FlightDetailsDTO flightDetailsDTO) {
        FlightDetails existingFlight = flightDetailsRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));
        
        modelMapper.map(flightDetailsDTO, existingFlight);
        existingFlight.setFlightNumber(id); // Ensure ID remains the same
        
        FlightDetails updatedFlight = flightDetailsRepository.save(existingFlight);
        return modelMapper.map(updatedFlight, FlightDetailsDTO.class);
    }
    
    public void deleteFlightDetails(String id) {
        FlightDetails existingFlight = flightDetailsRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));
        
        flightDetailsRepository.delete(existingFlight);
    }
    
    public FlightDetailsDTO getFlightDetailsById(String id) {
        FlightDetails flightDetails = flightDetailsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Flight not found with id: " + id));
        
        return modelMapper.map(flightDetails, FlightDetailsDTO.class);
    }
    
    public List<FlightDetailsDTO> getAllFlightDetails() {
        List<FlightDetails> flightDetailsList = flightDetailsRepository.findAll();
        
        return flightDetailsList.stream()
                .map(flight -> modelMapper.map(flight, FlightDetailsDTO.class))
                .collect(Collectors.toList());
    }
    
    public List<FlightDetailsDTO> searchFlights(String departureCity, String arrivalCity, String departureDate) {
    	LocalDate parsedDate = LocalDate.parse(departureDate);
    	List<FlightDetails> flights = flightDetailsRepository.searchAvailableFlights(departureCity, arrivalCity, parsedDate);

        
        return flights.stream()
                .map(flight -> modelMapper.map(flight, FlightDetailsDTO.class))
                .collect(Collectors.toList());
    }


}
