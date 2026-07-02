package it.unibo.bmbman.test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.unibo.bmbman.controller.SoundsController;
import it.unibo.bmbman.controller.game.BombControllerImpl;
import it.unibo.bmbman.model.TerrainFactoryImpl;
import it.unibo.bmbman.model.entities.Block;
import it.unibo.bmbman.model.entities.BombImpl;
import it.unibo.bmbman.model.entities.Entity;
import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.entities.Monster;
import it.unibo.bmbman.model.utilities.BombState;
import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;
/**
 * JUnit Test for Bomb and BombController.
 *
 */
public class TestBomb {
    private BombControllerImpl bc;
    private BombImpl bomb;
    private HeroImpl hero;
    private final SoundsController sc = new SoundsController();
    private static final Position HERO_POS = new Position(TerrainFactoryImpl.PLAYER_POSITION.getX(), 
            TerrainFactoryImpl.PLAYER_POSITION.getY());
    private static final Position MONSTER1_POS = new Position(HERO_POS.getX() / ScreenToolUtils.SCALE 
            + TerrainFactoryImpl.CELL_DIMENSION, HERO_POS.getY() / ScreenToolUtils.SCALE);
    private static final Position MONSTER2_POS = new Position(HERO_POS.getX() / ScreenToolUtils.SCALE 
            + 5 * TerrainFactoryImpl.CELL_DIMENSION, HERO_POS.getY() / ScreenToolUtils.SCALE);
    private static final Position BLOCK_POS = new Position(HERO_POS.getX() / ScreenToolUtils.SCALE, 
            HERO_POS.getY() / ScreenToolUtils.SCALE + TerrainFactoryImpl.CELL_DIMENSION);
    private Block block;
    private Monster monster1;
    private Monster monster2;
    /**
     * {@inheritDoc}
     */
    @Before
    public void init() {
        this.hero = new HeroImpl();
        this.hero.setPosition(HERO_POS);
        this.bc = new BombControllerImpl();
        this.sc.setEffectsOff();
    }
    /**
     * Test bomb planted.
     */
    @Test
    public void testBombsplanted() {
        this.bomb = this.bc.plantBomb(this.hero).get();
        Assert.assertEquals(this.bomb.getPosition(), this.hero.getPosition());
        Assert.assertEquals(this.bomb.getState(), BombState.PLANTED);
        this.hero.incrementBombsNumber();
        Assert.assertEquals(this.hero.getBombsNumber(), 2);
        Assert.assertTrue(this.bc.plantBomb(hero).isPresent());
        Assert.assertTrue(!this.bc.plantBomb(hero).isPresent());
    }
    /**
     * Test explosion with range 3 and collisions with hero, monster and block.
     * @throws InterruptedException 
     */
    @Test 
    public void testExplosion() throws InterruptedException {
        this.monster1 = new Monster(MONSTER1_POS);
        this.monster2 = new Monster(MONSTER2_POS);
        this.block = new Block(BLOCK_POS, new Dimension(TerrainFactoryImpl.CELL_DIMENSION, 
                TerrainFactoryImpl.CELL_DIMENSION));
        this.bomb = this.bc.plantBomb(this.hero).get();
        Assert.assertTrue(this.bc.getBombsInExplosion().isEmpty());
        TimeUnit.SECONDS.sleep(3);
        this.bomb.update();
        Assert.assertTrue(this.bomb.getState() == BombState.IN_EXPLOSION);
        Assert.assertTrue(this.bc.getBombsInExplosion().size() == 1);
        Assert.assertFalse(this.bomb.remove());
        Assert.assertEquals(this.hero.getBombsNumber(), 1);
        Assert.assertTrue(!this.bc.plantBomb(hero).isPresent());
        Assert.assertTrue(this.bc.getBombsInExplosion().size() == 1);
        Assert.assertTrue(this.bc.getBombsToRemove().isEmpty());
        Set<Entity> set = new HashSet<>();
        set.add(monster1);
        set.add(monster2);
        set.add(hero);
        set.add(block);
        this.bc.collision(set);
        Assert.assertTrue(this.bomb.getState() == BombState.EXPLODED);
        Assert.assertTrue(this.bc.getBombsInExplosion().isEmpty());
        Assert.assertEquals(this.bc.getBombsToRemove().size(), 1);
        Assert.assertFalse(this.monster1.isAlive());
        Assert.assertTrue(this.monster2.isAlive());
        Assert.assertEquals(this.hero.getLives(), 2);
        Assert.assertTrue(this.block.remove());
    }
}
