package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.book.dto.BookingRequestDTO;
import com.book.dto.BookingResponseDTO;
import com.book.dto.PassengerDTO;
import com.book.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/bookings")
@Tag(name = "Booking API", description = "Endpoints for managing flight bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(summary = "Create a new booking")
    @PostMapping("/create")
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
        BookingResponseDTO response = bookingService.createBooking(bookingRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get booking by PNR")
    @GetMapping("/{pnr}")
    public ResponseEntity<BookingResponseDTO> getBookingByPnr(
            @Parameter(description = "PNR of the booking") @PathVariable String pnr) {
        BookingResponseDTO response = bookingService.getBookingByPnr(pnr);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get all bookings for a flight")
    @GetMapping("/flight/{flightNumber}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByFlightNumber(
            @Parameter(description = "Flight number") @PathVariable String flightNumber) {
        List<BookingResponseDTO> response = bookingService.getBookingsByFlightNumber(flightNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Update an existing booking")
    @PutMapping("/{pnr}")
    public ResponseEntity<BookingResponseDTO> updateBooking(
            @Parameter(description = "PNR of the booking") @PathVariable String pnr,
            @RequestBody BookingRequestDTO bookingRequestDTO) {
        BookingResponseDTO response = bookingService.updateBooking(pnr, bookingRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Cancel a booking")
    @DeleteMapping("/{pnr}")
    public ResponseEntity<Void> cancelBooking(
            @Parameter(description = "PNR of the booking") @PathVariable String pnr) {
        bookingService.cancelBooking(pnr);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get passengers for a specific booking")
    @GetMapping("/{pnr}/passengers")
    public ResponseEntity<List<PassengerDTO>> getPassengersForBooking(
            @Parameter(description = "PNR of the booking") @PathVariable String pnr) {
        List<PassengerDTO> response = bookingService.getPassengersForBooking(pnr);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Perform check-in for passengers")
    @PostMapping("/{pnr}/checkin")
    public ResponseEntity<BookingResponseDTO> passengerCheckin(
            @Parameter(description = "PNR of the booking") @PathVariable String pnr,
            @RequestBody List<Long> passengerIds) {
        BookingResponseDTO response = bookingService.passengerCheckin(pnr, passengerIds);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}