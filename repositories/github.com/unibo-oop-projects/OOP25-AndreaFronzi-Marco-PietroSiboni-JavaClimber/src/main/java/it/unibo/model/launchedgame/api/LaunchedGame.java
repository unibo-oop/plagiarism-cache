package it.unibo.model.launchedgame.api;

import java.util.Optional;

import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.world.impl.World;

/**
 * Interface representing a running game session.
 * It manages the lifecycle and states (running, paused, ended) of an active game.
 */
public interface LaunchedGame extends CommandState<RunningCommand> {

    /**
     * Sets the current state of the launched game.
     * 
     * @param state the new state to set
     */
    void setState(StateOfLaunchedGame state);

    /**
     * Gets the current state of the launched game.
     * 
     * @return the current state
     */
    StateOfLaunchedGame getState();

    /**
     * Gets the current world of the launched game.
     * 
     * @return an Optional containing the current world if available, or an empty Optional if no world is set
     */
    Optional<World> getWorld();

    /**
     * Sets the world for the launched game.
     * 
     * @param world the world to set
     */
    void setWorld(World world);

    /**
     * Checks if the game is currently running.
     * 
     * @return true if the game is running, false otherwise
     */
    boolean isRunning();

    /**
     * Sets the running state of the game.
     * 
     * @param running true to set the game as running, false to set it as not running
     */
    void setRunning(boolean running);

    /**
     * Gets the menu associated with the launched game.
     * 
     * @return the menu of the launched game
     */
    Menu getMenu();
}
