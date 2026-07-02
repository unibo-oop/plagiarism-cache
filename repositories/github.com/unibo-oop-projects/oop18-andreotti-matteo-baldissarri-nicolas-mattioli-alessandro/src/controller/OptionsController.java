package controller;

/**
 * 
 * Interface to control options.
 *
 */
public interface OptionsController {

    /**
     * 
     * @param volume new sfx volume
     */
    void sfxVolumeChanger(double volume);

    /**
     * 
     * @param volume new music volume
     */
    void musicVolumeChanger(double volume);
}
