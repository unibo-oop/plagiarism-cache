package zengine.interfaces;

/**
 * This interface represents the Zengine component that loads and stores assets,
 * such as fonts, images and audio. Stored resources are used by other Zengine
 * Components, not the user. However it provide methods to get properties of
 * particular resources to the user. Resources are loaded only the first time
 * one is requested.
 */
public interface GameFunctionsAssets {

    // TODO controlla bene i return e valuta tutti i casi

    /**
     * checks if a sprite with the given name exists.
     * 
     * @param sprite
     *            name of the sprite to be checked
     * @return true if sprite is a valid name
     */
    boolean spriteExists(String sprite);

    /**
     * returns the number of frames contained in the given sprite.
     * 
     * @param sprite
     *            name of the sprite to check
     * @return the number of frames contained in the given sprite
     */
    int spriteGetNumber(String sprite);

    /**
     * returns the x coordinate of the given sprite's X hoffset, which is the
     * sprite's center around where it will be rotated and/or scaled.
     * 
     * @param sprite
     *            name of the sprite to be checked
     * @return the x coordinate of the given sprite's X hoffset
     */
    int spriteGetXoffset(String sprite);

    /**
     * returns the y coordinate of the given sprite's Y hoffset, which is the
     * sprite's center around where it will be rotated and/or scaled.
     * 
     * @param sprite
     *            name of the sprite to be checked
     * @return the y coordinate of the given sprite's Y hoffset
     */
    int spriteGetYoffset(String sprite);

    /**
     * returns the width of the given sprite, in pixels.
     * 
     * @param sprite
     *            name of the sprite to be checked
     * @return the width of the given sprite, in pixels
     */
    int spriteGetWidth(String sprite);

    /**
     * returns the height of the given sprite, in pixels.
     * 
     * @param sprite
     *            name of the sprite to be checked
     * @return the height of the given sprite, in pixels
     */
    int spriteGetHeight(String sprite);

    /**
     * defines a new font named alias, to be used in the drawing functions. You
     * can use the same fontName for multiple fonts of different sizes, but they
     * must have different aliases. The alias is the name you will pass as
     * argument to the drawing functions. This method can only be called during
     * the initialize() event.
     * 
     * @param fontName
     *            name of the font file to load to create the new font (must be
     *            .ttf format)
     * @param alias
     *            name to assign to the font at run-time
     * @param size
     *            size to assign to the font
     * @return true if the font could be defined
     */
    boolean fontDefine(String fontName, String alias, float size);

    /**
     * returns true if a font with the given name can be loaded. The name
     * specifies a path, not the alias you could give it.
     * 
     * @param fontName
     *            path of the font to check
     * @return true if the font was found
     */
    boolean fontExists(String fontName);

    /**
     * returns true if a font with the given alias has been defined.
     * 
     * @param alias
     *            the alias name of the font to check
     * @return true if a font named alias exists
     */
    boolean fontAliasExists(String alias);

    /**
     * returns the height of the font, in pixels.
     * 
     * @param alias
     *            alias name of the font to check
     * @return height of the font in pixels
     */
    double fontGetHeight(String alias);

    /**
     * checks if a sound effect with the given name exists.
     * 
     * @param soundName
     *            name of the sound to check
     * @return true if the sound exists and can be played
     */
    boolean soundExists(String soundName);

    /**
     * checks if a music with the given name exists.
     * 
     * @param musicName
     *            name of the music to check
     * @return true if the music exists and can be played
     */
    boolean musicExists(String musicName);
}
