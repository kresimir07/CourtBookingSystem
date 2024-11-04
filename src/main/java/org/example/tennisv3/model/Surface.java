package org.example.tennisv3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
