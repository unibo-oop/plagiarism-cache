package view.gamegui;

import controller.file.FileController;
import controller.file.FileControllerImpl;
import enums.GameFont;
import enums.SceneImage;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import view.JavaFXView;

/**
 * This class model with JavaFX the number of the stage in the Game GUI Scene
 * with the flag icon. Extends the JavaFX's class AnchorPane.
 */
public final class StagePanel extends AnchorPane {

    // Stage panel magic numbers.
    private static final double FLAG_WIDTH = JavaFXView.STAGE_DIMESNION / 15.0;
    private static final double FLAG_HEIGHT = JavaFXView.STAGE_DIMESNION / 15.0;
    private static final double FLAG_LAYOUT_X = 0.0;
    private static final double FLAG_LAYOUT_Y = 0.0;
    private static final double FONT_SIZE = JavaFXView.STAGE_DIMESNION / 30.0;
    private static final double STAGE_NUMBER_LAYOUT_X = JavaFXView.STAGE_DIMESNION / 18.0;
    private static final double STAGE_NUMBER_LAYOUT_Y = JavaFXView.STAGE_DIMESNION / 11.0;

    // The file controller.
    private final FileController fc;
    // The player number
    private final int stageNumber;

    /**
     * Constructor method.
     * 
     * @param stageNumber the number of the stage.
     */
    public StagePanel(final int stageNumber) {
        this.fc = new FileControllerImpl();
        this.stageNumber = stageNumber;
        init();
    }

    /*
     * Method that initialize the panel.
     */
    private void init() {
        createBackground();
        createFlagIcon();
        createNumberOfStage();
    }

    /*
     * This method set the background.
     */
    private void createBackground() {
        final BackgroundFill backgroundFill = new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY);
        final Background background = new Background(backgroundFill);
        setBackground(background);
    }

    /*
     * Method that create the flag icon in the panel.
     */
    private void createFlagIcon() {
        final ImageView iv = new ImageView(fc.getSceneImage(SceneImage.FLAG_STAGE));
        iv.setFitWidth(FLAG_WIDTH);
        iv.setFitHeight(FLAG_HEIGHT);
        iv.setLayoutX(FLAG_LAYOUT_X);
        iv.setLayoutY(FLAG_LAYOUT_Y);
        this.getChildren().add(iv);
    }

    /*
     * Method that create the number of the stage.
     */
    private void createNumberOfStage() {
        final Text nStage = new Text(Integer.toString(stageNumber));
        nStage.setFill(Color.BLACK);
        nStage.setFont(fc.getFont(GameFont.PRESS_START));
        nStage.setStyle("-fx-font-size: " + Double.toString(FONT_SIZE));
        nStage.setLayoutX(STAGE_NUMBER_LAYOUT_X);
        nStage.setLayoutY(STAGE_NUMBER_LAYOUT_Y);
        this.getChildren().add(nStage);
    }

}
