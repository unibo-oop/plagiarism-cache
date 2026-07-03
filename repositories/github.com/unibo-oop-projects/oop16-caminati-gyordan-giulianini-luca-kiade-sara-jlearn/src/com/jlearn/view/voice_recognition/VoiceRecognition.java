package com.jlearn.view.voice_recognition;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.jlearn.controller.ControllerExercise;
import com.jlearn.view.screens.MenuScreenController;
import com.jlearn.view.utilities.ViewUtilities;
import com.jlearn.view.utilities.enums.NotificationType;
import com.jlearn.view.utilities.enums.NotificationType.Duration;
import com.jlearn.view.utilities.enums.SoundFX;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import javafx.application.Platform;

/**
 * Class for {@link VoiceRecognition}. Singleton pattern that allows to attache a lot of observer in a very simple way.
 * <p>
 * The {@link VoiceRecognition} is not so accurate, this is only an experiment for now.
 */
public final class VoiceRecognition implements ESource<String> { // NOPMD //DEVO SECCARE I LOG DELLA LIBRERIA
    private static final Logger LOG = Logger.getLogger(VoiceRecognition.class);
    private static VoiceRecognition singleton;
    private final Set<EObserver<? super String>> observer;
    // Threads
    private Thread speechThread;
    private String result;
    // LiveRecognizer
    private LiveSpeechRecognizer recognizer;
    private boolean recognize;
    private final Object waiter = new Object();
    private final java.util.logging.Logger cmRootLogger = java.util.logging.Logger.getLogger("default.config");

    private VoiceRecognition() {
        this.observer = new HashSet<>();
        this.cmRootLogger.setLevel(java.util.logging.Level.OFF);
        try {
            // Configuration
            final Configuration configuration = new Configuration();
            // Load model from the jar
            configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
            configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
            // Grammar
            configuration.setGrammarPath("resource:/grammars");
            configuration.setGrammarName("grammar");
            configuration.setUseGrammar(true);

            this.recognizer = new LiveSpeechRecognizer(configuration);
            this.recognizer.startRecognition(true);
            this.startSpeechThread();
        } catch (final Exception e) {
            Platform.runLater(() -> ViewUtilities.showNotificationPopup("Error: MIC", "No mic found", Duration.MEDIUM,
                    NotificationType.ERROR, null));
        }
    }

    /**
     * Get the {@link VoiceRecognition} instance.
     *
     * @return The only instance of {@link ControllerExercise}
     */
    public static VoiceRecognition getInstance() {

        synchronized (VoiceRecognition.class) {
            if (singleton == null) {
                singleton = new VoiceRecognition();
            }
        }
        return singleton;
    }

    /**
     * Starting the main Thread of speech recognition.
     */
    private void startSpeechThread() {
        if ((this.speechThread != null) && this.speechThread.isAlive()) {
            return;
        }
        this.speechThread = new Thread(() -> {

            while (true) {
                synchronized (this.waiter) {
                    if (!this.recognize) {
                        try {
                            this.waiter.wait();
                        } catch (final InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    final SpeechResult speechResult = this.recognizer.getResult();
                    if (speechResult != null) {
                        this.result = speechResult.getHypothesis();
                        LOG.info(this.result);
                        this.notifyEObservers(this.result);
                    }
                }
            }
        });
        this.speechThread.start();
    }

    /**
     * Toggle the {@link VoiceRecognition} OFF.
     */
    public void toggleRecognize() {
        if (!this.recognize) {
            synchronized (this.waiter) {
                this.waiter.notifyAll();
                MenuScreenController.getInstance().playAudio(SoundFX.SPEECH_ON);
            }
        } else {
            MenuScreenController.getInstance().playAudio(SoundFX.SPEECH_OFF);
        }
        this.recognize = !this.recognize;
    }

    @Override
    public void addEObserver(final EObserver<? super String> obs) {
        this.observer.add(obs);
    }

    @Override
    public void notifyEObservers(final String arg) {
        Platform.runLater(() -> this.observer.forEach(t -> t.update(null, arg)));
    }

    @Override
    public void clearObservers() {
        this.observer.clear();
    }

}
