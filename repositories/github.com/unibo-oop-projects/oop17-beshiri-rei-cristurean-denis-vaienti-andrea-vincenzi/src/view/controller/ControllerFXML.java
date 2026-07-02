package view.controller;

import javafx.scene.layout.Region;

/**
 * Interface that provides methods common to all views.
 */
public interface ControllerFXML {

    /**
     * Getter of the root node for the FXML file.
     * @return The root element.
     */
    Region getRoot();

    /**
     * Set text in different scene.
     */
    void setText();
}
