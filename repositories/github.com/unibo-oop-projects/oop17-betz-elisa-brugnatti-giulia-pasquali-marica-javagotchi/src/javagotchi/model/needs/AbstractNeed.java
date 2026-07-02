package javagotchi.model.needs;

import java.io.Serializable;

/**
 * This abstract class models a template for the several 
 * needs the javagotchi has.
 * 
 * @author elisa
 *
 */
public abstract class AbstractNeed implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -7667249865943462372L;

    /**
     * The minimum level.
     */
    public static final double MIN_LEVEL = 0.5;
    /**
     * The maximum level.
     */
    protected static final double MAX_LEVEL = 1.0;
    private static final double DEATH_LEVEL = 0;

    private double level;

    /**
     * Getter for the level of the need.
     * 
     * @return The current level of the need.
     */
    public final double getLevel() {
        return this.level;
    }

    /**
     * Setter for the level of the need.
     * 
     * @param value value to use
     */
    public final void setLevel(final double value) {
        this.level = value;
    }

    /**
     * Method to check if the level of the need reached the Death level.
     * 
     * @return True if the level is fatal; false otherwise
     */
    protected boolean hasReachedTheDeathLevel() {
        return (this.level <= DEATH_LEVEL);
    }

    /**
     * Method to check if the level of the need reached the Maximum level.
     * 
     * @return True if the level is maximum; false otherwise
     */
    private boolean hasReachedTheMaximumLevel() {
        return (this.level >= MAX_LEVEL);
    }

    /**
     * Abstract method that indicates how much the level of the need increases.
     */
    protected abstract void incLevel();

    /**
     * Abstract method that indicates how much the level of the need decreases.
     */
    protected abstract void decLevel();

    /**
     * Method to raise the need (this is a Template Method).
     */
    public final void raise() {
        this.incLevel();
        if (this.hasReachedTheMaximumLevel()) {
            this.setLevel(MAX_LEVEL);
        } 
    }

    /**
     * Method to drop the need (this is a Template Method).
     */
    public final void reduce() {
        this.decLevel();
        if (this.hasReachedTheDeathLevel()) {
            this.setLevel(DEATH_LEVEL);
        }
    }

}
