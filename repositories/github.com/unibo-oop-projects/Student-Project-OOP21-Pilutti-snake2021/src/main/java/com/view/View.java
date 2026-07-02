package main.java.com.view;

import javax.swing.JLabel;

/**
 * Interface that models this application's view entry point.
 * These methods are used by the controller to interact with the view.
 *
 */
public interface View extends BasicWindow {

    /**
     * Updates the view.
     */
    void updateView();

    /**
     * Enables the Pause, Reset and Quit buttons.
     */
    void enableButtons();

    /**
     * 
     * @return the instance of the MapView.
     */
    MapViewImpl getMapView();

    /**
     * 
     * @return the JLabel in which the score is written.
     */
    JLabel getScoreLabel();

    /**
     * 
     * @return the JLabel in which the highscore is written.
     */
    JLabel getHiScoreLabel();

    /**
     * Shows the game over window on screen.
     */
    void showGameOver();

}
