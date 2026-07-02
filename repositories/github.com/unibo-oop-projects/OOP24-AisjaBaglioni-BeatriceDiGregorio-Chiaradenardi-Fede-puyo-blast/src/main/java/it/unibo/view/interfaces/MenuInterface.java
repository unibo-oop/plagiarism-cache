package it.unibo.view.interfaces;

import javax.swing.JButton;

/**
 * Interface for the Menu class.
 * This interface defines the contract for the Menu class in terms of
 * providing access to the UI elements like buttons and selected level.
 */
public interface MenuInterface {

    /**
     * Gets the "Start Game" button.
     * This button is used to start the game when clicked.
     * 
     * @return the start button.
     */
    JButton getStartButton();

    /**
     * Gets the "Controls" button.
     * This button is used to show the game controls when clicked.
     * 
     * @return the controls button.
     */
    JButton getControlsButton();

    /**
     * Gets the currently selected level from the level dropdown.
     * This method returns the level selected by the user from the dropdown menu.
     * 
     * @return the selected level as a string.
     */
    String getSelectedLevel();
}
