package model.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

/**
 * PhysicEntity that contains the physic body.
 */
public final class PhysicEntityImpl implements PhysicEntity {

    private final Body body;
    private final Size2D dimension;

    /**
     * @param body the physic body form JBox2D
     * @param dimension the entity physic dimension
     */
    public PhysicEntityImpl(final Body body, final Size2D dimension) {
        this.body = body;
        this.dimension = dimension;
    }

    @Override
    public Body getBody() {
        return this.body;
    }

    @Override
    public Vec2 getPosition() {
        return body.getPosition();
    }

    @Override
    public Size2D getDimension() {
        return dimension;
    }

    @Override
    public void setLinearVelocity(final Vec2 velocity) {
        body.setLinearVelocity(velocity);
    }

    @Override
    public Vec2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

    @Override
    public void applyImpulse(final Vec2 impulse) {
        body.applyLinearImpulse(impulse.mul(body.getMass()), body.getWorldCenter(), true);
    }

    @Override
    public void applyForce(final Vec2 force, final Vec2 point) {
        body.applyForce(force, point);
    }

    @Override
    public void setGravityScale(final double scale) {
        body.setGravityScale((float) scale);
    }

    @Override
    public boolean isSolid() {
        return body.getFixtureList().isSensor();
    }

    @Override
    public void setToSleep(final boolean allowSleep) {
        body.setSleepingAllowed(allowSleep);
    }

    @Override
    public boolean isSleeping() {
        return body.isSleepingAllowed();
    }

    @Override
    public void removeBodyFromWorld(final World world) {
        world.destroyBody(body);
    }
}
