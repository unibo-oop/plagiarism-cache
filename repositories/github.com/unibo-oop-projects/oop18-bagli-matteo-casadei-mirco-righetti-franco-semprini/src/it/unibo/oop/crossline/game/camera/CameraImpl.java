package it.unibo.oop.crossline.game.camera;

import java.util.Optional;

import org.lwjgl.util.Dimension;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * This class manages the camera used to view the game world.
 */
public class CameraImpl extends OrthographicCamera implements Camera {

    private static final float STRENGTH = 20;

    private Optional<Body> target = Optional.empty();

    /**
     * Creates a new camera instance.
     * @param viewportSize the size of the viewport to display
     */
    public CameraImpl(final Dimension viewportSize) {
        super();
        this.setToOrtho(false, viewportSize.getWidth(), viewportSize.getHeight());
        this.target = Optional.empty();
    }

    @Override
    public final Optional<Body> getTarget() {
        return target;
    }

    @Override
    public final void setTarget(final Body target) {
        this.target = Optional.of(target);
    }

    @Override
    public final void update(final float delta) {
        final float weight = (float) Math.pow(STRENGTH * delta, 2);
        if (target.isPresent()) {
            final Vector2 position = getPosition();
            final Vector2 targetPosition = target.get().getPosition();
            final Vector2 interpolatedPosition = new Vector2();
            interpolatedPosition.x = MathUtils.lerp(position.x, targetPosition.x, weight);
            interpolatedPosition.y = MathUtils.lerp(position.y, targetPosition.y, weight);
            setPosition(interpolatedPosition);
        }
        super.update();
    }

    @Override
    public final Matrix4 getCombined() {
        return this.combined;
    }

    private void setPosition(final Vector2 position) {
        final Vector2 delta = position.sub(new Vector2(this.position.x, this.position.y));
        translate(delta);
    }

    @Override
    public final Vector2 getPosition() {
        return new Vector2(this.position.x, this.position.y);
    }

    @Override
    public final void setZoom(final float zoom) {
        this.zoom = zoom;
    }

}
