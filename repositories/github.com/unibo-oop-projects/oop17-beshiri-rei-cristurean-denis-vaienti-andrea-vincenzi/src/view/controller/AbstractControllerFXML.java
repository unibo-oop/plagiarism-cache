package view.controller;

import javafx.scene.layout.Region;

/**
 * Abstract class with the common behavior to all controller classes.
 */
public abstract class AbstractControllerFXML implements ControllerFXML {

    /**
     * Getter of the root node for the FXML file.
     */
    @Override
    public abstract Region getRoot();

}
