package it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.worldconstructor.gameobjectspawn.api.SpawnPoolCreator;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.Pair;

/**
 * Base implementation of {@link AddOnPool}.
 */
public abstract class AbstractAddOnPool implements AddOnPool {

    // CHECKSTYLE: VisibilityModifier OFF
    // The rule is disabled because this class is a template and classes that extend
    // it need to access the fields to set the pool of add-ons to spawn.

    /**
     * List of pairs where the first element is the probability of spawning the
     * add-on and the second element is a BiConsumer that takes the time and the
     * platform on which to spawn the add-on.
     */
    protected final List<Pair<Double, BiConsumer<Double, Platform>>> addOnPool;

    /**
     * The spawn pool creator used to create the spawn pool for the add-ons.
     */
    protected final SpawnPoolCreator spawnPoolCreator;

    // CHECKSTYLE: VisibilityModifier ON

    /**
     * The probability of spawning an add-on.
     */
    private final double chanceAddOn;

    /**
     * Constructor for AddOnPoolBase.
     *
     * @param spawnPoolCreator the spawn pool creator used to create the spawn pool
     *                         for the add-ons
     * @param chanceAddOn      the probability of spawning an add-on
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "The spawnPoolCreator is modified only by the world constructor"
            + " and is not exposed to the outside, so it is safe to expose the reference.")
    public AbstractAddOnPool(final SpawnPoolCreator spawnPoolCreator, final double chanceAddOn) {
        this.addOnPool = new LinkedList<>();
        this.chanceAddOn = chanceAddOn;
        this.spawnPoolCreator = spawnPoolCreator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Double, BiConsumer<Double, Platform>>> getAddOnPool() {
        return Collections.unmodifiableList(this.addOnPool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getChanceAddOn() {
        return this.chanceAddOn;
    }

}
