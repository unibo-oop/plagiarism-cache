package com.jlearn.view;

import java.awt.Frame;
import java.io.IOException;
import java.util.Map;

import org.omg.CORBA.Environment;

import com.jfoenix.controls.JFXDialog;
import com.jlearn.controller.Main;
import com.jlearn.controller.sound.AudioAgent;
import com.jlearn.controller.sound.AudioAgentImpl;
import com.jlearn.view.screens.FXMLScreens;
import com.jlearn.view.utilities.ViewUtilities;
import com.jlearn.view.utilities.enums.DialogsType.DimDialogs;
import com.jlearn.view.utilities.enums.NotificationType;
import com.jlearn.view.utilities.enums.SoundFX;

import eu.hansolo.enzo.notification.Notification;
import eu.hansolo.enzo.notification.NotificationEvent;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import netscape.javascript.JSException;

/**
 * The {@link Environment}; this class if the core of the the framework for charging new screens. All the javafx
 * {@link Application} relie to this class, this is a only instace classs(singleton).
 * <p>
 * This environment has all the {@link Node} of the application: {@link Stage}, {@link Scene} and {@link StackPane}.
 *
 */
public final class FXEnvironment extends Application {
    private static final String AUDIO_PATH = "/audioFX/";
    // SINGLETON
    private static FXEnvironment singleton;
    // MINI FRAMEWORK
    private final ScreenAudioLoader audioLoader;
    private final ScreenLoader loader;
    // ENVIRONMENT
    private final StackPane mainPane;
    private final Scene mainScene;
    private Stage primaryStage;
    // TRANSITIONS
    private final FadeTransition fade;
    private final TranslateTransition translate;
    // MOVE APP
    private double xOffset;
    private double yOffset;

    /**
     * This return an instance of the environment, it builds so the Stage the Scene, charge all the sheets and set the
     * main pane.
     * <p>
     * Initiallize also a {@link ScreenLoader} that implements all the cache for the {@link Node}.
     *
     */

    private FXEnvironment() {
        // INIT ENVIRONMENT
        this.mainPane = new StackPane();
        this.mainScene = new Scene(this.mainPane);
        this.audioLoader = ScreenAudioLoader.getInstance();
        this.loader = ScreenLoader.getScreenLoader();
        // INIT FADE
        this.fade = new FadeTransition();
        this.translate = new TranslateTransition();
        this.setDrag();

        // SHEETS
        this.chargeSceneSheets(FXMLScreens.MENU);
        this.chargeSceneSheets(FXMLScreens.DRAWER);
        this.chargeSceneSheets(FXMLScreens.EXERCISE);
        this.chargeSceneSheets(FXMLScreens.DIALOG);
        this.chargeSceneSheets(FXMLScreens.RADIAL_MENU);
        this.chargeSceneSheets(FXMLScreens.EXERCISE_TAB);
        this.chargeSceneSheets(FXMLScreens.THEORY);
        this.chargeSceneSheets(FXMLScreens.STATISTICS);
        // AUDIO
        this.audioLoader.addAgentSong(FXMLScreens.STATISTICS,
                new AudioAgentImpl(AUDIO_PATH + SoundFX.STATISTICS.getPath()));
        this.audioLoader.addAgentSong(FXMLScreens.EXERCISE,
                new AudioAgentImpl(AUDIO_PATH + SoundFX.EXERCISE.getPath()));
        this.audioLoader.addAgentSong(FXMLScreens.THEORY, new AudioAgentImpl(AUDIO_PATH + SoundFX.THEORY.getPath()));
        this.audioLoader.addAgentSong(FXMLScreens.MENU, new AudioAgentImpl(AUDIO_PATH + SoundFX.MENU_ANTH.getPath()));
        // HANDLE ALL EXCEPTION THROUGH JAVAFX
        this.handleExceptions();
    }

    private void chargeSceneSheets(final FXMLScreens screen) {
        this.mainScene.getStylesheets().add(Main.class.getResource(screen.getCssPath()).toString());
    }

    /**
     * Get the {@link FXEnvironment} instance.
     *
     * @return the {@link FXEnvironment}
     */
    public static FXEnvironment getInstance() {
        synchronized (FXEnvironment.class) {
            if (singleton == null) {

                FXEnvironment.singleton = new FXEnvironment();
            }
        }
        return singleton;
    }

    /**
     * Play an audio FX.
     *
     * @param sound
     *            the {@link SoundFX}
     */
    public void playAudioFXClip(final SoundFX sound) {
        this.audioLoader.playLowLatencyAudio(sound);
    }

    /**
     * Get the {@link AudioAgent}.
     *
     * @param screen
     *            the {@link FXMLScreens}
     * @return the {@link AudioAgent}
     */
    public AudioAgent getAudioAgent(final FXMLScreens screen) {
        return this.audioLoader.getAgent(screen);
    }

    /**
     * Mute/Unmute the audio.
     */
    public void muteAudio() {
        this.audioLoader.muteAll();
    }

