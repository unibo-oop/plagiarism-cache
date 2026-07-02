package model.character;

import org.dyn4j.dynamics.World;

import model.world.GameWorld;

/**
 * This is an implementation of {@link Character} interface.
 */
public class TheMoon implements Character {

    private static final double MOON_GRAVITY = 1.62;
    private final String name;

    public TheMoon() {
        this.name = "The Moon";
    }

    @Override
    public final void usePower(final GameWorld world) {
        world.setGravity(MOON_GRAVITY);
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final void deletePower(final GameWorld gameWorld) {
        gameWorld.setGravity(World.EARTH_GRAVITY.getMagnitude());
    }
}
