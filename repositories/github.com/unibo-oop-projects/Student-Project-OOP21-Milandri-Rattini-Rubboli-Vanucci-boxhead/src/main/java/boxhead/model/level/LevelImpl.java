package boxhead.model.level;

import javafx.geometry.Point2D;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import boxhead.model.entities.Wall;

/**
 * Implementation of {@link Level}
 */
public class LevelImpl implements Level {

	private final Map<Point2D, Integer> blocks;
	private final List<Wall> walls;
	private final Set<Point2D> ammoSpawns;
	private final Set<Point2D> zombieSpawns;
	private final double width;
	private final double height;
	private final double tileSize;

	/**
	 * Constructor of the level.
	 *
	 * @param blocks
	 * @param width
	 * @param height
	 * @param tileSize
	 */
	public LevelImpl(final Map<Point2D, Integer> blocks, final double width, final double height,
			final double tileSize) {
		this.blocks = blocks;
		this.walls = new LinkedList<>();
		this.ammoSpawns = new HashSet<>();
		this.zombieSpawns = new HashSet<>();
		this.width = width;
		this.height = height;
		this.tileSize = tileSize;
		loadObjects();
	}

	/**
	 * Internal method to load all the objects.
	 */
	private void loadObjects() {
		blocks.forEach((point, id) -> {
			switch (id) {
			case 2:
				this.walls.add(new Wall(new Point2D(point.getX()*tileSize, point.getY()*tileSize)));
				break;
			case 3:
				this.zombieSpawns.add(new Point2D(point.getX()*tileSize, point.getY()*tileSize));
				break;
			case 4:
				this.ammoSpawns.add(new Point2D(point.getX()*tileSize, point.getY()*tileSize));
				break;
			case 1:
				break;
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Point2D, Integer> getBlocks() {
		return blocks;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Wall> getWalls() {
		return this.walls;
	}
	
	@Override
	public Set<Point2D> getAmmoSpawnPoints() {
		return this.ammoSpawns;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Point2D> getZombieSpawnPoints() {
		return this.zombieSpawns;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getWidth() {
		return width;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getHeight() {
		return height;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getTileSize() {
		return tileSize;
	}
}
