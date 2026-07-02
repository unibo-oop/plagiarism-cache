package it.unibo.scat.view.util;

/**
 * Interface for managing audio playback.
 * 
 */
public interface Audio {

    /**
     * Plays the specified track.
     * 
     * @param audioTrack the track to play.
     * 
     * @param loop       to enable continuous looping.
     */
    void play(AudioTrack audioTrack, boolean loop);

    /**
     * Stops the current audio playback.
     * 
     */
    void stop();

    /**
     * Sets the volume of the audio playback.
     * 
     * @param volume the volume level to set (between 0.0 and 1.0).
     */
    void setVolume(float volume);

}
