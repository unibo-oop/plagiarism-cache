package model;

/**
 * Exception linked to item's problem.
 *
 * @author Edoardo
 *
 */
public class ItemException extends Exception {

  private static final long serialVersionUID = 3593556768061468120L;

  /**
   * Empty constructor.
   */
  public ItemException() {
    super();
  }

  /**
   * Constructor with @param msg error.
   */
  public ItemException(final String msg) {
    super(msg);
  }

}
