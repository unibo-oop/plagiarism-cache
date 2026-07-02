package it.unibo.oop.crossline.game.attributes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * This interface represents an entity that has a physical body.
 */
public interface Physical {

    /**
     * Get the current instance's body.
     * @return the instance's body
     */
    Body getBody();

    /**
     * Get the world position of the instance.
     * @return the world position
     */
    default Vector2 getPosition() {
        return getBody().getPosition();
    }

}
