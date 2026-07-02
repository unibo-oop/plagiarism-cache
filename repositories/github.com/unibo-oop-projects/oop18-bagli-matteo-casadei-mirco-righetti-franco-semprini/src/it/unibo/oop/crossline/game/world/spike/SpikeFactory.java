package it.unibo.oop.crossline.game.world.spike;

import com.badlogic.gdx.math.Vector2;

/**
 * This is an interface that represents a factory of {@link it.unibo.oop.crossline.game.world.spike.Spike}.
 */
public interface SpikeFactory {

    /**
     * Create a spike.
     * @param position the position where to spawn 
     * @return the spike instance
     */
    Spike createSpike(Vector2 position);

}
