package view.pointsrecap;

import controller.file.FileController;
import controller.file.FileControllerImpl;
import enums.GameFont;
import enums.SceneImage;
import enums.Sprite;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import view.JavaFXView;

/**
 * Utility class for points recapitulation.
 */
public final class PointsRecapUtils {

    // Magic Numbers.
    private static final double FONT_SIZE = JavaFXView.STAGE_DIMESNION / 28.0;
    private static final double ARROW_WIDTH = JavaFXView.STAGE_DIMESNION / 24.0;
    private static final double ARROW_HEIGHT = JavaFXView.STAGE_DIMESNION / 24.0;
    private static final double TANK_WIDTH = JavaFXView.STAGE_DIMESNION / 19.0;
    private static final double TANK_HEIGHT = JavaFXView.STAGE_DIMESNION / 19.0;
    private static final double BAR_WIDTH = JavaFXView.STAGE_DIMESNION / 2.8;
    private static final double BAR_HEIGHT = JavaFXView.STAGE_DIMESNION / 100.0;
    private static final double LEFT_ARROW_ROTATE = 270.0;
    private static final double RIGHT_ARROW_ROTATE = 90.0;

    // The file controller.
    private static final FileController FILE_CONTROLLER = new FileControllerImpl();

    /*
     * Empty private constructor.
     */
    private PointsRecapUtils() {
    }

    /**
     * Method that returns the text formatted for the PointsRecap View.
     * 
     * @param string the string to format.
     * @param color  the color of the string.
     * @return the string formatted.
     */
    public static Text createText(final String string, final Color color) {
        final Text text = new Text(string);
        text.setFill(color);
        text.setFont(FILE_CONTROLLER.getFont(GameFont.PRESS_START));
        text.setStyle("-fx-font-size: " + Double.toString(FONT_SIZE));
        return text;
    }

    /**
     * Method that returns the arrow image view with left direction.
     * 
     * @return the arrow image view with left direction.
     */
    public static ImageView getLeftArrowImageView() {
        final ImageView iv = new ImageView(FILE_CONTROLLER.getSceneImage(SceneImage.ARROW_WHITE));
        iv.setRotate(LEFT_ARROW_ROTATE);
        iv.setFitWidth(ARROW_WIDTH);
        iv.setFitHeight(ARROW_HEIGHT);
        return iv;
    }

    /**
     * Method that returns the arrow image view with right direction.
     * 
     * @return the arrow image view with right direction.
     */
    public static ImageView getRightArrowImageView() {
        final ImageView iv = new ImageView(FILE_CONTROLLER.getSceneImage(SceneImage.ARROW_WHITE));
        iv.setRotate(RIGHT_ARROW_ROTATE);
        iv.setFitWidth(ARROW_WIDTH);
        iv.setFitHeight(ARROW_HEIGHT);
        iv.setVisible(false);
        return iv;
    }

    /**
     * Method that returns the normal tank image view with up direction.
     * 
     * @return the normal tank image view with up direction.
     */
    public static ImageView getNormalTankImageView() {
        final ImageView iv = new ImageView(FILE_CONTROLLER.getSprite(Sprite.ENEMY_NORMAL_TANK));
        iv.setFitWidth(TANK_WIDTH);
        iv.setFitHeight(TANK_HEIGHT);
        return iv;
    }

    /**
     * Method that returns the fast tank image view with up direction.
     * 
     * @return the fast tank image view with up direction.
     */
    public static ImageView getFastTankImageView() {
        final ImageView iv = new ImageView(FILE_CONTROLLER.getSprite(Sprite.ENEMY_FAST_TANK));
        iv.setFitWidth(TANK_WIDTH);
        iv.setFitHeight(TANK_HEIGHT);
        return iv;
    }

    /**
     * Method that returns the power tank image view with up direction.
     * 
     * @return the power tank image view with up direction.
     */
    public static ImageView getPowerTankImageView() {
        final ImageView iv = new ImageView(FILE_CONTROLLER.getSprite(Sprite.ENEMY_POWER_TANK));
        iv.setFitWidth(TANK_WIDTH);
        iv.setFitHeight(TANK_HEIGHT);
        return iv;
    }

    /**
     * Method that returns the armor tank image view with up direction.
     * 
     * @return the armor tank image view with up direction.
     */
    public static ImageView getArmorTankImageView() {
        final ImageView iv = new ImageView(FILE_CONTROLLER.getSprite(Sprite.ENEMY_ARMOR_TANK));
        iv.setFitWidth(TANK_WIDTH);
        iv.setFitHeight(TANK_HEIGHT);
        return iv;
    }

    /**
     * Method that returns the white bar for the total.
     * 
     * @return the white bar for the total.
     */
    public static ImageView getWhitebar() {
        final ImageView iv = new ImageView(FILE_CONTROLLER.getSceneImage(SceneImage.WHITE_BAR));
        iv.setFitWidth(BAR_WIDTH);
        iv.setFitHeight(BAR_HEIGHT);
        return iv;
    }

}
