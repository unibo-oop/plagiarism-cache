package it.unibo.bmbman.test;


import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import it.unibo.bmbman.model.collision.CollisionImpl;
import it.unibo.bmbman.model.entities.BombImpl;
import it.unibo.bmbman.model.entities.Hero;
import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.entities.Monster;
import it.unibo.bmbman.model.utilities.Direction;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.model.utilities.Velocity;

/**
 * Test class for monster.
 *
 */
public class TestMonster {

    private final Monster monster = new Monster(new Position(200, 200));
    private final Hero hero = new HeroImpl();
    private final BombImpl bomb = new BombImpl(new Position(100, 300), 3);
    /**
     * Test monster movement.
     */
    @Test
    public void testMonsterMovement() {
        /*
         * Test monster initial direction not IDLE.
         */
        assertNotEquals(Direction.IDLE, monster.getDirection());
        /*
         * Test monster initial velocity not zero.
         */
        assertNotEquals(Velocity.ZERO, monster.getVelocity());
        /*
         * Test monster continue moving it the same direction if it doesn't collide with nothing.
         */
        Direction precDir = monster.getDirection();
        monster.update();
        monster.update();
        assertEquals(precDir, monster.getDirection());
        /*
         * Test the fact that when a monster collides with the hero goes in the opposite direction
         */
        precDir = monster.getDirection();
        monster.onCollision(new CollisionImpl(hero, hero.getPosition()));
        monster.update();
        monster.getDirection();
        assertEquals(Direction.getOpposite(precDir), monster.getDirection());
    }
    /**
     * Test monster lives.
     * @throws InterruptedException 
     */
    @Test
    public void testMonsterLives() throws InterruptedException {
        /*
         * Test that the monster has only one life
         */
        assertEquals(1, monster.getLives());
        /*
         * Test that if collides with an explosion the monster dies
         */
        bomb.startTimer();
        TimeUnit.SECONDS.sleep(3);
        bomb.update();
        monster.onCollision(new CollisionImpl(bomb, bomb.getPosition()));
        assertFalse(monster.isAlive());
    }
}
