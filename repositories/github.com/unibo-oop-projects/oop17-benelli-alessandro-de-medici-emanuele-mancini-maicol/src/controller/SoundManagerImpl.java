package controller;

import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import utilities.UserPreferences;
import utilities.UserPreferencesImpl;
import utilities.Utilities;

/**
 * Class that manages which music and sound has to be played.
 * 
 */
public final class SoundManagerImpl implements SoundManager {

    private static final SoundManagerImpl SINGLETON = new SoundManagerImpl();
    private final UserPreferences userPreferences;
    private MediaPlayer musicPlayer;
    private MediaPlayer effectPlayer;
    private boolean isPlaying;
    private double musicVolume;

    /**
     * Constructor.
     */
    private SoundManagerImpl() {
        musicVolume = Utilities.MUSIC_VOLUME;
        this.userPreferences = UserPreferencesImpl.get();
    }

    @Override
    public void startMusic(final String path, final boolean repeat) {
        if (!this.isPlaying) {
            this.isPlaying = true;
            final Media music = new Media(this.getClass().getResource(path).toExternalForm());
            this.musicPlayer = new MediaPlayer(music);
            if (repeat) {
                this.musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            }
            this.setMusicVolume();
            this.musicPlayer.play();
        }
    }

    @Override
    public void startEffect(final String path) {
        final Media effect = new Media(this.getClass().getResource(path).toExternalForm());
        this.effectPlayer = new MediaPlayer(effect);
        this.setEffectVolume();
        this.effectPlayer.play();
    }

    @Override
    public void stopMusic() {
        if (this.isPlaying) {
            this.isPlaying = false;
            this.musicPlayer.stop();
        }
    }

    @Override
    public void stopEffect() {
        this.effectPlayer.stop();
    }

    @Override
    public double getMusicVolume() {
        return this.musicVolume;
    }

    @Override
    public void updateMusicVolume(final double volume) {
        this.musicVolume = volume;
        this.setMusicVolume();
        this.saveSoundVolume();
    }

    private void setMusicVolume() {
        this.musicPlayer.setVolume(this.getMusicVolume());
    }

    private void setEffectVolume() {
        if (this.getMusicVolume() == 0) {
            this.effectPlayer.setVolume(0);
        } else {
            this.effectPlayer.setVolume(this.getMusicVolume() + Utilities.EFFECT_VOLUME_DELTA);
        }
    }

    @Override
    public void loadSoundVolume() {
        String ret = null;

        try {
            ret = this.userPreferences.loadProperty("sound_volume");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (ret != null) {
            this.musicVolume = Double.parseDouble(ret);
        }
    }

    @Override
    public void saveSoundVolume() {
        try {
            this.userPreferences.saveProperty("sound_volume", String.valueOf(this.musicVolume));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an instance of SoundManagerImpl.
     * 
     * @return an instance of SoundManagerImpl
     */
    public static SoundManagerImpl get() {
        return SINGLETON;
    }
}
