package view.menu;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import view.AdaptableResolution;
import view.Sound;
import view.display.ScreenUtilities;
import view.image.ViewComponentUtilities;

/** 
 * The Controller related to the initialMenu.fxml GUI.
 */
public final class InitialMenuUIController implements AdaptableResolution {

    // X and Y positions of the backgorundImage in percentage relative to the stage.
    private static final double BACKGROUND_IMG_X = 50;
    private static final double BACKGROUND_IMG_Y = 50;

    // X and Y positions of the backgorundImage in percentage relative to the stage.
    private static final double OK_BTN_X = 50;
    private static final double OK_BTN_Y = 80;

    // X and Y positions of the backgorundImage in percentage relative to the stage.
    private static final double LABEL_X = 50;
    private static final double LABEL_Y = 70;

    @FXML
    private Rectangle background;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private ImageView okButton;

    @FXML
    private Label label;

    @FXML
    private void okButtonClicked() throws IOException {
        ((MainMenuUIController) view.image.Loader.loadFXML("layouts/mainMenu.fxml").getController()).draw();
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
        background.resize(ScreenUtilities.getCurrentWidth(), ScreenUtilities.getCurrentHeight());
        ViewComponentUtilities.resizeAndReposition(backgroundImage, BACKGROUND_IMG_X, BACKGROUND_IMG_Y);
        ViewComponentUtilities.resizeAndReposition(okButton, OK_BTN_X, OK_BTN_Y);

        label.setWrapText(true);
        label.setText("Resolution detected: " + (int) ScreenUtilities.getScreenWidth() + "x" + (int) ScreenUtilities.getScreenHeight() 
                + "    Scaling detected: " + ScreenUtilities.getScaling() + "%"
                + "    The game will start in: " + (int) ScreenUtilities.getCurrentWidth() + "x" + (int) ScreenUtilities.getCurrentHeight());
        ViewComponentUtilities.resizeAndReposition(label, LABEL_X, LABEL_Y);
    }

}
