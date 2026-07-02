package boxhead.view.world.tile;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class TileSetImpl implements TileSet {

	private final double tileSize;
	private final Map<Integer, Image> tiles;

	/**
	 * Constructor if image of the TileSet is not known yet.
	 */
	public TileSetImpl(final double ts) {
		this.tileSize = ts;
		this.tiles = new HashMap<>();
		this.loadImage();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image getTile(final int id) {
		return this.tiles.get(id);
	}

	@Override
	public void loadImage() {
		this.tiles.put(TileType.GROUND.getId(),new Image(getClass().getResourceAsStream("/ground.png"), tileSize, tileSize, true, true));
		this.tiles.put(TileType.GRASS.getId(),new Image(getClass().getResourceAsStream("/grass.png"), tileSize, tileSize, true, true));
		this.tiles.put(TileType.CONCRETE.getId(),new Image(getClass().getResourceAsStream("/concrete.png"), tileSize, tileSize, true, true));
		this.tiles.put(TileType.WALL.getId(),new Image(getClass().getResourceAsStream("/wall.png"), tileSize, tileSize, true, true));
		this.tiles.put(TileType.ZOMBIE_SPAWN.getId(),new Image(getClass().getResourceAsStream("/ground.png"), tileSize, tileSize, true, true));
		this.tiles.put(TileType.AMMO_SPAWN.getId(),new Image(getClass().getResourceAsStream("/ground.png"), tileSize, tileSize, true, true));
	}
}
