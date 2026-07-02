package it.unibo.model.worldconstructor.gameobjectspawn.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.gameobj.api.GameObject;
import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.world.api.BaseWorld;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.api.AddOnPositionSetter;
import it.unibo.model.worldconstructor.gameobjectspawn.addonspawn.impl.AddOnPositionSetterImpl;
import it.unibo.model.worldconstructor.gameobjectspawn.api.SpawnPool;
import it.unibo.model.worldconstructor.gameobjectspawn.api.SpawnPoolCreator;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.api.Pair;

/**
 * Implementation of PlatformPoolCreator.
 * Iterates through the configured pools to find a matching object for a given
 * spawn chance.
 */
public class SpawnPoolCreatorImpl implements SpawnPoolCreator {

    /**
     * Represents a set of a spawn pool that can be used to create game objects
     * based on spawn chances.
     */
    private SpawnPool spawnPool;

    /**
     * Represents the game world where the created objects will be added.
     */
    private final BaseWorld world;

    /**
     * Responsible for calculating the positions of add-ons (monsters, gadgets,
     * money) on platforms.
     */
    private final AddOnPositionSetter addOnPositionSetter;

    /**
     * Constructs a SpawnPoolCreatorImpl with the specified game world.
     *
     * @param world the game world where the created objects will be added
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Is necesery to have a reference to the world to"
            + " add the created objects to it")
    public SpawnPoolCreatorImpl(final BaseWorld world) {
        this.addOnPositionSetter = new AddOnPositionSetterImpl();
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpawnPool(final SpawnPool spawnPool) {
        this.spawnPool = spawnPool;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createStaticPlatform(final double chance, final Vector2d pos) {
        this.selector(chance, pos.getX(), pos.getY(), this.spawnPool.getStaticPlatformPool())
                .ifPresent(world::addStaticPlatform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createMovingPlatform(final double chance, final Vector2d pos) {
        this.selector(chance, pos.getX(), pos.getY(), this.spawnPool.getMovingPlatformPool())
                .ifPresent(world::addMovingPlatform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createOnTouchPlatform(final double chance, final Vector2d pos) {
        this.selector(chance, pos.getX(), pos.getY(), this.spawnPool.getOnTouchPlatformPool())
                .ifPresent(world::addOnTouchPlatform);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createMonster(final double chance, final Platform platform) {
        this.selector(chance, platform.getPosX(), platform.getPosY(), this.spawnPool.getMonsterPool()).ifPresent(
                monster -> world.addMonster(addOnPositionSetter.generatePosition(monster, platform.getWidth())));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createGadget(final double chance, final Platform platform) {
        this.selector(chance, platform.getPosX(), platform.getPosY(), this.spawnPool.getGadgetPool()).ifPresent(
                gadget -> world.addGadget(addOnPositionSetter.generatePosition(gadget, platform.getWidth())));
    }

    /**
     * {@inheritDoc}chance
     */
    @Override
    public void createMoney(final double chance, final Platform platform) {
        this.selector(chance, platform.getPosX(), platform.getPosY(), this.spawnPool.getMoneyPool())
                .ifPresent(money -> world.addMoney(addOnPositionSetter.generatePosition(money, platform.getWidth())));
    }

    /**
     * Selects a game object from the provided pool based on the given spawn chance.
     * 
     * @param <X>    the type of game object to be created
     * @param chance the spawn chance to compare against the pool probabilities
     * @param x      the x-coordinate for the position of the game object
     * @param y      the y-coordinate for the position of the game object
     * @param addOns the list of pairs containing spawn probabilities and
     *               corresponding game object creation functions
     * @return an Optional containing the created game object if a match is found,
     *         or an empty Optional if no match is found
     */
    public <X extends GameObject> Optional<X> selector(final double chance, final double x, final double y,
            final List<Pair<Double, Function<Vector2d, X>>> addOns) {
        for (final var addOn : addOns) {
            if (chance <= addOn.getX()) {
                return Optional.of(addOn.getY().apply(new Vector2dImpl(x, y)));
            }
        }
        return Optional.empty();
    }
}
