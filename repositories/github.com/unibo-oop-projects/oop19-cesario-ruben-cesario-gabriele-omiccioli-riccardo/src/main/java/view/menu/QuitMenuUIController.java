package view.menu;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import view.AdaptableResolution;
import view.Sound;
import view.image.ViewComponentUtilities;

/** 
 * The Controller related to the quitMenu.fxml GUI.
 */
public final class QuitMenuUIController implements AdaptableResolution {

    // X and Y positions of the backgorundImage in percentage relative to the stage.
    private static final double BACKGROUND_IMG_X = 50;
    private static final double BACKGROUND_IMG_Y = 50;

    // X and Y positions of the backButton image in percentage relative to the stage.
    private static final double BACK_BTN_X = 4;
    private static final double BACK_BTN_Y = 7;

    // X and Y positions of the quitText in percentage relative to the stage.
    private static final double TEXT_X = 50;
    private static final double TEXT_Y = 35;

    // X and Y positions of the yesButton in percentage relative to the stage.
    private static final double YES_BTN_X = 40;
    private static final double YES_BTN_Y = 50;

    // X and Y positions of the noButton in percentage relative to the stage.
    private static final double NO_BTN_X = 60;
    private static final double NO_BTN_Y = 50;

    @FXML
    private Pane panel;

    @FXML
    private Rectangle background;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private ImageView backButton;

    @FXML
    private ImageView quitText;

    @FXML
    private ImageView yesButton;

    @FXML
    private ImageView noButton;

    @FXML
    private void backButtonClicked() throws IOException {
            ((MainMenuUIController) view.image.Loader.loadFXML("layouts/mainMenu.fxml").getController()).draw();
    }

    @FXML
    private void noButtonClicked() throws IOException {
        ((MainMenuUIController) view.image.Loader.loadFXML("layouts/mainMenu.fxml").getController()).draw();
    }

    @FXML
    private void yesButtonClicked() {
        System.exit(0);
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
        ViewComponentUtilities.resizeAndReposition(backButton, BACK_BTN_X, BACK_BTN_Y);
        ViewComponentUtilities.resizeAndReposition(quitText, TEXT_X, TEXT_Y);
        ViewComponentUtilities.resizeAndReposition(yesButton, YES_BTN_X, YES_BTN_Y);
        ViewComponentUtilities.resizeAndReposition(noButton, NO_BTN_X, NO_BTN_Y);
    }

}
