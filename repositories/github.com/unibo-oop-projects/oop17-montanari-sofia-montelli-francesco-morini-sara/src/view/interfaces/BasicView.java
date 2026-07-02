package view.interfaces;

/**
 * This interfaces defines some basic element of a view.
 */
public interface BasicView {

    /**
     * Initialises the UI.
     */
    void initialize();

    /**
     * resets the UI.
     */
    void reset();

    /**
     * Updates the content of the UI.
     */
    void update();
}
