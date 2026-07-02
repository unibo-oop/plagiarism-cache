package it.unibo.view.window;

/**
 * The GameWindow interface represents a window for a game application.
 * It defines methods for initializing window properties and the game panel.
 */
public interface GameWindow {

    /**
     * Enumeration representing different types of panels in a game or application.
     * Possible values include GAME, MENU, and SCOREBOARD.
     */
    enum PanelType { GAME, MENU, SCOREBOARD }

    /**
     * Paints the content of the game window.
     * This method is responsible for updating and rendering the content of the game window.
     * It should be called whenever the content needs to be refreshed or repainted.
     * @param centiSeconds Time in hundredths of a second.
     * @param currentDeaths number of deaths in the current session.
     */
    void paint(int centiSeconds, int currentDeaths);

    /**
     * This method is responsible for showing a graphical message or UI elements
     * indicating the player's victory.
     */
    void displayVictoryMessage();

    /**
     * This method set visible the current panel.
     */
    void setPanelVisible();

    /**
     * This method is responsible for switching between different panels within the game window.
     * 
     * @param type The type of panel to set.
     */
    void switchPanel(PanelType type);
}
