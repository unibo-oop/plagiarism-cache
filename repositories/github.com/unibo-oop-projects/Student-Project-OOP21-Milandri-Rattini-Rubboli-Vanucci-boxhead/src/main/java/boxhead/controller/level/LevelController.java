package boxhead.controller.level;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.util.Set;

import boxhead.model.level.Level;
import boxhead.model.level.LevelGenerator;
import boxhead.model.level.LevelGeneratorImpl;
import boxhead.view.level.LevelView;

/**
 * Controller for Level and and LevelView
 */
public class LevelController {

	private static final int TILE_SIZE = 32;

	private Level l;
	private LevelView lv;

	private final LevelGenerator lGenerator;

	/**
	 *
	 * @param w
	 * @param h
	 * @param ts
	 * @param renderScale
	 */
	public LevelController(final double w, final double h, final double ts, final int renderScale) {
		this.lGenerator = new LevelGeneratorImpl(TILE_SIZE);
		this.generateMap(w, h, ts);
		this.lv.setScale(renderScale);
	}

	public final Level getLevel() {
		return this.l;
	}

	public final void generateMap(final double w, final double h, final double ts) {
		final Pair<Level, LevelView> result = this.lGenerator.loadLevel(w, h, ts);
		this.l = result.getKey(); 
		this.lv = result.getValue();
	}

	/**
	 * @return Level Map.
	 */
	public final Level getMap() {
		return this.l;
	}

	/**
	 * @return Set<Pair<Point2D, Image>> Map elements coordinates
	 */
	public final Set<Pair<Point2D, Image>> render() {
		return this.lv.renderLevelMap();
	}

	/**
	 *
	 * @return {@link LevelView}
	 */
	public final LevelView getLevelView() {
		return this.lv;
	}

	/**
	 *
	 * @param scale
	 */
	public final void resize(final double scale) {
		this.lv.setScale(scale);
	}
}
