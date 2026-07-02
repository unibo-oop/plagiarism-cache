package bzzbomber.controller;

import java.util.List;

import bzzbomber.model.Level;
import bzzbomber.model.Model;
import bzzbomber.model.Timer;
import bzzbomber.model.entities.BzzBomber;
import bzzbomber.model.entities.Entity;
import bzzbomber.view.menu.ViewManager;

/**
 * This interface declared a controller's method .
 */
public interface ControllerInterface {

    /**
     * Method to start the game.
     */
    void startPlayingGame();

    /**
     * Method to start from the @MainMenuView .
     */
    void startFromMainView();

    /**
     * Update frame and object in game 60 time per second.
     */
    void updateFrame();

    /**
     * Method that update every second these objects.
     */
    void secPassed();

    /**
     * Setter for model.
     * 
     * @param model
     *            The model.
     */
    void setModel(Model model);

    /**
     * Setter for view.
     * 
     * @param view
     *            The view.
     */
    void setView(ViewManager view);

    /**
     * @return current level
     */
    Level getCurrentLevel();

    /**
     * Getter for the number of level.
     * 
     * @return the number of level
     */
    int getLevelInt();

    /**
     * @return defensive copy of entities
     */
    List<Entity> getEntities();

    /**
     * Getter for Bomberman.
     * 
     * @return a defensive copy of bomber
     */
    BzzBomber getBomber();

    /**
     * Setter the game in pause.
     * 
     * @param gamePause
     *            The boolean state of pause
     */
    void setGamePause(boolean gamePause);

    /**
     * Setter of game lose.
     * 
     * @param gameLose
     *            The boolean state of loose
     */
    void setGameLose(boolean gameLose);

    /**
     * Setter of game relaunch.
     * 
     * @param gameRelaunch
     *            The boolean state of relaunch
     */
    void setGameRelaunch(boolean gameRelaunch);

    /**
     * Setter for game win.
     * 
     * @param gameWin
     *            The boolean state of win
     */
    void setGameWin(boolean gameWin);

    /**
     * the level was change.
     */
    void levelChanged();

    /**
     * Getter for the timer.
     * 
     * @return the timer
     */
    Timer getTimer();

    /**
     * Getter for the game state.
     * 
     * @return the game state
     */
    GameState getGameState();

    /**
     * Change the number of life in HUD panel.
     */
    void changeLife();

    /**
     * Change the value of monster in HUD panel.
     */
    void changeEnemy();

    /**
     * Getter for the score that calculate in a match.
     * 
     * @return the score
     */
    int getScore();

    /**
     * Getter for the @ViewManager .
     * 
     * @return the view manager
     */
    ViewManager getViewManager();

    /**
     * Method that set the door visible.
     */
    void setDoorVisible();

    /**
     * Method to show winner menu.
     */
    void heroWin();

    /**
     * Method to show statistic.
     */
    void showStatistic();
}
