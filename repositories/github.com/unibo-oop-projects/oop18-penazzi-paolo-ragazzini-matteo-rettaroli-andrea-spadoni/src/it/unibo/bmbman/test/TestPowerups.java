package it.unibo.bmbman.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.bmbman.model.TerrainFactoryImpl;
import it.unibo.bmbman.model.collision.CollisionImpl;
import it.unibo.bmbman.model.entities.Hero;
import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.entities.powerups.BonusLife;
import it.unibo.bmbman.model.entities.powerups.MalusFreeze;
import it.unibo.bmbman.model.entities.powerups.MalusInvert;
import it.unibo.bmbman.model.entities.powerups.MalusLife;
import it.unibo.bmbman.model.entities.powerups.MalusSlow;
import it.unibo.bmbman.model.utilities.Direction;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;

/**
 * Class to test power-ups behavior.
 */
public class TestPowerups {
    /**
     * .
     */
    private static final Position POWERUP_POSITION = new Position(TerrainFactoryImpl.PLAYER_POSITION.getX(), 
                                                               TerrainFactoryImpl.PLAYER_POSITION.getY());
    private static final Position HERO_POSITION = new Position(TerrainFactoryImpl.PLAYER_POSITION.getX() + TerrainFactoryImpl.CELL_DIMENSION, 
                                                               TerrainFactoryImpl.PLAYER_POSITION.getY());
    private final Hero hero = new HeroImpl();
    private final BonusLife bonusLife = new BonusLife(POWERUP_POSITION);
    private final MalusLife malusLife = new MalusLife(POWERUP_POSITION);
    private final MalusFreeze malusFreeze = new MalusFreeze(POWERUP_POSITION);
    private final MalusInvert malusInvert = new MalusInvert(POWERUP_POSITION);
    private final MalusSlow malusSlow = new MalusSlow(POWERUP_POSITION);
    /**
     * Test if the bonus life effect is activated on collision.
     */
    @Test
    public void testBonusLifeEffect() {
        assertEquals(3, hero.getLives());
        bonusLife.onCollision(new CollisionImpl(this.hero, POWERUP_POSITION));
        assertEquals(4, hero.getLives());
    }
    /**
     * Test if the malus life effect is activated on collision.
     */
    @Test
    public void testMalusLifeEffect() {
        assertEquals(3, hero.getLives());
        malusLife.onCollision(new CollisionImpl(this.hero, POWERUP_POSITION));
        assertEquals(2, hero.getLives());
    }
    /**
     * Test if the malus freeze effect is activated on collision.
     */
    @Test
    public void testMalusFreezeEffect() {
        hero.setPosition(POWERUP_POSITION);
        malusFreeze.onCollision(new CollisionImpl(this.hero, POWERUP_POSITION));
        hero.setDirection(Direction.RIGHT);
        hero.update();
        assertEquals(hero.getPosition().getX(), POWERUP_POSITION.getX());
        assertEquals(hero.getPosition().getY(), POWERUP_POSITION.getY());
    }
    /**
     * Test if the malus freeze effect is activated on collision.
     * Set the hero direction to left. That means the velocity on the X axe should be -2.
     * In this case we took a Malus that invert the direction (velocity), so we check if the velocity is equals to 2.
     */
    @Test
    public void testMalusInvertEffect() {
        hero.setPosition(HERO_POSITION);
        malusInvert.onCollision(new CollisionImpl(this.hero, POWERUP_POSITION));
        hero.setDirection(Direction.LEFT);
        hero.update();
        assertEquals((int) 2 * ScreenToolUtils.SCALE, hero.getVelocity().getXcomponent());
    }
    /**
     * Test if the malus slow effect is activated on collision.
     * @throws InterruptedException 
     */
    @Test
    public void testMalusSlowEffect() throws InterruptedException {
        hero.setPosition(POWERUP_POSITION);
        malusSlow.onCollision(new CollisionImpl(this.hero, POWERUP_POSITION));
        hero.setDirection(Direction.RIGHT);
        hero.update();
        assertTrue(1 * ScreenToolUtils.SCALE == hero.getVelocity().getXcomponent());
    }
}
