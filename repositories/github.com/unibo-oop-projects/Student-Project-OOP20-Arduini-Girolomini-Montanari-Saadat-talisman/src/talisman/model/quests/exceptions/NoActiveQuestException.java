package talisman.model.quests.exceptions;

public class NoActiveQuestException extends RuntimeException {

    private static final long serialVersionUID = -7762154730712697492L;

    public NoActiveQuestException() {
        super();
    }

    public NoActiveQuestException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoActiveQuestException(String message) {
        super(message);
    }

    public NoActiveQuestException(Throwable cause) {
        super(cause);
    }
}

