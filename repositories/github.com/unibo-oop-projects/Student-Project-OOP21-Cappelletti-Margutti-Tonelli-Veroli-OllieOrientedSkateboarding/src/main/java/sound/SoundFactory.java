package sound;

/**
 * 
 * Interface of a factory of {@link Sound}.
 *
 */
public interface SoundFactory {

    /**
     * 
     * @return a {@link Sound} that plays the game's music.
     */
    Sound createGameSoundtrack();

    /**
     * 
     * @return a {@link Sound} that plays the player jump sound.
     */
    Sound createJumpSound();

    /**
     * 
     * @return a {@link Sound} that plays the coin collected.
     */
    Sound createCoinCollectedSound();

    /**
     * 
     * @return a {@link Sound} that plays the game over sound.
     */
    Sound createGameOverSound();
}
