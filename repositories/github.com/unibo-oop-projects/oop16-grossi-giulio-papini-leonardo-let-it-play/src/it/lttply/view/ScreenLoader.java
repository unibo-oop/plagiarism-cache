package it.lttply.view;

import java.io.IOException;

import it.lttply.view.fxmlscreens.FXMLScreens;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Interface for Load Screen.
 */
public interface ScreenLoader {

    /**
     * Sets the loaded Node as main screen in the container.
     *
     * @param screen
     *            screen to be loaded
     * @param mainPane
     *            the main pane
     * @throws IOException
     *             if the resource is not found
     *
     */
    void loadScreen(FXMLScreens screen, Pane mainPane) throws IOException;

    /**
     *
     * @param screen
     *            .fxml of the view
     * @return return the loaded screen
     * @throws IllegalStateException
     *             Signals that a method has been invoked at an illegal or
     *             inappropriate time
     */
    Node getLoadedNode(FXMLScreens screen) throws IllegalStateException;

    /**
     * Seeks for the required screen in the cache If not present it is loaded
     * then returned.
     *
     * @param screen
     *            screen to be loaded
     * @param controller
     *            the view controller
     * @return the Node loaded
     * @throws IOException
     *             if the resource is not found
     */
    Node loadFXMLInCache(FXMLScreens screen, Object controller) throws IOException;

    /**
     * Bypass the cache and loads directly the .fxml.
     *
     * @param screen
     *            screen to be loaded
     * @param controller
     *            the view controller
     * @return the Node loaded
     * @throws IOException
     *             if the resource is not found
     */
    Node loadFXML(FXMLScreens screen, Object controller) throws IOException;

}