package com.fares.testController;

import com.fares.controller.FareController;
import com.fares.entitty.FareDetail;
import com.fares.service.FareService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FareControllerTest {

    @InjectMocks
    private FareController fareController;

    @Mock
    private FareService fareService;

    private FareDetail fareDetail;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fareDetail = new FareDetail();
        fareDetail.setFlightNumber("FL123");
        fareDetail.setAirline("TestAir");
        fareDetail.setDepartureCity("CityA");
        fareDetail.setArrivalCity("CityB");
        fareDetail.setDepartureDate(LocalDate.of(2024, 6, 1));
        fareDetail.setFinalPrice(1000.0);
    }

    @Test
    void testSearchFares() {
        List<FareDetail> fares = Arrays.asList(fareDetail);
        when(fareService.searchFares("CityA", "CityB", "2024-06-01")).thenReturn(fares);

        List<FareDetail> result = fareController.searchFares("CityA", "CityB", "2024-06-01");

        assertEquals(1, result.size());
        assertEquals(fareDetail, result.get(0));
    }

    @Test
    void testGetFareForFlight() {
        when(fareService.getFareForFlight("FL123", LocalDate.of(2024, 6, 1), 2)).thenReturn(fareDetail);

        FareDetail result = fareController.getFareForFlight("FL123", LocalDate.of(2024, 6, 1), 2);

        assertEquals(fareDetail, result);
    }

    @Test
    void testCreateFareDetail() {
        when(fareService.createFareDetail(fareDetail)).thenReturn(fareDetail);

        FareDetail result = fareController.createFareDetail(fareDetail);

        assertEquals(fareDetail, result);
    }

    @Test
    void testUpdateFareDetail() {
        when(fareService.updateFareDetail("1", fareDetail)).thenReturn(fareDetail);

        FareDetail result = fareController.updateFareDetail("1", fareDetail);

        assertEquals(fareDetail, result);
    }

    @Test
    void testDeleteFareDetail() {
        doNothing().when(fareService).deleteFareDetail("1");

        ResponseEntity<Void> response = fareController.deleteFareDetail("1");

        assertEquals(200, response.getStatusCodeValue());
        verify(fareService, times(1)).deleteFareDetail("1");
    }
}
