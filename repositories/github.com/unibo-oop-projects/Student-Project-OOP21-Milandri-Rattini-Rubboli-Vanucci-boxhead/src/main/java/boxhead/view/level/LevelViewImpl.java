 package boxhead.view.level;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import boxhead.view.world.tile.Tile;
import boxhead.view.world.tile.TileFactory;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Pair;

public class LevelViewImpl implements LevelView {

	private final Set<Tile> tiles;
	private Set<Pair<Point2D, Image>> levelRendered;
	/**
	 * Constructor of the LevelView
	 *
	 * @param level - Map<Point2D, Integer> to be transformed in relative tiles
	 * @param tf    - tileFactory to create tiles
	 */
	public LevelViewImpl(final Map<Point2D, Integer> blocks, final TileFactory tf, final double ts) {
		tiles = tf.createTiles(blocks, ts);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Set<Tile> getLevelMap() {
		return this.tiles;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Set<Pair<Point2D, Image>> renderLevelMap() {
		Set<Pair<Point2D, Image>> result;
		if (levelRendered == null) {
			result = new HashSet<>();
			tiles.stream().forEach(t -> {
				result.add(new Pair<>(t.getRelativePos(), t.getTile()));
			});
			levelRendered = result;
		} else {
			result = levelRendered;
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setScale(final double scale) {
		tiles.forEach(t -> t.setRenderScale(scale));
		levelRendered = null;
	}
}