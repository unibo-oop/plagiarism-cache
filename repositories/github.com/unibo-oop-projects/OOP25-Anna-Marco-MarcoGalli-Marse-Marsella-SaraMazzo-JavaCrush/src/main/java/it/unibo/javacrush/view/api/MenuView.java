package it.unibo.javacrush.view.api;

import javafx.scene.Parent;

/**
 * Interface for the menu view.
 */
@FunctionalInterface
public interface MenuView {

    /**
     * Get the view.
     * 
     * @return the view
     */
    Parent getView();

}
