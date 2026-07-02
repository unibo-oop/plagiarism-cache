package it.unibo.javacrush.model.api;

import it.unibo.javacrush.common.CellType;

/**
 * Interface representing a goal in the game.
 */
public interface Goal {

    /**
     * Return the type of the goal (type of cells that need to be collected).
     * 
     * @return the type of the goal
     */
    CellType getTargetType();

    /**
     * Return the amount of cells that need to be collected to achieve the goal.
     * 
     * @return the target amount of the goal
     */
    int getTargetAmount();

    /**
     * Return the number of cells already collected for this specific goal.
     * 
     * @return the number of cells already collected
     */
    int getCurrentAmount();

    /**
     * Add the collected cells to the current amount of the goal.
     * 
     * @param count the number of cells collected to be added to the current amount
     */
    void addProgress(int count);

    /**
     * Return whether the goal is reached or not.
     * 
     * @return true if the goal is reached, false otherwise
     */
    boolean isReached();
}
