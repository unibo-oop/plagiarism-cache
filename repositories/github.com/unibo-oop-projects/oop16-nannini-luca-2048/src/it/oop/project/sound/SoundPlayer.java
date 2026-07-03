package it.oop.project.sound;

/**
 * A class to make 2048 game sounds.
 *
 */
public interface SoundPlayer {

    /**
     * Plays a sound for shift event.
     */
    void playShift();

    /**
     * Plays a sound for no shift event.
     */
    void playNoShift();

    /**
     * Plays a sound for game won.
     */
    void playGameWon();

    /**
     * Plays a sound for game over.
     */
    void playGameOver();
}
