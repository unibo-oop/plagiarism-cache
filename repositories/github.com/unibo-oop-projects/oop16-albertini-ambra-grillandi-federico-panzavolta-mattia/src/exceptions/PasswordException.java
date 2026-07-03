package exceptions;

/**
 * It is the exception that is thrown when the owner's password isn't correct.
 *
 */
public class PasswordException extends Exception {

    private static final long serialVersionUID = 595877417442176005L;

    @Override
    public String getMessage() {
        return "The password is not correct. Please, try again.";
    }


}
