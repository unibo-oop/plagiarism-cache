package snakerunner.graphics.panel;

/**
 * Base interface for all panels in the game.
 * Define the common contract for panel initialization.
 */
public interface BasePanel {

    /**
     * Setup the layout and component of the panel.
     */
    void setLayoutPanel();

    /**
     * Register action listeners for components,
     * user interaction with buttons.
     */
    void addActionListeners();

}
