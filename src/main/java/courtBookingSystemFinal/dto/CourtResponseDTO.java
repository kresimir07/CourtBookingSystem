package courtBookingSystemFinal.dto;
import lombok.Data;
import courtBookingSystemFinal.model.Court;

@Data

public class CourtResponseDTO extends Court {

    private String message;
    private Court court;

    public CourtResponseDTO(String message, Court court) {
        this.message = message;
        this.court = court;
    }


}
