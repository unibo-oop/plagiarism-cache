package test.matteini;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import spacesurvival.model.EngineImage;
import spacesurvival.model.World;
import spacesurvival.model.collision.bounding.CircleBoundingBox;
import spacesurvival.model.collision.bounding.RectBoundingBox;
import spacesurvival.model.common.P2d;
import spacesurvival.model.common.V2d;
import spacesurvival.model.gameobject.GameObject;
import spacesurvival.model.gameobject.main.MainObject;
import spacesurvival.model.gameobject.main.Status;
import spacesurvival.model.gameobject.moveable.movement.implementation.FixedMovementLogic;
import spacesurvival.model.worldevent.WorldEvent;
import spacesurvival.utilities.Delay;
import spacesurvival.utilities.RandomUtils;
import spacesurvival.utilities.ThreadUtils;
import spacesurvival.utilities.dimension.ScaleOf;
import spacesurvival.utilities.dimension.Screen;
import spacesurvival.utilities.gameobject.StatusUtils;
import spacesurvival.utilities.path.animation.AnimationShip;

public class TestGameObject {

    private static final P2d STARTING_POSITION = new P2d(RandomUtils.RANDOM.nextInt(200), RandomUtils.RANDOM.nextInt(200));
    private static final P2d FINAL_POSITION = new P2d(RandomUtils.RANDOM.nextInt(200), RandomUtils.RANDOM.nextInt(200));
    private static final int DURATION_TOLLERANCE = 300;

    private final GameObject rectGameObject = new GameObject(new EngineImage(ScaleOf.GAME_OBJECT, Screen.WIDTH_FULLSCREEN, AnimationShip.NORMAL0),
            STARTING_POSITION, new RectBoundingBox(), null) {
        @Override
        public void collided(final World world, final WorldEvent worldEvent) {
        }
    };

    private final GameObject circleGameObject = new GameObject(new EngineImage(ScaleOf.GAME_OBJECT, Screen.WIDTH_FULLSCREEN, AnimationShip.NORMAL0),
            STARTING_POSITION, new CircleBoundingBox(), null) {
        @Override
        public void collided(final World world, final WorldEvent worldEvent) {
        }
    };

    private final MainObject mainObject = new MainObject(new EngineImage(ScaleOf.GAME_OBJECT, Screen.WIDTH_FULLSCREEN, AnimationShip.NORMAL0),
            STARTING_POSITION, new RectBoundingBox(), null, new V2d(10, 10), 0, new FixedMovementLogic(),
            500, 50, 70, null) {
        @Override
        public void collided(final World world, final WorldEvent worldEvent) {
        }
    };

    @Test
    public void testObjectPosition() {
        rectGameObject.setTransformFromPosition(FINAL_POSITION);
        assertEquals(Double.valueOf(rectGameObject.getTransform().getTranslateX()), Double.valueOf(FINAL_POSITION.getX()));
        assertEquals(Double.valueOf(rectGameObject.getTransform().getTranslateY()), Double.valueOf(FINAL_POSITION.getY()));

        circleGameObject.setTransformFromPosition(FINAL_POSITION);
        assertEquals(Double.valueOf(circleGameObject.getTransform().getTranslateX()), Double.valueOf(FINAL_POSITION.getX()));
        assertEquals(Double.valueOf(circleGameObject.getTransform().getTranslateY()), Double.valueOf(FINAL_POSITION.getY()));
    }

    @Test
    public void testLifeAndDeath() {
        assertTrue(mainObject.isAlive());
        mainObject.decreaseLife(mainObject.getLife());
        assertTrue(mainObject.isDead());
        assertFalse(mainObject.isAlive());
        mainObject.increaseLife(1);
        assertTrue(mainObject.isAlive());
        assertFalse(mainObject.isDead());
    }

    @Test
    public void testInvincibility() {
        mainObject.setStatus(Status.INVINCIBLE);
        assertEquals(mainObject.getStatus(), Status.INVINCIBLE);
        assertTrue(mainObject.isInvincible());

        ThreadUtils.sleep(Status.INVINCIBLE.getDuration() + DURATION_TOLLERANCE);
        assertEquals(mainObject.getStatus(), Status.NORMAL);
    }

    @Test
    public void testOnFire() {
        final int rightLife = mainObject.getLife() - (Status.ON_FIRE.getDuration() / Delay.FIRE_EFFECT) * StatusUtils.FIRE_DAMAGE;
        mainObject.setStatus(Status.ON_FIRE);
        assertEquals(mainObject.getStatus(), Status.ON_FIRE);
        ThreadUtils.sleep(Status.ON_FIRE.getDuration() + DURATION_TOLLERANCE);
        assertEquals(mainObject.getLife(), rightLife);
    }

    @Test
    public void testFrozen() {
        final V2d initialVelocity = mainObject.getVelocity();
        final Double rightVelocityX = mainObject.getVelocity().getX() * StatusUtils.FROZEN_SLOWDOWN;
        final Double rightVelocityY = mainObject.getVelocity().getY() * StatusUtils.FROZEN_SLOWDOWN;
        mainObject.setStatus(Status.FROZEN);
        assertEquals(mainObject.getStatus(), Status.FROZEN);
        assertEquals(Double.valueOf(mainObject.getVelocity().getX()), rightVelocityX);
        assertEquals(Double.valueOf(mainObject.getVelocity().getY()), rightVelocityY);

        ThreadUtils.sleep(Status.FROZEN.getDuration() + DURATION_TOLLERANCE);
        assertEquals(Double.valueOf(mainObject.getVelocity().getX()), Double.valueOf(initialVelocity.getX()));
        assertEquals(Double.valueOf(mainObject.getVelocity().getY()), Double.valueOf(initialVelocity.getY()));

    }

    @Test
    public void testParalyzed() {
        mainObject.setStatus(Status.PARALYZED);
        assertEquals(mainObject.getStatus(), Status.PARALYZED);
        assertFalse(mainObject.isMoving());

        ThreadUtils.sleep(Status.PARALYZED.getDuration() + DURATION_TOLLERANCE);
        assertTrue(mainObject.isMoving());
    }

}
