package it.unibo.arkanoid.audio;

/**
 * This class it's useful to produce audio in a Java FX scene.
 * 
 *
 */
public interface SoundManager {

    /**
     * It's useful to reproduce audio in a JavaFX scene.
     * 
     * @param filePath
     *            The filePath of .mp3 to reproduce.
     */
    void play(String filePath);

    /**
     * Pause the audio playing.
     */
    void pause();

    /**
     * Resume the audio paused.
     */
    void resume();

}
