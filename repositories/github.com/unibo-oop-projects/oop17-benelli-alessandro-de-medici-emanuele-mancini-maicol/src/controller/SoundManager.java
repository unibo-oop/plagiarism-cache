package controller;

/**
 * SoundManagerImpl interface.
 *
 */
public interface SoundManager {

    /**
     * Starts the music.
     * 
     * @param path
     *            of the music's file
     * @param repeat
     *            if the music has to be repeated
     */
    void startMusic(String path, boolean repeat);

    /**
     * Starts the sound effect.
     * 
     * @param path
     *            of the sound effect's file
     */
    void startEffect(String path);

    /**
     * Stops the music.
     */
    void stopMusic();

    /**
     * Stops the sound effect.
     */
    void stopEffect();

    /**
     * Getter of the music volume.
     * 
     * @return musicVolume
     */
    double getMusicVolume();

    /**
     * Sets the music volume and the sound effect volume. Calls method to save the
     * value on an external file.
     * 
     * @param volume
     *            value of the volume
     */
    void updateMusicVolume(double volume);

    /**
     * Loads the value of volume from an external file.
     */
    void loadSoundVolume();

    /**
     * Saves the value of volume on an external file.
     */
    void saveSoundVolume();
}
