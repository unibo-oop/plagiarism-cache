package zombieversity.view;

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
import javafx.stage.Stage;
import zombieversity.controller.core.GameState;

/**
 * 
 * Define the main menu view.
 *
 */
public class MenuView {

    private static final int HEIGHT = 650;
    private static final int WIDTH = 850;

    private static final int TUTORIAL_LAYOUTX = 10;
    private static final int TUTORIAL_LAYOUTY = 590;
    private static final int TUTORIAL_WIDTH = 600;
    private static final int TUTORIAL_HEIGHT = 400;

    private static final int PLAY_LAYOUTX = 310;
    private static final int PLAY_LAYOUTY = 500;

    private static final int SOUND_WIDTH = 30;
    private static final int SOUND_HEIGHT = 30;
    private static final int SOUND_LAYOUTX = 650;
    private static final int SOUND_LAYOUTY = 590;

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
     * 
     * @return the scene.
     */
    public final Scene getMenuScene() {
        return this.scene;
    }

    private void createBackGround() {
        final Image backgroundImage = new Image(getClass().getResourceAsStream("/backgroundMenu.png"), WIDTH, HEIGHT,
                false, true);

        final BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);

        pane.setBackground(new Background(background));

    }

    private void createButton() {
        createPlayButton();
        createTutorialButton();
        createSoundButton();

    }

    private void createSoundButton() {
        final ImageView imgS = new ImageView(new Image(getClass().getResourceAsStream("/media/icon.png")));
        final ImageView imgNS = new ImageView(new Image(getClass().getResourceAsStream("/media/iconNotSelect.png")));
        imgS.setFitHeight(SOUND_HEIGHT);
        imgS.setFitWidth(SOUND_WIDTH);
        imgNS.setFitHeight(SOUND_HEIGHT);
        imgNS.setFitWidth(SOUND_WIDTH);

        final MenuButton menu = new MenuButton("");
        menu.setGraphic(imgS);
        menu.setLayoutY(SOUND_LAYOUTY);
        menu.setLayoutX(SOUND_LAYOUTX);
        this.pane.getChildren().add(menu);

        menu.setOnAction(new EventHandler<>() {

            @Override
            public void handle(final ActionEvent event) {
                if (GameState.soundOff) {
                    menu.setGraphic(imgS);
                } else {
                    menu.setGraphic(imgNS);
                }
                GameState.soundOff = !GameState.soundOff;
            }

        });
    }

    private void createTutorialButton() {
        final MenuButton button = new MenuButton("Tutorial");
        button.setLayoutX(TUTORIAL_LAYOUTX);
        button.setLayoutY(TUTORIAL_LAYOUTY);
        pane.getChildren().add(button);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                final AnchorPane pane = new AnchorPane();
                final Stage stage = new Stage();
                final Scene scene = new Scene(pane, TUTORIAL_WIDTH, TUTORIAL_HEIGHT);
                stage.setResizable(false);
                stage.setScene(scene);
                final Image image;

                image = new Image(getClass().getResourceAsStream("/tutorial.png"), TUTORIAL_WIDTH, TUTORIAL_HEIGHT,
                        false, true);
                final BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.REPEAT,
                        BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
                pane.setBackground(new Background(background));
                stage.show();
            }

        });
    }

    private void createPlayButton() {
        final MenuButton playButton = new MenuButton("PLAY");
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
