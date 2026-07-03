package view;

import java.util.List;

import javax.swing.JPanel;

import controller.ViewObserver;
import game.GameObject;

/**
 * The basic methods needed by the view to communicate with the controller.
 */
public interface View {
    /**
     * Starts the view.
     */
    void start();

    /**
     * Draws the view. Must be called in every cycle of the gameLoop
     * @param list all the entities currently in the game
     * @param score the current score
     * @param level the current level
     */
    void draw(List<GameObject> list, int score, int level);

    /**
     * @return the controller bound to the view
     */
    ViewObserver getObserver();

    /**
     * Switches the window to another one in the cardLayout.
     * 
     * @param window the window to be added or switched to
     * @param title the String identifying the Window
     */
    void switchWindow(JPanel window, String title);

    /**
     * Closes the game bringing the user back to the main menu.
     */
    void resetToMenu();

    /**
     * Attach the observer to the view.
     * @param observer observer to attach to this view
     */
    void attach(ViewObserver observer);
}
