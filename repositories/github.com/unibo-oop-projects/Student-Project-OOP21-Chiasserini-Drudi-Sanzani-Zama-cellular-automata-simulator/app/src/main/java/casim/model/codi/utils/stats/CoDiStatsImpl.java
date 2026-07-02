package casim.model.codi.utils.stats;

import java.util.Map;

import casim.model.abstraction.utils.stats.StatsImpl;
import casim.model.codi.cell.CoDiCellState;

/**
 * Stats implementation for CoDi automaton.
 * It implements {@link CoDiStats}.
 */
public class CoDiStatsImpl extends StatsImpl<CoDiCellState> implements CoDiStats {

    private static final String NEWLINE = System.lineSeparator();

    private final int outputLayer;

    /**
     * Construct a new {@link CoDiStatsImpl}.
     * 
     * @param iterationCounter the counter of the automaton iterations.
     * @param statesMap the map which describes the number of cells for each type.
     * @param outputlayer the current output layer.
     */
    public CoDiStatsImpl(final int iterationCounter, final Map<CoDiCellState, Integer> statesMap, final int outputlayer) {
        super(iterationCounter, statesMap);
        this.outputLayer = outputlayer;
    }

    @Override
    public int getOutputLayer() {
        return this.outputLayer;
    }

    @Override
    public String toString() {
        return "OutputLayer: " + this.outputLayer + NEWLINE
                + "Iteration: " + this.getIteration() + NEWLINE 
                + "States: " + this.getCellStats();
    }

}
