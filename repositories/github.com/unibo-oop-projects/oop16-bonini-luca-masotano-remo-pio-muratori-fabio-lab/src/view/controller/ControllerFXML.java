package view.controller;

import javafx.scene.layout.Region;

/**
 * Provides methods to get fxml controller root or display it's contents.
 */
public interface ControllerFXML {

    /**
     * Getter of the root node of the fxml file contents.
     * 
     * @return the root element of this {@link javafx.scene.Node} graph
     */
    Region getRoot();

    /**
     * Whenever this method is called a default opening transition start.
     */
    void openAnimation();

    /**
     * Whenever this method is called a default closing transition start.
     * 
     * @param action
     *            at the end of the transition the specifier {@link Runnable} action
     *            is started
     */
    void closeAnimation(Runnable action);
}