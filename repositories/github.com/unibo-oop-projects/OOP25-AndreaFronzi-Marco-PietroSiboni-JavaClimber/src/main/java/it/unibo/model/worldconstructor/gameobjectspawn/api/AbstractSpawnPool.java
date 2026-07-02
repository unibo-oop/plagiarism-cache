package it.unibo.model.worldconstructor.gameobjectspawn.api;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api.FactoryAddOn;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.FactoryAddOnImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.Director;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.Pair;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.DirectorImpl;

/**
 * A base class for implementing spawn pools for different difficult.
 */
public abstract class AbstractSpawnPool implements SpawnPool {

    // CHECKSTYLE: VisibilityModifier OFF
    // The rule is disabled because this class is a template and classes that extend
    // it need to access the fields to set the pool of objects to spawn.

    /**
     * Represents a list of multiple types of static platforms with their associated
     * spawn probabilities.
     */
    protected final List<Pair<Double, Function<Vector2d, Platform>>> staticPlatformPool;

    /**
     * Represents a set of multiple types of moving platforms with their associated
     * spawn probabilities.
     */
    protected final List<Pair<Double, Function<Vector2d, Platform>>> movingPlatformPool;

    /**
     * Represents a set of multiple types of on-touch platforms with their
     * associated spawn probabilities.
     */
    protected final List<Pair<Double, Function<Vector2d, Platform>>> onTouchPlatformPool;

    /**
     * Represents a set of multiple types of monsters with their associated spawn
     * probabilities.
     */
    protected final List<Pair<Double, Function<Vector2d, Enemy>>> monsterPool;

    /**
     * Represents a set of multiple types of gadgets with their associated spawn
     * probabilities.
     */
    protected final List<Pair<Double, Function<Vector2d, Gadget>>> gadgetPool;

    /**
     * Represents a set of multiple types of coins with their associated spawn
     * probabilities.
     */
    protected final List<Pair<Double, Function<Vector2d, Coin>>> moneyPool;

    /**
     * Represents the director for managing platform spawning.
     */
    protected final Director director;

    /**
     * Represents the factory add-on for managing additional object spawning.
     */
    protected final FactoryAddOn factoryAddOn;

    // CHECKSTYLE: <VisibilityModifier> ON

    /**
     * Constructs the easy platform pool and initializes the object lists.
     * 
     * @param width        the width of the game world
     * @param height       the height of the game world
     * @param scoreManager the score manager to be used for the factory add-on
     */
    public AbstractSpawnPool(final double width, final double height, final ScoreManager scoreManager) {
        this.staticPlatformPool = new LinkedList<>();
        this.movingPlatformPool = new LinkedList<>();
        this.onTouchPlatformPool = new LinkedList<>();
        this.monsterPool = new LinkedList<>();
        this.gadgetPool = new LinkedList<>();
        this.moneyPool = new LinkedList<>();
        this.director = new DirectorImpl(width, height);
        this.factoryAddOn = new FactoryAddOnImpl(scoreManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Double, Function<Vector2d, Platform>>> getStaticPlatformPool() {
        return List.copyOf(this.staticPlatformPool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Double, Function<Vector2d, Platform>>> getMovingPlatformPool() {
        return List.copyOf(this.movingPlatformPool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Double, Function<Vector2d, Platform>>> getOnTouchPlatformPool() {
        return List.copyOf(this.onTouchPlatformPool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Double, Function<Vector2d, Enemy>>> getMonsterPool() {
        return List.copyOf(this.monsterPool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Double, Function<Vector2d, Gadget>>> getGadgetPool() {
        return List.copyOf(this.gadgetPool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<Double, Function<Vector2d, Coin>>> getMoneyPool() {
        return List.copyOf(this.moneyPool);
    }

}
