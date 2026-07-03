package user.game.effects;

import user.enums.GameSprites;
import user.enums.Objects;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class represents a particle that pops, goes somewhere and stops.
 */
public class ObjParticle extends GameObject {

    private double speed;
    private double initialSpeed;
    private static final double FRICTION = 0.7;

    private static final int SPEED_MIN = 15;
    private static final int SPEED_MAX = 25;
    private static final double SPEED_THRESHOLD = 5;

    @Override
    public void create() {
        // aspect parameters
        setSpriteIndex(GameSprites.PARTICLE.getValue());
        setImageIndex(1);

        setImageAngle(z().random(360));
        speed = z().randomRange(SPEED_MIN, SPEED_MAX);
        initialSpeed = speed;

        setImageXscale(3);
        setImageYscale(3);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        // move according to my speed and angle
        moveX(z().lengthDirX(speed, getImageAngle()));
        moveY(z().lengthDirY(speed, getImageAngle()));

        // apply FRICTION to my speed
        speed = z().max(speed - FRICTION, 0d);
        // reduce alpha proportionally to my speed, for a disappearance effect
        setImageAlpha(speed / initialSpeed);

        // if too slow, remove this instance
        if (speed > 10 && z().choose(false, false, true)) {
            final ObjEffectEmitterImpl ef = (ObjEffectEmitterImpl) z().instanceCreate(this.getX(), this.getY(),
                    Objects.EFFECT_EMITTER.getValue());
            if (z().instanceExists(ef)) {
                ef.createSmallExplosion();
            }
        }
        if (speed <= SPEED_THRESHOLD) {
            z().instanceDestroy(this);
        }
    }

    @Override
    public void draw() {
        drawSelf();
    }

    @Override
    public void collide(final GameObject other) {

    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

}
