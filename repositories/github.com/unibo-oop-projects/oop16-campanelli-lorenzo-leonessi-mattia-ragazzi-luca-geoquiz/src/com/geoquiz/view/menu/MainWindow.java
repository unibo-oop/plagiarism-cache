package com.geoquiz.view.menu;

import java.io.IOException;

import com.geoquiz.utility.ResourceLoader;
import com.geoquiz.view.utility.ScreenAdapter;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * Main window of game.
 */
public class MainWindow extends Application {

    private static final String FILE = "/images/image.jpg";

    private final SplashScreen splashScreen = new SplashScreen();
    private static final MediaPlayer GEOQUIZMUSIC = new MediaPlayer(
            new Media(MainWindow.class.getResource("/media/geoMusic.mp3").toString()));
    private static final MediaPlayer CLICK = new MediaPlayer(
            new Media(MainWindow.class.getResource("/media/click.wav").toExternalForm()));
    private static boolean soundOff;
    private static boolean clickOff;

    /**
     * @param primaryStage
     *            the principal stage.
     */
    public void start(final Stage primaryStage) {

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setWidth(ScreenAdapter.getScreenWidth());
        primaryStage.setHeight(ScreenAdapter.getScreenHeight());
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(ResourceLoader.loadResourceAsStream(FILE)));
        try {
            primaryStage.setScene(new LoginMenuScene(primaryStage));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        splashScreen.showWindow();
        final PauseTransition splashScreenDelay = new PauseTransition(Duration.seconds(2));
        splashScreenDelay.setOnFinished(e -> {
            primaryStage.show();
            splashScreen.hideWindow();
        });
        splashScreenDelay.playFromStart();
        GEOQUIZMUSIC.setVolume(GEOQUIZMUSIC.getVolume() / 2);
        GEOQUIZMUSIC.setCycleCount(MediaPlayer.INDEFINITE);
        GEOQUIZMUSIC.setAutoPlay(true);
    }

    /**
     * Method to stop background music.
     */
    public static void disableMusic() {
        soundOff = true;
        GEOQUIZMUSIC.stop();
    }

    /**
     * Method to resume background music.
     */
    public static void resumeMusic() {
        soundOff = false;
        GEOQUIZMUSIC.play();
    }

    /**
     * Method to play click sound.
     */
    public static void playClick() {
        clickOff = false;
        CLICK.stop();
        CLICK.play();
    }

    /**
     * Method to stop click sound.
     */
    public static void stopClick() {
        clickOff = true;
        CLICK.stop();
        CLICK.play();
    }

    /**
     * Method to know if music is disabled.
     * 
     * @return true if music is off, else true.
     */
    public static boolean isMusicDisabled() {
        return soundOff;
    }

    /**
     * Method to know if sound click is disabled.
     * 
     * @return true if sound click is off, else true.
     */
    public static boolean isWavDisabled() {
        return clickOff;
    }

}
