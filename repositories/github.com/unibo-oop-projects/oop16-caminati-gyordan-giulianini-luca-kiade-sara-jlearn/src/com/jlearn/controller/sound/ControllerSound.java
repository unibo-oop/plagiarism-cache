package com.jlearn.controller.sound;

import javafx.scene.media.Media;

/**
 * Representing An Audio player.
 *
 */
public interface ControllerSound {

    /**
     * Set the new FileAudio for {@link AudioAgent}.
     *
     * @param filename
     *            The {@link String} for {@link Media} to play.
     */
    void changeTrack(String filename);

    /**
     * Check {@link AudioAgent} is ready to play.
     *
     * @return true if {@link AudioAgent} is ready to play.
     */
    boolean checkAudio();

    /**
     * Deleate the actual track Now {@link AudioAgent} is empty.
     */
    void cleanTrack();

    /**
     * Pause the {@link AudioAgent}.
     *
     * @return true if If the action is completed correctly
     */
    Boolean pauseAudioExercise();

    /**
     * Play the {@link} {@link AudioAgent}.
     *
     * @return true if If the action is completed correctly
     */
    Boolean playAudioExercise();

    /**
     * Stop the {@link AudioAgent}.
     *
     * @return true if If the action is completed correctly
     */
    Boolean stopAudioExercise();

}