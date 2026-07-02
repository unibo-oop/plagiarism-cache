package it.unibo.pacman.model.utilities;

/**
 * It represents a sound.
 */
public interface Sound {

    /**
     * Plays a sound if it's stopped.
     */
    void play();

    /**
     * Stops a sound.
     */
    void stop();

    /**
     * Gets the sound state.
     *
     * @return true if sound is playing, otherwise false
     */
    boolean isPlaying();
    /**
     * Play a sounds continuously.
     */
    void playInLoop();
}
