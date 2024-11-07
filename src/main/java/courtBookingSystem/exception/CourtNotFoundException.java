package courtBookingSystem.exception;

public class CourtNotFoundException extends RuntimeException {
    public CourtNotFoundException(String message) {
        super(message);
    }
}