package courtBookingSystemFinal.dto;

import lombok.Data;


@Data
public class BookingConfirmationDTO {

    private Long bookingId;
    private Boolean confirmed;

}
