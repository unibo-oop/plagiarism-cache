package javarogue.config;

import javarogue.utility.Resolution;

/**
 * 
 * (Config)uration is a collection of static values used for project-wise
 * "constants" initialized at the moment of building. Pre-builder values are set
 * to null.
 * 
 * Note that the use of static primitives is deprecated, because they cannot be
 * set to null prior to calling the builder.
 *
 */
public class ConfigGraphics {

	/**
	 * The game window resolution width in pixels.
	 */
	public static Integer resolutionWidth = null;
	/**
	 * The game window resolution height in pixels.
	 */
	public static Integer resolutionHeight = null;
	/**
	 * The tileset's single tile size.
	 */
	public static Integer tileSize = null;
	/**
	 * The scale of rendered image.
	 */
	public static Double renderScale = null;
	/**
	 * The scale of the saved Tileset, impacts performance!
	 */
	public static Integer qualityScale = null;
	/**
	 *  The path the tileset to be loaded.
	 */
	public static String tilesetPath = null;
	
	public static Boolean fullscreen = null;

	/**
	 * Set config's values. Must be called prior to launching the game window.
	 * 
	 * @param resolution selected resolution
	 * @param tileSize   selected tile size
	 */
	public ConfigGraphics(Resolution resolution, boolean fullscreen, int tileSize, double renderScale, Integer rasterScale, String tilesetPath) {
		ConfigGraphics.resolutionWidth = resolution.getWidth();
		ConfigGraphics.resolutionHeight = resolution.getHeight();
		ConfigGraphics.fullscreen = fullscreen;
		ConfigGraphics.tileSize = tileSize;
		ConfigGraphics.renderScale = renderScale;
		ConfigGraphics.qualityScale = rasterScale;
		ConfigGraphics.tilesetPath = "res/tileset_" + tilesetPath + ".png";
	}

}
