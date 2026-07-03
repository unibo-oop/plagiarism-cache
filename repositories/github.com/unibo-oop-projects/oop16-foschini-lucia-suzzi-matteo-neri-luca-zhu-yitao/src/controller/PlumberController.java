package controller;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JPanel;

import model.pkglevels.GameWindowModelImpl;
import model.pkglevels.PlumberModel;
import model.pkgplayer.Player;
import view.GameLevel;
import view.GameWindow;

/**
 * Inteface for the PlumberController.
 * 
 * 
 *
 */
public interface PlumberController {

   

    /**
     * Method that call the model code for checking the level solution.
     *
     * 
     * @return the solution button behaviour
     */
    ActionListener modelSolutionListener();

    /**
     * Method that adds a model to this controller.
     * 
     * 
     * @param model
     *            model to add
     */
    void addModel(PlumberModel model);

    /**
     * Method that sets the current player to the parameter.
     * 
     * 
     * @param p
     *            player to set
     */
    void setPlayer(Player p);

    /**
     * Return the model which is connected to the controller.
     * 
     * 
     * @return the current model
     */
    PlumberModel getModel();

    /**
     * Method that returns the level.
     * 
     * 
     * @return get the current level
     */
    GameLevel getLevel();

    /**
     * Return the current player.
     * 
     * @return current player
     */
    Player getPlayer();

    /**
     * Return the gameWindow associated.
     * 
     * @return gamewindow
     */
    GameWindow getView();

    /**
     * Method for enabling the gameWindow.
     * 
     */
    void enableView();

    /**
     * Method that returns the gameWindowModel relative to the gameWindow.
     * 
     * @return model
     */
    GameWindowModelImpl getGameWindowModel();

    /**
     * Calls the model method used to build a level.
     * 
     * @param lines
     *            grid lines
     * @param columns
     *            grid columns
     * @param panel
     *            panel to populate
     * @param newX
     *            image width
     * @param newYimage
     *            height
     */
    void createLevel(int lines, int columns, JPanel panel, int newX, int newY);

    /**
     * Calls the model method used to show the error message.
     * 
     * @param message
     *            message to display
     * @param title
     *            window title
     */
    void getFailure(String message, String title);

    /**
     * Read solutions from file, uses the model method.
     * 
     * @param f
     *            file to read
     */
    void readSolutionsFromFile(File f);

    /**
     * Calls the model method to reset the level stats.
     * 
     */
    void initializeLevel();

    /**
     * Gets the grid lines.
     * 
     * @return lines number
     */
    int getLines();

    /**
     * Returns the columns.
     * 
     * @return grid columns
     */
    int getColumns();

}
