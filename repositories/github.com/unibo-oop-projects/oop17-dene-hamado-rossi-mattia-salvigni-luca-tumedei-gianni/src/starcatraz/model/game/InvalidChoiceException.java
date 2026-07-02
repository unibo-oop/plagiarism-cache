package starcatraz.model.game;

/**
 * Exception thrown when an invalid choice is provided as a method argument.
 */
public class InvalidChoiceException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    private final String message;

    /**
     * Constructor for InvalidChoiceException.
     * @param choice
     */
    public InvalidChoiceException(final RobotAttackChoice choice) {
        super();
        this.message = "The choice " + choice.toString() + " cannot be made in the current situation";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
