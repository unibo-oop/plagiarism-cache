package casim.model.langtonsant;

import casim.utils.automaton.config.WrappingConfig;

/**
 * LangtonsAnt configuration class.
 */
public class LangtonsAntConfig extends WrappingConfig {

    private final int antNumber;

    /**
     * Constucts a new {@link LangtonsAntConfig}
     * with given values.
     * 
     * @param rows the number of rows of the grid.
     * @param cols the number of columns of the grid.
     * @param isAutomatic true if the automaton has to run in automatic configuration.
     * @param wrapping true if the grid is a wrapping grid.
     * @param antNumber the number of ants in the automaton.
     */
    public LangtonsAntConfig(
            final int rows, final int cols, final boolean isAutomatic, final boolean wrapping, final int antNumber) {
        super(rows, cols, isAutomatic, wrapping);
        this.antNumber = antNumber;
    }

    /**
     * Returns the number of ants in the
     * automaton.
     * 
     * @return the number of ants in the
     *          automaton.
     */
    public int getAntNumber() {
        return this.antNumber;
    }
}
