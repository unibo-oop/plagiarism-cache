package it.unibo.aknightstale.views;

import it.unibo.aknightstale.controllers.interfaces.GameFinishedController;
import it.unibo.aknightstale.models.ScoreboardImpl;
import it.unibo.aknightstale.views.interfaces.GameFinishedView;
import it.unibo.aknightstale.views.interfaces.MainMenuView;
import it.unibo.aknightstale.views.interfaces.MapView;
import it.unibo.aknightstale.views.interfaces.ScoreboardView;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@SuppressWarnings("checkstyle:TodoComment")
@ExtendWith(ApplicationExtension.class)
class GameFinishedTest extends BaseViewTest<GameFinishedController, GameFinishedView> {
    private static final Integer SCORE = 4;

    GameFinishedTest() {
        super(GameFinishedView.class, GameFinishedController.class);
    }

    @Override
    @Start
    public void start(final Stage stage) {
        super.start(stage);
    }

    @Override
    protected void showView() {
        this.getController().setScore(SCORE);
        super.showView();
    }

    @Test
    @DisplayName("Start a new game when the button is pressed")
    void onNewGameButtonClicked(final FxRobot robot) {
        robot.clickOn("#newGameButton");
        Assertions.assertThat(getWindow().getCurrentView()).isInstanceOf(MapView.class);
    }

    @Test
    @DisplayName("Open the game with the exit button")
    void checkScoreLabel(final FxRobot robot) {
        final var score = Integer.parseInt(robot.lookup("#scoreLabel").queryAs(Label.class).getText());
        Assertions.assertThat(score).isEqualTo(SCORE).isEqualTo(getController().getScore());
    }

    @Test
    @DisplayName("Checks the scoreboard after saving the score")
    void checkNewScoreAfterSaving(final FxRobot robot) {
        final var score = Integer.parseInt(robot.lookup("#scoreLabel").queryAs(Label.class).getText());
        robot.clickOn("#nameTextField");
        robot.write("TestFXPlayer");
        robot.clickOn("#saveScoreButton");

        final var scoreboard = new ScoreboardImpl();
        scoreboard.load();
        Assertions.assertThat(scoreboard.getScore("TestFXPlayer")).isEqualTo(score);
        scoreboard.deleteScore("TestFXPlayer");
        scoreboard.save();
    }


    @Test
    @DisplayName("Open the game with the exit button")
    void onExitButtonClicked(final FxRobot robot) {
        robot.clickOn("#mainMenuButton");
        Assertions.assertThat(getWindow().getCurrentView()).isInstanceOf(MainMenuView.class);
    }

    @Test
    @DisplayName("Open the scoreboard with the scoreboard button")
    void onScoreboardButtonClicked(final FxRobot robot) {
        robot.clickOn("#scoreboardButton");
        Assertions.assertThat(getWindow().getCurrentView()).isInstanceOf(ScoreboardView.class);
    }
}
