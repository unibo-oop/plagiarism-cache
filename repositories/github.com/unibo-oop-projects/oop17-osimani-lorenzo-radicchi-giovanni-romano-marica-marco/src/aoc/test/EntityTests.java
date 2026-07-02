package aoc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import aoc.model.WorldConstants;
import aoc.model.entity.EntityInterface;
import aoc.model.entity.child.ChildFactory;
import aoc.model.entity.child.ChildFactoryInterface;
import aoc.model.entity.child.ChildInterface;
import aoc.model.entity.child.Children;
import aoc.model.entity.mother.Mother;
import aoc.model.entity.mother.MotherInterface;
import aoc.model.entity.slipper.Projectile;
import aoc.model.entity.slipper.Slipper;
import aoc.model.entity.slipper.SlipperInterface;
import aoc.model.level.Level;
import aoc.model.level.StoryLevel;
import aoc.utilities.Direction;
import aoc.utilities.Vector;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;
import org.omg.CORBA.portable.Streamable;


/**
 * Testing of Model features.
 */
public class EntityTests {
    
    @Test
    public void motherTest() {
	Vector motherStart = new Vector(0, WorldConstants.WORLD_HEIGHT * WorldConstants.CELL_WIDTH / 2);
        final MotherInterface mother = new Mother(new Vector(motherStart.getX(), motherStart.getY()), Projectile.BASIC_SLIPPER);
        mother.attach(new StoryLevel(1));
        assertTrue(mother.getPosition().equals(motherStart));
        mother.move(Direction.UP);
        motherStart = motherStart.increaseY(WorldConstants.CELL_WIDTH * Direction.UP.getDir());
        assertTrue(mother.getPosition().equals(motherStart));
        mother.move(Direction.DOWN);
        motherStart = motherStart.increaseY(WorldConstants.CELL_WIDTH * Direction.DOWN.getDir());
        assertTrue(mother.getPosition().equals(motherStart));
        mother.move(Direction.UP);
        mother.move(Direction.UP);
        mother.move(Direction.UP);
        motherStart = motherStart.increaseY(3 * WorldConstants.CELL_WIDTH * Direction.UP.getDir());
        assertTrue(mother.getPosition().equals(motherStart));
        final EntityInterface s = mother.attack();
        assertTrue(s instanceof SlipperInterface);
        assertTrue(mother.getPosition().equals(s.getPosition()));
        s.update();
        assertTrue(mother.getPosition().equals(motherStart));
    }
    
    @Test
    public void slipperTest() {
	Vector slipperStart =  new Vector(0, WorldConstants.WORLD_HEIGHT * WorldConstants.CELL_WIDTH / 2);
	final Slipper slipper = new Slipper(new Vector(slipperStart.getX(), slipperStart.getY()), Projectile.BASIC_SLIPPER);
	slipper.update();
	slipperStart = slipperStart.increaseWithVector(new Vector(Projectile.valueOf(slipper.getName()).getXSpeed(), Projectile.valueOf(slipper.getName()).getYSpeed()));
	assertEquals(slipperStart, slipper.getPosition());
	assertEquals(Projectile.valueOf(slipper.getName()).getDamage(), slipper.hit());
	assertFalse(slipper.isAlive());
	//checking position changes
	slipper.getPosition().increaseX(12);
	assertTrue(slipperStart.equals(slipper.getPosition()));
    }
    
    @Test
    public void childTest() {
	final ChildFactoryInterface factory = new ChildFactory();
	final Level level = new StoryLevel(1);
	Stream.of(Children.values()).forEach(type -> {
	    final Vector childStart = new Vector(WorldConstants.WORLD_WIDTH * WorldConstants.CELL_WIDTH,
                    WorldConstants.WORLD_HEIGHT * WorldConstants.CELL_WIDTH / 2);
            final ChildInterface child = (ChildInterface) factory.spawnChild(type, childStart, level);
            child.update();
            assertTrue(child.getPosition().equals(childStart.increaseWithVector(new Vector(type.getXSpeed(), type.getYSpeed()))));
            SlipperInterface slipper = new Slipper(child.getPosition(), Projectile.BASIC_SLIPPER);
            assertFalse(child.hitterListChecker(Arrays.asList(slipper)).isEmpty());
            assertFalse(child.isHit(Arrays.asList(slipper)).isEmpty());
            child.update();
            assertFalse(child.getPosition().equals(slipper.getPosition()));
	});
    }
}
