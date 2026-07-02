package it.unibo.aknightstale.views;

import it.unibo.aknightstale.controllers.interfaces.MainMenuController;
import it.unibo.aknightstale.views.interfaces.MainMenuView;
import it.unibo.aknightstale.views.interfaces.ScoreboardView;
import javafx.stage.Stage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class MainMenuTest extends BaseViewTest<MainMenuController, MainMenuView> {
    MainMenuTest() {
        super(MainMenuView.class, MainMenuController.class);
    }

    @Override
    @Start
    public void start(final Stage stage) {
        super.start(stage);
    }

    @Test
    @DisplayName("Exit the game with the exit button")
    void onExitButtonClicked(final FxRobot robot) {
        robot.clickOn("#exitButton");
        Assertions.assertThat(getWindow().isOpened()).isFalse();
    }

    @Test
    @DisplayName("Open the scoreboard with the scoreboard button")
    void onScoreboardButtonClicked(final FxRobot robot) {
        robot.clickOn("#scoreboardButton");
        Assertions.assertThat(getWindow().getCurrentView()).isInstanceOf(ScoreboardView.class);
    }
}
