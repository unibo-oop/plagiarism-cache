package it.unibo.model.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.common.Pair;
import it.unibo.model.api.Entity;
import it.unibo.model.api.EntityFactory;
import it.unibo.model.api.GamePerformance;
import it.unibo.utilities.EntityType;
import it.unibo.model.api.Component;

/**
 * EntityFactoryImpl.
 */
public class EntityFactoryImpl implements EntityFactory {
    private final GamePerformance gamePerformance;

    /**
     * EntityFactoryImpl constructor.
     * 
     * @param gamePerformance the game performance of the entity factory.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public EntityFactoryImpl(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createFelix(final Pair<Double, Double> pos) {
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent(),
                new PointsComponent(),
                new ImmortalityComponent(),
                new LivesComponent(),
                new FixWindowsComponent(),
                new HitboxComponent(pos.getX(), pos.getY(), EntityType.FELIX)));
        return new EntityImpl(EntityType.FELIX, pos, this.gamePerformance, components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createRalph(final Pair<Double, Double> pos) {
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent(), 
                new HitboxComponent(pos.getX(), pos.getY(), EntityType.RALPH),
                new StopRalphComponent(),
                new ThrowBrickComponent(this.gamePerformance)));
        return new EntityImpl(EntityType.RALPH, pos, this.gamePerformance, components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createWindows(final Pair<Double, Double> pos, final boolean state) {
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent(),
                new HitboxComponent(pos.getX(), pos.getY(), EntityType.WINDOW),
                new FixedWindowsComponent(state)));
        return new EntityImpl(EntityType.WINDOW, pos, this.gamePerformance, components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createBrick(final Pair<Double, Double> pos) {
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent(), 
        new HitboxComponent(pos.getX(), pos.getY(), EntityType.BRICK)));
        return new EntityImpl(EntityType.BRICK, pos, this.gamePerformance, components);
    }

   /**
     * {@inheritDoc}
     */
    @Override
    public Entity createCake(final Pair<Double, Double> pos) {
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent(),
                new CakePositionComponent(gamePerformance),
                new HitboxComponent(pos.getX(), pos.getY(), EntityType.CAKE)));
        return new EntityImpl(EntityType.CAKE, pos, this.gamePerformance, components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createBird(final Pair<Double, Double> pos) {
        final Set<Component> components = new HashSet<>(Arrays.asList(new MovementComponent(),
                new BirdPositionComponent(),
                new HitboxComponent(pos.getX(), pos.getY(), EntityType.BIRD)));
        return new EntityImpl(EntityType.BIRD, pos, this.gamePerformance, components);
    }
}

