package startmenu.controller;

import java.util.List;

import model.gamerules.GameRules;

/**
 * Handles the interactions between the user's choice and the GUI response.
 */
public interface MainController {

    /**
     * Shows the first menu that is the beginning of the application.
     */
    void showMenu();

    /**
     * Calls the second menu for the choice of the players.
     * @param height of the window;
     * @param width of the window;
     */
    void callGameMenu(double height, double width);

    /**
     * @return the list of game modes
     */
    List<GameRules> getGameModes();

    /**
     * @param index the index of the game mode in the list
     */
    void setGameMode(int index);

}
