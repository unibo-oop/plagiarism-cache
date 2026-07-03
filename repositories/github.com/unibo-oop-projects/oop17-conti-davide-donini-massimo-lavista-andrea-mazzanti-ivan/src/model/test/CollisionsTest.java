package model.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.entities.Bullet;
import model.entities.BulletImpl;
import model.entities.BulletType;
import model.entities.Enemy;
import model.entities.Entity;
import model.entities.Spaceship;
import model.entities.SpaceshipImpl;
import model.entities.powerup.PowerUp;
import model.entities.properties.PositionImpl;
import model.entities.properties.VelocityImpl;
import model.factories.EnemyFactoryImpl;
import model.factories.PowerUpFactoryImpl;
import model.manager.Collisions;

/**
 * 
 * Test for Collisions.
 *
 */
public class CollisionsTest {

    private static final double INITIAL_SHAPES_POSITION_X = 0.0;
    private static final double INITIAL_SHAPES_POSITION_Y = 0.0;

    private static final double RECTANGLE_WIDTH = 3.0;
    private static final double RECTANGLE_HEIGHT = 2.0;
    private static final double RECTANGLE_DELTA_SHIFT = 0.1;
    private static final double CIRCLE_RADIUS = 3.0;
    private static final double CIRCLE2_RADIUS = 2.0;

    private final Spaceship spaceship = new SpaceshipImpl(
            new Circle(INITIAL_SHAPES_POSITION_X, INITIAL_SHAPES_POSITION_Y, CIRCLE_RADIUS), new VelocityImpl(0, 0), 3,
            5, 100);

    private final Enemy circleEnemy = new EnemyFactoryImpl().createBasicActive(new VelocityImpl(0, 0),
            new Circle(INITIAL_SHAPES_POSITION_X, INITIAL_SHAPES_POSITION_Y, CIRCLE_RADIUS), 3, 5);
    private final Enemy squareEnemy = new EnemyFactoryImpl().createBasicActive(new VelocityImpl(0, 0),
            new Rectangle(INITIAL_SHAPES_POSITION_X, INITIAL_SHAPES_POSITION_Y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT), 3,
            5);

    private final Bullet friendlyBullet = new BulletImpl(
            new Circle(INITIAL_SHAPES_POSITION_X, INITIAL_SHAPES_POSITION_Y, CIRCLE2_RADIUS), new VelocityImpl(0, 0), 1,
            BulletType.FRIENDLY);
    private final Bullet enemyBullet = new BulletImpl(
            new Circle(INITIAL_SHAPES_POSITION_X, INITIAL_SHAPES_POSITION_Y, CIRCLE2_RADIUS), new VelocityImpl(0, 0), 1,
            BulletType.ACTIVE_ENEMY);

    private final PowerUp powerUp = new PowerUpFactoryImpl().createRandomPowerUp(new PositionImpl(0, 0));

    /**
     * Test the Spaceship collisions.
     */
    @Test
    public void testSpaceShipCollisions() {
        this.testCollisions(this.spaceship, this.enemyBullet);
        this.testCollisions(this.spaceship, this.circleEnemy);
        this.testCollisions(this.spaceship, this.squareEnemy);
        this.testCollisions(this.spaceship, this.powerUp);
    }

    /**
     * Test the collisions of the bullets fired by the Spaceship.
     */
    @Test
    public void testFriendlyBulletCollisions() {
        this.testCollisions(this.friendlyBullet, this.circleEnemy);
    }

    /**
     * Test the collisions of the Spaceship created with a different shape.
     */
    @Test
    public void testAlternativeSspaceShipCollision() {
        final Spaceship spaceship = new SpaceshipImpl(
                new Rectangle(INITIAL_SHAPES_POSITION_X, INITIAL_SHAPES_POSITION_Y, RECTANGLE_WIDTH, RECTANGLE_HEIGHT),
                new VelocityImpl(0, 0), 3, 5, 100);

        this.testCollisions(spaceship, this.enemyBullet);
        this.testCollisions(spaceship, this.circleEnemy);
        this.testCollisions(spaceship, this.squareEnemy);
        this.testCollisions(spaceship, this.powerUp);
    }

    private void testCollisions(final Entity entity, final Entity entity2) {
        entity.setPosition(entity2.getPosition());

        if (entity.getShape() instanceof Circle && entity2.getShape() instanceof Circle) {
            this.testCirclesCollisions((Circle) entity.getShape(), (Circle) entity2.getShape());
        } else if (entity.getShape() instanceof Circle && entity2.getShape() instanceof Rectangle) {
            this.testCircleRectangleCollisions((Circle) entity.getShape(), (Rectangle) entity2.getShape());
        } else if (entity.getShape() instanceof Rectangle && entity2.getShape() instanceof Circle) {
            this.testCircleRectangleCollisions((Circle) entity2.getShape(), (Rectangle) entity.getShape());
        } else if (entity.getShape() instanceof Rectangle && entity2.getShape() instanceof Rectangle) {
            this.testRectanglesCollisions((Rectangle) entity2.getShape(), (Rectangle) entity.getShape());
        } else {
            if (!Collisions.checkShapesIntersection(entity.getShape(), entity2.getShape())) {
                fail("Entities don't collide.");
            }
        }
    }

