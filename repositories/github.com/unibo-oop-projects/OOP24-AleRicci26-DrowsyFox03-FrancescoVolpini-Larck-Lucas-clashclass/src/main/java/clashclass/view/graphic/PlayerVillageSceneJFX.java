package clashclass.view.graphic;

import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Represents a {@link AbstractVillageSceneJFX} extension.
 */
public class PlayerVillageSceneJFX extends AbstractVillageSceneJFX {
    /**
     * Constructs the scene.
     *
     * @param window the window
     * @param stage the stage
     * @param playerCsvPath the player village file path
     * @param battleCsvPath the battle village fila path
     *
     * @throws IOException IO file exception
     */
    public PlayerVillageSceneJFX(
            final Window window,
            final Stage stage,
            final Path playerCsvPath,
            final Path battleCsvPath) throws IOException {
        super(window, stage, "Clash Of Class", playerCsvPath, battleCsvPath);
    }
}
