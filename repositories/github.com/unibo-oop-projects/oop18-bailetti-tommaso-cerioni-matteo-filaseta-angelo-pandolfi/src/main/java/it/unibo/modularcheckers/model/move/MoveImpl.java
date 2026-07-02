package it.unibo.modularcheckers.model.move;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Basic move implementation.
 */
public class MoveImpl implements Move {

    private final List<Step> stepList;

    /**
     * @param stepList the list containing all the step to generate the move.
     */
    public MoveImpl(final List<Step> stepList) {
        this.stepList = stepList;
    }

    /**
     * @return an iterator for the move.
     */
    @Override
    public Iterator<Step> iterator() {
        return stepList.iterator();
    }

    /**
     * @return the unmodifiable list containing the steps of the move.
     */
    @Override
    public List<Step> getSteps() {
       return Collections.unmodifiableList(stepList);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.stepList.toString();
    }

}
