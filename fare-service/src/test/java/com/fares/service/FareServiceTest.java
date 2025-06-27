package com.fares.service;

import com.fares.dto.FlightDTO;
import com.fares.entitty.FareDetail;
import com.fares.feign.FlightDetailsClient;
import com.fares.repository.FareDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FareServiceTest {

    @InjectMocks
    private FareService fareService;

    @Mock
    private FareDetailRepository fareDetailRepository;

    @Mock
    private FlightDetailsClient flightDetailsClient;

    private FlightDTO flightDTO;
    private FareDetail fareDetail;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        flightDTO = new FlightDTO();
        flightDTO.setFlightNumber("FL123");
        flightDTO.setAirline("TestAir");
        flightDTO.setDepartureCity("CityA");
        flightDTO.setArrivalCity("CityB");
        flightDTO.setDepartureDate(LocalDate.of(2024, 6, 1));
        flightDTO.setAvailableSeats(10);
        flightDTO.setTotalSeats(100);
        flightDTO.setBasePrice(1000.0);
        flightDTO.setActive(true);

        fareDetail = new FareDetail();
        fareDetail.setFlightNumber("FL123");
        fareDetail.setAirline("TestAir");
        fareDetail.setDepartureCity("CityA");
        fareDetail.setArrivalCity("CityB");
        fareDetail.setDepartureDate(LocalDate.of(2024, 6, 1));
        fareDetail.setTaxPercentage(10.0);
        fareDetail.setDiscountPercentage(5.0);
        fareDetail.setAvailabilityStatus(true);
    }

    @Test
    void testCalculateDynamicPrice() {
        double price = fareService.calculateDynamicPrice(flightDTO, fareDetail);
        assertTrue(price > 0);
        assertEquals(price, fareDetail.getFinalPrice());
    }

    @Test
    void testSearchFares() {
        List<FlightDTO> flights = Collections.singletonList(flightDTO);
        when(flightDetailsClient.searchFlights(anyString(), anyString(), anyString())).thenReturn(flights);
        when(fareDetailRepository.findByFlightNumberAndDepartureDate(anyString(), any(LocalDate.class)))
                .thenReturn(Optional.of(fareDetail));
        when(fareDetailRepository.save(any(FareDetail.class))).thenReturn(fareDetail);

        List<FareDetail> result = fareService.searchFares("CityA", "CityB", "2024-06-01");
        assertEquals(1, result.size());
        assertEquals(fareDetail, result.get(0));
    }

    @Test
    void testGetFareForFlight() {
        when(flightDetailsClient.getFlightByNumber(anyString())).thenReturn(flightDTO);
        when(fareDetailRepository.findByFlightNumberAndDepartureDate(anyString(), any(LocalDate.class)))
                .thenReturn(Optional.of(fareDetail));
        when(fareDetailRepository.save(any(FareDetail.class))).thenReturn(fareDetail);

        FareDetail result = fareService.getFareForFlight("FL123", LocalDate.of(2024, 6, 1), 2);
        assertEquals(fareDetail, result);
    }

    @Test
    void testCreateFareDetail() {
        when(fareDetailRepository.save(fareDetail)).thenReturn(fareDetail);
        FareDetail result = fareService.createFareDetail(fareDetail);
        assertEquals(fareDetail, result);
    }

    @Test
    void testUpdateFareDetail() {
        FareDetail updates = new FareDetail();
        updates.setTaxPercentage(15.0);
        updates.setDiscountPercentage(10.0);

        when(fareDetailRepository.findById("1")).thenReturn(Optional.of(fareDetail));
        when(fareDetailRepository.save(any(FareDetail.class))).thenReturn(fareDetail);

        FareDetail result = fareService.updateFareDetail("1", updates);
        assertEquals(fareDetail, result);
    }

    @Test
    void testUpdateFareDetail_NotFound() {
        when(fareDetailRepository.findById("1")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> fareService.updateFareDetail("1", fareDetail));
    }

    @Test
    void testDeleteFareDetail() {
        doNothing().when(fareDetailRepository).deleteById("1");
        assertDoesNotThrow(() -> fareService.deleteFareDetail("1"));
        verify(fareDetailRepository, times(1)).deleteById("1");
    }
}