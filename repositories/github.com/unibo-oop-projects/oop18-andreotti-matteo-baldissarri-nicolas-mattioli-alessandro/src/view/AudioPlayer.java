package view;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * 
 * Main class to handle music and SFX audio.
 *
 */
public class AudioPlayer {
    private MediaPlayer mediaPlayer;
    private double sfxVol = 1;
    private double musicVol = 1;

    /**
     * constructor that load main music.
     */
    public AudioPlayer() {
        loadTune(Sound.MENUTRACK.getPath());
    }

    /**
     * 
     * @return getter of sfxVol.
     */
    public double getSfxVol() {
        return sfxVol;
    }

    /**
     * 
     * @return getter of musicVol.
     */
    public double getMusicVol() {
        return musicVol;
    }

    /**
     * 
     * @param volume New value of sfxVol.
     */
    public void setSfxVol(final double volume) {
        this.sfxVol = volume;
    }

    /**
     * 
     * @param volume New value of musicVol.
     */
    public void setMusicVol(final double volume) {
        this.musicVol = volume;
        mediaPlayer.setVolume(musicVol);
    }

    /**
     * Reproduce an SFX audio clip.
     * 
     * @param path Audio clip path.
     */
    public void playSFX(final String path) {
        new AudioClip(path).play(sfxVol);
    }

    /**
     * Reproduce background music.
     * 
     * @param path Music path.
     */
    public void playMusic(final String path) {
        mediaPlayer.stop();
        loadTune(path);
        mediaPlayer.play();
    }

    /**
     * method to pause music.
     */
    public void pauseMusic() {
        mediaPlayer.pause();
    }

    /**
     * method to pause music.
     */
    public void resumeMusic() {
        mediaPlayer.play();
    }

    private void loadTune(final String path) {
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource(path).toExternalForm()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(musicVol);
    }
}
