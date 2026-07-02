//package it.unibo.oop.test.game.actor.robot;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.World;
//
//import it.unibo.oop.crossline.game.actor.robot.RobotImpl;
//
///**
// * Test class for RobotImpl.
// */
//public class RobotImplTest {
//
//    /**
//     * Zero value.
//     */
//    private static final int ZERO = 0;
//    /**
//     * One value.
//     */
//    private static final int ONE = 1;
//    /**
//     * First robot.
//     */
//    private RobotImpl robot1;
//    /**
//     * Second robot.
//     */
//    private RobotImpl robot2;
//    /**
//     * Same as first robot.
//     */
//    private RobotImpl sameAsRobot1;
//    /**
//     * Unimplemented robot.
//     */
//    private static final RobotImpl UNIMPLEMENTED_ROBOT = null;
//    private static final double HEALTH_1 = 50.0f;
//    private static final double HEALTH_2 = 100.0f;
//    private static final float EARTH_GRAVITY = -9.8f;
//
//    /**
//     * SetUp method for instance robots.
//     * 
//     * @throws java.lang.Exception generic exception
//     */
//    @Before
//    public void setUp() throws Exception {
//        final World world = new World(new Vector2(0, EARTH_GRAVITY), true);
//        robot1 = new RobotImpl(ZERO, (float) HEALTH_1, null, UNIMPLEMENTED_ROBOT, ZERO, null, world);
//        robot2 = new RobotImpl(10, (float) HEALTH_2, null, robot1, ONE, null, world);
//        sameAsRobot1 = robot1;
//    }
//
//    /**
//     * Test method for
//     * {@link it.unibo.oop.crossline.game.actor.robot.RobotImpl#hashCode()}.
//     */
//    @Test
//    public final void testHashCode() {
//        assertNotEquals(robot1.hashCode(), ZERO);
//        assertNotEquals(robot2.hashCode(), ZERO);
//        assertNotEquals(robot1.hashCode(), robot2.hashCode());
//        assertEquals("robot1 equals to sameAsButton1", robot1.hashCode(), sameAsRobot1.hashCode());
//    }
//
//    /**
//     * Test method for
//     * {@link it.unibo.oop.crossline.game.actor.robot.RobotImpl#RobotImpl(float, float, com.badlogic.gdx.math.Vector2, it.unibo.oop.crossline.game.attributes.Physical, int, it.unibo.oop.crossline.game.weapon.Weapon, com.badlogic.gdx.physics.box2d.World)}.
//     */
//    @Test
//    public final void testRobotImpl() {
//        assertNotNull("robot1 is not null", robot1);
//        assertNotNull("robot2 is not null", robot2);
//        assertNotNull("sameAsButton1 is not null", sameAsRobot1);
//        assertNull("UNIMPLEMENTED_ROBOT is null", UNIMPLEMENTED_ROBOT);
//    }
//
//    /**
//     * Test method for
//     * {@link it.unibo.oop.crossline.game.actor.robot.RobotImpl#getBody()}.
//     */
//    @Test
//    public final void testGetBody() {
//        assertNotNull("robot1's body is not null", robot1.getBody());
//    }
//
//    /**
//     * Test method for
//     * {@link it.unibo.oop.crossline.game.actor.robot.RobotImpl#isQueuedForDestruction()}.
//     */
//    @Test
//    public final void testIsQueuedForDestruction() {
//        assertTrue("robot1 is queued for descruction", robot1.isQueuedForDestruction());
//    }
//
//    /**
//     * Test method for
//     * {@link it.unibo.oop.crossline.game.actor.robot.RobotImpl#queueForDestruction()}.
//     */
//    @Test
//    public final void testQueueForDestruction() {
//        fail("Not yet implemented"); // TODO
//    }
//
//    /**
//     * Test method for
//     * {@link it.unibo.oop.crossline.game.actor.robot.RobotImpl#getHealth()}.
//     */
//    @Test
//    public final void testGetHealth() {
//        assertEquals("robot1's health equals to health", Double.valueOf(robot1.getHealth()), Double.valueOf(HEALTH_1));
//        assertEquals("robot2's health equals to health", Double.valueOf(robot2.getHealth()), Double.valueOf(HEALTH_2));
//    }
//
//    /**
//     * Test method for
//     * {@link it.unibo.oop.crossline.game.actor.robot.RobotImpl#applyDamage(float)}.
//     */
//    @Test
//    public final void testApplyDamage() {
//        fail("Not yet implemented"); // TODO
//    }
//
//    /**
//     * Test method for
//     * {@link it.unibo.oop.crossline.game.actor.robot.RobotImpl#addObserver(java.util.Observer)}.
//     */
//    @Test
//    public final void testAddObserverObserver() {
//        fail("Not yet implemented"); // TODO
//    }
//
//    /**
//     * Test method for
//     * {@link it.unibo.oop.crossline.game.actor.robot.RobotImpl#getTarget()}.
//     */
//    @Test
//    public final void testGetTarget() {
//        assertNotNull("robot1's target is not null", robot1.getWeapon());
//    }
//
//    /**
//     * Test method for
//     * {@link it.unibo.oop.crossline.game.actor.robot.RobotImpl#getTeam()}.
//     */
//    @Test
//    public final void testGetTeam() {
//        assertNotNull("robot1's team is not null", robot1.getTeam());
//    }
//
//    /**
//     * Test method for
//     * {@link it.unibo.oop.crossline.game.actor.robot.RobotImpl#getWeapon()}.
//     */
//    @Test
//    public final void testGetWeapon() {
//        assertNotNull("robot1's weapon is not null", robot1.getWeapon());
//    }
//
//    /**
//     * Test method for
//     * {@link it.unibo.oop.crossline.game.actor.robot.RobotImpl#equals(java.lang.Object)}.
//     */
//    @Test
//    public final void testEqualsObject() {
//        assertNotEquals(robot1, robot2);
//        assertEquals("robot1 equals to sameAsRobot1", robot1, sameAsRobot1);
//    }
//
//    /**
//     * Test method for
//     * {@link it.unibo.oop.crossline.game.actor.robot.RobotImpl#toString()}.
//     */
//    @Test
//    public final void testToString() {
//        assertNotNull("robot1's string is not null", robot1.toString());
//    }
//
//}
