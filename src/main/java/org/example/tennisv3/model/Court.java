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

    @ManyToOne
    @JoinColumn(name = "surface_id")
    private Surface surface;

    private boolean isIndoor;

    public Court(Surface surface, boolean isIndoor) {
        this.surface = surface;
        this.isIndoor = isIndoor;
    }


}
