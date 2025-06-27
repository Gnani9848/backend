package com.flight.testService;

import com.flight.dto.FlightDetailsDTO;
import com.flight.entity.FlightDetails;
import com.flight.exception.FlightNotFoundException;
import com.flight.repository.FlightDetailsRepository;
import com.flight.service.impl.FlightDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightDetailsServiceTest {

    @InjectMocks
    private FlightDetailsServiceImpl service;

    @Mock
    private FlightDetailsRepository repository;

    @Mock
    private ModelMapper modelMapper;

    private FlightDetails flightEntity;
    private FlightDetailsDTO flightDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        flightEntity = new FlightDetails();
        flightEntity.setFlightNumber("FL123");
        flightEntity.setDepartureCity("CityA");
        flightEntity.setArrivalCity("CityB");
        flightEntity.setDepartureDate(LocalDate.of(2024, 6, 1));
        

        flightDTO = new FlightDetailsDTO();
        flightDTO.setFlightNumber("FL123");
        flightDTO.setDepartureCity("CityA");
        flightDTO.setArrivalCity("CityB");
        flightDTO.setDepartureDate(LocalDate.of(2024, 6, 1));
        
    }

    @Test
    void testAddFlightDetails() {
        when(modelMapper.map(flightDTO, FlightDetails.class)).thenReturn(flightEntity);
        when(repository.save(flightEntity)).thenReturn(flightEntity);
        when(modelMapper.map(flightEntity, FlightDetailsDTO.class)).thenReturn(flightDTO);

        FlightDetailsDTO result = service.addFlightDetails(flightDTO);

        assertEquals(flightDTO, result);
    }

    @Test
    void testUpdateFlightDetails_Success() {
        when(repository.findById("FL123")).thenReturn(Optional.of(flightEntity));
        when(modelMapper.map(flightDTO, FlightDetails.class)).thenReturn(flightEntity);
        when(repository.save(flightEntity)).thenReturn(flightEntity);
        when(modelMapper.map(flightEntity, FlightDetailsDTO.class)).thenReturn(flightDTO);

        FlightDetailsDTO result = service.updateFlightDetails("FL123", flightDTO);

        assertEquals(flightDTO, result);
    }

    @Test
    void testUpdateFlightDetails_NotFound() {
        when(repository.findById("FL123")).thenReturn(Optional.empty());

        assertThrows(FlightNotFoundException.class, () -> service.updateFlightDetails("FL123", flightDTO));
    }

    @Test
    void testDeleteFlightDetails_Success() {
        when(repository.findById("FL123")).thenReturn(Optional.of(flightEntity));
        doNothing().when(repository).delete(flightEntity);

        assertDoesNotThrow(() -> service.deleteFlightDetails("FL123"));
        verify(repository, times(1)).delete(flightEntity);
    }

    @Test
    void testDeleteFlightDetails_NotFound() {
        when(repository.findById("FL123")).thenReturn(Optional.empty());

        assertThrows(FlightNotFoundException.class, () -> service.deleteFlightDetails("FL123"));
    }

    @Test
    void testGetFlightDetailsById_Success() {
        when(repository.findById("FL123")).thenReturn(Optional.of(flightEntity));
        when(modelMapper.map(flightEntity, FlightDetailsDTO.class)).thenReturn(flightDTO);

        FlightDetailsDTO result = service.getFlightDetailsById("FL123");

        assertEquals(flightDTO, result);
    }

    @Test
    void testGetFlightDetailsById_NotFound() {
        when(repository.findById("FL123")).thenReturn(Optional.empty());

        assertThrows(FlightNotFoundException.class, () -> service.getFlightDetailsById("FL123"));
    }

    @Test
    void testGetAllFlightDetails() {
        List<FlightDetails> entityList = Arrays.asList(flightEntity);
        when(repository.findAll()).thenReturn(entityList);
        when(modelMapper.map(flightEntity, FlightDetailsDTO.class)).thenReturn(flightDTO);

        List<FlightDetailsDTO> result = service.getAllFlightDetails();

        assertEquals(1, result.size());
        assertEquals(flightDTO, result.get(0));
    }

    @Test
    void testSearchFlights() {
        List<FlightDetails> entityList = Arrays.asList(flightEntity);
        when(repository.searchAvailableFlights("CityA", "CityB", LocalDate.of(2024, 6, 1))).thenReturn(entityList);
        when(modelMapper.map(flightEntity, FlightDetailsDTO.class)).thenReturn(flightDTO);

        List<FlightDetailsDTO> result = service.searchFlights("CityA", "CityB", "2024-06-01");

        assertEquals(1, result.size());
        assertEquals(flightDTO, result.get(0));
    }
}
