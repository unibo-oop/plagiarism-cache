package it.unibo.view.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.core.impl.GameEngineImpl;
import it.unibo.utilities.Constants;
import it.unibo.utilities.GameState;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * MainMenu represents the main menu of the game.
 */
public final class MainMenu extends StackPane {
    private final Stage gameStage;
    private static final int BORDER_WIDTH = 5;
    private static final int SPACING_HBOX = 20;
    private static final int CONTINUE_BUTTON_WIDTH = 125;
    private static final int CONTINUE_BUTTON_HEIGHT = 50;
    private static final int QUIT_BUTTON_WIDTH = 125;
    private static final int QUIT_BUTTON_HEIGHT = 50;
    private static final int HOME_BUTTON_WIDTH = 125;
    private static final int HOME_BUTTON_HEIGHT = 50;
    private static final Logger LOGGER = Logger.getLogger(GameEngineImpl.class.getName());

    /**
     * Constructs a MainMenu.
     * 
     * @param gameStage the stage of the game
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public MainMenu(final Stage gameStage) {
        ImageView pauseButton;
        this.gameStage = gameStage;
        pauseButton = new ImageView("pauseButton.png");
        pauseButton.setFitWidth(Constants.Button.WIDTH_PAUSE_BUTTON);
        pauseButton.setFitHeight(Constants.Button.HEIGHT_PAUSE_BUTTON);
        pauseButton.setOnMouseClicked(this::handlePauseButtonClick);
        getChildren().add(pauseButton);
    }

    /**
     * Handles the click event on the pause button.
     *
     * @param event the mouse event
     */
    @SuppressWarnings("unused")
    private void handlePauseButtonClick(final MouseEvent event) {
        GameState.setGameState(GameState.PAUSED);
        final AnotherStage secondStage = new AnotherStage(gameStage);
        final Scene secondScene = secondStage.getScene();
        secondStage.initOwner(getScene().getWindow());
        secondStage.setScene(secondScene);
        secondStage.show();
    }

    /**
     * AnotherStage represents the pause menu stage.
     */
    public static final class AnotherStage extends Stage {
        private static final int WIDTH = 500;
        private static final int HEIGHT = 400;
        private static final String BACKGROUND = "pauseMenu.png";
        private static final String TOP_IMAGE = "pauseMenuWord.png";
        private static final String UNDER_IMAGE = "underImage.png";
        @SuppressWarnings("unused")
        private final Stage gameStage;

        /**
         * Constructs an AnotherStage.
         * 
         * @param gameStage the stage of the game
         */
        @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
        public AnotherStage(final Stage gameStage) {
            this.gameStage = gameStage;
            initStyle(StageStyle.UNDECORATED);
            final Image backgroundMainMenu = new Image(BACKGROUND);
            final Image topImage = new Image(TOP_IMAGE);
            final Image underImage = new Image(UNDER_IMAGE);
            final StackPane root = new StackPane();
            final BackgroundSize backgroundSize = new BackgroundSize(WIDTH, HEIGHT, false, false, false, false);
            final BackgroundImage backgroundImage = new BackgroundImage(
                    backgroundMainMenu,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    backgroundSize);
            root.setBackground(new Background(backgroundImage));

            root.setBorder(new Border(new BorderStroke(
                    Color.RED,
                    BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY,
                    new BorderWidths(BORDER_WIDTH))));

            final ImageView topImageView = new ImageView(topImage);
            topImageView.setFitWidth(Constants.Button.TOP_IMAGE_WIDTH);
            topImageView.setFitHeight(Constants.Button.TOP_IMAGE_HEIGHT);
            StackPane.setAlignment(topImageView, Pos.TOP_CENTER);
            root.getChildren().add(topImageView);
            final ImageView underImageView = new ImageView(underImage);
            underImageView.setFitHeight(Constants.Button.UNDER_IMAGE_HEIGHT);
            underImageView.setFitWidth(Constants.Button.UNDER_IMAGE_WIDTH);
            StackPane.setAlignment(underImageView, Pos.BOTTOM_CENTER);
            root.getChildren().add(underImageView);
            final HBox pane = new HBox(SPACING_HBOX);
            pane.setAlignment(Pos.CENTER);
            final ImageView continueButton = new ImageView(new Image("resumeB.png"));
            final ImageView quitButton = new ImageView(new Image("quitB.png"));
            final ImageView homeButton = new ImageView(new Image("homeB.png"));
            continueButton.setFitHeight(CONTINUE_BUTTON_HEIGHT);
            continueButton.setFitWidth(CONTINUE_BUTTON_WIDTH);
            quitButton.setFitHeight(QUIT_BUTTON_HEIGHT);
            quitButton.setFitWidth(QUIT_BUTTON_WIDTH);
            homeButton.setFitHeight(HOME_BUTTON_HEIGHT);
            homeButton.setFitWidth(HOME_BUTTON_WIDTH);
            continueButton.setOnMouseClicked(event -> {
                GameState.setGameState(GameState.PLAYING);
                close();
            });
            quitButton.setOnMouseClicked(event -> {
                this.gameStage.close();
                close();
                try {
                    GameState.setGameState(GameState.GAMEOVER);
                    new EndGameView().start(new Stage());
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error in quitButton", e);
                }
            });
            homeButton.setOnMouseClicked(event -> {
                this.gameStage.close();
                close();
                try {
                    GameState.setGameState(GameState.GAMEOVER);
                    new StartGameView().start(new Stage());
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error in homeButton", e);
                }
            });
            pane.getChildren().add(continueButton);
            pane.getChildren().add(quitButton);
            pane.getChildren().add(homeButton);
            root.getChildren().add(pane);
            setScene(new Scene(root, WIDTH, HEIGHT));
        }
    }
}
