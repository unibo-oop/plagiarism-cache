package it.lttply.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.lttply.view.fxmlscreens.FXMLScreens;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * An utility class to load and store the .fxml nodes of the view.
 *
 * The caching mechanism is useful when switching between views it prevents to
 * reload the nodes and all the values stored in their components (textboxes
 * content, sliders position, ecc...).
 *
 * It is also possible to force the reloading of the nodes for heavy .fxml files
 * instead of keeping them cached.
 */
public final class ScreenLoaderConcrete implements ScreenLoader {

    private final Map<FXMLScreens, Node> cache;

    /**
     * Maps of the screens.
     */
    public ScreenLoaderConcrete() {
        cache = new HashMap<>();
    }

    @Override
    public void loadScreen(final FXMLScreens screen, final Pane mainPane) throws IOException {
        mainPane.getChildren().setAll(getLoadedNode(screen));

    }

    @Override
    public Node getLoadedNode(final FXMLScreens screen) throws IllegalStateException {
        if (!cache.containsKey(screen)) {
            throw new IllegalStateException();
        } else {
            return cache.get(screen);
        }
    }

    @Override
    public Node loadFXMLInCache(final FXMLScreens screen, final Object controller) throws IOException {

        if (cache.containsKey(screen)) {
            return cache.get(screen);
        } else {
            final Node loadedNode = loadFXML(screen, controller);
            cache.put(screen, loadedNode);
            return loadedNode;
        }
    }

    @Override
    public Node loadFXML(final FXMLScreens screen, final Object controller) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);

        loader.setLocation(ViewApp.class.getResource(screen.getPath()));
        return loader.load();

    }

}
