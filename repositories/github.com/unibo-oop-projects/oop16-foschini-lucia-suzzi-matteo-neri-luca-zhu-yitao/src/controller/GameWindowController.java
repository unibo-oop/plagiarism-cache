package controller;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.pkglevels.GameWindowModelImpl;
import view.GameWindow;

/**
 * Basic interface for the GameWindowController.
 * 
 *
 */
public interface GameWindowController {
    /**
     * Method that adds model&view to the controller.
     * 
     * 
     * @param m
     *            gameWindow model
     * @param g
     *            view
     */
    void addItems(GameWindowModelImpl m, GameWindow g);

    /**
     * Method that returns the current view.
     * 
     * 
     * @return the gameWindow view
     */
    GameWindow getView();

    /**
     * Method that obtains the button behavior.
     * 
     * 
     * @return the action listener of the button
     */
    ActionListener customLevelListener();

    /**
     * This method use the model method to build the menu layout.
     * 
     * @param inputPanel
     *            panel that contains the buttons
     * @param inputMap
     *            map of buttons and values
     * @param inputLevelList
     *            buttonList
     * @param inputFile
     *            file from which the program reads the levels
     * @param isCustom
     *            boolean that specify if is creating the customLevel menu
     */
    void createMenu(JPanel inputPanel, Map<JButton, Integer> inputMap, List<Integer> inputLevelList, File inputFile,
            boolean isCustom);

    /**
     * Method that enables the current view.
     * 
     */
    void enableWindow();

    /**
     * Method that returns the current model.
     * 
     * 
     * @return GameWindow model instance
     */
    GameWindowModelImpl getModel();

    /**
     * Used to close the view.
     * 
     */
    void closeWindow();

}
