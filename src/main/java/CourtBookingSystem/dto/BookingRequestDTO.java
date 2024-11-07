package CourtBookingSystem.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequestDTO {

    private Long userId;
    private Long courtId;
    private String bookingTime;


}
