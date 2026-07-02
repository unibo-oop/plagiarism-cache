package it.unibo.view.impl;

import it.unibo.utilities.GameState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.model.impl.PointsComponent;

/**
 * EndGame represents the end game screen.
 * This class provides a simple "Game Over" message.
 */
public final class EndGameView extends Application {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private static final int IMAGE1_HEIGHT = 175;
    private static final int IMAGE1_WIDTH = 400;
    private static final int IMAGE2_HEIGHT = 175;
    private static final int IMAGE2_WIDTH = 400;
    private static final double DURATION = 0.5;
    private static final int CYCLE_COUNT = 10;
    private static final int SPACING = 20;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 75;
    private static final double LEFT_ANCHOR = 10.0;
    private static final double TOP_ANCHOR = 10.0;
    private static final int BUTTON_WIDTH = 125;
    private static final int BUTTON_HEIGHT = 50;
    private static final int TRANSLATE_Y = 50;
    private static final Logger LOGGER = Logger.getLogger(EndGameView.class.getName());

    /**
     * Starts the end game screen.
     *
     * @param primaryStage the primary stage for the application
     * @throws Exception if an error occurs during startup
     */
    @Override
    public void start(final Stage primaryStage) throws IOException {
        GameState.setGameState(GameState.GAMEOVER);
        primaryStage.setResizable(false);

        final ImageView background = new ImageView(new Image("losegame1.png"));
        background.setFitWidth(WIDTH);
        background.setFitHeight(HEIGHT);

        final ImageView imageView1 = new ImageView(new Image("gameOver3.png"));
        imageView1.setFitWidth(IMAGE1_WIDTH);
        imageView1.setFitHeight(IMAGE1_HEIGHT);

        final ImageView imageView2 = new ImageView(new Image("gameOver2.png"));
        imageView2.setFitWidth(IMAGE2_WIDTH);
        imageView2.setFitHeight(IMAGE2_HEIGHT);

        final StackPane root = new StackPane();
        root.getChildren().addAll(background, imageView1);

        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.setTitle("GAME OVER");
        primaryStage.show();

        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(DURATION), event -> {
                    if (root.getChildren().contains(imageView1)) {
                        root.getChildren().remove(imageView1);
                        root.getChildren().add(imageView2);
                    } else {
                        root.getChildren().remove(imageView2);
                        root.getChildren().add(imageView1);
                    }
                }));

        timeline.setCycleCount(CYCLE_COUNT);
        timeline.setOnFinished(event -> {
            root.getChildren().remove(imageView1);
            root.getChildren().remove(imageView2);
            showButtonsAndPoints(root, primaryStage);
        });
        timeline.play();
    }

    /**
     * Shows the buttons and points section after the timeline finishes.
     *
     * @param root         the root StackPane
     * @param primaryStage the primary stage of the application
     */
    private void showButtonsAndPoints(final StackPane root, final Stage primaryStage) {
        final PointsComponent pointsComponent = new PointsComponent();
        final HighPointsView highPointsView = new HighPointsView(pointsComponent);

        highPointsView.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: yellow;"
                        + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75), 10, 0.5, 0, 0);");
        highPointsView.updateLabel();

        final HBox pointsBox = new HBox(SPACING, highPointsView);
        pointsBox.setAlignment(Pos.CENTER);

        final AnchorPane pointsPane = new AnchorPane();
        pointsPane.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-border-width: 3;");
        pointsPane.setMaxWidth(WINDOW_WIDTH);
        pointsPane.setMaxHeight(WINDOW_HEIGHT);
        pointsPane.setMinWidth(WINDOW_WIDTH);
        pointsPane.setMinHeight(WINDOW_HEIGHT);

        AnchorPane.setTopAnchor(pointsBox, TOP_ANCHOR);
        AnchorPane.setLeftAnchor(pointsBox, LEFT_ANCHOR);
        pointsPane.getChildren().add(pointsBox);

        final ImageView button1 = new ImageView(new Image("homeB.png"));
        button1.setFitWidth(BUTTON_WIDTH);
        button1.setFitHeight(BUTTON_HEIGHT);
        button1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                primaryStage.close(); 
                new StartGameView().start(new Stage());
                GameState.setGameState(GameState.HOME);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An error occurred", e);
            }
        });

        final ImageView button2 = new ImageView(new Image("quitB.png"));
        button2.setFitWidth(BUTTON_WIDTH);
        button2.setFitHeight(BUTTON_HEIGHT);
        button2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            primaryStage.close(); 
        });

        final HBox buttonBox = new HBox(SPACING, button1, button2);
        buttonBox.setAlignment(Pos.CENTER);

        final VBox vbox = new VBox(SPACING, pointsPane, buttonBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setTranslateY(TRANSLATE_Y);

        StackPane.setAlignment(vbox, Pos.CENTER);

        root.getChildren().add(vbox);
    }
}
