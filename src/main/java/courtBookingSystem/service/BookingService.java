package courtBookingSystem.service;

import courtBookingSystem.model.Booking;
import java.time.LocalDateTime;
import java.util.List;


public interface BookingService {

    Booking newBookingRequest(Long userId, Long courtId, LocalDateTime startTime, LocalDateTime endTime);

    List<Booking> getAllBookings();

    void confirmBooking(Long bookingId, Boolean confirmed);

    boolean isCourtOccupied(Long courtId, LocalDateTime startTime);

    void verifyCourtExistence(Long courtId);

    void verifyBookingTime(LocalDateTime startTime, LocalDateTime endTime);

    void verifyUserExistence(Long userId);

}