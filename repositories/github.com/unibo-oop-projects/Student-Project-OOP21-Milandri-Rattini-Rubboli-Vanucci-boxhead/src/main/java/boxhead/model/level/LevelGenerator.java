package boxhead.model.level;

import boxhead.view.level.LevelView;

import javafx.util.Pair;

/**
 * Interface for the LevelGenerator, that reads map and convert it from the txt file.
 */
public interface LevelGenerator {

	/**
	 * Main method of the Generator. that load the {@link Level} and associates it with the {@link LevelView}.
	 * @param w
	 * @param h
	 * @param ts
	 * @return
	 */
	public Pair<Level, LevelView> loadLevel(final double w, final double h, final double ts);

}
