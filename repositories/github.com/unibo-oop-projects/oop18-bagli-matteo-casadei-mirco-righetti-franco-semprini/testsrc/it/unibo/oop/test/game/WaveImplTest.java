package it.unibo.oop.test.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.oop.crossline.game.actor.player.PlayerImpl;
import it.unibo.oop.crossline.game.actor.robot.Robot;
import it.unibo.oop.crossline.game.actor.robot.RobotImpl;
import it.unibo.oop.crossline.game.wave.WaveImpl;

/**
 * Test class for WaveImpl.
 */
public class WaveImplTest {

    private static final float EARTH_GRAVITY = -9.8f;
    private static final float DIFFICULTY = 0;
    /**
     * Wave.
     */
    private WaveImpl wave;
    /**
     * World.
     */
    private World world;
    private final Vector2 nullVector = new Vector2(0, 0); 

    /**
     * SetUp method for instance buttons.
     * 
     * @throws java.lang.Exception generic exception
     */
    @Before
    public void setUp() throws Exception {
        world = new World(new Vector2(0, EARTH_GRAVITY), true);
        wave = new WaveImpl(new PlayerImpl(world, nullVector), DIFFICULTY);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.game.wave.WaveImpl#WaveImpl(it.unibo.oop.crossline.game.actor.player.Player, float)}.
     */
    @Test
    public final void testWaveImpl() {
        assertNotNull("wave is not null", wave);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.game.wave.WaveImpl#update(java.util.Observable, java.lang.Object)}.
     */
    @Test
    public final void testUpdate() {
        final List<Robot> list = new ArrayList<>();
        final RobotImpl r1 = new RobotImpl(10, 100, nullVector, null, 0, null, world);
        final RobotImpl r2 = new RobotImpl(20, 200, nullVector, r1, 0, null, world);
        final RobotImpl r3 = new RobotImpl(30, 300, nullVector, r2, 0, null, world);
        list.add(r1);
        list.add(r2);
        list.add(r3);
        wave.getRobots().add(r1);
        wave.getRobots().add(r2);
        wave.getRobots().add(r3);
        assertEquals("wave's robot's list equals to list", wave.getRobots(), list);
        wave.update(r1, null);
        assertNotEquals("wave's robot's list not equals to list because one element has been removed in wave's robot's list", wave.getRobots(), list);
        list.remove(r1);
        assertEquals("wave's robot's list equals to list", wave.getRobots(), list);
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.game.wave.WaveImpl#getRobots()}.
     */
    @Test
    public final void testGetRobots() {
        assertNotNull("wave has a list of robots", wave.getRobots());
    }

    /**
     * Test method for
     * {@link it.unibo.oop.crossline.game.wave.WaveImpl#getDifficulty()}.
     */
    @Test
    public final void testGetDifficulty() {
        assertEquals("wave difficulty equals to DIFFICULTY",  Double.valueOf(wave.getDifficulty()), Double.valueOf(DIFFICULTY));
    }

    /**
     * Test method for {@link it.unibo.oop.crossline.game.wave.WaveImpl#hasEnded()}.
     */
    @Test
    public final void testHasEnded() {
        assertTrue("wave has ended", wave.hasEnded());
    }

}
