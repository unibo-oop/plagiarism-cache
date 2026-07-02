package it.unibo.aknightstale.views;

import io.github.palexdev.materialfx.controls.MFXTableView;
import it.unibo.aknightstale.controllers.interfaces.ScoreboardController;
import it.unibo.aknightstale.models.ScoreboardImpl;
import it.unibo.aknightstale.views.interfaces.MainMenuView;
import it.unibo.aknightstale.views.interfaces.ScoreboardView;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class ScoreboardViewTest extends BaseViewTest<ScoreboardController, ScoreboardView> {

    ScoreboardViewTest() {
        super(ScoreboardView.class, ScoreboardController.class);
    }

    @Start
    @Override
    public void start(final Stage stage) {
        super.start(stage);
    }

    @Test
    @DisplayName("Return to main menu")
    void onMainMenuButtonClicked(final FxRobot robot) {
        robot.clickOn("#mainMenuButton");
        Assertions.assertThat(getWindow().getCurrentView()).isInstanceOf(MainMenuView.class);
    }

    @Test
    @DisplayName("Update scoreboard table")
    void updateScoreboard(final FxRobot robot) {
        // Create scoreboard
        final var scoreboard = new ScoreboardImpl();
        scoreboard.setScore("player1", 1);
        scoreboard.setScore("player2", 2);
        scoreboard.setScore("player3", 3);
        scoreboard.setScore("player4", 4);
        final var scores = scoreboard.getEntries();

        // Update scoreboard table and check assertions when the GUI is ready
        Platform.runLater(() -> {
            getView().updateScoreboard(scores);

            final var table = robot.lookup("#scoreboardTableView").queryAs(MFXTableView.class);
            // Check columns number
            final var tableColumns = table.getTableColumns();
            Assertions.assertThat(tableColumns.size()).isEqualTo(2);
            // Check if scores are correct
            final var tableItems = table.getItems().toArray();
            Assertions.assertThat(tableItems).isEqualTo(scores.toArray());
        });
    }
}
