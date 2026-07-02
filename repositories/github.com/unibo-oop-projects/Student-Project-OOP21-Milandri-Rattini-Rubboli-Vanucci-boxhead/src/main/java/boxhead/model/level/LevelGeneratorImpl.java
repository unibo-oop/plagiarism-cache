package boxhead.model.level;

import javafx.geometry.Point2D;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import boxhead.view.level.LevelView;
import boxhead.view.level.LevelViewImpl;
import boxhead.view.world.tile.TileFactory;
import boxhead.view.world.tile.TileFactoryImpl;

/**
 * Implementation of {@link LevelGenerator}.
 */
public class LevelGeneratorImpl implements LevelGenerator {
	
	private final static int MAP_WIDTH = 29;
	private final static int MAP_HEIGHT = 15;
	
	private static Map<Point2D, Integer> level = new HashMap<>();
	private final TileFactory tFactory;

	/**
	 * Contructor that takes only tileSize.
	 * @param ts
	 */
	public LevelGeneratorImpl(final double ts) {
		this.tFactory = new TileFactoryImpl(ts);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Pair<Level, LevelView> loadLevel(final double width, final double height, final double tileSize) {
		final Map<Point2D, Integer> level = readLevel();
		return new Pair<>(LevelGeneratorImpl.generateLevel(level, width, height, tileSize),
				LevelGeneratorImpl.generateLevelView(level, tFactory, tileSize));
	}
	
	/**
	 * Method to read the level from a file.
	 * @return Map with Point2D and type of the tile(in integer).
	 */
	private final Map<Point2D, Integer> readLevel() {
		BufferedReader br = null;
		try {
			final InputStream is = getClass().getResourceAsStream("/prova.txt");
			br = new BufferedReader(new InputStreamReader(is));
			int x = 0;
			int y = 0;
			while ((x < MAP_WIDTH) && (y < MAP_HEIGHT)) {
				final String line = br.readLine();
				while (x < MAP_WIDTH) {
					final String numbers[] = line.split(" ");
					final int num = Integer.parseInt(numbers[x]);
					LevelGeneratorImpl.level.put(new Point2D(x, y), num);
					x++;
				}
				if (x == MAP_WIDTH) {
					x = 0;
					y++;
				}
			}
			br.close();
		} catch (final Exception e) {
		}
		return LevelGeneratorImpl.level;
	}

	/**
	 * Method to instantiate a new Level.
	 * @param level
	 * @param width
	 * @param height
	 * @param tileSize
	 * @return
	 */
	private static Level generateLevel(final Map<Point2D, Integer> level, final double width, final double height, final double tileSize) {
		return new LevelImpl(level, width, height, tileSize);

	}

	/**
	 * Method to instantiate a new LevelView.
	 * @param level
	 * @param tf
	 * @param tileSize
	 * @return
	 */
	private static LevelView generateLevelView(final Map<Point2D, Integer> level, final TileFactory tf, final double tileSize) {
		return new LevelViewImpl(level, tf, tileSize);
	}
}
