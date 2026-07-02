package model;

/**
 * Exception linked to user's error.
 *
 * @author Edoardo
 *
 */
public class UserException extends Exception {

  private static final long serialVersionUID = 5209340746100665647L;

  /**
   * Empty constructor.
   */
  public UserException() {
    super();
  }

  /**
   * Constructor with @param msg error.
   */
  public UserException(final String msg) {
    super(msg);
  }
}
