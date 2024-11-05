package org.example.tennisv3.dto;
import lombok.Getter;
import lombok.Setter;
import org.example.tennisv3.model.Court;


@Getter
@Setter
public class CourtResponseDTO extends Court {

    private String message;
    private Court court;

    public CourtResponseDTO(String message, Court court) {
        this.message = message;
        this.court = court;
    }


}
