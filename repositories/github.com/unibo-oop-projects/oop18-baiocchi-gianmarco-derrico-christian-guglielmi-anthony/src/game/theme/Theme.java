package game.theme;

/**
 * It represents a game theme.
 */
public interface Theme {

    /**
     * Getter for all sounds.
     * @return sound manager with all sounds within
     */
    SoundsManager getSounds();

    /**
     * Getter for all sprites.
     * @return sprites manager with all sprites within
     */
    SpritesManager getSprites();
}
