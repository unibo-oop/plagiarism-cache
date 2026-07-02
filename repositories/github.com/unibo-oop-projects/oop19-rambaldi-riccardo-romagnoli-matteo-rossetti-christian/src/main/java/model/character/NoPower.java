package model.character;

import model.world.GameWorld;

/**
 * This is the implementation of {@link Character} interface.
 */

public class NoPower implements Character {

    private final String name;

    public NoPower() {
        this.name = "No Power";
    }

    @Override
    public void usePower(final GameWorld world) { }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public void deletePower(final GameWorld gameWorld) { }

}
