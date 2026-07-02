package starcatraz.model;

/**
 * Stores the game's settings.
 */
public interface Settings {

    /**
     * Default music volume.
     */
    double DEFAULT_MUSIC_VOLUME = 0.5;

    /**
     * Default sound effects volume.
     */
    double DEFAULT_SOUND_VOLUME = 0.8;

    /**
     * @return the volume of the sound effects
     */
    double getSoundEffectsVolume();

    /**
     * @return the volume of the music
     */
    double getMusicVolume();

    /**
     * @return the height of the resolution
     */
    int getResolutionHeight();

    /**
     * @return the width of the resolution
     */
    int getResolutionWidth();

    /**
     * Set the sound effects volume.
     * 
     * @param volumeSound: the volume of the sound effects
     */
    void setSoundEffectsVolume(double volumeSound);

    /**
     * Set the music volume.
     * 
     * @param volumeMusic: the volume of the music
     */
    void setMusicVolume(double volumeMusic);

    /**
     * Set the height resolution.
     * 
     * @param resHeight: the resolution Height
     */
    void setResolutionHeight(int resHeight);

    /**
     * Set the width resolution.
     * 
     * @param resWidth: the resolution Width
     */
    void setResolutionWidth(int resWidth);

}
