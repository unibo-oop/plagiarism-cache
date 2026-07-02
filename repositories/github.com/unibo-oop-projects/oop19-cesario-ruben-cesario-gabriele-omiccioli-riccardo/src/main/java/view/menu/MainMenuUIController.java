package view.menu;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import view.AdaptableResolution;
import view.Sound;
import view.arena.SpaceshipSelectionUIController;
import view.image.Loader;
import view.image.ViewComponentUtilities;

/**
 * The Controller related to the mainMenu.fxml GUI.
 */
public final class MainMenuUIController implements AdaptableResolution {

    // X and Y positions of the backgroundImage in percentage relative to the stage.
    private static final double BACKGROUND_IMG_X = 50;
    private static final double BACKGROUND_IMG_Y = 50;

    // X and Y positions of the startButton image in percentage relative to the stage.
    private static final double START_BTN_X = 50;
    private static final double START_BTN_Y = 40;

    // X and Y positions of the leaderboardButton image in percentage relative to the stage.
    private static final double LEADERBOARD_BTN_X = 50;
    private static final double LEADERBOARD_BTN_Y = 55;

    // X and Y positions of the optionsButton image in percentage relative to the stage.
    private static final double OPTIONS_BTN_X = 50;
    private static final double OPTIONS_BTN_Y = 70;

    // X and Y positions of the quitButton image in percentage relative to the stage.
    private static final double QUIT_BTN_X = 50;
    private static final double QUIT_BTN_Y = 85;

    @FXML
    private Pane panel;

    @FXML
    private Rectangle background;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private ImageView startButton;

    @FXML
    private ImageView leaderboardButton;

    @FXML
    private ImageView optionsButton;

    @FXML
    private ImageView quitButton;

    @FXML
    private void startButtonClicked() throws IOException {
        ((SpaceshipSelectionUIController) Loader.loadFXML("layouts/spaceshipSelection.fxml").getController()).draw();
    }

    @FXML
    private void leaderboardButtonClicked() throws IOException {
        ((LeaderboardUIController) Loader.loadFXML("layouts/leaderboard.fxml").getController()).draw();
    }

    @FXML
    private void optionsButtonClicked() throws IOException {
        ((OptionsMenuUIController) Loader.loadFXML("layouts/optionsMenu.fxml").getController()).draw();
    }

    @FXML
    private void quitButtonClicked() throws IOException {
        ((QuitMenuUIController) Loader.loadFXML("layouts/quitMenu.fxml").getController()).draw();
    }

    @FXML
    private void buttonMouseEntered() {
        Sound.play("mouseOver");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        ViewComponentUtilities.resizeAndReposition(panel, background);
        ViewComponentUtilities.resizeAndReposition(backgroundImage, BACKGROUND_IMG_X, BACKGROUND_IMG_Y);
        ViewComponentUtilities.resizeAndReposition(startButton, START_BTN_X, START_BTN_Y);
        ViewComponentUtilities.resizeAndReposition(leaderboardButton, LEADERBOARD_BTN_X, LEADERBOARD_BTN_Y);
        ViewComponentUtilities.resizeAndReposition(optionsButton, OPTIONS_BTN_X, OPTIONS_BTN_Y);
        ViewComponentUtilities.resizeAndReposition(quitButton, QUIT_BTN_X, QUIT_BTN_Y);
        Sound.changeMusic("menuMusic");
    }

}
