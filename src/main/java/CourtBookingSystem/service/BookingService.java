package CourtBookingSystem.service;

import CourtBookingSystem.model.Booking;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    Booking bookCourt(Long userId, Long courtId, LocalDateTime bookingTime);
    List<Booking> getAllBookings();
    void confirmBooking(Long bookingId, Boolean confirmed);

}
