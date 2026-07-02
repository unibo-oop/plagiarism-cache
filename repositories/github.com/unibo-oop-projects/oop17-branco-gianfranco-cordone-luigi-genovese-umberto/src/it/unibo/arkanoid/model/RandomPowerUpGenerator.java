package it.unibo.arkanoid.model;

import java.util.List;
import java.util.Random;

import it.unibo.arkanoid.subject.Brick;
import it.unibo.arkanoid.subject.PowerUpType;

/**
 * The implementation of {@link PowerUpGenerator}, where after "n" bricks
 * destroyed, randomly one PowerUp spawn.
 *
 */
public class RandomPowerUpGenerator implements PowerUpGenerator {

    private final List<PowerUpType> powerUpTypes;
    private int count;
    private final int brickCount;

    /**
     * 
     * @param powerTypes
     *            The list of the {@link PowerUpType}.
     * @param brickCount
     *            When the brickCount value is reached, the Power Up is generated.
     */
    public RandomPowerUpGenerator(final List<PowerUpType> powerTypes, final int brickCount) {
        this.powerUpTypes = powerTypes;
        this.brickCount = brickCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBrickDestroyed(final Brick brick, final Level level) {
        count++;
        if (count >= this.brickCount) {
            level.getSubjectFactory().createPowerUp(brick.getPosition(), this.generateRandomPowerUp());
            count = 0;
        }
    }

    private PowerUpType generateRandomPowerUp() {
        return this.powerUpTypes.get(new Random().nextInt(this.powerUpTypes.size()));
    }
}
