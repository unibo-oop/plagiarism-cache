package com.jlearn.controller.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Interface represent an audioPlayer.
 */
public interface AudioAgent {

    /**
     * Set the {@link Media} for {@link AudioAgent}.
     *
     * @param filename
     *            The {@link String} for {@link Media} to play.
     */
    void changeTrack(String filename);

    /**
     * Reset the {@link AudioAgent} whit no{@link Media}.
     */
    void cleanTrack();

    /**
     * Return if {@link AudioAgent} can start.
     *
     * @return true if {@link AudioAgent} can start.
     */
    boolean canStart();

    /**
     * Pauses the {@link MediaPlayer}.
     *
     */
    void pausePlayer();

    /**
     * Starts the {@link MediaPlayer}.
     *
     */
    void startPlayer();

    /**
     * Stops playing the {@link MediaPlayer}. This operation resets {@link Media} to startTime.
     *
     */
    void stopPlayer();

    /**
     * Return the {@link Media} starting time,default is 0.
     *
     * @return the start time.
     */
    Duration getStartTime();

    /**
     * Return the {@link Media} stop time.
     *
     * @return the stop time.
     */
    Duration getStopTime();

    /**
     * Return the {@link Media} current time.
     *
     * @return the current media time
     */
    Duration getCurrentTime();

    /**
     * Return the remaining {@link Media} time.
     *
     * @return the remain duration.
     */
    Duration getRemainingTime();

    /**
     * Retrieves the total {@link Media} duration.
     *
     * @return the total duration
     */
    Duration getAudioDuration();

    /**
     * Set the cycle count.
     */
    void setCycleCount();

    /**
     * Set the voluime of the {@link AudioAgent}.
     *
     * @param volume
     *            the volume
     */
    void setVolume(double volume);

    /**
     * Mute the track.
     */
    void toggleMute();

    /**
     * If is playing.
     *
     * @return <code>true</code> if it is playing
     */
    boolean isPlaying();
}
