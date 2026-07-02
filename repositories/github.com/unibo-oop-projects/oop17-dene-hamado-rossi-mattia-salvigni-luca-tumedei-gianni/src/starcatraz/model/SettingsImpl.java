package starcatraz.model;

/**
 * Settings implementation.
 */
public class SettingsImpl implements Settings {

    private double musicVolume;
    private double soundEffectsVolume;
    private int resHeight;
    private int resWidth;

    @Override
    public double getSoundEffectsVolume() {
        return this.soundEffectsVolume;
    }

    @Override
    public double getMusicVolume() {
        return this.musicVolume;
    }

    @Override
    public int getResolutionHeight() {
        return this.resHeight;
    }

    @Override
    public int getResolutionWidth() {
        return this.resWidth;

    }

    @Override
    public void setSoundEffectsVolume(final double volumeSound) {
        this.soundEffectsVolume = volumeSound;

    }

    @Override
    public void setMusicVolume(final double volumeMusic) {
        this.musicVolume = volumeMusic;

    }

    @Override
    public void setResolutionHeight(final int resHeight) {
        this.resHeight = resHeight;

    }

    @Override
    public void setResolutionWidth(final int resWidth) {
        this.resWidth = resWidth;

    }

}
