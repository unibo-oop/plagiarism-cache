package boxhead.model.entities.utils;

import javafx.geometry.Point2D;

public enum Direction {

	EAST(1,0,0),
	
	SOUTH_EAST(0.707,0.707,45),
	
	SOUTH(0,1,90),

	SOUTH_WEST(-0.707,0.707,135),
	
	WEST(-1,0,180),
	
	NORTH_WEST(-0.707,-0.707,225),
	
	NORTH(0,-1, 270),
	
	NORTH_EAST(0.707,-0.707,315),
		
	NULL(0,0,-1);
	
	private final Point2D direction;
	private final double angle;
	
	Direction(final double x, final double y, final double angle) {
		this.direction = new Point2D(x, y);
		this.angle = angle;
	}
	
	public final double getX() {
		return this.direction.getX();
	}
	
	public final double getY() {
		return this.direction.getY();
	}
	
	public final Point2D traduce() {
		return this.direction;
	}
	
	public final double getAngle() {
		return this.angle;
	}
	
	public final Direction getNext() {
		switch(this) {
		case NORTH:
			return Direction.NORTH_EAST;
		case NORTH_EAST:
			return Direction.EAST;
		case EAST:
			return Direction.SOUTH_EAST;
		case SOUTH_EAST:
			return Direction.SOUTH;
		case SOUTH:
			return Direction.SOUTH_WEST;
		case SOUTH_WEST:
			return Direction.WEST;
		case WEST:
			return Direction.NORTH_WEST;
		case NORTH_WEST:
			return Direction.NORTH;
		case NULL:
			return null;
	}
		return null;
	}
	
	public final Direction getPrev() {
		switch(this) {
		case NORTH:
			return Direction.NORTH_WEST;
		case NORTH_WEST:
			return Direction.WEST;
		case WEST:
			return Direction.SOUTH_WEST;
		case SOUTH_WEST:
			return Direction.SOUTH;
		case SOUTH:
			return Direction.SOUTH_EAST;
		case SOUTH_EAST:
			return Direction.EAST;
		case EAST:
			return Direction.NORTH_EAST;
		case NORTH_EAST:
			return Direction.NORTH;
		case NULL:
			return null;
	}
		return null;
	}
	
	public final Point2D getShotOffset() {
		switch(this) {
		case NORTH:
			return new Point2D(35, 0);
		case NORTH_EAST:
			return new Point2D(40, 10);
		case EAST:
			return new Point2D(35, 25);
		case SOUTH_EAST:
			return new Point2D(25, 30);
		case SOUTH:
			return new Point2D(10, 35);
		case SOUTH_WEST:
			return new Point2D(0, 20);
		case WEST:
			return new Point2D(0, 25);
		case NORTH_WEST:
			return new Point2D(10, 0);
		case NULL:
			return null;
	}
		return null;
	}
}
