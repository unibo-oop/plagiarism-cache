package starcatraz.model.game;

/**
 * Exception thrown when a choise of an unhandled type is provided as a method argument.
 */
public class UnhandledChoiceException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    private final String message;

    /**
     * Constructor for UnhandledChoiceException.
     * @param choice
     */
    public UnhandledChoiceException(final RobotAttackChoice choice) {
        super();
        this.message = "The robot attack choice " + choice.toString() + " is not handled";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
