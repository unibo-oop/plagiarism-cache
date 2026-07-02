package it.unibo.arkanoid.audio;

import java.net.URISyntaxException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * This class is a implementation of {@link SoundManager} interface.
 *
 */
public class SongLoop implements SoundManager {

    private static MediaPlayer mediaPlayer;
    private boolean paused = true;

    /**
     * It's useful to reproduce audio infinitely in a JavaFX scene.
     * 
     * @param filePath
     *            The filePath of .mp3 to reproduce.
     */
    @Override
    public void play(final String filePath) {

        try {
            final Media mediaAudio = new Media(getClass().getResource(filePath).toURI().toString());
            setMedia(mediaAudio);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
            paused = false;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    /**
     * Associate the Media with your Media Player.
     * 
     * @param media
     *             The media to reproduce
     */
    public static void setMedia(final Media media) {
        mediaPlayer = new MediaPlayer(media);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pause() {
        paused = true;
        mediaPlayer.pause();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resume() {
        paused = false;
        mediaPlayer.play();
    }

    /**
     * 
     * @return True if the song is paused, False otherwise.
     */
    public boolean isPaused() {
        return this.paused;
    }

}
