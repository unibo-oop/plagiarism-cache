package it.unibo.model.world.api;

import java.util.Optional;

import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;

/**
 * Abstract implementation of {@link QueueWorld}.
 * Provides basic logic for retrieving and removing the first element of each entity type.
 */
public abstract class AbstractQueueWorld extends AbstractWorldContainer implements QueueWorld {

    /**
     * Constructs an AbstractQueueWorld.
     *
     * @param boundWorld the BoundWorld to be used for this world
     */
    public AbstractQueueWorld(final BoundWorld boundWorld) {
        super(boundWorld);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Platform> removeFirstStaticPlatform() {
        if (!staticPlatforms.isEmpty()) {
            return Optional.of(staticPlatforms.removeFirst());
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Platform> removeFirstMovingPlatform() {
        if (!movingPlatforms.isEmpty()) {
            return Optional.of(movingPlatforms.removeFirst());
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Platform> removeFirstOnTouchPlatform() {
        if (!onTouchPlatforms.isEmpty()) {
            return Optional.of(onTouchPlatforms.removeFirst());
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Enemy> removeFirstMonster() {
        if (!monsters.isEmpty()) {
            return Optional.of(monsters.removeFirst());
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Gadget> removeFirstGadget() {
        if (!gadgets.isEmpty()) {
            return Optional.of(gadgets.removeFirst());
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Coin> removeFirstMoney() {
        if (!moneys.isEmpty()) {
            return Optional.of(moneys.removeFirst());
        }
        return Optional.empty();
    }
}
