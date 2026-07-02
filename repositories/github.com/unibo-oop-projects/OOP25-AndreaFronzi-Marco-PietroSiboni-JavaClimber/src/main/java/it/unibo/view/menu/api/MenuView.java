package it.unibo.view.menu.api;

/**
 * Interface for the menu view.
 */
public interface MenuView {

    /**
     * Update the displayed high score in the menu view.
     * 
     * @param score the current high score to be displayed
     */
    void updateHighScore(int score);

    /**
     * Display the menu view.
     */
    void start();

    /**
     * Open the shop view.
     */
    void shop();

    /**
     * Open the inventory view.
     */
    void inventory();

    /**
     * Exit the application.
     */
    void exit();

}
