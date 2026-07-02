package clashclass.view.graphic;

import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * JavaFX-based implementation of GraphicSceneFactory.
 */
public class GraphicSceneFactoryJFX implements GraphicSceneFactory {
    private final Stage stage;

    /**
     * Constructor for GraphicSceneFactory.
     *
     * @param stage the stage in which the scene is created
     */
    public GraphicSceneFactoryJFX(final Stage stage) {
        this.stage = new Stage(stage.getStyle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseScene createMenuScene(final Window window) {
        return new MenuJFX(stage, window);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseScene createPlayerVillageScene(final Window window) {
        try {
            return new PlayerVillageSceneJFX(
                    window,
                    stage,
                    Paths.get("Villages-Data/player-village.csv"),
                    Paths.get("Villages-Data/battle-village.csv"));
        } catch (final IOException e) {
            // throw new IOException("Player village file not found", e);
            return null;
        }
    }
}
