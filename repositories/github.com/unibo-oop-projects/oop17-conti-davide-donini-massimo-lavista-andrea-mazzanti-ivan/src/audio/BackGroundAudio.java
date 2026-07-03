package audio;

import audio.utils.AudioPaths;
import javafx.scene.media.MediaPlayer;

/**
 * 
 * Implements a background audio.
 *
 */
public final class BackGroundAudio extends AbstractAudioPlayer {

    /**
     * 
     * @param path
     *            the path of the audio file.
     */
    public BackGroundAudio(final AudioPaths path) {
        super(path);
        super.getMediaplayer().setCycleCount(MediaPlayer.INDEFINITE);
    }

}
