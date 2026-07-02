package test;

import static org.junit.Assert.assertNotEquals;

import java.util.Optional;

import model.enemy.Enemy;
import model.enemy.Hawk;
import model.enemy.Vase;
import model.entities.EnemyEnvironment;
import model.entities.Position;
import utils.Pair;

/**
 * Test the enemies.
 */
public class EnemyTest {

    private static final double DIMENSIONS = 10;
    private final Enemy vase = new Vase(new Pair<>(DIMENSIONS, DIMENSIONS),
            new EnemyEnvironment(new Position(), DIMENSIONS / 2, DIMENSIONS / 2, Optional.empty()));
    private final Enemy hawk = new Hawk(new Pair<>(DIMENSIONS, DIMENSIONS),
            new EnemyEnvironment(new Position(), DIMENSIONS / 2, DIMENSIONS / 2, Optional.empty()));

    /**
     * Test move vase.
     */
    @org.junit.Test
    public void testMoveVase() {
        final Position pos = new Position(this.vase.getPosition().getX(), this.vase.getPosition().getY());
        this.vase.moveDown();
        this.vase.moveDown();
        this.vase.moveDown();
        assertNotEquals(this.vase.getPosition(), pos);
    }

    /**
     * Move vase in directions not allowed.
     */
    @org.junit.Test(expected = UnsupportedOperationException.class)
    public void testNotMoveVase() {
        this.vase.moveRight();
        this.vase.moveLeft();
    }

    /**
     * Test move hawk.
     */
    @org.junit.Test
    public void testMoveHawk() {
        final Position pos = new Position(this.hawk.getPosition().getX(), this.hawk.getPosition().getY());
        this.hawk.moveRight();
        this.hawk.moveRight();
        this.hawk.moveRight();
        assertNotEquals(this.hawk.getPosition(), pos);
    }

    /**
     * Move hawk in directions not allowed.
     */
    @org.junit.Test(expected = UnsupportedOperationException.class)
    public void testNotMoveHawk() {
        this.hawk.moveUp();
        this.vase.moveLeft();
    }
}
