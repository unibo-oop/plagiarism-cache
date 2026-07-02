package snakerunner.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import snakerunner.commons.Point2D;

/**
 * The Snake class represents the player's snake in the Snake Runner game.
 */
public final class Snake {

    private static final int FIXED_SIZE = 5; //the lenght never changes
    private final List<SnakeSegment> body = new LinkedList<>(); //the list
    private Direction currentDirection = Direction.RIGHT; //initial direction

    /**
     * Constructs a Snake object with the specified starting position.
     *
     * @param startPosition The starting position of the snake's head.
     */
    public Snake(final Point2D<Integer, Integer> startPosition) {

        for (int i = 0; i < FIXED_SIZE; i++) {
            final Point2D<Integer, Integer> p = new Point2D<>(startPosition.getX() - i, startPosition.getY());
            body.add(new SnakeSegment(p, SnakeSegment.SegmentType.BODY_STRAIGHT, null, null, null));
        }
        updateLogic();

    }

    /**
     * the movement like a train : the ereditate the position left by the previous pieces.
     */
    public void move() {
        //actual head position
        final Point2D<Integer, Integer> headPos = body.get(0).getPos();
        int nextX = headPos.getX();
        int nextY = headPos.getY();

        switch (currentDirection) {
            case UP -> nextY -= 1;
            case DOWN -> nextY += 1;
            case LEFT -> nextX -= 1;
            case RIGHT -> nextX += 1;
        }

        body.add(
            0,
            new SnakeSegment(
                new Point2D<>(nextX, nextY),
                SnakeSegment.SegmentType.HEAD,
                currentDirection,
                null,
                null
            )
        );

        body.remove(body.size() - 1);
        updateLogic();

    }

    /**
     * change direction so it doesn't turn 180 degrees.
     *
     * @param d the new direction of the snake.
     */
    public void setDirection(final Direction d) {
        if (d == null) {
            return;
        }
        if (currentDirection == Direction.UP && d == Direction.DOWN) {
            return;
        }
        if (currentDirection == Direction.DOWN && d == Direction.UP) {
            return;
        }
        if (currentDirection == Direction.LEFT && d == Direction.RIGHT) {
            return;
        }

        if (currentDirection == Direction.RIGHT && d == Direction.LEFT) {
            return;
        }

        currentDirection = d;

    }

    /**
     * returns the current direction of the snake.
     *
     * @return the current direction of the snake.
     */
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    /**
     * returns the head position.
     *
     * @return the head position of the snake.
     */
    public Point2D<Integer, Integer> getHead() {
        return body.get(0).getPos();
    }

    /**
     * Return the list of the segment for the view to draw.
     *
     * @return the list of snake segments.
     */
    public List<SnakeSegment> getFullBody() {
        return Collections.unmodifiableList(body);
    }

    /**
     * collision with itself.
     *
     * @return true if the snake is colliding with itself, false otherwise.
     */
    public boolean isCollidingWithItself() {
        final Point2D<Integer, Integer> head = body.get(0).getPos();
        for (int i = 1; i < body.size(); i++) {
            final Point2D<Integer, Integer> p = body.get(i).getPos();
            if (head.getX().equals(p.getX()) && head.getY().equals(p.getY())) {
                return true;
            }
        }
        return false;

    }

    /**
     * Updates the visual state and orientation of each snake segment
     * based on its position relative to neighboring segments.
     */
    private void updateLogic() {
        if (body.isEmpty()) {
            return;
        }

        for (int i = 0; i < body.size(); i++) {
            final Point2D<Integer, Integer> curr = body.get(i).getPos();

            if (i == 0) {
                final Direction toTail = getRelativeDirection(curr, body.get(1).getPos());
                body.set(i, new SnakeSegment(curr, SnakeSegment.SegmentType.HEAD, currentDirection, null, toTail));

            } else if (i == body.size() - 1) {
                final Direction toHead = getRelativeDirection(curr, body.get(i - 1).getPos());
                body.set(i, new SnakeSegment(curr, SnakeSegment.SegmentType.TAIL, null, toHead, null));

            } else {
                final Point2D<Integer, Integer> prev = body.get(i - 1).getPos();
                final Point2D<Integer, Integer> next = body.get(i + 1).getPos();

                final Direction toHead = getRelativeDirection(curr, prev);
                final Direction toTail = getRelativeDirection(curr, next);

                final SnakeSegment.SegmentType t = isStraight(prev, next)
                    ? SnakeSegment.SegmentType.BODY_STRAIGHT
                    : SnakeSegment.SegmentType.BODY_CURVE;

                body.set(i, new SnakeSegment(curr, t, null, toHead, toTail));
            }
        }
    }

    private boolean isStraight(final Point2D<Integer, Integer> prev, final Point2D<Integer, Integer> next) {
        return prev.getX().equals(next.getX()) || prev.getY().equals(next.getY());
    }

    private Direction getRelativeDirection(final Point2D<Integer, Integer> from, final Point2D<Integer, Integer> to) {
        if (to.getX() > from.getX()) {
            return Direction.RIGHT;
        }

        if (to.getX() < from.getX()) {
            return Direction.LEFT;
        }

        if (to.getY() > from.getY()) {
            return Direction.DOWN;
        }

        return Direction.UP;

    }

}


