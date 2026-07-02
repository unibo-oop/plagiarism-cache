package boxhead.view;

import boxhead.controller.game.GameState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

/**
 * Class to represent graphic menu'.
 */
public class MenuView {

    private static final int HEIGHT = 480;
    private static final int WIDTH = 928;

    private static final int PLAY_LAYOUTX = 400;
    private static final int PLAY_LAYOUTY = 400;

    private static final int SOUND_WIDTH = 25;
    private static final int SOUND_HEIGHT = 25;
    private static final int SOUND_LAYOUTX = 700;
    private static final int SOUND_LAYOUTY = 50;

    private final AnchorPane pane;
    private final Scene scene;

    /**
     * Construct a {@link MenuView}.
     */
    public MenuView() {
        this.pane = new AnchorPane();
        this.scene = new Scene(pane, WIDTH, HEIGHT);
        createBackGround();
        createButton();
    }

    /**
     * Method to retrieve Menu's Scene.
     * @return the scene.
     */
    public final Scene getMenuScene() {
        return this.scene;
    }

    /**
     * Inner method to create the Background.
     */
    private void createBackGround() {
        final Image backgroundImage = new Image(getClass().getResourceAsStream("/backgroundMenu.png"), WIDTH, HEIGHT,
                false, true);

        final BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, 
        									 BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        pane.setBackground(new Background(background));
    }

    /**
     * Method to create the two Buttons.
     */
    private void createButton() {
        createPlayButton();
        createSoundButton();
    }

    /**
     * Method to create the Sound Button.
     */
    private void createSoundButton() {
        final ImageView imgS = new ImageView(new Image(getClass().getResourceAsStream("/media/unmute.png")));
        final ImageView imgNS = new ImageView(new Image(getClass().getResourceAsStream("/media/mute.png")));
        imgS.setFitHeight(SOUND_HEIGHT);
        imgS.setFitWidth(SOUND_WIDTH);
        imgNS.setFitHeight(SOUND_HEIGHT);
        imgNS.setFitWidth(SOUND_WIDTH);

        final BoxheadButton button = new BoxheadButton("");
        button.setGraphic(imgS);
        button.setLayoutY(SOUND_LAYOUTY);
        button.setLayoutX(SOUND_LAYOUTX);
        this.pane.getChildren().add(button);

        button.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(final ActionEvent event) {
                if (GameState.soundOff) {
                    button.setGraphic(imgS);
                } else {
                    button.setGraphic(imgNS);
                }
                GameState.soundOff = !GameState.soundOff;
            }
        });
    }

    /**
     * Method to create the Play Button.
     */
    private void createPlayButton() {
        final BoxheadButton playButton = new BoxheadButton("START");
        playButton.setLayoutX(PLAY_LAYOUTX);
        playButton.setLayoutY(PLAY_LAYOUTY);
        pane.getChildren().add(playButton);

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                GameState.state = GameState.GameStateEnum.GAME;
                GameState.change = true;
                GameState.init = true;
            }
        });
    }
}
