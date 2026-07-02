package javarogue.tileengine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javarogue.config.ConfigGraphics;
import javarogue.utility.Position;

/**
 * 
 * Handles the operations on the tileset. Must be built with a path to the
 * tileset prior to any operations. Can return the cropped image of a tile for
 * rendering.
 *
 */
public class TileSet {

	// The tileset loaded as a JavaFX image.
	private Image tileset;
	// Tileset size constants.
	// IMPORTANT: Remember to update once the tileset size has been finalized.
	private final int tilesetWidth = 96 * ConfigGraphics.qualityScale;
	private final int tilesetHeight = 96 * ConfigGraphics.qualityScale;
	// Tile cache to preload and improve performance
	private Map<Tile, Image> tileCache;

	/**
	 * Loads a tileset.
	 * 
	 * @param path The path to the tileset image.
	 * @throws FileNotFoundException
	 */
	public TileSet(String path) {
		// Tries to load a tileset, if fails, launches the exception and crashes.
		try {
			this.tileset = new Image(new FileInputStream(new File(path)), this.tilesetWidth, this.tilesetHeight, true,
					true);
			this.initCache();
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("Tileset not found!");
		}
	}

	/**
	 * Returns a cropped image of the passed tile. Tile cropping coordinates are
	 * handled by the Tile and tile size by GameConfig.
	 * 
	 * @param tile The tile to be "cut" out.
	 * @return The cropped image of a tile.
	 */
	public Image getTile(Tile tile) {
		return this.tileCache.get(tile);
	}
	
	private void initCache() {
		// Allocate resources
		this.tileCache = new HashMap<>();
		// Fill map with tiles
		for(Tile t : Arrays.asList(Tile.values())) {
			this.tileCache.put(t, this.extractTile(t));
		}
	}
	
	private Image extractTile(Tile tile) {
		Position origin = tile.getOrigin();
		return new WritableImage(tileset.getPixelReader(), origin.getX()  * ConfigGraphics.qualityScale, origin.getY()  * ConfigGraphics.qualityScale, ConfigGraphics.tileSize * ConfigGraphics.qualityScale,
				ConfigGraphics.tileSize * ConfigGraphics.qualityScale);
	}

}
