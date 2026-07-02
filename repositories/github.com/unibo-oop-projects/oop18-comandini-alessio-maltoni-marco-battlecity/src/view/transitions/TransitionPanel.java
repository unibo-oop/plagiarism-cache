package view.transitions;

import java.util.Arrays;

import controller.file.FileController;
import controller.file.FileControllerImpl;
import enums.GameFont;
import enums.SceneImage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import view.JavaFXView;

/**
 * This class create a Panel that display the message of the Transition between
 * two scenes. The class extends the JavaFX's class VBox.
 */
public final class TransitionPanel extends VBox {

    // Magic Numbers.
    private static final double TRANSITION_TEXT_FONT_SIZE = JavaFXView.STAGE_DIMESNION / 25.0;
    private static final double TRANSITION_PREF_WIDTH = JavaFXView.STAGE_DIMESNION;
    private static final double TRANSITION_PREF_HEIGHT = JavaFXView.STAGE_DIMESNION;
    private static final double TRANSITION_SPACING = JavaFXView.STAGE_DIMESNION / 3.0;
    private static final double H_SPACING = JavaFXView.STAGE_DIMESNION / 20.0;
    private static final double ARROW_WIDTH = JavaFXView.STAGE_DIMESNION / 20.0;
    private static final double ARROW_HIGHT = JavaFXView.STAGE_DIMESNION / 20.0;
    private static final double ARROW_ROTATE_LEFT = 270.0;
    private static final double ARROW_ROTATE_RIGHT = 90.0;

    // Contains the text to display.
    private String[] text;
    // The file controller.
    private final FileController fc;
    // Booleans for display the left/right arrow.
    private boolean left;
    private boolean right;

    /**
     * Constructor method.
     * 
     * @param left    boolean for display the left arrow.
     * @param right   boolean for display the right arrow.
     * @param strings the text to display at the center of the Scene. Line per line.
     */
    public TransitionPanel(final boolean left, final boolean right, final String... strings) {
        text = Arrays.copyOf(strings, strings.length);
        this.left = left;
        this.right = right;
        fc = new FileControllerImpl();
        init();
    }

    /*
     * Method that initialize the Menu Transition.
     */
    private void init() {
        if (text.length == 3) {
            this.setSpacing(TRANSITION_SPACING);
        }
        setPrefSize(TRANSITION_PREF_WIDTH, TRANSITION_PREF_HEIGHT);
        setAlignment(Pos.CENTER);
        createBackground();
        setText(left, right, text);
    }

    /*
     * Method that set the background of the Menu Transition.
     */
    private void createBackground() {
        final BackgroundFill backgroundFill = new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY);
        final Background background = new Background(backgroundFill);
        setBackground(background);
    }

    /**
     * Method that set the text shown at the center of the menu transition. The text
     * is shown line by line.
     * 
     * @param left    boolean for display the left arrow.
     * @param right   boolean for display the right arrow.
     * @param strings the text to display in the Scene. Line per line.
     */
    public void setText(final boolean left, final boolean right, final String... strings) {
        this.getChildren().clear();
        this.text = Arrays.copyOf(strings, strings.length);
        this.left = left;
        this.right = right;
        if (text.length == 3) {
            final HBox hBox = new HBox(H_SPACING);
            hBox.setAlignment(Pos.CENTER);
            final ImageView ivArrowLeft = createArrow(ARROW_ROTATE_LEFT);
            ivArrowLeft.setVisible(left);
            final Text firstString = createText(text[0], Color.BLACK);
            final ImageView ivArrowRight = createArrow(ARROW_ROTATE_RIGHT);
            ivArrowRight.setVisible(right);
            hBox.getChildren().addAll(ivArrowLeft, firstString, ivArrowRight);
            final Text secondString = createText(text[1], Color.BLACK);
            final Text thirdString = createText(text[2], Color.BLACK);
            this.getChildren().addAll(hBox, secondString, thirdString);
        } else {
            for (final String s : text) {
                final Text text = createText(s, Color.BLACK);
                getChildren().add(text);
            }
        }
    }

    /*
     * Method for create a formatted text for the panel.
     * 
     * @param string the string to format.
     * 
     * @param color the color of the text.
     * 
     * @return the text formatted.
     */
    private Text createText(final String string, final Color color) {
        final Text text = new Text(string);
        text.setFill(color);
        text.setFont(fc.getFont(GameFont.PRESS_START));
        text.setStyle("-fx-font-size: " + Double.toString(TRANSITION_TEXT_FONT_SIZE));
        return text;
    }

    /*
     * Method for create an image rotated of a black arrow.
     * 
     * @param rotate how much rotate the arrow.
     * 
     * @return the image view of the black arrow rotated.
     */
    private ImageView createArrow(final double rotate) {
        final ImageView ivArrow = new ImageView(fc.getSceneImage(SceneImage.ARROW_BLACK));
        ivArrow.setRotate(rotate);
        ivArrow.setFitWidth(ARROW_WIDTH);
        ivArrow.setFitHeight(ARROW_HIGHT);
        return ivArrow;
    }

}
