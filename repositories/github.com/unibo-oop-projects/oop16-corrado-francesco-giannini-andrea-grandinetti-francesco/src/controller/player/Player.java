package controller.player;

import java.util.List;

import utility.Driver;
import utility.TyreType;

/**
 * Interface that represents AI and user.
 */
public interface Player {

    /**
     * Get the player's driver.
     * @return the driver that the player has 
     */
    Driver getDriver();

    /**
     * Tells the player to enter in the box.
     */
    void goToBox();

    /**
     * Tells the player to exit from the box.
     */
    void exitBox();

    /**
     * Know if the player is boxing or not.
     * @return the answer
     */
    boolean isInBox();

    /**
     * Tells the player to retire from the session.
     */
    void retire();

    /**
     * Know if the player is retired.
     * @return the answer
     */
    boolean isRetired();

    /**
     * Add a new stint.
     * @param tyre the tyre the player has mounted on its car
     */
    void addStint(TyreType tyre);

    /**
     * Get all the stints that the player did.
     * @return a list of TyreType with all the stints
     */
    List<TyreType> getPlayerStints();
}
