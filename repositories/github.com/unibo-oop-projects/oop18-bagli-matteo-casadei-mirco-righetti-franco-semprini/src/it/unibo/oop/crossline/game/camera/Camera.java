package it.unibo.oop.crossline.game.camera;

import java.util.Optional;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * The game camera.
 */
public interface Camera {

    /**
     * The combined projection and view matrix.
     * @return the combined projection and view matrix
     */
    Matrix4 getCombined();

    /**
     * Get the camera target.
     * @return the target body
     */
    Optional<Body> getTarget();

    /**
     * Set the target that the camera.
     * @param target the target to follow
     */
    void setTarget(Body target);

    /**
     * Get the camera world position.
     * @return the camera world position
     */
    Vector2 getPosition();

    /**
     * Set the camera zoom.
     * @param zoom the zoom level
     */
    void setZoom(float zoom);

    /**
     * Update the camera.
     * @param delta the time gap between previous and current frame
     */
    void update(float delta);

}
