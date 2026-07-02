package tmw.controller.audio;

import java.util.Observer;
import tmw.model.audio.AudioMasterImpl;
import tmw.model.audio.AudioTracks;

/**
 * Interface that manage audio feature in game.
 *
 */
public interface AudioController extends Observer {

    /**
     * Getter for the audio.
     * 
     * @return the current instance of audio
     */
    AudioMasterImpl getAudio();

    /**
     * This method permits to mute background music and SFX effects by receiving
     * true as a parameter.
     * 
     * @param bool value
     */
    void muteVolume(boolean bool);

    /**
     * Set as current volume the volume receiving as a parameter.
     * 
     * @param value volume value
     */
    void setVolume(double value);

    /**
     * set the background music.
     * 
     * @param track current background track
     */
    void setBackgroudMusic(AudioTracks track);

    /**
     * Getter for the default volume.
     * 
     * @return the default volume
     */
    double getDefaultVolume();

}
