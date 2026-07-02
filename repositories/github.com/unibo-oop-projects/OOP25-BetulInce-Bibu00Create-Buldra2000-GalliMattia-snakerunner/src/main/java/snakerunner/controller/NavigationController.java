package snakerunner.controller;

import snakerunner.graphics.MainFrame;

/**
 * Coordinate interaction between views and action.
 */
public interface NavigationController {
    /**
     * Initializes the application and set up view state (Controller - View).
     */
    void init();

    /**
     * Transition from current view to OptionPanel (Controller - View).
     */
    void onOption();

    /**
     * Transition from current view to MenuPanel (Controller - View).
     */
    void onBackMenu();

    /**
     * Starts a new game session and transitions to the game view.
     */
    void startGame();

    /**
     * Navigates to the Tutorial panel.
     */
    void onTutorial();

    /**
     * Terminate the application.
     */
    void exit();

    /**
     * Return main application frame.
     * 
     * @return return the mainFrame.
     */
    MainFrame getView();

}
