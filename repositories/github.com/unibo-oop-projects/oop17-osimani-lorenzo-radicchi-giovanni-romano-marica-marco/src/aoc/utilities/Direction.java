package aoc.utilities;

/**
 * This enumeration implements the possible  directions of the mother's movement.
 */
public enum Direction {
    
	UP(-1),
	DOWN(+1);

	private Integer direction;

	private Direction(final Integer direction) {
		this.direction = direction;
	}

	public Integer getDir() {
		return this.direction;
	}
}
