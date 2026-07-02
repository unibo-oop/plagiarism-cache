package model.memento;

import javafx.util.Pair;

/**
 *
 * Rastaldi Martina.
 *
 */
public interface Memento {
    /**
     * 
     * @return the last registered movement.
     */
    Pair<Integer, Integer> getLastStep();

    /**
     *
     * @return true if there is a step, false otherwise
     */
    boolean isStepPresent();

    /**
     *
     * @param step
     *            step to put in the list of steps
     */
    void lastStep(Pair<Integer, Integer> step);

    /**
     * Restore settings of Memento.
     */
    void resetMemento();

}
