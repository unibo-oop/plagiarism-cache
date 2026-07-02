package sound;

/**
 * This class has methods to reproduce a sound and to enable and disable it.
 */
public interface Sound {

    /**
     * Reproduces the sound specified by its name.
     * 
     * @param soundName specifies the name of the proper sound to reproduce.
     */
    void play(String soundName);

    /**
     * Set the sound reproduction enabled or disabled.
     * 
     * @param isEnabled specifies if the sound must be enabled or disabled.
     */
    void setEnable(boolean isEnabled);

    /**
     * Check if sound is enabled.
     * 
     * @return true if sound is enabled, otherwise it returns false.
     */
    boolean isEnabled();
}
