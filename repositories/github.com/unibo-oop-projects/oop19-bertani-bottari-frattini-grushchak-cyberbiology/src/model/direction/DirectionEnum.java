package model.direction;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Implementation of interface for all possible directions. Each compass points
 * match a pair of integer.
 *
 */
public enum DirectionEnum implements Direction {
    /**
     *  Direction to east (1, 0).
     */
    EAST(1, 0),
    /**
     * Direction to northeast (1, -1).
     */
    NORTHEAST(1, -1),
    /**
     * Direction to north (0, -1).
     */
    NORTH(0, -1),
    /**
     * Direction to northwest (-1, -1).
     */
    NORTHWEST(-1, -1),
    /**
     * Direction to west (-1, 0).
     */
    WEST(-1, 0),
    /**
     * Direction to southwest (-1, 1).
     */
    SOUTHWEST(-1, 1),
    /**
     * Direction to south (0, 1).
     */
    SOUTH(0, 1),
    /**
     * Direction to southeast (1, 1).
     */
    SOUTHEAST(1, 1);

    /**
     * The number of directions.
     */
    public static final int SIZE = DirectionEnum.values().length;

    /**
     * The map from index to direction.
     */
    private static final Map<Integer, DirectionEnum>  DIRECTION_MAP = new HashMap<>() {
        private static final long serialVersionUID = -7069331028250445469L;
        {
            for (final DirectionEnum direction : DirectionEnum.values()) {
                this.put(direction.ordinal(), direction);
            }
        }
    };

    private final int x;
    private final int y;

    DirectionEnum(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return direction of the index.
     * @param index of direction.
     * @return the direction of this index.
     * @throws IllegalArgumentException 
     *              if the index does not correspond to any direction.
     */
    public static Direction getDirection(final int index) {
        if (!DIRECTION_MAP.containsKey(index)) {
            throw new IllegalArgumentException("The index is not correspond to any direction.");
        }
        return DIRECTION_MAP.get(index);
    }

    @Override
    public int movementAlongX() {
        return this.x;
    }

    @Override
    public int movementAlongY() {
        return this.y;
    }

    @Override
    public int getIndex() {
        return this.ordinal();
    }

}
