package talisman.model.character.exceptions;

public class NotEnoughSpaceInventoryException extends RuntimeException {

    private static final long serialVersionUID = -7762154730712697492L;

    public NotEnoughSpaceInventoryException() {
        super();
    }

    public NotEnoughSpaceInventoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughSpaceInventoryException(String message) {
        super(message);
    }

    public NotEnoughSpaceInventoryException(Throwable cause) {
        super(cause);
    }
}
