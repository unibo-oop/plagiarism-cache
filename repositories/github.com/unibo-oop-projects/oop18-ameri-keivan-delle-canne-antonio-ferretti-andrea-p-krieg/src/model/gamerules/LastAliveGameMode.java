package model.gamerules;

import javafx.util.Pair;
import model.objectives.LastStandingObjective;
import model.objectives.Objective;

/**
 * The default game mode that is: each player starts from his own capital with
 * one soldier and wins if he's the only player left on the map.
 */
public class LastAliveGameMode implements GameRules {

    private static final String DESCRIPTION = "Last Alive";
    private static final String LONG_DESCRIPTION = "The last Player surviving wins the game";
    private static final LastAliveGameMode SINGLETON = new LastAliveGameMode();

    private static final int INITIAL_VALUES = 250;

    private final Pair<Integer, Integer> mapSize = new Pair<Integer, Integer>(20, 20);

    /**
     * @return the singleton
     */
    public static LastAliveGameMode getLastAliveGameMode() {
        return SINGLETON;
    }

    /** {@inheritDoc} **/
    @Override
    public Pair<Integer, Integer> getMapSize() {
        return this.mapSize;
    }

    /** {@inheritDoc} **/
    @Override
    public Objective generateObjective() {
        return new LastStandingObjective();
    }

    /** {@inheritDoc} **/
    @Override
    public int getInitialValues() {
        return INITIAL_VALUES;
    }

    /** @inheritDoc} **/
    @Override
    public String getDescription() {
        return DESCRIPTION + "\n" + LONG_DESCRIPTION;
    }

    /** @{inheritDoc} **/
    @Override
    public String toString() {
        return this.getDescription();
    }

}
