package test.minigames.perilouspath;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.DifficultyLevel;
import model.minigames.perilouspath.PerilousPathDifficulty;
import model.minigames.perilouspath.PerilousPathDifficultyBuilderImpl;
import view.controllers.FxController;
import view.controllers.minigames.FxMiniGameController;
import view.controllers.minigames.perilouspath.Instructions;

/**
 * Class to test if {@link PerilousPathFx} works correctly.
 */
class PerilousPathFxTest extends ApplicationTest {

    private static final String PERILOUS_PATH = "/layouts/minigames/PerilousPath.fxml";
    private PerilousPathDifficulty difficulty;
    private Parent root;

    @Override
    public void start(final Stage stage) {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(PERILOUS_PATH));
        Optional<FxMiniGameController> fxController = Optional.empty();
        try {
            this.root = loader.load(this.getClass().getResourceAsStream(PERILOUS_PATH));
            fxController = Optional.of((FxMiniGameController) loader.<FxController>getController());
            stage.setScene(new Scene(this.root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fxController.get().initGame(DifficultyLevel.EASY);
        this.difficulty = new PerilousPathDifficultyBuilderImpl().setDifficultyLevel(DifficultyLevel.EASY).build();
    }

    /**
     * Test if the images are set.
     */
    @Test
    public void gridButtonsTest() {
        final GridPane grid = from(this.root).lookup("#grid").query();
        final List<Node> backgrounds = new ArrayList<>();
        for (final Node b : grid.getChildren()) {
            final Optional<Node> node = Optional.ofNullable(((Button) b).getGraphic());
            if (node.isPresent()) {
                backgrounds.add(((Button) b).getGraphic());
            }
        }
        assertFalse(backgrounds.isEmpty());
        assertTrue(backgrounds.size() - 2 <= this.difficulty.getNumMines());
        assertEquals(this.difficulty.getSize() * this.difficulty.getSize(), grid.getChildren().size());
    }

    /**
     * Test if the instructions are set.
     */
    @Test
    public void instructionsTest() {
        final Label instruction = from(this.root).lookup("#instructions").query();
        final Set<String> instructions = new HashSet<>();
        for (final Instructions i : Instructions.values()) {
            instructions.add(i.toString());
        }
        assertTrue(instructions.contains(instruction.getText()));
    }
}
