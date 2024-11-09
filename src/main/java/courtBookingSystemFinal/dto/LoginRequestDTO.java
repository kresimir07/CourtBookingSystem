package courtBookingSystemFinal.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    private String username;
    private String password;

}