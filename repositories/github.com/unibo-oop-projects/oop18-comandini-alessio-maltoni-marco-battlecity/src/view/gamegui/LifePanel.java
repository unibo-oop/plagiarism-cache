package view.gamegui;

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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import view.JavaFXView;

/**
 * Class that drawn the player's life remaining.
 */
public final class LifePanel extends GridPane {

    // Life panel magic numbers.
    private static final double IMAGE_WIDTH = JavaFXView.STAGE_DIMESNION / 27.0;
    private static final double IMAGE_HEIGHT = JavaFXView.STAGE_DIMESNION / 27.0;
    private static final double PANEL_H_GAP = 1.0;
    private static final double PANEL_V_GAP = 1.0;
    private static final double FONT_SIZE = JavaFXView.STAGE_DIMESNION / 35.0;
    private static final int REMAINING_LIVES = 2;

    // The file controller.
    private final FileController fc;
    // The player number
    private final int playerNumber;

    /**
     * Constructor method.
     * 
     * @param playerNumber the number of the player.
     */
    public LifePanel(final int playerNumber) {
        this.fc = new FileControllerImpl();
        this.playerNumber = playerNumber;
        init();
    }

    /*
     * Method that initialize the panel.
     */
    private void init() {
        createBackground();
        setAlignment(Pos.CENTER_RIGHT);
        setHgap(PANEL_H_GAP);
        setVgap(PANEL_V_GAP);
        setPlayerNumber();
        setLifeIconImage();
        setNLives();
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
     * Method that sets the number of the player in the panel.
     */
    private void setPlayerNumber() {
        final Text playerNumberText = new Text("I ");
        if (playerNumber == 2) {
            playerNumberText.setText("II");
        }
        playerNumberText.setFill(Color.BLACK);
        playerNumberText.setFont(fc.getFont(GameFont.PRESS_START));
        playerNumberText.setStyle("-fx-font-size: " + Double.toString(FONT_SIZE));
        add(playerNumberText, 0, 0);

        final Text p = new Text("P");
        p.setFill(Color.BLACK);
        p.setFont(fc.getFont(GameFont.PRESS_START));
        p.setStyle("-fx-font-size: " + Double.toString(FONT_SIZE));
        add(p, 1, 0);
    }

    /*
     * Method that set the image of the lives icon.
     */
    private void setLifeIconImage() {
        final ImageView iv = new ImageView(fc.getSceneImage(SceneImage.LIVES_ICON));
        iv.setFitWidth(IMAGE_WIDTH);
        iv.setFitHeight(IMAGE_HEIGHT);
        add(iv, 0, 1);
    }

    /*
     * Method that sets for the first time the number of remaining lives for the
     * player. By default 2.
     */
    private void setNLives() {
        final Text nLives = new Text(Integer.toString(REMAINING_LIVES));
        nLives.setFill(Color.BLACK);
        nLives.setFont(fc.getFont(GameFont.PRESS_START));
        nLives.setStyle("-fx-font-size: " + Double.toString(FONT_SIZE));
        add(nLives, 1, 1);
    }

    /**
     * Method that draw the life in the grid.
     * 
     * @param nLives number of remaining life.
     */
    public void drawNLives(final int nLives) {
        ((Text) getChildren().get(3)).setText(Integer.toString(nLives));
    }

}
