package org.example.tennisv3.dto;

import lombok.Data;


@Data
public class BookingConfirmationDTO {

    private Long bookingId;
    private Boolean confirmed;

}
