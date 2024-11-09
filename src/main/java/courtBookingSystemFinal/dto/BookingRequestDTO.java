package courtBookingSystemFinal.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BookingRequestDTO {

    private Long userId;
    private Long courtId;
    private String startTime;
    private String endTime;


}
