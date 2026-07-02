package it.unibo.oop.crossline.game.world.spike;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * This class is a factory of spikes, used to add {@link it.unibo.oop.crossline.game.world.spike.Spike} during the world creation.
 */
public class SpikeFactoryImpl implements SpikeFactory {

    private final World world;

    /**
     * Initialize the spike factory.
     * @param world the world instance
     */
    public SpikeFactoryImpl(final World world) {
        this.world = world;
    }

    @Override
    public final Spike createSpike(final Vector2 position) {
        return new SpikeImpl(world, position);
    }
}
