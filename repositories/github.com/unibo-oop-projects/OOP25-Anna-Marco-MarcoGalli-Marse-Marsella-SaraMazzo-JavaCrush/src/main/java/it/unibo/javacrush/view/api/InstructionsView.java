package it.unibo.javacrush.view.api;

import javafx.scene.Parent;

/**
 * Interface for the instructions view.
 */
@FunctionalInterface
public interface InstructionsView {

    /**
     * Get the view.
     * 
     * @return the view
     */
    Parent getView();

}
