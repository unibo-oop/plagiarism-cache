package it.unibo.ninjafrog.world;

import java.util.Optional;

import com.badlogic.gdx.maps.MapObject;

import it.unibo.ninjafrog.game.utilities.GameConst;
import it.unibo.ninjafrog.screens.PlayScreen;

/**
 * Definition of a {@link it.unibo.ninjafrog.world.NonInteractiveBuilder
 * NonInteractiveBuilder} implementation.
 */
public final class NonInteractiveBuilderImpl implements NonInteractiveBuilder {
    private MapObject object;
    private final PlayScreen screen;
    private Optional<Short> bit = Optional.empty();

    /**
     * Public constructor of a NonInteractiveBuilderImpl.
     * 
     * @param screen The {@link it.unibo.ninjafrog.screens.PlayScreen PlayScreen}
     *               which contains the game world.
     */
    public NonInteractiveBuilderImpl(final PlayScreen screen) {
        this.screen = screen;
    }

    @Override
    public NonInteractiveBuilder selectObject(final MapObject object) {
        this.object = object;
        return this;
    }

    @Override
    public NonInteractiveBuilder chooseCategoryBit(final Short bit) {
        this.bit = Optional.of(bit).filter(
                b -> b.equals(GameConst.GROUND) || b.equals(GameConst.GROUND_OBJECT) || b.equals(GameConst.FINISH));
        return this;
    }

    @Override
    public NonInteractiveObject build() {
        if (this.object == null) {
            throw new IllegalStateException("Object can't be null.");
        }
        if (this.screen == null) {
            throw new IllegalStateException("Screen can't be null.");
        }
        if (!this.bit.isPresent()) {
            throw new IllegalStateException("Illegal category-bit input.");
        }
        return new NonInteractiveObject(this.screen, this.object, this.bit.get());
    }

}
