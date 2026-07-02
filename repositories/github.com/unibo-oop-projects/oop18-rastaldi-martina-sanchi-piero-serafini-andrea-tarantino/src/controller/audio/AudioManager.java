package controller.audio;

/**
 *
 * Piero Sanchi. An interface to manage audio files.
 *
 */
public interface AudioManager {

    /**
     *
     * @return true if the audio is active.
     */
    boolean isAudio();

    /**
     *
     * @param audio
     *            the audio to play.
     */
    void play(Audio audio);

    /**
     *
     * @param b
     *            is true for setting audio active, otherwise false.
     */
    void setAudio(Boolean b);

    /**
     *
     * @param audio
     *            the audio to stop.
     */
    void stop(Audio audio);

    /**
     * 
     * @param volume
     *            the volume level to set.
     */
    void setVolume(int volume);

}
