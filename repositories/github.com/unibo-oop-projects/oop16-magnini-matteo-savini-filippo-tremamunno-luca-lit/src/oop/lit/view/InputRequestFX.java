package oop.lit.view;

import javafx.scene.layout.Pane;
import oop.lit.util.InputRequest;

/**
 * An input request using javafx to ask user for input.
 *
 * @param <T> the type of input needed
 */
public interface InputRequestFX<T> extends InputRequest<T> {
    /**
     * Returns a javafx pane containing all nodes necessary to the user for providing the input.
     * A visualization of the label is contained in the pane.
     * @return
     *      the pane. 
     */
    Pane getFXPane();
}
