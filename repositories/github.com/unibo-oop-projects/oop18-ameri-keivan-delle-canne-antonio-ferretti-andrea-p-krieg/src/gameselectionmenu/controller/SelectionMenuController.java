package gameselectionmenu.controller;

import java.util.List;

/**
 * The SelectionMenuController interface represents the controller part of the
 * players selection menu. This interface aims to make the view part interact
 * with the model part. He will also take care of starting the game.
 */
public interface SelectionMenuController {

    /**
     * The method takes care of warning the view of drawing the on-screen menu.
     */
    void showMenu();

    /**
     * The method takes care of starting the game with the selected players. The
     * method passes the height and width to be taken by the game window.
     * 
     * @param height is the height of the game window.
     * @param width  is the width of the game window.
     * @throws IllegalStateException the method throws the exception if the game can't be start.
     */
    void startGame(double height, double width);

    /**
     * Method that sets the current number of selected players.
     * 
     * @param currentPlayers is the number of players to set.
     */
    void setCurrentPlayers(int currentPlayers);

    /**
     * @return the number of players currently selected by the user.
     */
    int getCurrentPlayers();

    /**
     * @return the minimum number of players that could be set.
     */
    int getMinimumPlayers();

    /**
     * @return the maximum number of players that could be set.
     */
    int getMaximumPlayers();

    /**
     * @return the list with all the races available within the game.
     */
    List<String> getRaceNameList();

    /**
     * Method that checks if the player can be added, and if it can then adds it. It
     * verify if name and raceName aren't null or empty. The player will not be
     * added if he has a race and / or a name already selected by another player.
     * 
     * @param id       is the player id.
     * @param name     is the name of the player to add.
     * @param raceName is the race of the player to add.
     * @return true if the player has been added.
     */
    boolean verify(int id, String name, String raceName);

    /**
     * @return true if the conditions to get the game to start are all met.
     */
    boolean canStartGame();

}
