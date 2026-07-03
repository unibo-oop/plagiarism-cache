package com.jlearn.view;

import java.io.IOException;

import com.jlearn.controller.Main;
import com.jlearn.view.screens.FXMLScreens;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * An utility class to load and store the .fxml nodes of the view.
 *
 * The caching mechanism is useful when switching between views it prevents to reload the nodes and all the values
 * stored in their components (textboxes content, sliders position, ecc...).
 *
 * It is also possible to force the reloading of the nodes for heavy .fxml files instead of keeping them cached.
 */
public final class ScreenLoader {

    private final ObservableMap<FXMLScreens, Node> cache;
    private static ScreenLoader singleton;

    private ScreenLoader() {
        this.cache = FXCollections.observableHashMap();
    }

    /**
     * Singleton class that return the {@link ScreenLoader}.
     *
     * @return the {@link ScreenLoader}
     */
    public static ScreenLoader getScreenLoader() {
        synchronized (ScreenLoader.class) {
            if (singleton == null) {
                singleton = new ScreenLoader();
            }
        }
        return singleton;
    }

    /**
     * Sets the loaded {@link Node} as main screen in the {@link Pane}.
     *
     * @param screen
     *            {@link FXMLScreens} to be loaded
     * @param mainPane
     *            the {@link Pane}
     * @throws IOException
     *             if the resource is not found
     */
    public void loadScreen(final FXMLScreens screen, final Pane mainPane) throws IOException {
        mainPane.getChildren().setAll(this.getLoadedNode(screen));
    }

    /**
     * Return the loaded {@link Node}.
     *
     * @param screen
     *            the {@link FXMLScreens}
     * @return the {@link Node}
     * @throws IllegalStateException
     *             the {@link IllegalStateException}n
     */
    public Node getLoadedNode(final FXMLScreens screen) throws IllegalStateException {
        if (!this.cache.containsKey(screen)) {
            throw new IllegalStateException();
        } else {
            return this.cache.get(screen);
        }
    }

    /**
     * Seeks for the required {@link FXMLScreens} in the cache If not present it is loaded then returned.
     *
     * @param screen
     *            {@link FXMLScreens} to be loaded
     * @param controller
     *            the Object controller
     * @return the {@link Node} rendered
     * @throws IOException
     *             if the resource is not found
     */
    public Node loadFXMLInCache(final FXMLScreens screen, final Object controller) throws IOException {

        if (this.cache.containsKey(screen)) {
            return this.cache.get(screen);
        } else {
            final Node loadedNode = this.loadFXML(screen, controller);
            this.cache.put(screen, loadedNode);
            return loadedNode;
        }
    }

    /**
     * Bypass the cache and loads directly the {@link FXMLScreens}.
     *
     * @param screen
     *            {@link FXMLScreens} to be loaded
     * @param controller
     *            the Object controller
     * @return the {@link Node} loaded
     * @throws IOException
     *             if the resource is not found
     */
    public Node loadFXML(final FXMLScreens screen, final Object controller) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(Main.class.getResource(screen.getPath()));
        return loader.load();

    }

}
