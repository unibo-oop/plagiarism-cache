package it.unibo.model.world.api;

import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;

/**
 * Abstract implementation of {@link GameWorld}.
 * Provides basic removal logic for game entities.
 */
public abstract class AbstractGameWorld extends AbstractQueueWorld implements GameWorld {

    /**
     * Constructs a new AbstractGameWorld.
     * 
     * @param boundWorld the BoundWorld instance associated with this game world
     */
    public AbstractGameWorld(final BoundWorld boundWorld) {
        super(boundWorld);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeStaticPlatform(final Platform platform) {
        return staticPlatforms.remove(platform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeMovingPlatform(final Platform platform) {
        return movingPlatforms.remove(platform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeOnTouchPlatform(final Platform platform) {
        return onTouchPlatforms.remove(platform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeMonster(final Enemy monster) {
        return monsters.remove(monster);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeGadget(final Gadget gadget) {
        return gadgets.remove(gadget);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeMoney(final Coin money) {
        return moneys.remove(money);
    }
}
