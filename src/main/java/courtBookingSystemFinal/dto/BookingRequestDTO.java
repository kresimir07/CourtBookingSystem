package courtBookingSystemFinal.dto;


import lombok.Data;



@Data
public class BookingRequestDTO {

    private Long userId;
    private Long courtId;
    private String startTime;
    private String endTime;


}
