package todo.view.drawables.screens;

/**
 * This exception is thrown when there are no results from an XPath query.
 */
public class NoXPathResultsException extends RuntimeException {
    private static final long serialVersionUID = 1175651900846747135L;

    public NoXPathResultsException(final String message) {
        super(message);
    }
}
