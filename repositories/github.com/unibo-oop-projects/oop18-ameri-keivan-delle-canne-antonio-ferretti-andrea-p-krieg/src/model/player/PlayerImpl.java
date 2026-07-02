package model.player;

import model.objectives.Objective;
import model.races.Race;

/**
 * The player for the current game.
 */
public class PlayerImpl implements Player {

    private final String name;
    private final int id;
    private final Race race;
    private final Objective objective;

    /**
     * Initialize the player with its informations.
     * 
     * @param name      the name of the player
     * 
     * @param id        the unique identifier of the player
     * 
     * @param race      the race of the player
     * 
     * @param objective the objective of the player
     * 
     */
    public PlayerImpl(final String name, final int id, final Race race, final Objective objective) {
        this.name = name;
        this.id = id;
        this.race = race;
        this.objective = objective;
    }

    /** {@inheritDoc} **/
    @Override
    public String getName() {
        return this.name;
    }

    /** {@inheritDoc} **/
    @Override
    public int getId() {
        return this.id;
    }

    /** {@inheritDoc} **/
    @Override
    public Race getRace() {
        return this.race;
    }

    /** {@inheritDoc} **/
    @Override
    public Objective getObjective() {
        return this.objective;
    }

    /**
     * @return the description of the Player
     */
    public String toString() {
        return "Player " + this.id + "\nName: " + this.name + ", Race: " + this.race.getRaceName() + ", Resources:\n";
    }

    /**
     * @param other the player to be compared.
     * 
     * @return whether this player is the same of the one passed
     */
    public boolean equals(final Player other) {
        return this.id == other.getId();
    }

}
