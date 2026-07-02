package it.unibo.javacrush.model.api;

import it.unibo.javacrush.common.CellType;

/**
 * Interface representing a factory for creating Goal instances.
 */
@SuppressWarnings("PMD.ImplicitFunctionalInterface")
public interface GoalFactory {

    /**
     * Create a new goal with the specified type and target amount.
     * 
     * @param type the type of the goal (type of cells that need to be collected)
     * @param targetAmount the amount of cells
     * @return a new Goal instance
     */
    Goal createGoal(CellType type, int targetAmount);
}
