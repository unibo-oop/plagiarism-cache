package view.util;

import java.util.Collection;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.ChoiceBox;

/**
 * This simple utility offers methods to set javafx choiceboxes values.
 */
public interface ChoiceBoxInitializer {

    /**
     * fills a javafx ChoiceBox with selected values.
     * @param <T> - type of contained item
     * @param cb - choicebox
     * @param c - collection of values used to fill the choicebox
     * @param cl - choicebox's changelistener
     */
    <T> void initChoiceBox(ChoiceBox<T> cb, Collection<T> c, ChangeListener<T> cl);
}
