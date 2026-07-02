package casim.model.abstraction.utils.stats;

import java.util.Collections;
import java.util.Map;

/**
 * A {@link Stats} implementation.
 * 
 *  @param <T> the type of the finite states of the Automaton's Cell.
 */
public class StatsImpl<T> implements Stats<T> {

    private static final String NEWLINE = System.lineSeparator();

    private final int iterationCounter;
    private final Map<T, Integer> statesMap;

    /**
     * Construct a new {@link StatsImpl}.
     * 
     * @param iterationCounter the counter of the Automaton iterations.
     * @param statesMap the map which describes the number of cells for each type.
     */
    public StatsImpl(final int iterationCounter, final Map<T, Integer> statesMap) {
        this.iterationCounter = iterationCounter;
        this.statesMap = statesMap;
    }

    @Override
    public int getIteration() {
        return this.iterationCounter;
    }

    @Override
    public Map<T, Integer> getCellStats() {
        return Collections.unmodifiableMap(this.statesMap);
    }

    @Override
    public String toString() {
        return "Iterations: " + this.iterationCounter + NEWLINE + "States: " + statesMap;
    }

}
