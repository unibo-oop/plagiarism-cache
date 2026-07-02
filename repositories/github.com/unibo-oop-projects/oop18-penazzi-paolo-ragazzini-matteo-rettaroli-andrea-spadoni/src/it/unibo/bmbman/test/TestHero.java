package it.unibo.bmbman.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import it.unibo.bmbman.controller.SoundsController;
import it.unibo.bmbman.model.TerrainFactoryImpl;
import it.unibo.bmbman.model.collision.CollisionImpl;
import it.unibo.bmbman.model.entities.Hero;
import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.entities.powerups.Door;
import it.unibo.bmbman.model.entities.powerups.Key;
import it.unibo.bmbman.model.utilities.Direction;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.model.utilities.Velocity;

/**
 * Class to test Hero.
 */
public class TestHero {
    private static final Position POSITION = new Position(TerrainFactoryImpl.PLAYER_POSITION.getX() + Velocity.SPEED * 3, 
            TerrainFactoryImpl.PLAYER_POSITION.getY() + Velocity.SPEED * 2);
    private final Hero hero = new HeroImpl();
    /**
     * Test hero's position.
     */
    @Test
    public void testHeroMovement() {
        /*
         * Test initial hero's position.
         */
        assertEquals(TerrainFactoryImpl.PLAYER_POSITION, hero.getPosition());
        /*
         * Test initial hero state.
         */
        assertEquals(hero.getDirection(), Direction.IDLE);
        /*
         * Test of right movement of hero 
         */
        final Position newRightPosition = new Position(TerrainFactoryImpl.PLAYER_POSITION.getX() + Velocity.SPEED, TerrainFactoryImpl.PLAYER_POSITION.getY());
        hero.setDirection(Direction.RIGHT);
        hero.update();
        assertEquals(newRightPosition, hero.getPosition());
        //Move hero in his initial position
        hero.setPosition(TerrainFactoryImpl.PLAYER_POSITION);
        /*
         * Test of down movement
         */
        final Position newDownPosition = new Position(TerrainFactoryImpl.PLAYER_POSITION.getX(), TerrainFactoryImpl.PLAYER_POSITION.getY() + Velocity.SPEED);
        hero.setDirection(Direction.DOWN);
        hero.update();
        assertEquals(newDownPosition, hero.getPosition());
        /*
         * Test of left movement
         */
        hero.setPosition(TerrainFactoryImpl.PLAYER_POSITION);
        final Position newLeftPosition = new Position(TerrainFactoryImpl.PLAYER_POSITION.getX() - Velocity.SPEED, TerrainFactoryImpl.PLAYER_POSITION.getY());
        hero.setDirection(Direction.LEFT);
        hero.update();
        assertEquals(newLeftPosition, hero.getPosition());
        /*
         * Test of up movement
         */
        hero.setPosition(TerrainFactoryImpl.PLAYER_POSITION);
        final Position newUpPosition = new Position(TerrainFactoryImpl.PLAYER_POSITION.getX(), TerrainFactoryImpl.PLAYER_POSITION.getY() - Velocity.SPEED);
        hero.setDirection(Direction.UP);
        hero.update();
        assertEquals(newUpPosition, hero.getPosition());
        /*
         * Test multiple movement
         */
        hero.setPosition(TerrainFactoryImpl.PLAYER_POSITION);
        final Position newPosition = POSITION;
        hero.setDirection(Direction.RIGHT);
        hero.update();
        hero.update();
        hero.setDirection(Direction.DOWN);
        hero.update();
        hero.update();
        hero.setDirection(Direction.RIGHT);
        hero.update();
        assertEquals(newPosition, hero.getPosition());
    }
    /**
     * Test hero pick up Key and Win.
     */
    @Test
    public void testWin() {
        final Key key = new Key(POSITION);
        final SoundsController s = new SoundsController();
        s.setEffectsOff();
        assertFalse(hero.hasKey());
        assertFalse(hero.hasWon());
        key.onCollision(new CollisionImpl(hero, POSITION));
        assertTrue(hero.hasKey());
        assertFalse(hero.hasWon());
        final Door door = new Door();
        door.onCollision(new CollisionImpl(hero, TerrainFactoryImpl.DOOR_POSITION));
        assertTrue(hero.hasWon());
    }
}
