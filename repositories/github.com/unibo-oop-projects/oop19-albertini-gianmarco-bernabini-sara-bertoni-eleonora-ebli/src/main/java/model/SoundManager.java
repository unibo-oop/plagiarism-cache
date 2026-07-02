package model;

public interface SoundManager {
    /**
     * Plays the effect sound.
     */
    void play();

    /**
     * Plays the background music.
     */
    void loop();

    /**
     * Stops the current clip.
     */
    void stop();
}
