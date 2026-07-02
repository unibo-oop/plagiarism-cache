package it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api.AddOnCreator;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api.AddOnPool;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.Pair;

/**
 * Implementation of the {@link AddOnCreator} interface.
 */
public class AddOnCreatorImpl implements AddOnCreator {

    /**
     * The add-on pool is a list of pairs, where the first element is a double
     * representing the chance of spawning an add-on, and the second element is a
     * BiConsumer.
     */
    private List<Pair<Double, BiConsumer<Double, Platform>>> addOnPool;

    /**
     * Random number generator to determine which add-on to spawn based on the
     * given chance.
     */
    private final Random random;

    /**
     * Constructs an AddOnCreatorImpl with the given add-on pool.
     *
     * @param addOnPool the add-on pool to use for creating add-ons.
     */
    public AddOnCreatorImpl(final AddOnPool addOnPool) {
        this.addOnPool = addOnPool.getAddOnPool();
        this.random = new Random();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void selectAddOn(final Double chance, final Platform platform) {
        for (final var addOn : addOnPool) {
            if (chance <= addOn.getX()) {
                addOn.getY().accept(random.nextDouble(1.0), platform);
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAddOnPool(final AddOnPool addOnPool) {
        this.addOnPool = addOnPool.getAddOnPool();
    }

}
