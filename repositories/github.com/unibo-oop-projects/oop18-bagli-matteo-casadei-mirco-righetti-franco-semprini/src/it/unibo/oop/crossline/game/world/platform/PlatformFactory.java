package it.unibo.oop.crossline.game.world.platform;

import com.badlogic.gdx.math.Vector2;

/**
 * This is an interface that represents a factory of {@link it.unibo.oop.crossline.game.world.platform.Platform}.
 */
public interface PlatformFactory {

    /**
     * Create a platform.
     * @param position the position where to spawn the platform
     * @return the platform instance
     */
    Platform createPlatform(Vector2 position);

}
