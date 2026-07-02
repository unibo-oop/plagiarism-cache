package model.gamerules;

import javafx.util.Pair;
import model.objectives.CitiesOwnedObjective;
import model.objectives.Objective;

/**
 * Game Mode that ends with a number of cities owned by a Player.
 */
public class OwnCitiesGameMode implements GameRules {

    private static final String DESCRIPTION = "Own Cities";
    private static final String LONG_DESCRIPTION = "The first Player that can hold 8 cities simultaneously wins";
    private static final OwnCitiesGameMode SINGLETON = new OwnCitiesGameMode();

    private static final int INITIAL_VALUES = 250;

    private final Pair<Integer, Integer> mapSize = new Pair<Integer, Integer>(20, 20);

    /**
     * @return the singleton
     */
    public static OwnCitiesGameMode getOwnCitiesGameMode() {
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
        return new CitiesOwnedObjective();
    }

    /** {@inheritDoc} **/
    @Override
    public int getInitialValues() {
        return INITIAL_VALUES;
    }

    /** @{inheritDoc} **/
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
