package com.jlearn.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.jlearn.controller.sound.AudioAgent;
import com.jlearn.model.exercises.Exercise;
import com.jlearn.model.exercises.Unit;
import com.jlearn.view.ui.UIExercise;
import com.jlearn.view.ui.UITheory;
import com.jlearn.view.utilities.enums.ExerciseType;

/**
 * Interface for {@link UIExercise}, and {@link UITheory} Logic.
 *
 */
public interface ControllerExercise {
    /**
     * Attache the UI.
     *
     * @param observedUI
     *            the {@link UIExercise}
     */
    void attacheExerciseUI(UIExercise observedUI);

    /**
     * Attache the Theory UI.
     *
     * @param observedUI
     *            the {@link UITheory}
     */
    void attacheTheoryUI(UITheory observedUI);

    /**
     * Give the actual {@link Unit} ID.
     *
     * @return the actual {@link Unit} ID.
     */
    int getActualUnitId();

    /**
     * Get the answers from User and return a unmodifiable list of input error answer.
     *
     * @param mappaOut
     *            the {@link Map} of {@link List} of answers.
     * @return An unmodifiable {@link List} of the wrong input answer.
     */
    List<Integer> getViewModule(Map<ExerciseType, List<?>> mappaOut);

    /**
     * Load the html page for theory and send it to view.
     *
     * @param level
     *            the index of the theory to load inside jar.
     *
     */
    void loadPdf(int level);

    /**
     * Check if the module can be selected, and if is possible load it and send to view.
     *
     * @param selectedIndex
     *            the index of the csv {@link File} for the {@link Exercise}.
     * @return true if is possible select module.
     */
    boolean moduleSelected(int selectedIndex);

    /**
     * Pause playing {@link ExerciseType} audio.
     *
     * @return true {@link AudioAgent} can be paused.
     */
    Boolean pauseAudioExercise();

    /**
     * Play {@link ExerciseType} audio.
     *
     * @return true {@link AudioAgent} can be played.
     */
    Boolean playAudioExercise();

    /**
     * Stop playing audio {@link ExerciseType} audio.
     *
     * @return true {@link AudioAgent} can be stopped.
     */
    Boolean stopAudioExercise();

    /**
     * If User go back to menu, clean all {@link ControllerExerciseImpl} and notify to view.
     */
    void notifyBack();
}
