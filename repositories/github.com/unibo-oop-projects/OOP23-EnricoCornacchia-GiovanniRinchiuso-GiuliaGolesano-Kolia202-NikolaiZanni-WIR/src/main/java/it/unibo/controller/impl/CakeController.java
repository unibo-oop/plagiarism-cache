package it.unibo.controller.impl;

import java.util.HashSet;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.common.Pair;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.model.impl.CakePositionComponent;
import it.unibo.model.impl.EntityFactoryImpl;
import it.unibo.utilities.Constants;
import it.unibo.controller.api.Controller;

/**
 * The CakeController class is responsible for managing the creation and removal
 * of cakes in the game.
 */
public class CakeController implements Controller {
    private static final long ACTIVE_DURATION = 5_000;

    private long lastCreationTime;
    private final GamePerformance gamePerformance;
    private final Set<Entity> activeCakes = new HashSet<>();
    private final Set<Pair<Entity, Long>> cakesCreationTimes = new HashSet<>();

    /**
     * Creates a new CakeController.
     *
     * @param gamePerformance the game performance
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public CakeController(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
    }

    /**
     * Returns the set of active cakes.
     *
     * @return the set of active cakes
     */
    public Set<Entity> getCakes() {
        return this.gamePerformance.getCakes();
    }

    private void createCake() {
        final CakePositionComponent cakePositionComponent = new CakePositionComponent(this.gamePerformance);
        final Pair<Double, Double> position = cakePositionComponent.randomPosition();
        final Entity cake = new EntityFactoryImpl(this.gamePerformance).createCake(position);
        this.gamePerformance.addEntity(cake);
        this.activeCakes.add(cake);
        this.cakesCreationTimes.add(new Pair<>(cake, System.currentTimeMillis()));
    }

    private void removeCake(final Entity cake) {
        this.gamePerformance.removeEntity(cake);
        this.activeCakes.remove(cake);
        this.cakesCreationTimes.removeIf(pair -> pair.getX().equals(cake));
    }

    private long getTimeByLevel() {
        switch (this.gamePerformance.getLevel()) {
            case 1:
                return Constants.Cake.CREATION_INTERVA_1_C;
            case 2:
                return Constants.Cake.CREATION_INTERVA_2_C;
            case 3:
                return Constants.Cake.CREATION_INTERVA_3_C;
            default:
        }
        return 0;
    }

    /**
     * Updates the state of the cake controller.
     * This method checks if it's time to create a new cake and removes cakes that
     * have exceeded their active duration.
     */
    @Override
    public void update() {
        final long currentTime = System.currentTimeMillis();
        if (currentTime - lastCreationTime >= getTimeByLevel()) {
            createCake();
            lastCreationTime = currentTime;
        }
        final Set<Entity> cakesToRemove = new HashSet<>();
        for (final Pair<Entity, Long> pair : cakesCreationTimes) {
            if (currentTime - pair.getY() >= ACTIVE_DURATION) {
                cakesToRemove.add(pair.getX());
            }
        }
        for (final Entity cake : cakesToRemove) {
            removeCake(cake);
        }
    }
}
