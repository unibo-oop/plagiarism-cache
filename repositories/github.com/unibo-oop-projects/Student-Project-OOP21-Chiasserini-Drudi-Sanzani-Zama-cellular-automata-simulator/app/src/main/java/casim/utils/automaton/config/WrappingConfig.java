package casim.utils.automaton.config;

/**
 * Configuration class with a boolean
 * wrapping value indicating if the grid used
 * is a wrapping grid or otherwise.
 */
public class WrappingConfig extends BaseConfig {

    private final boolean wrapping;

    /**
     * Constructs a {@link WrappingConfig} with given values.
     * 
     * @param rows the number of rows of the grid.
     * @param cols the number of columns of the grid.
     * @param isAutomatic true if the automaton has to run in automatic configuration.
     * @param wrapping true if the grid is a wrapping grid.
     */
    public WrappingConfig(final int rows, final int cols, final boolean isAutomatic, final boolean wrapping) {
        super(rows, cols, isAutomatic);
        this.wrapping = wrapping;
    }

    /**
     * Retuns the wrapping value of the config.
     * 
     * @return true if the grid is a wrapping grid
     *          false otherwise.
     */
    public boolean isWrapped() {
        return this.wrapping;
    }
}
