package courtBookingSystem.service.impl;

import courtBookingSystem.exception.BookingNotFoundException;
import courtBookingSystem.exception.CourtNotFoundException;
import courtBookingSystem.exception.CourtOccupiedException;
import lombok.RequiredArgsConstructor;
import courtBookingSystem.model.Booking;
import courtBookingSystem.model.Court;
import courtBookingSystem.model.User;
import courtBookingSystem.repository.BookingRepository;
import courtBookingSystem.repository.CourtRepository;
import courtBookingSystem.repository.UserRepository;
import courtBookingSystem.service.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;



@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final CourtRepository courtRepository;

    public Booking newBookingRequest(Long userId, Long courtId, LocalDateTime startTime, LocalDateTime endTime) {
        verifyBookingTime(startTime, endTime);
        verifyCourtExistence(courtId);
        verifyUserExistence(userId);

        if (isCourtOccupied(courtId, startTime)) {
            throw new CourtOccupiedException("Court is already occupied at the requested time");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BookingNotFoundException("User ID not found"));
        Court court = courtRepository.findById(courtId)
                .orElseThrow(() -> new CourtNotFoundException("Court ID not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCourt(court);
        booking.setStartTime(startTime);
        booking.setEndDateTime(endTime);
        double price = booking.calculatePrice();
        booking.setPrice(price);
        return bookingRepository.save(booking);

    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    @Transactional
    public void confirmBooking(Long bookingId, Boolean confirmed) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() ->
                new BookingNotFoundException("Booking not found"));
        booking.setConfirmed(confirmed);
        bookingRepository.save(booking);

    }

    @Override
    public boolean isCourtOccupied(Long courtId, LocalDateTime startTime) {
        return bookingRepository.existsByCourtIdAndStartTime(courtId, startTime);

    }

    @Override
    public void verifyCourtExistence(Long courtId) {
        if (!courtRepository.existsById(courtId)) {
            throw new CourtNotFoundException("Court ID not found");
        }
    }

    @Override
    public void verifyBookingTime(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null || !startTime.isBefore(endTime)) {
            throw new BookingNotFoundException("Invalid booking time");
        }
    }

    @Override
    public void verifyUserExistence(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new BookingNotFoundException("User ID not found");
        }
    }

}
