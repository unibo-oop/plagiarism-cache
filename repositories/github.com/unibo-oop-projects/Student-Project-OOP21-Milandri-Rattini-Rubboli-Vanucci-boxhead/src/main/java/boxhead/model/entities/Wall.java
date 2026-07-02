package boxhead.model.entities;

import javafx.geometry.Point2D;

public class Wall extends AbstractEntity {
	
	private static final double WALL_WIDTH = 32;
	private static final double WALL_HEIGHT = 32;
	
	/**
	 * Constructor of the wall.
	 * @param position
	 */
	public Wall(final Point2D position) {
		super(position,EntityType.WALL);
		super.setBoundingBox(WALL_WIDTH, WALL_HEIGHT);
	}
}
