package model.player;

import model.objectives.Objective;
import model.races.Race;

/**
 * Models a Player for the current game.
 */
public interface Player {

    /**
     * @return the name of the player
     */
    String getName();

    /**
     * @return the unique identifier of the player
     */
    int getId();

    /**
     * @return the tribe of the player
     */
    Race getRace();

    /**
     * @return the objective of the player
     */
    Objective getObjective();

}
