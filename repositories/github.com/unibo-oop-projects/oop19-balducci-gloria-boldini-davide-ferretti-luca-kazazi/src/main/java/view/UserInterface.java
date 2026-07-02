package view;

/**
 * UserInterface.
 * 
 *
 */
public interface UserInterface {

    /**
     * Creates a new GUI window.
     */
    void createWindow();

    /**
     * Set the visibility of window.
     */
    void show();

    /**
     * 
     * @param x width
     * @param y height
     * 
     * Set the size of window.
     */
    void setDimensions(int x, int y);

}
