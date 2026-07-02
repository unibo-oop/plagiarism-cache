package clashclass.view.graphic;

import javafx.stage.Stage;

/**
 * Class representing a window using JavaFX.
 */
public class WindowJavaFX extends AbstractWindow {
    private final GraphicSceneFactory sceneFactory;

    /**
     * Constructs the window.
     */
    public WindowJavaFX() {
        final var stage = new Stage();
        this.sceneFactory = new GraphicSceneFactoryJFX(stage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GraphicSceneFactory getGraphicSceneFactory() {
        return this.sceneFactory;
    }
}
