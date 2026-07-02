package test.minigames.colorgama;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashSet;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.DifficultyLevel;
import model.minigames.colorgama.ColorGamaSettings;
import model.minigames.colorgama.ColorGamaSettingsImpl;
import model.minigames.colorgama.QuestionType;

import view.controllers.FxController;
import view.controllers.minigames.FxMiniGameController;

class ColorGamaTestFx extends ApplicationTest {

    private static final String COLOR_GAMA_PATH = "/layouts/minigames/ColorGama.fxml";
    private ColorGamaSettings settings;
    private Parent root; 

    @Override
    public void start(final Stage stage) {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(COLOR_GAMA_PATH));
        Optional<FxMiniGameController> fxController = Optional.empty();
        try {
            this.root = loader.load(this.getClass().getResourceAsStream(COLOR_GAMA_PATH));
            fxController = Optional.of((FxMiniGameController) loader.<FxController>getController());
            stage.setScene(new Scene(this.root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fxController.get().initGame(DifficultyLevel.EASY);
        this.settings = new ColorGamaSettingsImpl(DifficultyLevel.EASY);
    }

    @Test
    public void testButtons() {
        final GridPane grid = from(this.root).lookup("#buttonsGrid").query();
        final Set<Background> backgrounds = new HashSet<>();
        for (final Node b : grid.getChildren()) {
            backgrounds.add(((Button) b).getBackground());
            assertTrue(((Button) b).backgroundProperty().isNotNull().get());
        }
        assertFalse(backgrounds.isEmpty());
        assertTrue(backgrounds.size() <= this.settings.getNumColor() + 1);
        assertEquals(this.settings.getGridSize() * this.settings.getGridSize(), grid.getChildren().size());
    }

    @Test
    public void testQuestion() {
        final Label question = from(this.root).lookup("#questionTitle").query();
        final Set<String> questions = new HashSet<>();
        for (final QuestionType q : QuestionType.values()) {
            questions.add(q.getQuestionName());
        }
        assertTrue(questions.contains(question.getText()));
    }
}
