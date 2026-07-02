package it.unibo.controller.interfaces;

import it.unibo.controller.LevelManager;

/**
 * Interface for the MenuManager class.
 * This interface defines the contract for managing transitions between different views in the menu,
 * and starting the game with the appropriate level configuration.
 */
public interface MenuManagerInterface {

    /**
     * Starts the game.
     * This method is responsible for initiating the game process, including starting
     * the gameplay with the selected level and configuration.
     */
    void start();

    /**
     * Switches to the menu view.
     * This method handles the transition from any other view back to the main menu.
     */
    void switchToMenuView();

    /**
     * Switches to the rules view.
     * This method handles the transition from the main menu or gameplay to the rules/instructions view.
     */
    void switchToRulesView();

    /**
     * Shows a popup for selecting a level.
     * This method displays a popup with the configuration of the selected level.
     * 
     * @param level the name of the selected level.
     * @param config the configuration of the selected level.
     */
    void showLevelPopup(String level, LevelManager.LevelConfig config);
}
