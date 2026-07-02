package model;

import org.junit.jupiter.api.Test;

import model.entity.CollidableEntity;
import model.entity.CollidableEntityImpl;
import model.entity.ConditionalEntity;
import model.entity.Entity;
import model.entity.EntityImpl;
import model.entity.MovingEntity;
import model.entity.MovingEntityImpl;
import model.entity.RotatingEntity;
import model.entity.RotatingEntityImpl;
import utilities.math.Point2D;
import utilities.math.Point2DImpl;
import utilities.math.Vector2D;
import utilities.math.Vector2DImpl;

/**
 * This set of tests checks the correct implementation of the entity hierarchy.
 */
public class TestEntityHierarchy {

    /**
     * This test checks the correct implementation of Entity.
     */
    @Test
    public void testEntityImpl() {
        //TEST INITIALIZATION
        final Point2D origin1 = new Point2DImpl(0, 0);
        final Point2D origin2 = new Point2DImpl(0, 3);
        //------------------------|position
        final Entity e1 = new EntityImpl(origin1);
        final Entity e2 = new EntityImpl(origin2);
        final Entity e3 = new EntityImpl(origin1);

        //TEST IMPLEMENTATION
        //test equals()
        assert !e1.equals(e2);
        assert e1.equals(e3);

        //test getPosition()
        assert origin1.equals(e1.getPosition());
        assert origin2.equals(e2.getPosition());

        //test resetPosition()
        final Point2D newPosition = new Point2DImpl(3, 3);
        e1.resetPosition(newPosition);
        assert newPosition.equals(e1.getPosition());
    }

    /**
     * This test checks the correct implementation of CollidableEntity.
     */
    @Test
    public void testCollidableEntityImpl() {
        //TEST INITIALIZATION
        final Point2D origin1 = new Point2DImpl(0, 0);
        final Point2D origin2 = new Point2DImpl(0, 3);
        //--------------------------------------------|position|radius
        final CollidableEntity e1 = new CollidableEntityImpl(origin1, 3);
        final CollidableEntity e2 = new CollidableEntityImpl(origin2, 2);
        final CollidableEntity e3 = new CollidableEntityImpl(origin1, 3);
        final CollidableEntity e4 = new CollidableEntityImpl(origin1, 4);

        //TEST IMPLEMENTATION
        //test equals()
        assert !e1.equals(e2);
        assert e1.equals(e3);
        assert !e1.equals(e4);

        //test getRadialHitbox()
        assert e1.getRadialHitbox() == 3;
        assert e2.getRadialHitbox() == 2;
        assert e3.getRadialHitbox() == 3;
        assert e4.getRadialHitbox() == 4;
    }

    /**
     * This test checks the correct implementation of RotatingEntity.
     */
    @Test
    public void testRotatingEntityImpl() {
        //TEST INITIALIZATION
        final Point2D origin1 = new Point2DImpl(0, 0);
        final Point2D origin2 = new Point2DImpl(0, 3);
        //----------------------------------------|position|radius|rotation
        final RotatingEntity e1 = new RotatingEntityImpl(origin1, 3,     0);
        final RotatingEntity e2 = new RotatingEntityImpl(origin2, 2,     Math.PI / 2);
        final RotatingEntity e3 = new RotatingEntityImpl(origin1, 3,     0);

        //TEST IMPLEMENTATION
        //test equals()
        assert !e1.equals(e2);
        assert e1.equals(e3);

        //test getRotation()
        assert e1.getRotation() == 0;
        assert e2.getRotation() == Math.PI / 2;

        //test resetRotation()
        e3.resetRotation(Math.PI / 2);
        assert e3.getRotation() == Math.PI / 2;

        //test rotateAnticlockwise() & rotateClockwise()
        e1.rotateAnticlockwise(Math.PI / 2);
        assert e1.getRotation() == Math.PI / 2;

        e1.resetRotation(0);
        final double repetitiveRotation1 = 5 * Math.PI;
        e1.rotateAnticlockwise(repetitiveRotation1);
        assert e1.getRotation() == Math.PI;

        e1.resetRotation(0);
        e1.rotateClockwise(Math.PI);
        assert e1.getRotation() == -Math.PI;
        e1.resetRotation(0);
        final double repetitiveRotation2 = 5 * Math.PI;
        e1.rotateClockwise(repetitiveRotation2);
        assert e1.getRotation() == -Math.PI;
    }

    /**
     * This test checks the correct implementation of MovingEntity.
     */
    @Test
    public void testMovingEntityImpl() {
        //TEST INITIALIZATION
        final Point2D origin1 = new Point2DImpl(0, 0);
        final Point2D origin2 = new Point2DImpl(0, 3);
        final Vector2D speed1 = new Vector2DImpl(new Point2DImpl(0, 2));
        final Vector2D speed2 = new Vector2DImpl(new Point2DImpl(2, 0));
        //------------------------------------|position|radius|rotation     |speed
        final MovingEntity e1 = new MovingEntityImpl(origin1, 3,     0,            speed1);
        final MovingEntity e2 = new MovingEntityImpl(origin2, 2,     Math.PI / 2,  speed2);
        final MovingEntity e3 = new MovingEntityImpl(origin1, 3,     0,            speed1);

        //TEST IMPLEMENTATION
        //test equals()
        assert !e1.equals(e2);
        assert e1.equals(e3);

        //test getSpeed()
        assert speed1.equals(e1.getSpeed());
        assert speed2.equals(e2.getSpeed());

        //test move()
        e1.move();
        assert e1.getPosition().equals(new Point2DImpl(0, 2));
        e2.move();
        assert e2.getPosition().equals(new Point2DImpl(2, 3));

        //test resetSpeed()
        e3.resetSpeed(speed2);
        assert speed2.equals(e3.getSpeed());

        //test accelerate() & decelerate()
        final Vector2D acceleration = new Vector2DImpl(new Point2DImpl(10, 10));

        e1.accelerate(acceleration);
        final Vector2D expectedVelocity1 = new Vector2DImpl(new Point2DImpl(10, 12));
        assert e1.getSpeed().equals(expectedVelocity1);

        e2.decelerate(acceleration);
        final Vector2D expectedVelocity2 = new Vector2DImpl(new Point2DImpl(-8, -10));
        assert e2.getSpeed().equals(expectedVelocity2);
    }

    /**
     * This test checks the correct structure of the functional interface ConditionalEntity.
     */
    @Test
    public void testConditionalEntity() {
        //TEST INITIALIZATION
        final ConditionalEntity e1 = () -> true;
        final ConditionalEntity e2 = () -> 3 < 2;

        //TEST IMPLEMENTATION
        assert e1.is();
        assert !e1.isnt();

        assert !e2.is();
        assert e2.isnt();
    }

}
