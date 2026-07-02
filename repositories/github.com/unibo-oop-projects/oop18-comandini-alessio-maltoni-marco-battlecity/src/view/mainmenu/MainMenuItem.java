package view.mainmenu;

import controller.file.FileController;
import controller.file.FileControllerImpl;
import enums.GameFont;
import enums.SceneImage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import view.JavaFXView;

/**
 * Class that create a new Main Menu Item whit a Tank image and a text.
 * Implemented in JavaFx. The class extends a JavaFX's class HBox.
 */
public final class MainMenuItem extends HBox {

    // Item Magic Numbers.
    private static final double ITEM_SPACING = JavaFXView.STAGE_DIMESNION / 40.0;
    private static final double ITEM_IMAGE_ROTATE = 90.0;
    private static final double ITEM_IMAGE_WIDTH = JavaFXView.STAGE_DIMESNION / 23.0;
    private static final double ITEM_IMAGE_HEIGHT = JavaFXView.STAGE_DIMESNION / 23.0;
    private static final double ITEM_TEXT_FONT_SIZE = JavaFXView.STAGE_DIMESNION / 30.0;
    private static final int DURATION_ANIMATIONS_MS = 70;

    // The ImageView for the tank icon image.
    private ImageView iv;
    // The string of the text to be displayed.
    private final String string;
    // The file controller.
    private final FileController fc;
    // Animation.
    private Timeline movingTank;
    private boolean flag;

    /**
     * Constructor of the Main Menu Item.
     * 
     * @param string the string of the text to be displayed.
     */
    public MainMenuItem(final String string) {
        super(ITEM_SPACING);
        this.string = string;
        fc = new FileControllerImpl();
        init();
    }

    /*
     * Method that initialize the menu item.
     */
    private void init() {
        setAlignment(Pos.CENTER_LEFT);
        // Set the image.
        iv = new ImageView(fc.getSceneImage(SceneImage.PLAYER_ICON));
        iv.setRotate(ITEM_IMAGE_ROTATE);
        iv.setFitWidth(ITEM_IMAGE_WIDTH);
        iv.setFitHeight(ITEM_IMAGE_HEIGHT);
        iv.setVisible(false);

        // Set the text.
        final Text text = new Text(string);
        text.setFont(fc.getFont(GameFont.PRESS_START));
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font-weight: bold; -fx-font-size: " + Double.toString(ITEM_TEXT_FONT_SIZE) + ";");

        // Add image and text.
        getChildren().addAll(iv, text);
        setAnimation();
    }

    /*
     * Method that sets the animation of the tank incon.
     */
    private void setAnimation() {
        movingTank = new Timeline();
        movingTank.setCycleCount(Timeline.INDEFINITE);
        movingTank.getKeyFrames().add(new KeyFrame(Duration.millis(DURATION_ANIMATIONS_MS), event -> {
            if (!flag) {
                iv.setImage(fc.getSceneImage(SceneImage.PLAYER_ICON_2));
                flag = true;
            } else {
                iv.setImage(fc.getSceneImage(SceneImage.PLAYER_ICON));
                flag = false;
            }
        }));
    }

    /**
     * Set the item active or non active.
     * 
     * @param active boolean parameter for the activation of the menu item. True =
     *               active, False = not active.
     */
    public void setActive(final boolean active) {
        if (active) {
            movingTank.play();
        } else {
            movingTank.play();
        }
        iv.setVisible(active);
    }

}
