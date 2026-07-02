package view.util;

import java.util.Collection;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ChoiceBox;

/**
 * implementation of ChoiceBoxinitiliazer.
 */
public final class ChoiceBoxInitializerImpl implements ChoiceBoxInitializer {

    /**
     * fills a javafx ChoiceBox with selected values.
     * @param <T> - type of contained item
     * @param cb - choicebox
     * @param c - collection of values used to fill the choicebox
     * @param cl - choicebox's changelistener
     */
    public <T> void initChoiceBox(final ChoiceBox<T> cb, final Collection<T> c, final ChangeListener<T> cl) {
        c.forEach(x -> cb.getItems().add(x));
        cb.setStyle("-fx-font: 18px \"Serif\";");
        cb.getSelectionModel().selectedItemProperty().addListener(cl);
    }

}
