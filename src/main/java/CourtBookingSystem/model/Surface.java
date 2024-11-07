package CourtBookingSystem.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.Collection;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Surface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;



    public Surface(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "surface")
    @JsonIgnore
//    Had to add @Jsonignore here because my Postman was giving me infinite loop, and nested results.
//    This one even though is simple solution gave me a lot of headache
    private Collection<Court> courts;





}
