package courtBookingSystemFinal.controller;

import courtBookingSystemFinal.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import courtBookingSystemFinal.dto.BookingConfirmationDTO;
import courtBookingSystemFinal.dto.BookingRequestDTO;
import courtBookingSystemFinal.model.Booking;
import courtBookingSystemFinal.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {


    private final BookingService bookingService;


    @PostMapping("/createNew")
    public ResponseEntity<Booking> newBookingRequest(@RequestBody BookingRequestDTO bookingRequest) {
        Long userId = bookingRequest.getUserId();
        Long courtId = bookingRequest.getCourtId();
        LocalDateTime startTime = LocalDateTime.parse(bookingRequest.getStartTime());
        LocalDateTime endTime = LocalDateTime.parse(bookingRequest.getEndTime());

        if (startTime.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Booking cannot be made in the past");
        }
        if (endTime.isBefore(startTime)) {
            throw new RuntimeException("End time is before start time");
        }
        Booking booking = bookingService.newBookingRequest(userId, courtId, startTime, endTime);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PutMapping("/confirm")
    @ResponseStatus(HttpStatus.OK)
    public void confirmBookings(@RequestBody List<BookingConfirmationDTO> requests) {
        for (BookingConfirmationDTO request : requests) {
            bookingService.confirmBooking(request.getBookingId(), request.getConfirmed());
        }
    }


}