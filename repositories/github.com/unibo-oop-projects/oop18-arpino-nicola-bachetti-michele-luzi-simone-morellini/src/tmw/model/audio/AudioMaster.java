package tmw.model.audio;

import javafx.scene.media.MediaPlayer;

/**
 * This class is the audioEngine that allows to play music/tunes according to
 * situations. Is based on JavaFx media core, so uses both Media {@link Media}
 * and AudioClip {@link AudioClip}.
 * <p>
 * Basically it provides methods for play/stop both music/sounds in reaction to
 * user's commands and GameModel notifications.
 * 
 * @version 1.3
 */

public interface AudioMaster extends Runnable {

    /**
     * Initialize to default audioMaster.
     */
    void initialize();

    /**
     * Method used to set the volume to a value.
     * 
     * @param value double value to set
     */
    void setVoulme(double value);

    /**
     * Stops all current sounds/music.
     */
    void stopPlaying();

    /**
     * Pause all current sounds/music playing.
     */
    void pauseAll();

    /**
     * Resume all paused sounds/music.
     */
    void resumePlaying();

    /**
     * Plays SFX sounds.
     * 
     * @param sound a sound to be played
     */
    void playSFX(AudioSfx sound);

    /**
     * Reproduces a background music.
     * 
     * @param track {@link AudioTracks} track to be played
     */
    void playBackMusic(AudioTracks track);

    /**
     * Check if audioMaster is playing.
     * 
     * @return boolean response.
     */
    boolean isPlaying();

    /**
     * Just mutes the audioPlayer.
     */
    void mutePlayer();

    /**
     * Removes mute.
     */
    void unMute();

    /**
     * Gets the mediaPlayer.
     * 
     * @return {@link MediaPlayer} player
     */
    MediaPlayer getPlayer();
}

