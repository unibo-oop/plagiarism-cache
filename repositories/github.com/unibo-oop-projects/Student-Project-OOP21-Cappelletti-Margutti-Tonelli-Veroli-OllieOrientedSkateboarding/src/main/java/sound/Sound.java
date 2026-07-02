package sound;

/**
 * 
 * Interface of a sound of the game.
 *
 */
public interface Sound {

    /**
     * Plays the sound.
     * 
     */
    void play();

    /**
     * Stops the sound.
     */
    void stop();

    /**
     * Controls if the sound is playing.
     * @return true if the sound is playing, false otherwise.
     */
    boolean isPlaying();

}
