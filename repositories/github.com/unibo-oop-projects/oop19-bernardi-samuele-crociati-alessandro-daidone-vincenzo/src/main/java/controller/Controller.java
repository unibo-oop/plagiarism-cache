package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import uicontrollers.BattleController;
import uicontrollers.HeroPickerController;
import view.View;

/**
 * Interface for a generic Controller.
 */
public interface Controller {

    /**
     * Creates the model by reading the arena and hero pool settings.
     */
    void loadModel();

    /**
     * Method for reading all the Maps names from the folder "src/resources/maps/".
     */
    void loadMapNames();

    /**
     * Method for reading all the Heroes files from the folder
     * "src/resources/heroes". And getting the Heroes Stats on the Hero pool by
     * reading the .txt files of all the Heroes. Then Loading the Image for all the
     * Heroes.
     */
    void loadHeroImages();

    /**
     * Sets the team size in model.
     * 
     * @param teamSize
     *            the size provided
     */
    void setTeamSize(int teamSize);

    /**
     * Finalizes the arena map selection and generates it in model.
     * 
     * @param name
     *            the name of the arena map chosen
     */
    void chooseArenaMap(String name) throws FileNotFoundException, IOException;

    /**
     * Starts the game loop.
     * 
     */
    void startGameLoop();

    /**
     * Sets the Controller "view".
     *
     * @param v
     *            The provided ViewInterface
     */
    void setView(View v);

    /**
     * Getter for the Controller "view".
     * 
     * @return the view
     */
    View getView();

    /**
     * Method for creating the Map from the name of the file.txt where the map is
     * saved.
     *
     * @param row
     * @param column
     * @param columnCount
     * @param hpc
     */
    void changePickerPosition(int row, int column, int columnCount, HeroPickerController hpc);

    /**
     *
     * Method for Updating the status oh the HeroPool.
     *
     * @param hpc
     */
    void updatePool(HeroPickerController hpc);

    /**
     * Method that organize the Pick of the Heroes for the 2 Teams.
     *
     * @param hpc
     * @param identifier
     */
    void pickerSelectionAction(HeroPickerController hpc, int identifier);

    /**
     * Method for sending the Stats of the picked Hero.
     *
     * @param hpc
     */
    void pickerSelectionInfo(HeroPickerController hpc);

    /**
     * Method for Loading The Game Battle Scene.
     */
    void loadBattleScene();

    /**
     * Method for Loading the Map and the Heroes that are currently on the Game.
     *
     * @param bc
     */
    void loadBattleMap(BattleController bc);

    /**
     * Method that gives info about The Stats of the enemy Hero selected.
     *
     * @param bc
     */
    void battleSelectionInfo(BattleController bc);

    /**
     * Method that gives info about The Hero currently used.
     *
     * @param bc
     */
    void battleCursorInfo(BattleController bc);

    /**
     * Method For changing the Cursor Position.
     *
     * @param row
     * @param column
     * @param bc
     */
    void changeGameCursorPosition(int row, int column, BattleController bc);

    /**
     * Method for executing moving or attack actions during the Game.
     *
     * @param bc
     * @param string
     */
    void gameSelectionAction(BattleController bc, String string);

    /**
     * Method for changing the turn.
     *
     * @param bc
     */
    void changeGameTurn(BattleController bc);

}
