package CourtBookingSystem.model;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToOne
    @JoinColumn(name = "surface_id")
    private Surface surface;
    private boolean isIndoor;


    public Court(String name, Surface surface, boolean isIndoor) {
        this.name = name;
        this.surface = surface;
        this.isIndoor = isIndoor;
    }

}
