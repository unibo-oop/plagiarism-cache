package it.unibo.bmbman.test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import it.unibo.bmbman.model.TerrainFactoryImpl;
import it.unibo.bmbman.model.collision.CollisionImpl;
import it.unibo.bmbman.model.collision.EntityCollisionManager;
import it.unibo.bmbman.model.collision.EntityCollisionManagerImpl;
import it.unibo.bmbman.model.entities.Hero;
import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.entities.Monster;
import it.unibo.bmbman.model.entities.Wall;
import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;

/**
 * JUnit Test for  Collision Manager and entityController.
 *
 */
public class TestCollision {
    private static final int HERO_LIVES = 3;
    private static final Position MONSTER_POSITION = new Position(60, 70);
    private static final Position WALL_POSITION = new Position(100, 50);
    private static final Position NEAR_WALL_POSITION = new Position(WALL_POSITION.getX() - TerrainFactoryImpl.CELL_DIMENSION / 2,
                                                                    WALL_POSITION.getY() - TerrainFactoryImpl.CELL_DIMENSION / 2);
    private final Hero hero = new HeroImpl();
    private final Monster monster = new Monster(MONSTER_POSITION);
    private final Wall wall = new Wall(WALL_POSITION, new Dimension(TerrainFactoryImpl.CELL_DIMENSION * ScreenToolUtils.SCALE, TerrainFactoryImpl.CELL_DIMENSION * ScreenToolUtils.SCALE));
    private final EntityCollisionManager heroMng = new EntityCollisionManagerImpl(hero.getCollisionComponent());
    /**
     * Used to test wall Collision.
     * Hero doesn't lose lives when he collide with wall
     */
    @Test
    public void testWallCollision() {
        assertFalse(heroMng.checkCollision(wall, hero.getCollisionComponent().getHitbox()));
        assertEquals(HERO_LIVES, hero.getLives());
        hero.setPosition(new Position(WALL_POSITION.getX() * ScreenToolUtils.SCALE, WALL_POSITION.getY() * ScreenToolUtils.SCALE));
        assertTrue(heroMng.checkCollision(wall, hero.getCollisionComponent().getHitbox()));
        hero.onCollision(new CollisionImpl(wall, WALL_POSITION));
        assertEquals(HERO_LIVES, hero.getLives());
        hero.setPosition(new Position(NEAR_WALL_POSITION.getX() * ScreenToolUtils.SCALE, NEAR_WALL_POSITION.getY() * ScreenToolUtils.SCALE));
        assertTrue(heroMng.checkCollision(wall, hero.getCollisionComponent().getHitbox()));
        assertEquals(HERO_LIVES, hero.getLives());
    }
    /**
     * Used to test Monster collision.
     * Hero lose a life when he collide with monster
     */
    @Test
    public void testMonsterCollision() {
        hero.setPosition(TerrainFactoryImpl.PLAYER_POSITION);
        assertEquals(HERO_LIVES, hero.getLives());
        hero.setPosition(MONSTER_POSITION);
        assertTrue(heroMng.checkCollision(monster, hero.getCollisionComponent().getHitbox()));
        hero.onCollision(new CollisionImpl(monster, MONSTER_POSITION));
        assertEquals(HERO_LIVES - 1, hero.getLives());
    }
    /**
     * Used to test hero dead after three collision with Monster.
     * @throws InterruptedException 
     */
    @Test
    public void testHeroDead() throws InterruptedException {
       assertEquals(HERO_LIVES, hero.getLives());
       hero.onCollision(new CollisionImpl(monster, MONSTER_POSITION));
       assertEquals(HERO_LIVES - 1, hero.getLives());
       TimeUnit.SECONDS.sleep(2);
       hero.onCollision(new CollisionImpl(monster, MONSTER_POSITION));
       TimeUnit.SECONDS.sleep(2);
       hero.onCollision(new CollisionImpl(monster, MONSTER_POSITION));
       assertFalse(hero.isAlive());
    }
}
