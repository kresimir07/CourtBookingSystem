package org.example.tennisv3.controller;

import lombok.RequiredArgsConstructor;
import org.example.tennisv3.dto.BookingConfirmationDTO;
import org.example.tennisv3.dto.BookingRequestDTO;
import org.example.tennisv3.model.Booking;
import org.example.tennisv3.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {


    private final BookingService bookingService;

    @PostMapping("/create")
    public Booking bookCourt(@RequestBody BookingRequestDTO bookingRequest) {
        Long userId = bookingRequest.getUserId();
        Long courtId = bookingRequest.getCourtId();
        LocalDateTime bookingDateTime = LocalDateTime.parse(bookingRequest.getBookingTime());
        return bookingService.bookCourt(userId, courtId, bookingDateTime);
    }

    @GetMapping

    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PutMapping("/confirm")
    public void confirmBookings(@RequestBody List<BookingConfirmationDTO> requests) {
        for (BookingConfirmationDTO request : requests) {
            bookingService.confirmBooking(request.getBookingId(), request.getConfirmed());
        }
    }




}
