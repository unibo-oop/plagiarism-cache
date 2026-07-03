package com.jlearn.controller;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.jlearn.view.FXEnvironment;
import com.jlearn.view.screens.ExerciseScreenController;
import com.jlearn.view.screens.MenuScreenController;
import com.jlearn.view.screens.StatisticsScreenController;
import com.jlearn.view.screens.TheoryScreenController;
import com.jlearn.view.voice_recognition.VoiceRecognition;
import com.sun.javafx.application.PlatformImpl;

import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Class main.
 *
 */
public final class Main {

    private static final Logger LOG = Logger.getLogger(Main.class);

    private Main() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the args
     * @throws IOException
     *             The exception
     */
    public static void main(final String[] args) throws IOException {
        PlatformImpl.startup(() -> {
        });
        final VoiceRecognition recog = VoiceRecognition.getInstance();
        // ############################## ENVIRONMENT ################################
        final FXEnvironment environment = FXEnvironment.getInstance();

        // ########################### EXERCISE AND THEORY ##############################

        final ControllerExercise controllerExercise = ControllerExerciseImpl.getInstance();
        final ExerciseScreenController esScreenCtrl = ExerciseScreenController.getInstance();
        controllerExercise.attacheExerciseUI(esScreenCtrl);
        final TheoryScreenController thScreenCtrl = TheoryScreenController.getInstance();
        controllerExercise.attacheTheoryUI(thScreenCtrl);

        // ############################ MENU AND STATISTICS ###########################
        final ControllerLogic controllerLogic = ControllerLogicImpl.getInstance();
        final StatisticsScreenController statsScreenCtrl = StatisticsScreenController.getInstance();
        controllerLogic.attacheStatisticsUI(statsScreenCtrl);
        final MenuScreenController menuScreenCtrl = MenuScreenController.getInstance();
        controllerLogic.attacheMenuUI(menuScreenCtrl);

        // NEW TRHEAD FOR JAVAFX
        Platform.runLater(() -> {
            try {
                final Stage primaryStage = new Stage(StageStyle.UNDECORATED);
                primaryStage.setTitle("JLearn");
                environment.start(primaryStage);
            } catch (final Exception e) {
                System.out.println("Unable to load graphic environment.");
                e.printStackTrace();
            }
            menuScreenCtrl.show();
        });
        LOG.debug("Stage lauched");
        recog.getClass();
    }
}
