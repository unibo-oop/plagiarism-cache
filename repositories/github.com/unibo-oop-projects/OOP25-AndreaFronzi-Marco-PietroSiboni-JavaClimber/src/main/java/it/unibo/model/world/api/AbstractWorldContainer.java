package it.unibo.model.world.api;

import java.util.LinkedList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;

/**
 * Abstract implementation of {@link BaseWorld}.
 * Provides storage and management for game entities using Lists.
 */
public abstract class AbstractWorldContainer implements BaseWorld {

    // CHECKSTYLE: VisibilityModifier OFF
    // The rule was disabled for an architectural reason. The camera must lower each
    // game object, so instead of copying all the lists into a new list each time,
    // the object is passed directly to it.

    private static final String ERROR_TYPE = "EI_EXPOSE_REP";

    private static final String ERROR_MESSAGE = "The method returns a reference to the internal list of entities,"
            + " which allows external code to not create a copy for every time the list is modified. This is"
            + " intentional for performance reasons, as it avoids unnecessary copying of the list.";

    /**
     * Represents the list of static platforms in the world.
     */
    protected final List<Platform> staticPlatforms;

    /**
     * Represents the list of moving platforms in the world.
     */
    protected final List<Platform> movingPlatforms;

    /**
     * Represents the list of on-touch platforms in the world.
     */
    protected final List<Platform> onTouchPlatforms;

    /**
     * Represents the list of coins in the world.
     */
    protected final List<Coin> moneys;

    /**
     * Represents the list of enemies in the world.
     */
    protected final List<Enemy> monsters;

    /**
     * Represents the list of static platforms in the world.
     */
    protected final List<Gadget> gadgets;

    // CHECKSTYLE: VisibilityModifier OFF

    /**
     * Represents the list of static platforms in the world.
     */
    private final BoundWorld boundWorld;

    /**
     * Constructs a new AbstractWorldContainer.
     * 
     * @param boundWorld the BoundWorld instance associated with this world
     *                   container
     */
    public AbstractWorldContainer(final BoundWorld boundWorld) {
        this.staticPlatforms = new LinkedList<>();
        this.movingPlatforms = new LinkedList<>();
        this.onTouchPlatforms = new LinkedList<>();
        this.moneys = new LinkedList<>();
        this.gadgets = new LinkedList<>();
        this.monsters = new LinkedList<>();
        this.boundWorld = boundWorld;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addGadget(final Gadget gadget) {
        return this.gadgets.add(gadget);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addMoney(final Coin money) {
        return this.moneys.add(money);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addMonster(final Enemy monster) {
        return this.monsters.add(monster);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addStaticPlatform(final Platform platform) {
        return this.staticPlatforms.add(platform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addMovingPlatform(final Platform platform) {
        return this.movingPlatforms.add(platform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addOnTouchPlatform(final Platform platform) {
        return this.onTouchPlatforms.add(platform);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = ERROR_TYPE, justification = ERROR_MESSAGE)
    @Override
    public List<Gadget> getGadgets() {
        return this.gadgets;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = ERROR_TYPE, justification = ERROR_MESSAGE)
    @Override
    public List<Coin> getMoneys() {
        return this.moneys;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = ERROR_TYPE, justification = ERROR_MESSAGE)
    @Override
    public List<Enemy> getMonsters() {
        return this.monsters;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = ERROR_TYPE, justification = ERROR_MESSAGE)
    @Override
    public List<Platform> getStaticPlatforms() {
        return this.staticPlatforms;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = ERROR_TYPE, justification = ERROR_MESSAGE)
    @Override
    public List<Platform> getMovingPlatforms() {
        return this.movingPlatforms;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = ERROR_TYPE, justification = ERROR_MESSAGE)
    @Override
    public List<Platform> getOnTouchPlatforms() {
        return this.onTouchPlatforms;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoundWorld getBoundWorld() {
        return this.boundWorld;
    }

}
