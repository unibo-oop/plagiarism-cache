package starcatraz.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import starcatraz.model.Achievements;

/**
 * Achievements controller implementation.
 */
public class AchievementsControllerImpl extends StarcatrazController implements Initializable, AchievementsController {

    /**
     * Name of the class in style.css to apply when an achievement is achieved.
     */
    private static final String PANE_ACHIEVEMENT = "achievement_pane";

    @FXML
    private AnchorPane achievementsRoot;
    @FXML
    private Pane paneFirstWin;
    @FXML
    private Pane paneRightPath;
    @FXML
    private Pane paneSpaceMaster;
    @FXML
    private Pane paneYouDied;
    @FXML
    private Pane paneKill;
    @FXML
    private Pane paneColorful;
    @FXML
    private Pane paneHurryUp;
    @FXML
    private Pane paneHalf;
    @FXML
    private Pane paneCatch;

    /**
     * Instance of Achievements
     */
    private Achievements achievement;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) { }

    @Override
    public void closeAchievementsButtonClick() {
        achievementsRoot.setVisible(false);
    }

    @Override
    public void setAchievements(final Achievements achievement) {
        this.achievement = achievement;
        if (this.achievement.isFirstVictoryCompleted()) {
            paneFirstWin.getStyleClass().add(PANE_ACHIEVEMENT);
        }
        if (this.achievement.isFirstDefeatCompleted()) {
            paneYouDied.getStyleClass().add(PANE_ACHIEVEMENT);
        }
        if (this.achievement.isFirstTenVictoriesCompleted()) {
            paneRightPath.getStyleClass().add(PANE_ACHIEVEMENT);
        }
        if (this.achievement.isFirstHundredVictoriesCompleted()) {
            paneSpaceMaster.getStyleClass().add(PANE_ACHIEVEMENT);
        }
        if (this.achievement.isFiftyGamePlayedCompleted()) {
            paneCatch.getStyleClass().add(PANE_ACHIEVEMENT);
        }
        if (this.achievement.isRejectTenRobotAttacksCompleted()) {
            paneKill.getStyleClass().add(PANE_ACHIEVEMENT);
        }
        if (this.achievement.isSixCardStreakCompleted()) {
            paneColorful.getStyleClass().add(PANE_ACHIEVEMENT);
        }
        if (this.achievement.isWinInTwoMinutesCompleted()) {
            paneHurryUp.getStyleClass().add(PANE_ACHIEVEMENT);
        }
        if (this.achievement.isWinWithHalfDeckLeftCompleted()) {
            paneHalf.getStyleClass().add(PANE_ACHIEVEMENT);
        }
    }
}
