package casim.model.codi;

import casim.utils.automaton.config.BaseConfig;

/**
 * CoDi config class.
 */
public class CoDiConfig extends BaseConfig {

    private final int depth;

    /**
     * Constructs a {@link CoDiConfig} with given values.
     * 
     * @param rows the number of rows of the grid.
     * @param cols the number of columns of the grid.
     * @param depth the depth of the grid.
     * @param isAutomatic true if the automaton has to run in automatic configuration.
     */
    public CoDiConfig(final int rows, final int cols, final int depth, final boolean isAutomatic) {
        super(rows, cols, isAutomatic);
        this.depth = depth;
    }

    /**
     * Returns the depth of the grid.
     * 
     * @return the depth of the grid.
     */
    public int getDepth() {
        return this.depth;
    }
}
