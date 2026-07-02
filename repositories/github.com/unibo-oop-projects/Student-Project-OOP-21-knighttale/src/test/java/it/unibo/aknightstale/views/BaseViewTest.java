package it.unibo.aknightstale.views;

import com.simtechdata.sceneonefx.SceneOne;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.aknightstale.controllers.factories.ControllerFactory;
import it.unibo.aknightstale.controllers.interfaces.Controller;
import it.unibo.aknightstale.views.factories.ViewFactory;
import it.unibo.aknightstale.views.interfaces.View;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeoutException;

import static org.testfx.api.FxToolkit.registerPrimaryStage;

@SuppressFBWarnings("EI_EXPOSE_REP") // View must be passed as reference to allow view loader caching.
@ExtendWith(ApplicationExtension.class)
public abstract class BaseViewTest<C extends Controller<V>, V extends View<C>> { //NOPMD - suppressed AbstractClassWithoutAbstractMethod - Without abstract the test method runs (since it would be a real class that JUnit recognizes as a test class).
    private V view;
    private final Class<V> viewInterface;
    private C controller;
    private final Class<C> controllerInterface;

    protected BaseViewTest(final Class<V> viewInterface, final Class<C> controllerInterface) {
        super();
        this.viewInterface = viewInterface;
        this.controllerInterface = controllerInterface;
    }

    @BeforeAll
    public static void setupSpec() throws TimeoutException {
        if (Boolean.getBoolean("headless")) {
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("java.awt.headless", "true");
            System.setProperty("testfx.setup.timeout", "2500");
        }
        registerPrimaryStage();
    }

    @Test
    @DisplayName("Check if view is displayed")
    void checkIfViewIsOpened() {
        Assertions.assertThat(this.getWindow().isOpened()).isTrue();
    }

    /**
     * Starts the JavaFX application.
     *
     * @param stage JavaFX app stage.
     */
    @Start
    public void start(final Stage stage) {
        // Clear cache when starting a new test set.
        this.clearCache();
        WaitForAsyncUtils.waitForFxEvents();

        SceneOne.disableNotice();

        final var controllerFactory = Controller.of(this.controllerInterface, this.viewInterface);
        this.controller = controllerFactory.get();

        this.showView();
        this.view = View.of(this.viewInterface).get();
    }

    /**
     * Show the view.
     */
    protected void showView() {
        this.controller.showView();
    }

    /**
     * Get the view.
     *
     * @return view.
     */
    public V getView() {
        return this.view;
    }

    /**
     * Get the view.
     *
     * @return view.
     */
    public <T extends BaseView<C>> T getView(final Class<T> viewClass) {
        return viewClass.cast(view);
    }

    /**
     * Get view controller.
     *
     * @return controller.
     */
    public C getController() {
        return controller;
    }

    /**
     * Get the window related to the view of this test.
     *
     * @return window instance.
     */
    public Window getWindow() {
        return ((BaseView<?>) getView()).getWindow();
    }

    /**
     * Clears the cache of instantiated controllers and views.
     */
    protected void clearCache() {
        ViewFactory.clearCache();
        ControllerFactory.clearCache();
    }
}
