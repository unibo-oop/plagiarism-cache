package controller;

import utility.Driver;
import utility.TyreType;
import view.game.InfoDriver;

/**
 * The interface of the game's controller.
 */
public interface Controller {

    /**
     * Set the controller to perform only the race.
     */
    void setOnlyRace();

    /**
     * Add a player to the game.
     * @param name the name of the player
     * @param driver the driver he/she wants to play
     */
    void addPlayer(String name, Driver driver);

    /**
     * Set the circuit chosen by the user where the race weekend will happen.
     * @param cir the circuit chosen
     */
    void setCircuit(CircuitPlayable cir);

    /**
     * Start the game.
     */
    void startWeekend();

    /**
     * Set the initial tyre that the player will use.
     * @param driver the driver
     * @param tyre the initial player's tyre
     */
    void setPlayerInitialTyre(Driver driver, TyreType tyre);

    /**
     * A method to know the number of laps completed so far during the race.
     * @return the number of laps remaining for the race
     */
    int lapsCompleted();

    /**
     * Restart the game from menu'.
     */
    void restart();

    /**
     * Get info from model.
     * @param drv driver
     * @return Info of driver
     */
    InfoDriver getInfo(Driver drv);
}
