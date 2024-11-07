package CourtBookingSystem.service.impl;

import CourtBookingSystem.exception.BookingNotFoundException;
import CourtBookingSystem.exception.CourtNotFoundException;
import CourtBookingSystem.exception.CourtOccupiedException;
import CourtBookingSystem.exception.UsernameNotFoundException;
import lombok.RequiredArgsConstructor;
import CourtBookingSystem.model.Booking;
import CourtBookingSystem.model.Court;
import CourtBookingSystem.model.User;
import CourtBookingSystem.repository.BookingRepository;
import CourtBookingSystem.repository.CourtRepository;
import CourtBookingSystem.repository.UserRepository;
import CourtBookingSystem.service.BookingService;
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

    public Booking newBookingRequest(Long userId, Long courtId, LocalDateTime bookingDateTime) {
        verifyBookingTime(bookingDateTime);
        verifyCourtExistence(courtId);
        verifyUserExistence(userId);

        if (isCourtOccupied(courtId, bookingDateTime)) {
            throw new CourtOccupiedException("Court is already occupied at the requested time");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BookingNotFoundException("User ID not found"));

        Court court = courtRepository.findById(courtId)
                .orElseThrow(() -> new CourtNotFoundException("Court ID not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCourt(court);
        booking.setBookingTime(bookingDateTime);
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
    public boolean isCourtOccupied(Long courtId, LocalDateTime bookingDateTime) {
        return bookingRepository.existsByCourtIdAndBookingTime(courtId, bookingDateTime);
    }

    @Override
    public void verifyCourtExistence(Long courtId) {
        if (!courtRepository.existsById(courtId)) {
            throw new CourtNotFoundException("Court ID not found");
        }
    }

    @Override
    public void verifyBookingTime(LocalDateTime bookingDateTime) {
        if (bookingDateTime == null) {
            throw new BookingNotFoundException("Booking time not found");
        }
    }

    @Override
    public void verifyUserExistence(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new BookingNotFoundException("User ID not found");
        }
    }

}
