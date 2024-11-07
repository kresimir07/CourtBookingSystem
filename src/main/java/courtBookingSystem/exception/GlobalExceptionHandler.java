package courtBookingSystem.exception;
import courtBookingSystem.dto.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<UserResponseDTO> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        UserResponseDTO response = new UserResponseDTO(ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourtNotFoundException.class)
    public ResponseEntity<UserResponseDTO> handleCourtNotFoundException(CourtNotFoundException ex) {
        UserResponseDTO response = new UserResponseDTO(ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourtOccupiedException.class)
    public ResponseEntity<UserResponseDTO> handleCourtOccupiedException(CourtOccupiedException ex) {
        UserResponseDTO response = new UserResponseDTO(ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<UserResponseDTO> handleBookingNotFoundException(BookingNotFoundException ex) {
        UserResponseDTO response = new UserResponseDTO(ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
