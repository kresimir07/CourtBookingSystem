package org.example.tennisv3.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.tennisv3.model.Booking;
import org.example.tennisv3.model.Court;
import org.example.tennisv3.model.User;
import org.example.tennisv3.repository.BookingRepository;
import org.example.tennisv3.repository.CourtRepository;
import org.example.tennisv3.repository.UserRepository;
import org.example.tennisv3.service.BookingService;
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
    public Booking bookCourt(Long userId, Long courtId, LocalDateTime bookingTime) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found"));
        Court court = courtRepository.findById(courtId).orElseThrow(() ->
                new RuntimeException("Court not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCourt(court);
        booking.setBookingTime(bookingTime);
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
                new RuntimeException("Booking not found"));
        booking.setConfirmed(confirmed);
        bookingRepository.save(booking);
    }






}
