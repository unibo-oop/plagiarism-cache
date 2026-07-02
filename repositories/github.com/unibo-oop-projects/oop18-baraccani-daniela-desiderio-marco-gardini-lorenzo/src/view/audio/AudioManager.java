package view.audio;

/**
 * An interface to control all audio aspects.
 */
public interface AudioManager {

    /**
     * The audio track starts.
     * 
     * @param music is the track ready to start.
     */
    void play(AudioTrack music);

    /**
     * The audio track stops.
     */
    void stop();

    /**
     * Change of the audio track's volume.
     * 
     * @param newVolume is the new audio track's volume.
     */
    void setVolume(Volume newVolume);

    /**
     * @return the volume of the audio track.
     */
    Volume getVolume();

    /**
     * Change of the isOff value.
     * 
     * @param newValue is the new value.
     */
    void setOff(boolean newValue);

    /**
     * @return true if the audio track is set off, false otherwise.
     */
    boolean isOff();

    /**
     *
     * @return the audio track.
     */
    AudioTrack getAudioTrack();

    /**
     * @return true if no track has ever been played, false otherwise.
     */
    boolean isFirstPlay();

}
