package it.unibo.model.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.common.Pair;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.GamePerformance;
import it.unibo.utilities.Constants;
import it.unibo.model.api.Entity;

/**
 * Class to manage a cake power-up position.
 */
public class CakePositionComponent extends AbstractComponent {
    private final GamePerformance gamePerformance;
    private final Random rand;

    /**
     * Constructor to initialize GamePerformance and Random.
     * 
     * @param gamePerformance the game performance.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public CakePositionComponent(final GamePerformance gamePerformance) {
        this.gamePerformance = gamePerformance;
        this.rand = new Random();
    }

    /**
     * Method to return a random position from window positions.
     * 
     * @return a pair with the x and y position of the cake.
     */
    public Pair<Double, Double> randomPosition() {
        final List<Pair<Double, Double>> windowPositions = this.gamePerformance.getWindows().stream()
                .map(Entity::getPosition)
                .collect(Collectors.toList());

        if (windowPositions.isEmpty()) {
            throw new IllegalStateException("No windows available to place the cake.");
        }

        final Pair<Double, Double> chosenPosition = windowPositions.get(rand.nextInt(windowPositions.size()));

        // Apply the offset
        final double cakeX = chosenPosition.getX() + Constants.Cake.OFFSET_X;
        final double cakeY = chosenPosition.getY() + Constants.Cake.OFFSET_Y;

        return new Pair<>(cakeX, cakeY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.CAKEPOSITION;
    }
}
