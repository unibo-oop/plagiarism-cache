package model.gamerules;

import javafx.util.Pair;
import model.objectives.LastStandingObjective;
import model.objectives.Objective;

/**
 * A game mode in which each player starts with much more resources than normal.
 */
public class BigStartGameMode implements GameRules {

    private static final String DESCRIPTION = "Big Start";
    private static final String LONG_DESCRIPTION = "Last Alive but each Player starts with much more Resources";
    private static final BigStartGameMode SINGLETON = new BigStartGameMode();

    private static final int INITIAL_VALUES = 3000;

    private final Pair<Integer, Integer> mapSize = new Pair<Integer, Integer>(20, 20);

    /**
     * @return the singleton
     */
    public static BigStartGameMode getBigStartGameMode() {
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
