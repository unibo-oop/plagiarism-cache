package it.unibo.isaccoop.test.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.isaccoop.model.common.Point2D;
import it.unibo.isaccoop.model.player.Player;
import it.unibo.isaccoop.model.common.Direction;

/**
 * Class to test the player.
 * */
class TestPlayer {
    private final Player player = new Player(null, null, null);
    private static final double START_POSITION = 10.0;

    /**
     * Testing the player movement in 4 direction.
     * */
    @Test
    void testMovement() {
        final Point2D pos = player.getCoords();
        assertEquals(new Point2D(START_POSITION, START_POSITION), pos);

        player.update(Direction.UP);
        final Point2D upPos = player.getCoords();
        assertEquals(new Point2D(START_POSITION, START_POSITION - player.getSpeed()), upPos);

        player.update(Direction.RIGHT);
        player.update(Direction.RIGHT);
        final Point2D rightPos = player.getCoords();
        assertEquals(new Point2D(START_POSITION + player.getSpeed() * 2, START_POSITION - player.getSpeed()), rightPos);

        player.update(Direction.LEFT);
        final Point2D leftPos = player.getCoords();
        assertEquals(new Point2D(START_POSITION + player.getSpeed(), START_POSITION - player.getSpeed()), leftPos);

        player.update(Direction.DOWN);
        final Point2D downPos = player.getCoords();
        assertEquals(new Point2D(START_POSITION + player.getSpeed(), START_POSITION), downPos);
    }
}
