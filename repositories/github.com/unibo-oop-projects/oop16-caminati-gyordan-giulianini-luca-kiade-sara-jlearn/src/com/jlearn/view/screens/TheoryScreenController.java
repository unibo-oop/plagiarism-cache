package com.jlearn.view.screens;

import java.io.IOException;

import com.jlearn.controller.ControllerExercise;
import com.jlearn.controller.ControllerExerciseImpl;
import com.jlearn.view.FXEnvironment;
import com.jlearn.view.screens.abstracts.AbstractTheoryScreenController;
import com.jlearn.view.ui.UITheory;
import com.jlearn.view.utilities.enums.NotificationType;
import com.jlearn.view.utilities.enums.NotificationType.Duration;
import com.jlearn.view.utilities.enums.SoundFX;

import eu.hansolo.enzo.notification.NotificationEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 * Theory controller for the Theory.
 * <p>
 * This class represent a symple controller for a {@link FXMLScreens}. The fxml is atached to the controller by the
 * {@link FXEnvironment} and chached by the {@link FXMLLoader}.
 * <p>
 * This controller is connected to a {@link ControllerExercise} to implement a MVC design.
 *
 */
public final class TheoryScreenController extends AbstractTheoryScreenController implements UITheory {
    // ################################################ FXML VAR ###############################################

    // ################################################ LOCAL VAR ###############################################
    private final FXEnvironment environment; // NOPMD
    private final ControllerExercise controller; // NOPMD
    private static TheoryScreenController singleton;

    /**
     * The constructor of the controller.
     *
     * @param environment
     *            the {@link FXEnvironment}
     * @param controller
     *            the {@link ControllerExercise}
     * @throws IOException
     *             the exception cause by the loading of the {@link FXMLLoader}
     */
    private TheoryScreenController() {
        super(FXEnvironment.getInstance());
        this.environment = FXEnvironment.getInstance();
        this.environment.loadScreen(FXMLScreens.THEORY, this);
        this.controller = ControllerExerciseImpl.getInstance();
        this.controller.toString();
    }

    /**
     * Get the {@link TheoryScreenController} instance.
     *
     * @return the {@link TheoryScreenController}
     */
    public static TheoryScreenController getInstance() {
        synchronized (TheoryScreenController.class) {
            if (singleton == null) {
                singleton = new TheoryScreenController();
            }
        }
        return singleton;
    }

    /**
     * Init method to be called after the constructor. This is a symple method of the {@link FXML}.
     * 
     */
    @Override
    @FXML
    public void initialize() {
        super.initialize();
    }

    @Override
    public void playAudio(final SoundFX sound) {
        this.environment.playAudioFXClip(sound);
    }
    // ################################################ FROM CONTROLLER ###############################################

    @Override
    public void showNotificationPopup(final String title, final String message, final Duration secondsDuration,
            final NotificationType notiType, final EventHandler<NotificationEvent> ev) {
        this.environment.showNotificationPopup(title, message, secondsDuration, notiType, ev);
    }

    @Override
    public void showPdf(final String url) {
        super.getEngine().load(url);
    }

    // ################################################ TO CONTROLLER ###############################################
    @Override
    public void loadPdf(final int level) {
        this.controller.loadPdf(level);

    }
}
