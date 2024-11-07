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

    @Override
    @Transactional
    public Booking newBookingRequest(Long userId, Long courtId, LocalDateTime bookingTime) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
        Court court = courtRepository.findById(courtId).orElseThrow(() ->
                new CourtNotFoundException("Court not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCourt(court);
        booking.setBookingTime(bookingTime);
        if (bookingTime.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Booking cannot be made in the past!");
        }
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

    private void validateCourtAvailability(Long courtId, LocalDateTime bookingTime) {
        boolean isOccupied = bookingRepository.existsByCourtIdAndBookingTime(courtId, bookingTime);
        if (isOccupied) {
            throw new CourtOccupiedException("Court is already occupied at this time.");
        }
    }








}
