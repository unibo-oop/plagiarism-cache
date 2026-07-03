package audio;

import java.util.Optional;

import audio.utils.AudioPaths;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import settings.SettingsImpl;

/**
 * 
 * This Class factories the shared methods of the game's sounds.
 *
 */
public class AbstractAudioPlayer implements AudioPlayer {

    private final MediaPlayer mediaplayer;
    private Optional<Duration> duration = Optional.empty();

    /**
     * 
     * @param path
     *            the path of the audio file.
     */
    public AbstractAudioPlayer(final AudioPaths path) {
        final Media sound = new Media(AbstractAudioPlayer.class.getResource(path.getPath()).toExternalForm());
        this.mediaplayer = new MediaPlayer(sound);

    }

    /**
     * Plays the sound in the default way. This method can be overridden if a
     * particular Audio Class must play a sound in a different way.
     */
    @Override
    public void play() {
        if (SettingsImpl.getSettings().isBackGroundAudioActivated()) {
            if (this.duration.isPresent()) {
                this.mediaplayer.seek(this.duration.get());
            }
            this.mediaplayer.play();
            this.duration = Optional.empty();

        }
    }

    /**
     * Pauses the sound in the default way. This method can be overridden if a
     * particular Audio Class must implement pause in a different way.
     */
    @Override
    public void pause() {
        this.mediaplayer.pause();
        // Necessary to solve a bug with fast switch in Pause Status.
        this.mediaplayer.setOnPaused(() -> this.duration = Optional.of(this.mediaplayer.getCurrentTime()));

    }

    /**
     * Stops the sound in the default way. This method can be overridden if a
     * particular Audio Class must implement stop in a different way.
     */
    @Override
    public void stop() {
        this.mediaplayer.stop();
        this.duration = Optional.empty();
    }

    /**
     * 
     * @return the MediaPlayer audio.
     */
    protected MediaPlayer getMediaplayer() {
        return mediaplayer;
    }

}
