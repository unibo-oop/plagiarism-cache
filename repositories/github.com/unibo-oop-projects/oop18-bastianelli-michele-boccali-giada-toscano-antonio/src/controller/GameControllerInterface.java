package controller;

import java.util.Optional;

import com.google.common.eventbus.Subscribe;

import common.events.EscEvent;
import controller.menu.Controller;
import enumerators.Level;
import enumerators.PlayerCharacter;
import javafx.scene.Scene;
import model.GameModel;
import view.GameView;

public interface GameControllerInterface extends Controller {

    /**
     * Initiates the controller only if the level is set.
     * 
     * @param playerCharacter : current player Character
     * @param optionalLevel   : selected level
     */
    void initNewGame(PlayerCharacter playerCharacter, Optional<Level> optionalLevel);

    /**
     * Resumes (or starts) the game when paused and listens for input.
     */
    void resumeGame();

    /**
     * Pauses the game and stops listening for input.
     */
    void pauseGame();

    void stopGame();

    /**
     * Does a game tick, updates model, platform generator, and manages events.
     */
    void tick();

    /**
     * Returns the current step.
     * 
     * @return current step.
     */
    int getStep();

    /**
     * Returns the game model, that is all the entities and the world.
     * 
     * @return game model
     */
    GameModel getGameModel();
    
    /**
     * Returns the game view.
     * @return game view
     */
    GameView getGameView();

    /**
     * Returns the actual level.
     * 
     * @return the actual level
     */
    Level getLevel();

    /**
     * Sets the level.
     * 
     * @param level : level to be setted
     */
    void setLevel(Level level);

    /**
     * Returns the game scene.
     * 
     * @return the game scene
     */
    Scene getScene();

    /**
     * Method to handle an esc event.
     * 
     * @param event : the event to handle
     */
    @Subscribe
    void handleKeyEvent(EscEvent event);

}