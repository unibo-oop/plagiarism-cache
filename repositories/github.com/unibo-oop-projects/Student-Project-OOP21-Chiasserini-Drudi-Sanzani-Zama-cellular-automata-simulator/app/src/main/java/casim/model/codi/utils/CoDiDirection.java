package casim.model.codi.utils;

import java.util.EnumMap;
import java.util.Map;

import casim.utils.coordinate.Coordinates3D;
import casim.utils.coordinate.CoordinatesUtil;

/**
 * Enum containing all the neighbors directions.
 */
public enum CoDiDirection {
    /**
     * The north direction.
     */
    NORTH(CoordinatesUtil.of(0, 0, 1)),
    /**
     * The south direction.
     */
    SOUTH(CoordinatesUtil.of(0, 0, -1)),
    /**
     * The west direction.
     */
    WEST(CoordinatesUtil.of(-1, 0, 0)),
    /**
     * The east direction.
     */
    EAST(CoordinatesUtil.of(1, 0, 0)),
    /**
     * The top direction.
     */
    TOP(CoordinatesUtil.of(0, 1, 0)),
    /**
     * The bottom direction.
     */
    BOTTOM(CoordinatesUtil.of(0, -1, 0));

    private static final Map<CoDiDirection, CoDiDirection> OPPOSITE_DIRECTION = new EnumMap<>(CoDiDirection.class);
    private final Coordinates3D<Integer> offset;

    CoDiDirection(final Coordinates3D<Integer> offset) {
        this.offset = offset;
    }

    static {
        OPPOSITE_DIRECTION.put(NORTH, SOUTH);
        OPPOSITE_DIRECTION.put(SOUTH, NORTH);
        OPPOSITE_DIRECTION.put(WEST, EAST);
        OPPOSITE_DIRECTION.put(EAST, WEST);
        OPPOSITE_DIRECTION.put(TOP, BOTTOM);
        OPPOSITE_DIRECTION.put(BOTTOM, TOP);

    }

    /**
     * Return the opposite {@link CoDiDirection} to the current one.
     * 
     * @return the opposite {@link CoDiDirection}.
     */
    public CoDiDirection getOpposite() { 
        return OPPOSITE_DIRECTION.get(this);
    }

    /**
     * Return the {@link Coordinates3D} offset of the {@link CoDiDirection}.
     * 
     * @return the offset of the direction.
     */
    public Coordinates3D<Integer> getDirectionOffset() {
        return this.offset;
    }

}
