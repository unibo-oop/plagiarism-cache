package controller.soundmanager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * Manager of the sound of the game.
 *
 * @author Andrea Manoni
 *
 */
public class SoundManager {

    private final String themeSongPath = getClass().getResource("/menu_music.wav").toExternalForm();
    private final String shootingSoundPath = getClass().getResource("/shoot_sound.wav").toExternalForm();
    private final String reloadingSoundPath = getClass().getResource("/gun_reload.wav").toExternalForm();
    private final String explosionSoundPath = getClass().getResource("/explosion.wav").toExternalForm();
    private final String fallingRocksSoundPath = getClass().getResource("/falling_rocks.wav").toExternalForm();
    private final String gameMusicSoundPath = getClass().getResource("/game_music.wav").toExternalForm();

    private double musicVolume;
    private double soundVolume;

    /**
     *
     * @return sound of projectile shoot
     */
    public MediaPlayer getSoundShoot() {
        final Media media = new Media(shootingSoundPath);
        final MediaPlayer sound = new MediaPlayer(media);
        sound.setVolume(soundVolume);
        return sound;
    }

    /**
     *
     * @return music in the game
     */
    public MediaPlayer getMusicGame() {
        final Media media = new Media(gameMusicSoundPath);
        final MediaPlayer sound = new MediaPlayer(media);
        sound.setVolume(musicVolume);
        return sound;
    }

    /**
     *
     * @return theme sound of the game
     */
    public MediaPlayer getMusicTheme() {
        final Media media = new Media(themeSongPath);
        final MediaPlayer sound = new MediaPlayer(media);
        sound.setVolume(musicVolume);
        return sound;
    }

    /**
     *
     * @return reload sound of the game
     */
    public MediaPlayer getSoundReload() {
        final Media media = new Media(reloadingSoundPath);
        final MediaPlayer sound = new MediaPlayer(media);

        sound.setVolume(soundVolume);
        return sound;
    }

    /**
     *
     * @return explosion sound
     */
    public MediaPlayer getSoundExplosion() {
        final Media media = new Media(explosionSoundPath);
        final MediaPlayer sound = new MediaPlayer(media);

        sound.setVolume(soundVolume);
        return sound;
    }

    /**
     *
     * @return falling rocks sound
     */
    public MediaPlayer getSoundFallingRocks() {
        final Media media = new Media(fallingRocksSoundPath);
        final MediaPlayer sound = new MediaPlayer(media);
        sound.setVolume(soundVolume);
        return sound;
    }

    /**
     *
     * @param soundVolume
     *            soundVolume
     */
    public void setVolumeSound(final double soundVolume) {
        this.soundVolume = soundVolume;
    }

    /**
     *
     * @param musicVolume
     *            musicVolume
     */
    public void setVolumeMusic(final double musicVolume) {
        this.musicVolume = musicVolume;
    }

    /**
     *
     * @return musicVolume
     */
    public double getMusicVolume() {
        return musicVolume;
    }

    /**
     *
     * @return soundVolume
     */
    public double getSoundVolume() {
        return soundVolume;
    }
}
