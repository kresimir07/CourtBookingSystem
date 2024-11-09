package courtBookingSystemFinal.repository;

import courtBookingSystemFinal.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByCourtIdAndStartTime(Long courtId, LocalDateTime startTime);


}
