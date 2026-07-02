package it.unibo.oop.crossline.game.world.platform;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * This class is a factory of spikes, used to add spikes during the world creation.
 */
public class PlatformFactoryImpl implements PlatformFactory {

    private final World world;

    /**
     * Initialize the platform factory.
     * @param world the world instance
     */
    public PlatformFactoryImpl(final World world) {
        this.world = world;
    }

    @Override
    public final Platform createPlatform(final Vector2 position) {
        return new PlatformImpl(world, position);
    }

}
