package CourtBookingSystem.exception;

public class CourtOccupiedException extends RuntimeException {
    public CourtOccupiedException(String message) {
        super(message);
    }
}