package it.unibo.view.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.utilities.GameState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class is used to show the view of the game when the player wins.
 */
public final class WinGameView extends Application {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private static final int IMAGE_WIDTH = 200;
    private static final int IMAGE_HEIGHT = 25;
    private static final double ANIMATION_DURATION_FIRST = 0.25;
    private static final double ANIMATION_DURATION_SECOND = 0.5;
    private static final double ANIMATION_DURATION_THIRD = 0.75;
    private static final int TRANSLATE_Y = 100;
    private static final Logger LOGGER = Logger.getLogger(WinGameView.class.getName());

    /**
     * This method is used to show the view of the game when the player wins.
     * 
     * @param primaryStage the stage of the game.
     * @throws Exception if an error occurs.
     */
    @Override
    public void start(final Stage primaryStage) throws IOException {
        GameState.setGameState(GameState.WIN);
        final StackPane root = new StackPane();

        final Image newBackgroundImage = new Image(getClass().getResourceAsStream("/winEndView.png"));
        final ImageView newBackgroundImageView = new ImageView(newBackgroundImage);
        newBackgroundImageView.setFitWidth(WIDTH);
        newBackgroundImageView.setFitHeight(HEIGHT);

        final Image image1 = new Image("fix.png");
        final Image image2 = new Image("fix2.png");
        final Image image3 = new Image("fix3.png");

        final ImageView imageView1 = new ImageView(image1);
        final ImageView imageView2 = new ImageView(image2);
        final ImageView imageView3 = new ImageView(image3);

        imageView1.setFitWidth(IMAGE_WIDTH);
        imageView1.setFitHeight(IMAGE_HEIGHT);
        imageView2.setFitWidth(IMAGE_WIDTH);
        imageView2.setFitHeight(IMAGE_HEIGHT);
        imageView3.setFitWidth(IMAGE_WIDTH);
        imageView3.setFitHeight(IMAGE_HEIGHT);

        imageView1.setVisible(false);
        imageView2.setVisible(false);
        imageView3.setVisible(false);

        final StackPane imagesStack = new StackPane();
        imagesStack.getChildren().addAll(imageView1, imageView2, imageView3);

        final VBox topLayout = new VBox();
        topLayout.getChildren().add(imagesStack);
        topLayout.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        topLayout.setTranslateY(TRANSLATE_Y);
        root.getChildren().addAll(newBackgroundImageView, topLayout);

        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(ANIMATION_DURATION_FIRST), event -> {
                    imageView1.setVisible(true);
                    imageView2.setVisible(false);
                    imageView3.setVisible(false);
                }),
                new KeyFrame(Duration.seconds(ANIMATION_DURATION_SECOND), event -> {
                    imageView1.setVisible(false);
                    imageView2.setVisible(true);
                    imageView3.setVisible(false);
                }),
                new KeyFrame(Duration.seconds(ANIMATION_DURATION_THIRD), event -> {
                    imageView1.setVisible(false);
                    imageView2.setVisible(false);
                    imageView3.setVisible(true);
                }),
                new KeyFrame(Duration.seconds(1), event -> {
                }));

        timeline.setCycleCount(10);
        timeline.setOnFinished(event -> {
            try {
                final Stage currentStage = (Stage) root.getScene().getWindow();
                new StartGameView().start(new Stage());
                GameState.setGameState(GameState.HOME);
                currentStage.close();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An error occurred", e);
            }
        });

        timeline.play();

        final Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.setTitle("WinGame");
        primaryStage.show();
    }
}