    /*
     * Tests the collisions between circles.
     */
    private void testCirclesCollisions(final Circle circle, final Circle circle2) {

        if (!Collisions.checkShapesIntersection(circle, circle2)) {
            fail("Shapes don't collide in the middle.");
        }

        circle2.setCenterX(circle.getRadius());
        circle2.setCenterY(circle.getRadius());
        if (!Collisions.checkShapesIntersection(circle, circle2)) {
            fail("Shapes don't collide in the bottom rith angle.");
        }

        circle2.setCenterX(circle.getRadius());
        circle2.setCenterY(-circle.getRadius());
        if (!Collisions.checkShapesIntersection(circle, circle2)) {
            fail("Shapes don't collide in the upper right angle.");
        }

        circle2.setCenterX(-circle.getRadius());
        circle2.setCenterY(circle.getRadius());
        if (!Collisions.checkShapesIntersection(circle, circle2)) {
            fail("Shapes don't collide in the bottom left angle.");
        }

        circle2.setCenterX(-circle.getRadius());
        circle2.setCenterY(-circle.getRadius());
        if (!Collisions.checkShapesIntersection(circle, circle2)) {
            fail("Shapes don't collide in the upper left angle.");
        }

        circle2.setCenterX(circle.getCenterX() + circle.getRadius() + circle2.getRadius());
        circle2.setCenterY(circle.getCenterY() + circle.getRadius() + circle2.getRadius());
        if (Collisions.checkShapesIntersection(circle, circle2)) {
            fail("Shapes must not collide here.");
        }
    }

    /*
     * Tests the Circle-Rectangle collisions.
     */
    private void testCircleRectangleCollisions(final Circle circle, final Rectangle rectangle) {

        if (!Collisions.checkShapesIntersection(circle, rectangle)) {
            fail("Shapes don't collide in the middle.");
        }

        circle.setCenterX(rectangle.getX() + rectangle.getWidth());
        circle.setCenterY(rectangle.getY() + rectangle.getHeight());
        if (!Collisions.checkShapesIntersection(circle, rectangle)) {
            fail("Shapes don't collide in the bottom rith angle.");
        }

        circle.setCenterX(rectangle.getX() + rectangle.getWidth());
        circle.setCenterY(rectangle.getY());
        if (!Collisions.checkShapesIntersection(circle, rectangle)) {
            fail("Shapes don't collide in the upper right angle.");
        }

        circle.setCenterX(rectangle.getX());
        circle.setCenterY(rectangle.getY() + rectangle.getWidth());
        if (!Collisions.checkShapesIntersection(circle, rectangle)) {
            fail("Shapes don't collide in the bottom left angle.");
        }

        circle.setCenterX(rectangle.getX());
        circle.setCenterY(rectangle.getY());
        if (!Collisions.checkShapesIntersection(circle, rectangle)) {
            fail("Shapes don't collide in the upper left angle.");
        }

        circle.setCenterX(rectangle.getX() + rectangle.getWidth() + circle.getRadius());
        circle.setCenterY(rectangle.getY());
        if (Collisions.checkShapesIntersection(circle, rectangle)) {
            fail("Shapes must not collide here.");
        }
    }

    /*
     * Tests the collisions between rectangles.
     */
    private void testRectanglesCollisions(final Rectangle rectangle, final Rectangle rectangle2) {

        rectangle2.setX(rectangle.getX());
        rectangle2.setY(rectangle.getY());
        if (!Collisions.checkShapesIntersection(rectangle, rectangle2)) {
            fail("Shapes don't collide in the middle.");
        }

        rectangle2.setX(rectangle.getX() + rectangle.getWidth() - RECTANGLE_DELTA_SHIFT);
        rectangle2.setY(rectangle.getY() + rectangle.getHeight() - RECTANGLE_DELTA_SHIFT);
        if (!Collisions.checkShapesIntersection(rectangle, rectangle2)) {
            fail("Shapes don't collide in the bottom rith angle.");
        }

        rectangle2.setX(rectangle.getX() + rectangle.getWidth() - RECTANGLE_DELTA_SHIFT);
        rectangle2.setY(rectangle.getY() - rectangle2.getHeight() + RECTANGLE_DELTA_SHIFT);
        if (!Collisions.checkShapesIntersection(rectangle, rectangle2)) {
            fail("Shapes don't collide in the upper right angle.");
        }

        rectangle2.setX(rectangle.getX() - rectangle2.getWidth() + RECTANGLE_DELTA_SHIFT);
        rectangle2.setY(rectangle.getY() + rectangle.getHeight() - RECTANGLE_DELTA_SHIFT);
        if (!Collisions.checkShapesIntersection(rectangle, rectangle2)) {
            fail("Shapes don't collide in the bottom left angle.");
        }

        rectangle2.setX(rectangle.getX() - rectangle2.getWidth() + RECTANGLE_DELTA_SHIFT);
        rectangle2.setY(rectangle.getY() - rectangle2.getHeight() + RECTANGLE_DELTA_SHIFT);
        if (!Collisions.checkShapesIntersection(rectangle, rectangle2)) {
            fail("Shapes don't collide in the upper left angle.");
        }

        rectangle2.setX(rectangle.getX() + rectangle.getWidth());
        rectangle2.setY(rectangle.getY());
        if (Collisions.checkShapesIntersection(rectangle, rectangle2)) {
            fail("Shapes must not collide here.");
        }
    }
}
