package it.unibo.oop.crossline.game.world.platform;

import java.util.concurrent.atomic.AtomicReference;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * This class is platform.
 */
public class PlatformImpl implements Platform {

    private static final float WIDTH = 1f;
    private static final float HEIGHT = 1f;

    private static AtomicReference<PolygonShape> atomicShape = new AtomicReference<>();

    private final Body body;

    /**
     * Initialize the platform instance.
     * @param world the world instance
     * @param position the platform position
     */
    public PlatformImpl(final World world, final Vector2 position) {
        final BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyType.StaticBody;
        body = world.createBody(bodyDef);
        body.createFixture(getShape(), 0f);
        body.setUserData(this);
    }

    @Override
    public final Body getBody() {
        return body;
    }

    private static PolygonShape getShape() {
        PolygonShape polygonShape = atomicShape.get();
        if (polygonShape == null) {
            polygonShape = new PolygonShape();
            final float halfWidth = WIDTH / 2f;
            final float halfHeight = HEIGHT / 2f;
            final Vector2 center = new Vector2(halfWidth, halfHeight);
            polygonShape.setAsBox(halfWidth, halfHeight, center, 0);
            atomicShape.compareAndSet(null, polygonShape);
        }
        return polygonShape;
    }

}
