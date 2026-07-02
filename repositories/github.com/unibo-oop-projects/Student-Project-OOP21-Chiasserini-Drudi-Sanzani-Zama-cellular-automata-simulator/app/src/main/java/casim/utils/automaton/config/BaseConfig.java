package casim.utils.automaton.config;

/**
 * Basic configuration class Automaton.
 */
public class BaseConfig {

    private final int rows;
    private final int cols;
    private final boolean automatic;

    /**
     * Constructs a {@link BaseConfig} with the given values.
     * 
     * @param rows the number of rows of the grid.
     * @param cols the number of columns of the grid.
     * @param automatic true if the automaton has to run on automatic simulation.
     */
    public BaseConfig(final int rows, final int cols, final boolean automatic) {
        this.rows = rows;
        this.cols = cols;
        this.automatic = automatic;
    }

    /**
     * Returns the number of rows.
     * 
     * @return the number of rows in the grid.
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * Returns the number of columns.
     * 
     * @return the number of columns in the grid.
     */
    public int getCols() {
        return this.cols;
    }

    /**
     * Returns true if the automaton has to be run in automatic mode.
     * 
     * @return true if the simulation is automatic, false otherwise.
     */
    public boolean isAutomatic() {
        return this.automatic;
    }
}
