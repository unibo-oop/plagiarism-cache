package model.physics;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import model.GameModel;

/**
 * BodyBuilder implementation to create the physic entities.
 */
public final class BodyBuilderImpl implements BodyBuilder {

    private Optional<Vec2> pos;
    private Optional<Size2D> dimension;
    private Optional<World> world;
    private float frictionValue;
    private float densityValue = 1;
    private float restitutionValue;
    private float gravityValue = 1;
    private boolean isSubjectToForces = true;
    private boolean isSolid = true;
    private boolean isMoveable = true;
    private boolean isAllowedToSleep;

    private static final BodyBuilder BUILDER = new BodyBuilderImpl();

    private BodyBuilderImpl() {
    }

    /**
     * Get the BodyBuider unique instance.
     * 
     * @return the body builder
     */
    public static BodyBuilder getInstance() {
        return BUILDER;
    }

    @Override
    public BodyBuilder position(final Vec2 position) {
        this.pos = Optional.of(position);
        return this;
    }

    @Override
    public BodyBuilder size(final Size2D size) {
        this.dimension = Optional.of(size);
        return this;
    }

    @Override
    public BodyBuilder subjectToForces(final boolean isSubjectToForces) {
        this.isSubjectToForces = isSubjectToForces;
        return this;
    }

    @Override
    public BodyBuilder solid(final boolean isSolid) {
        this.isSolid = isSolid;
        return this;
    }

    @Override
    public BodyBuilder moveable(final boolean moveable) {
        this.isMoveable = moveable;
        return this;
    }

    @Override
    public BodyBuilder friction(final float friction) {
        this.frictionValue = friction;
        return this;
    }

    @Override
    public BodyBuilder restitution(final float restitution) {
        this.restitutionValue = restitution;
        return this;
    }

    @Override
    public BodyBuilder density(final float density) {
        this.densityValue = density;
        return this;
    }

    @Override
    public BodyBuilder gravity(final float gravity) {
        this.gravityValue = gravity;
        return this;
    }

    @Override
    public BodyBuilder allowedToSleep(final boolean allowedToSleep) {
        this.isAllowedToSleep = allowedToSleep;
        return this;
    }

    @Override
    public PhysicEntity build() {
        world = Optional.ofNullable(GameModel.getWorld());
        checkDataValidity();

        final BodyDef bodyDef = new BodyDef();
        bodyDef.setFixedRotation(true);
        bodyDef.setLinearDamping(0);
        bodyDef.setType(makeType());
        bodyDef.setPosition(pos.get());
        bodyDef.setAllowSleep(isAllowedToSleep);

        final Body body = world.get().createBody(bodyDef);
        body.setSleepingAllowed(isAllowedToSleep);
        final FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.setFriction(frictionValue);
        fixtureDef.setSensor(!isSolid);
        fixtureDef.setDensity(densityValue);
        fixtureDef.setRestitution(restitutionValue);
        final PolygonShape shape = new PolygonShape();
        shape.setAsBox((float) dimension.get().x / 2, (float) dimension.get().y / 2);
        fixtureDef.setShape(shape);

        body.createFixture(fixtureDef);
        body.resetMassData();
        body.setGravityScale(gravityValue);

        return new PhysicEntityImpl(body, dimension.get());
    }

    private BodyType makeType() {
        if (!isMoveable) {
            return BodyType.STATIC;
        }
        if (!isSubjectToForces) {
            return BodyType.KINEMATIC;
        }
        return BodyType.DYNAMIC;
    }

    private void checkDataValidity() {
        assertPresent(pos, "Position");
        assertPresent(dimension, "Size");
        assertNotNull("World is null", world);
    }

    private void assertPresent(final Optional<?> opt, final String varName) {
        if (!opt.isPresent()) {
            throw new IllegalArgumentException(varName + "not specified");
        }
    }
}

