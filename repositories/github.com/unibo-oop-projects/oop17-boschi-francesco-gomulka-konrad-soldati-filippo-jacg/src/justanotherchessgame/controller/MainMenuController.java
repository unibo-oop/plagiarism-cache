package justanotherchessgame.controller;

import java.io.File;

import javafx.scene.layout.Pane;

/**
 * This interface is interposed between user and view, and it's able to manage all the interactions between user and view.
 */
public interface MainMenuController {

    /**
     * This function is interposed between user and view, and its used to create the main menu view. 
     * @return the graphic content of the main menu.
     */
     Pane createMainMenuView();

    /**
     * This function is used to create a new game with two local players.
     */
     void createNewGame();

    /**
     * This function is used to create a new game with one local player and an artificial intelligence.
     */
     void createNewAIGame();

    /**
     * This function is used to load a game.
     * @param file is the loading file.
     */
     void loadGame(File file);

    /**
     * Function used to resize the main menu view.
     */
     void resize();
}
