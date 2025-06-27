package com.flight.testController;

import com.flight.controller.FlightDetailsController;
import com.flight.dto.FlightDetailsDTO;
import com.flight.service.impl.FlightDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightDetailControllerTest {

    @InjectMocks
    private FlightDetailsController controller;

    @Mock
    private FlightDetailsServiceImpl service;

    private FlightDetailsDTO sampleDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleDTO = new FlightDetailsDTO();
        sampleDTO.setFlightNumber("FL123");
        sampleDTO.setAirline("TestAir");
        sampleDTO.setDepartureCity("CityA");
        sampleDTO.setArrivalCity("CityB");
        // ...set other fields as needed...
    }

    @Test
    void testAddFlightDetails() {
        when(service.addFlightDetails(any(FlightDetailsDTO.class))).thenReturn(sampleDTO);

        ResponseEntity<FlightDetailsDTO> response = controller.addFlightDetails(sampleDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(sampleDTO, response.getBody());
    }

    @Test
    void testUpdateFlightDetails() {
        when(service.updateFlightDetails(eq("FL123"), any(FlightDetailsDTO.class))).thenReturn(sampleDTO);

        ResponseEntity<FlightDetailsDTO> response = controller.updateFlightDetails("FL123", sampleDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sampleDTO, response.getBody());
    }

    @Test
    void testDeleteFlightDetails() {
        doNothing().when(service).deleteFlightDetails("FL123");

        ResponseEntity<Void> response = controller.deleteFlightDetails("FL123");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).deleteFlightDetails("FL123");
    }

    @Test
    void testGetFlightDetailsById() {
        when(service.getFlightDetailsById("FL123")).thenReturn(sampleDTO);

        ResponseEntity<FlightDetailsDTO> response = controller.getFlightDetailsById("FL123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sampleDTO, response.getBody());
    }

    @Test
    void testGetAllFlightDetails() {
        List<FlightDetailsDTO> list = Arrays.asList(sampleDTO);
        when(service.getAllFlightDetails()).thenReturn(list);

        ResponseEntity<List<FlightDetailsDTO>> response = controller.getAllFlightDetails();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
    }

    @Test
    void testSearchFlights() {
        List<FlightDetailsDTO> list = Arrays.asList(sampleDTO);
        when(service.searchFlights("CityA", "CityB", "2024-06-01")).thenReturn(list);

        ResponseEntity<List<FlightDetailsDTO>> response = controller.searchFlights("CityA", "CityB", "2024-06-01");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
    }
}
