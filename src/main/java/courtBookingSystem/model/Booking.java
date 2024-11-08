package courtBookingSystem.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "court_id",nullable = false)
    private Court court;

    @Column(nullable = false)
    private LocalDateTime startTime;
    private LocalDateTime endDateTime;

    @Column(nullable = false)
    private Boolean confirmed = false;

//    @Transient // Used with intention to hide sensitive data from the table - and also I was curious how it works - later on realised
//    that my getAllBookings method does not show price, which I later fixed with getPrice method. Later on i decided to use same approach
//    in entire project, but learned how to hide sensitive data from requests, which is useful to know.
    @Column(nullable = false)
    private double price;

//    Calculation for calculating price, court allows bookings in 30 minute intervals
//    1 hour = 4 Euro, 30 minutes = 2 Euro

    public double calculatePrice() {

        Duration duration = Duration.between(startTime, endDateTime);
        double totalMinutes = duration.toMinutes();

        if (totalMinutes <= 30) {
            return 2.0;
        } else if (totalMinutes <= 60) {
            return 4.0;
        } else {
            double additionalHours = Math.ceil((totalMinutes - 60) / 30.0);
            return 4.0 + (additionalHours * 2.0);
        }
    }

// This method is not used, it was used for with @Transient annotation.

//    public double getPrice() {
//        return calculatePrice();
//    }

}
