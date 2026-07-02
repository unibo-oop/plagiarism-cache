package view.gameover;

import controller.file.FileController;
import controller.file.FileControllerImpl;
import enums.SceneImage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.JavaFXView;

/**
 * This class create a Panel that display the message of the Game Over. The
 * class extends the JavaFX's class VBox.
 */
public final class GameOverPanel extends VBox {

    // Magic Numbers.
    private static final double PANEL_PREF_WIDTH = JavaFXView.STAGE_DIMESNION;
    private static final double PANEL_PREF_HEIGHT = JavaFXView.STAGE_DIMESNION;
    private static final double GAME_OVER_IMAGE_WIDTH = JavaFXView.STAGE_DIMESNION / 1.8;
    private static final double GAME_OVER_IMAGE_HEIGHT = JavaFXView.STAGE_DIMESNION / 3.0;

    // The file controller.
    private final FileController fc;

    /**
     * Constructor method.
     */
    public GameOverPanel() {
        fc = new FileControllerImpl();
        init();
    }

    /*
     * Method that initialize the Game over panel.
     */
    private void init() {
        setPrefSize(PANEL_PREF_WIDTH, PANEL_PREF_HEIGHT);
        setAlignment(Pos.CENTER);
        createBackground();
        setImage();
    }

    /*
     * Method that set the background of the Game Over Scene.
     */
    private void createBackground() {
        final BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        final Background background = new Background(backgroundFill);
        setBackground(background);
    }

    /*
     * Method that sets the image of the text "Game Over" in the panel.
     */
    private void setImage() {
        final ImageView iv = new ImageView(fc.getSceneImage(SceneImage.GAME_OVER));
        iv.setFitWidth(GAME_OVER_IMAGE_WIDTH);
        iv.setFitHeight(GAME_OVER_IMAGE_HEIGHT);
        // Add image and text.
        getChildren().addAll(iv);
    }

}