    /**
     * Set the volume.
     *
     * @param volume
     *            the double volume
     */
    public void setAudioVolume(final double volume) {
        this.audioLoader.setVolume(volume);
    }

    private void setDrag() {
        this.mainPane.setOnMousePressed(event -> {
            FXEnvironment.this.xOffset = event.getSceneX();
            FXEnvironment.this.yOffset = event.getSceneY();
        });
        this.mainPane.setOnMouseDragged(event -> {
            FXEnvironment.this.primaryStage.setX(event.getScreenX() - FXEnvironment.this.xOffset);
            FXEnvironment.this.primaryStage.setY(event.getScreenY() - FXEnvironment.this.yOffset);
        });
    }

    private void handleExceptions() {
        Platform.runLater(() -> {
            Thread.currentThread().setUncaughtExceptionHandler((t, e) -> {
                if (e.getClass().equals(JSException.class)) {
                    this.showNotificationPopup("Maps Error", "Check Internet Connection",
                            NotificationType.Duration.MEDIUM, NotificationType.WARNING, null);
                } else {
                    e.printStackTrace();
                }
            });
        });
    }

    /**
     * Show a {@link JFXDialog} into the main {@link Pane}.
     *
     * @param title
     *            the String title dialog
     * @param description
     *            the STring description
     * @param size
     *            the {@link DimDialogs} size
     * @param ev
     *            the {@link MouseEvent}
     */
    public void showDialog(final String title, final String description, final DimDialogs size,
            final EventHandler<? super MouseEvent> ev) {
        ViewUtilities.showDialog(this.mainPane, title, description, size, ev);
        this.playAudioFXClip(SoundFX.POP);
    }

    /**
     * Show a {@link Notification} popup into the main windows of the operating system.
     *
     * @param title
     *            the String title of the {@link Notification}
     * @param message
     *            the String text of the {@link Notification}
     * @param secondsDuration
     *            the number of {@link com.jlearn.view.utilities.enums.NotificationType.Duration} of the
     *            {@link Notification}
     * @param notiType
     *            the {@link NotificationType} of the {@link Notification}
     * @param ev
     *            the {@link EventHandler} ev, lalmba
     */
    public void showNotificationPopup(final String title, final String message,
            final NotificationType.Duration secondsDuration, final NotificationType notiType,
            final EventHandler<NotificationEvent> ev) {
        ViewUtilities.showNotificationPopup(title, message, secondsDuration, notiType, ev);
        this.playAudioFXClip(SoundFX.POP);
    }

    /**
     * Play the transition.
     */
    public void fadeTransition() {
        this.fade.setDuration(Duration.seconds(NotificationType.Duration.SHORTEST.getValue()));
        this.fade.setNode(this.mainPane);
        this.fade.setFromValue(0.5);
        this.fade.setToValue(1);
        Platform.runLater(() -> this.fade.play());
    }

    /**
     * Translate transition.
     */
    public void translateTransition() {
        this.translate.setDuration(Duration.seconds(NotificationType.Duration.SHORTEST.getValue()));
        this.translate.setNode(this.mainPane);
        this.translate.setFromX(this.primaryStage.getWidth());
        this.translate.setToX(0);
        Platform.runLater(() -> this.translate.play());
    }

    @Override
    public void start(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setScene(this.mainScene);
        this.primaryStage.setOnCloseRequest(e -> System.exit(0));

    }

    /**
     * The main {@link Frame} of the application.
     *
     * @return The main application {@link Frame}
     */
    public Stage getMainStage() {
        return this.primaryStage;
    }

    /**
     * The main {@link Scene} of the application.
     *
     * @return The main application {@link Scene}
     */
    public Scene getMainScene() {
        return this.mainScene;
    }

    /**
     * All the node are charged in a {@link Map}, this method return the {@link Node} stored.
     *
     * @param screen
     *            the {@link FXMLScreens}
     * @return the {@link Node} loaded
     */
    public Node getNode(final FXMLScreens screen) {
        return this.loader.getLoadedNode(screen);
    }

    /**
     * Display the main window.
     */
    public void show() {
        this.primaryStage.show();
    }

    /**
     * Display the {@link FXMLScreens} into this scene.
     *
     * @param screen
     *            the {@link FXMLScreens} to display
     */
    public void displayScreen(final FXMLScreens screen) {
        try {
            this.loader.loadScreen(screen, this.mainPane);
            this.show();
        } catch (final IOException e) {
            System.out.println("Unable to display screen " + screen);
            e.printStackTrace();
        }
        // AUDIO
        this.audioLoader.startAndPauseOtherPlayers(screen, false);
    }

    /**
     * Loads a {@link FXMLScreens} ad sets its controller.
     *
     * @param screen
     *            the {@link FXMLScreens}
     * @param controller
     *            the controller
     */
    public void loadScreen(final FXMLScreens screen, final Object controller) {
        try {
            this.loader.loadFXMLInCache(screen, controller);
        } catch (final IOException e) {
            System.out.println("Unable to load screen " + screen);
            e.printStackTrace();
        }
    }

}
