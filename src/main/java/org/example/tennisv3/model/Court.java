package org.example.tennisv3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
