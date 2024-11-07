package CourtBookingSystem.service;

import CourtBookingSystem.model.Booking;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    Booking newBookingRequest(Long userId, Long courtId, LocalDateTime bookingTime);

    List<Booking> getAllBookings();

    void confirmBooking(Long bookingId, Boolean confirmed);

    boolean isCourtOccupied(Long courtId, LocalDateTime bookingDateTime);

    void verifyCourtExistence(Long courtId);

    void verifyBookingTime(LocalDateTime bookingDateTime);

    void verifyUserExistence(Long userId);

}