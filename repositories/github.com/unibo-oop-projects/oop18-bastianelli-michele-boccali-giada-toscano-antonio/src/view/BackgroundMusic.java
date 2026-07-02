package view;

import java.util.Optional;

import common.Log;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Singleton class to handle background music. Support MP3 file. If an exception
 * occurs in the audio file loading, music don't be play buy no exception is
 * throws.
 */
public final class BackgroundMusic {

    private final static BackgroundMusic INSTANCE = new BackgroundMusic();

    private Optional<MediaPlayer> mediaPlayer;
    private boolean isMusicOn;

    /**
     * Background music status: on or off
     * 
     * @return true if music is on, false otherwise
     */
    public boolean isOn() {
        return isMusicOn;
    }

    private BackgroundMusic() {
    }

    /**
     * Load a track from the given path. Music continues for indefinite times and
     * auto start. If an exception occurs, music don't start.
     * 
     * @param path : file path in String format
     */
    public void loadTune(final String path) {
        try {
            mediaPlayer = Optional.ofNullable(
                    new MediaPlayer(new Media(getClass().getClassLoader().getResource(path).toExternalForm())));
            mediaPlayer.ifPresent(e -> e.setCycleCount(MediaPlayer.INDEFINITE));
            this.start();
        } catch (Exception e) {
            Log.add("Music not supported!  " + e.getMessage());
        }

    }

    /**
     * Switch mode (from play to stop and viceversa), but only if music has been
     * created before.
     */
    public void switchAudioMode() {
        if (isMusicOn) {
            this.stop();
        } else {
            this.start();
        }
    }

    /**
     * Switch mode (from play to stop and viceversa), but only if music has been
     * created before.
     */
    public void start() {
        mediaPlayer.ifPresent(e -> {
            e.play();
            isMusicOn = true;
        });
    }

    /**
     * Switch mode (from play to stop and viceversa), but only if music has been
     * created before.
     */
    public void stop() {
        mediaPlayer.ifPresent(e -> {
            e.stop();
            isMusicOn = false;
        });
    }

    /**
     * Return a singleton instance of Music
     * 
     * @return a singleton instance of Music
     */
    public static BackgroundMusic getInstance() {
        return INSTANCE;
    }
}
