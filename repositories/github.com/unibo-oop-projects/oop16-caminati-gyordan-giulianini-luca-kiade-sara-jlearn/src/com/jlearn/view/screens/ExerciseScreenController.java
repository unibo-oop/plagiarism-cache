package com.jlearn.view.screens;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jlearn.controller.ControllerExercise;
import com.jlearn.controller.ControllerExerciseImpl;
import com.jlearn.controller.timer.TimerEventImpl;
import com.jlearn.view.FXEnvironment;
import com.jlearn.view.screens.abstracts.AbstractExerciseScreenController;
import com.jlearn.view.utilities.ExerciseListViewOperations;
import com.jlearn.view.utilities.QuestionToView;
import com.jlearn.view.utilities.enums.DialogsType.DimDialogs;
import com.jlearn.view.utilities.enums.ExerciseType;
import com.jlearn.view.utilities.enums.NotificationType;
import com.jlearn.view.utilities.enums.NotificationType.Duration;
import com.jlearn.view.utilities.enums.SoundFX;

import eu.hansolo.enzo.lcd.Lcd;
import eu.hansolo.enzo.notification.NotificationEvent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * Exercise controller for the Exercise.
 * <p>
 * This class represent a symple controller for a {@link FXMLScreens}. The fxml is atached to the controller by the
 * {@link FXEnvironment} and chached by the {@link FXMLLoader}.
 * <p>
 * This controller is connected to a {@link ControllerExercise} to implement a MVC design.
 *
 */
public final class ExerciseScreenController extends AbstractExerciseScreenController {

    // ################################################ FXML VAR ###############################################
    @FXML
    private JFXButton doneButton;
    @FXML
    private JFXListView<BorderPane> trueFalseListView, compListView, audioListView, multiListView;
    @FXML
    private JFXRadioButton radioButtonDrawer;
    @FXML
    private Lcd timer;

    // ################################################ LOCAL VAR ###############################################
    private final FXEnvironment environment;
    private final ControllerExercise controller;
    private static ExerciseScreenController singleton;

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
    private ExerciseScreenController() {
        super(FXEnvironment.getInstance());
        this.environment = FXEnvironment.getInstance();
        this.environment.loadScreen(FXMLScreens.EXERCISE, this);
        this.controller = ControllerExerciseImpl.getInstance();
    }

    /**
     * Get the {@link ExerciseScreenController} instance.
     *
     * @return the {@link ExerciseScreenController}
     */
    public static ExerciseScreenController getInstance() {
        synchronized (ExerciseScreenController.class) {
            if (singleton == null) {
                singleton = new ExerciseScreenController();
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
    public void initialize() { // NOPMD
        super.initialize();
        this.onActionDrawerListView();
    }

    // ################################################ TO CONTROLLER ###############################################

    private void onActionDrawerListView() {
        this.getDrawerListView().setOnMouseClicked(t -> {
            this.playAudio(SoundFX.SELECT_LVL);
            if (this.getDrawerListView().getSelectionModel().getSelectedIndex() == -1) {
                return;
            }
            final String tmp = this.getDrawerListView().getSelectionModel().getSelectedItem().getText();

            if (this.controller.moduleSelected(this.getDrawerListView().getSelectionModel().getSelectedIndex())) {
                this.environment.showNotificationPopup("Level", "Selected: " + tmp, Duration.MEDIUM,
                        NotificationType.INFO,
                        null);
                this.radioButtonDrawer.setText(tmp);
                this.getDrawerListView().setDisable(true);
                this.doneButton.setDisable(false);
            }

        });
    }

    @FXML
    private void sendDataToConroller(final ActionEvent event) { // NOPMD
        this.playAudio(SoundFX.SELECT_LVL);
        final Map<ExerciseType, List<?>> mappaOut = new HashMap<>();
        mappaOut.put(ExerciseType.TRUE_FALSE,
                ExerciseListViewOperations.readListView(this.trueFalseListView, ExerciseType.TRUE_FALSE));
        mappaOut.put(ExerciseType.COMPLETE,
                ExerciseListViewOperations.readListView(this.compListView, ExerciseType.COMPLETE));
        mappaOut.put(ExerciseType.MULTI,
                ExerciseListViewOperations.readListView(this.multiListView, ExerciseType.MULTI));
        mappaOut.put(ExerciseType.AUDIO,
                ExerciseListViewOperations.readListView(this.audioListView, ExerciseType.AUDIO));
        this.colorError(this.controller.getViewModule(mappaOut));
    }

    @Override
    protected void pauseAudioExercise() {
        if (this.controller.pauseAudioExercise()) {
            this.environment.showNotificationPopup("STOP", "Audio stopped", Duration.MEDIUM, NotificationType.WARNING,
                    null);
        }
    }

    @Override
    protected void playPauseAudioExercise() {
        if (this.controller.playAudioExercise()) {
            this.environment.showNotificationPopup("PLAY", "Audio played", Duration.MEDIUM, NotificationType.INFO,
                    null);
        }

    }

    // ########################################## FROM CONTROLLER #############################################

    @Override
    public void updateTimerView(final TimerEventImpl ev) {
        Platform.runLater(() -> this.timer.setValue(ev.getValue()));
        if (ev.getValue() == 0) {
            Platform.runLater(() -> {
                this.environment.showDialog("GAME OVER", "Time is up", DimDialogs.MEDIUM,
                        null);
                this.environment.getAudioAgent(FXMLScreens.EXERCISE).pausePlayer();
                this.playAudio(SoundFX.GAME_OVER);
            });
        }
    }

    @Override
    public void showNotificationPopup(final String title, final String message, final Duration secondsDuration,
            final NotificationType notiType, final EventHandler<NotificationEvent> ev) {
        this.environment.showNotificationPopup(title, message, secondsDuration, notiType, ev);
    }

    @Override
    public void setViewModule(final QuestionToView dataOutToView) {
        this.addElemToListView(dataOutToView.getElemNumber());
        ExerciseListViewOperations.fillQuestionListView(this.trueFalseListView,
                dataOutToView.getMappaTextField().get(ExerciseType.TRUE_FALSE));
        ExerciseListViewOperations.fillQuestionListView(this.multiListView,
                dataOutToView.getMappaTextField().get(ExerciseType.MULTI));
        ExerciseListViewOperations.fillQuestionListView(this.compListView,
                dataOutToView.getMappaTextField().get(ExerciseType.COMPLETE));
        ExerciseListViewOperations.fillQuestionListView(this.audioListView,
                dataOutToView.getMappaTextField().get(ExerciseType.AUDIO));
        ExerciseListViewOperations.fillComboBoxListView(this.multiListView, dataOutToView.getListComboBoxFields());
    }

    @Override
    public void resetScreen() {

        this.getDrawerListView().setDisable(false);
        this.doneButton.setDisable(true);
        ExerciseListViewOperations.resetModule(this.multiListView, ExerciseType.MULTI);
        ExerciseListViewOperations.resetModule(this.compListView, ExerciseType.COMPLETE);
        ExerciseListViewOperations.resetModule(this.trueFalseListView, ExerciseType.TRUE_FALSE);
        ExerciseListViewOperations.resetModule(this.audioListView, ExerciseType.AUDIO);
    }

    @Override
    public void playAudio(final SoundFX sound) {
        this.environment.playAudioFXClip(sound);
    }

    @Override
    protected void notifyBack() {
        this.controller.notifyBack();
    }

}
