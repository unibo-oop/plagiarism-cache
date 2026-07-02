package todo.view.drawables.level.ui;

import java.util.Iterator;
import java.util.List;

/**
 * This interface represents an infinite iterator that produces a sequence of
 * integers. It can be reset and forced to output a series of elements as first
 * ones.
 */
interface LabelsGenerator extends Iterator<Integer> {
    /**
     * Reset the labels generator.
     */
    void reset();

    /**
     * Set the labels that need to be skipped.
     *
     * @param labels is the list of labels that must be skipped
     */
    void setLabelsToSkip(List<Integer> labels);
}
