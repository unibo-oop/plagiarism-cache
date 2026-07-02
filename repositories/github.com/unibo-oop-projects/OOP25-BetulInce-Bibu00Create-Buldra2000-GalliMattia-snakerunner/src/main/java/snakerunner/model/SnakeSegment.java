package snakerunner.model;

import snakerunner.commons.Point2D;

/**
 * The SnakeSegment class represents a single segment of the snake.
 */
public final class SnakeSegment {

    /**
     * Enum representing the type of a snake segment.
     */
    public enum SegmentType {
        HEAD,
        BODY_STRAIGHT,
        BODY_CURVE,
        TAIL
    }

    private final Point2D<Integer, Integer> pos;
    private final SegmentType type;
    /*where the head is looking */
    private final Direction facing;
    /*direction of the previous piece closer to the head */
    private final Direction toHead;
    /*direction the next piece closer to the tail */
    private final Direction toTail;

    /**
     * Constructs a SnakeSegment with the specified position, type, facing direction, and directional information.
     * 
     * @param pos The position of the snake segment.
     * @param type The type of the snake segment.
     * @param facing The direction the head is facing (if this is a head segment).
     * @param toHead The direction towards the previous segment closer to the head.
     * @param toTail The direction towards the next segment closer to the tail.
     */
    public SnakeSegment(final Point2D<Integer, Integer> pos, final SegmentType type,
                        final Direction facing, final Direction toHead, final Direction toTail) {

        this.pos = new Point2D<>(pos.getX(), pos.getY());
        this.type = type;
        this.facing = facing;
        this.toHead = toHead;
        this.toTail = toTail;
    }

    /**
     * Returns the position of this snake segment.
     * 
     * @return the position of this snake segment.
     */
    public Point2D<Integer, Integer> getPos() {
        return new Point2D<>(pos.getX(), pos.getY());
    }

    /**
     * Returns the type of this snake segment.
     * 
     * @return the type of this snake segment.
     */
    public SegmentType getType() {
        return type;
    }

    /**
     * Returns the facing direction of the head segment.
     * 
     * @return the facing direction of the head segment.
     */
    public Direction getFacing() {
        return facing;
    }

    /**
     * Returns the direction towards the segment closer to the head.
     * 
     * @return the direction towards the segment closer to the head.
     */
    public Direction getToHead() {
        return toHead;
    }

    /**
     * Returns the direction towards the segment closer to the tail.
     * 
     * @return the direction towards the segment closer to the tail.
     */
    public Direction getToTail() {
        return toTail;
    }
}
