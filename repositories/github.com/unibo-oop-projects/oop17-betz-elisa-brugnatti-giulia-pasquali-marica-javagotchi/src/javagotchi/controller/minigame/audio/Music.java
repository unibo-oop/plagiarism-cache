package javagotchi.controller.minigame.audio;

/**
 * 
 * @author marica
 *
 */
public interface Music {

    /**
     * Activates background music in loop.
     */
    void startAudio();

    /**
     * Disables the music.
     */
    void stopAudio();

    /**
     * Indicates whether the music is running.
     * 
     * @return true if Music is running, otherwise false
     */
    boolean isOn();
}

