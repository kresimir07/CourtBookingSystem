package courtBookingSystem.repository;

import courtBookingSystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByCourtIdAndBookingTime(Long courtId, LocalDateTime bookingTime);
}
