package courtBookingSystem.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private LocalDateTime bookingDateTime;

    @Column(nullable = false)
    private Boolean confirmed = false;

}
