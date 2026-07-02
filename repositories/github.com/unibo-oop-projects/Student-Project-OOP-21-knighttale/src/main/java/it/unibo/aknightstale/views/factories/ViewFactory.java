package it.unibo.aknightstale.views.factories;

import com.google.common.base.CaseFormat;
import com.simtechdata.sceneonefx.SceneOne;
import it.unibo.aknightstale.controllers.interfaces.Controller;
import it.unibo.aknightstale.factories.ClassFactory;
import it.unibo.aknightstale.views.AlertType;
import it.unibo.aknightstale.views.BaseView;
import it.unibo.aknightstale.views.JavaFXApp;
import it.unibo.aknightstale.views.Window;
import it.unibo.aknightstale.views.exceptions.ViewLoadingException;
import it.unibo.aknightstale.views.interfaces.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ViewFactory<V extends View<? extends Controller<V>>> {
    private static final Map<Class<? extends View<?>>, View<?>> VIEWS = new HashMap<>();

    private Class<V> viewInterface;
    private boolean forceCreation;

    /**
     * Clears the cache of instantiated views.
     */
    public static void clearCache() {
        VIEWS.clear();
    }

    /**
     * Set the view interface to search implementing class to instantiate.
     *
     * @param viewInterface View interface to search implementing class to instantiate.
     * @return This instance of the factory.
     */
    public ViewFactory<V> fromInterface(final Class<V> viewInterface) {
        this.viewInterface = viewInterface;
        return this;
    }

    /**
     * Set the factory to always create a new view instance, even if it is already created.
     *
     * @return This instance of the factory.
     */
    public ViewFactory<V> forceCreation() {
        forceCreation = true;
        return this;
    }

    /**
     * Get the view instance.
     *
     * @return An instance of the view class implementing the interface.
     */
    public V get() {
        if (!forceCreation && VIEWS.containsKey(viewInterface)) {
            return viewInterface.cast(VIEWS.get(viewInterface));
        }

        final var view = ClassFactory.createInstanceFromInterface(viewInterface, "views");
        final var viewName = view.getClass().getSimpleName();

        // Get fxml file name from view class name (remove the ViewImpl suffix, make it upper camelcase and
        // add the file extension).
        final var fxmlFileName = CaseFormat.UPPER_CAMEL.to(
                CaseFormat.LOWER_UNDERSCORE,
                viewName.replace("ViewImpl", "")
        ) + ".fxml";

        // Load FXML file
        final FXMLLoader fxmlLoader = new FXMLLoader(JavaFXApp.class.getResource(fxmlFileName));
        try {
            SceneOne.set(viewName, fxmlLoader.<AnchorPane>load())
                    .title(view.getWindowTitle())
                    .build();
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
            Alert.showAlert(AlertType.ERROR, "Error loading " + fxmlFileName, e.getMessage());
            throw new ViewLoadingException(e);
        }

        final var viewInstance = fxmlLoader.<V>getController();
        ((BaseView<?>) viewInstance).setWindow(Window.getOrCreate("main_window"));

        VIEWS.put(viewInterface, viewInstance);

        return viewInstance;
    }
}
