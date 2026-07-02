package it.unibo.plantsfarm.view.music.api;

/**
 * Defines the audio player system.
 */
public interface MusicPlayer {

    /**
     * Plays the specified music file in a loop.
     *
     * @param fileName The name of the file.
     */
    void playLoop(String fileName);

    /**
     * Plays a sound effect once.
     *
     * @param fileName The name of the file.
     */
    void playEffect(String fileName);

    /**
     * Stops the background music.
     */
    void stop();

}
