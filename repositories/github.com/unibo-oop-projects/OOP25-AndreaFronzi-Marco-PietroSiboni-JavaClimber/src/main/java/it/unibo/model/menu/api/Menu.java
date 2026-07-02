package it.unibo.model.menu.api;

import java.util.Optional;

import it.unibo.controller.app.api.MainController;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.shop.api.ShopManager;

/**
 * Interface representing the menu context in the State Pattern.
 * It manages the current state of the menu and allows transitions between different states.
 */
public interface Menu {

    /**
     * Updates the current state of the menu.
     * 
     * @param state the new state to be set
     */
    void setState(StateOfMenu state);

    /**
     * Retrieves the current state of the menu.
     * 
     * @return the currently active StateOfMenu
     */
    StateOfMenu getState();

    /**
     * Retrieves the currently launched game.
     * 
     * @return an Optional containing the LaunchedGame if it exists, or an empty Optional if no game is launched
     */
    Optional<LaunchedGame> getLaunchedGame();

    /**
     * Sets the currently launched game.
     * 
     * @param launchedGame the LaunchedGame to be set as the current game
     */
    void setLaunchedGame(LaunchedGame launchedGame);

    /**
     * Retrieves the main controller associated with the menu.
     * 
     * @return the MainController instance
     */
    MainController getMainController();

    /**
     * Retrieves the inventory associated with the menu.
     * 
     * @return the Inventory instance
     */
    Inventory getInventory();

    /**
     * Retrieves the shop manager associated with the menu.
     * 
     * @return the ShopManager instance
     */
    ShopManager getShopManager();

    /**
     * Retrieves the score manager associated with the menu.
     * 
     * @return the ScoreManager instance
     */
    ScoreManager getScoreManager();
}
