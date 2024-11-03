package org.example.tennisv3.model;

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

    @OneToMany(mappedBy = "surface",cascade = CascadeType.ALL)
    private Collection<Court> courts;




}
