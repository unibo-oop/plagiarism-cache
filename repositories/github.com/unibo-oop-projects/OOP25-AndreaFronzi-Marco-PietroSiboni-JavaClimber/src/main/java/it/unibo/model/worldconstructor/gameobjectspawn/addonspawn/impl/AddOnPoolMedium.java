package it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl;

import java.util.function.BiConsumer;

import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api.AbstractAddOnPool;
import it.unibo.model.worldconstructor.gameobjectspawn.api.SpawnPoolCreator;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.PairImpl;

/**
 * Implementation of {@link AbstractAddOnPool} for the medium level.
 */
public class AddOnPoolMedium extends AbstractAddOnPool {

    private static final double CHANCE_GADGET = 0.2;
    private static final double CHANCE_MONSTER = 0.4;
    private static final double CHANCE_MONEY = 1.0;

    /**
     * Constructor for AddOnPoolMedium.
     * 
     * @param spawnPoolCreator the creator for generating game objects.
     * @param chanceAddOn the chance of spawning an add-on from this pool.
     */
    public AddOnPoolMedium(final SpawnPoolCreator spawnPoolCreator, final double chanceAddOn) {
        super(spawnPoolCreator, chanceAddOn);
        this.createAddOn();
    }

    private void createAddOn() {
        this.addOnPool
                .add(new PairImpl<Double, BiConsumer<Double, Platform>>(CHANCE_GADGET, this.spawnPoolCreator::createGadget));
        this.addOnPool
                .add(new PairImpl<Double, BiConsumer<Double, Platform>>(CHANCE_MONSTER, this.spawnPoolCreator::createMonster));
        this.addOnPool.add(new PairImpl<Double, BiConsumer<Double, Platform>>(CHANCE_MONEY, this.spawnPoolCreator::createMoney));
    }

}
