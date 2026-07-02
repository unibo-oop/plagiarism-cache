package test.minigames.oneway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.DifficultyLevel;
import model.minigames.oneway.OneWaySettingsImpl;
import view.controllers.FxController;
import view.controllers.minigames.FxMiniGameController;

public class OneWayFxTest extends ApplicationTest {
    private static final String ONE_WAY = "/layouts/minigames/OneWay.fxml";
    private final DifficultyLevel difficulty = DifficultyLevel.NORMAL;
    private OneWaySettingsImpl settings;
    private Parent root;

    /**
     * {@inheritDoc}.
     */
    @Override
    public void start(final Stage stage) {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(ONE_WAY));
        Optional<FxMiniGameController> fxController = Optional.empty();
        try {
            this.root = loader.load(this.getClass().getResourceAsStream(ONE_WAY));
            fxController = Optional.of((FxMiniGameController) loader.<FxController>getController());
            stage.setScene(new Scene(this.root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.settings = new OneWaySettingsImpl(difficulty);
        fxController.get().setRoot(root);
        fxController.get().initGame(difficulty);
    }

    /**
     * Test if the main grid of the game is correctly set.
     */
    @Test
    public void gridButtonTest() {
        final GridPane grid = from(this.root).lookup("#grid").query();
        final List<Node> backgrounds = new ArrayList<>();
        for (final Node b : grid.getChildren()) {
            final Optional<Node> node = Optional.ofNullable(((Button) b).getGraphic());
            if (node.isPresent()) {
                backgrounds.add(((Button) b).getGraphic());
            }
        }
        // solo un bottone ha lo sfondo: quello che identifica la posizione iniziale
        assertFalse(backgrounds.isEmpty());
        assertEquals(backgrounds.size(), 1); 

        final int gridDimension = this.settings.getGridSize() * this.settings.getGridSize();
        assertEquals(grid.getChildren().size(), gridDimension);
    }

    /**
     * Test if the arrow buttons are correctly set.
     */
    @Test
    public void arrowGridTest() {
        final GridPane arrows = from(this.root).lookup("#arrowRow").query();
        final List<Node> backgrounds = new ArrayList<>();
        int idx = 0;
        for (final Node b : arrows.getChildren()) {
            final Optional<Node> node = Optional.ofNullable(((Button) b).getGraphic());
            if (node.isPresent()) {
                backgrounds.add(((Button) b).getGraphic());
                assertNotNull(backgrounds.get(idx));
            }
            idx++;
        }
        final int gridDimension = 1 * this.settings.getArrows();
        assertEquals(backgrounds.size(), this.settings.getArrows());
        assertEquals(arrows.getChildren().size(), gridDimension);
    }
}
