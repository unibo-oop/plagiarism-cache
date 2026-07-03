package model.entities;

import java.util.Optional;
import java.util.Random;

import javafx.scene.shape.Shape;
import model.entities.powerup.PowerUpType;
import model.entities.properties.Velocity;

/**
 * This abstract class factories the common methods between different types of enemies.
 * 
 */

public class PassiveEnemy extends AbstractCharacter implements Enemy {


    private static final int LIFE_POX = 50;
    private static final int DEFENCE_POX = 50;
    private static final int ATTACK_DAMAGE_POX = 200;
    private static final int RATE_OF_FIRE_POX = 150;
    private static final int SCORE = 100;
    private Optional<PowerUpType> powerUp;

    /**
     * 
     * @param velocity
     *            initial enemy's velocity.
     * @param shape
     *            initial enemy's shape.
     * @param life
     *            initial enemy's life.
     * @param maxLife
     *            maximum life that the enemy can take on.
     * @param collisionDamage
     *            damage issued from the collision with other entity.
     */

    public PassiveEnemy(final Velocity velocity, final Shape shape, final double life, final double maxLife,
            final double collisionDamage) {
        super(shape, velocity, life, maxLife, collisionDamage);
        this.createRandomPowerUp();
    }

    /**
     * 
     * @param velocity
     *            initial entity's velocity.
     * @param shape
     *            initial entity's shape.
     * @param life
     *            initial entity's life.
     */
    public PassiveEnemy(final Velocity velocity, final Shape shape, 
            final double life) {
        this(velocity, shape, life, life, 1);
    }

    /**
     * Set the power up that may spawn after the enemy death.
     */
    private void createRandomPowerUp() {

        final int i = new Random().nextInt(1000);
        if (i < LIFE_POX) {
            this.powerUp = Optional.of(PowerUpType.LIFE);
        } else if (i < LIFE_POX + DEFENCE_POX) {
            this.powerUp = Optional.of(PowerUpType.DEFENCE);
        } else if (i < LIFE_POX + DEFENCE_POX + ATTACK_DAMAGE_POX) {
            this.powerUp = Optional.of(PowerUpType.DAMAGE);
        } else if (i < LIFE_POX + DEFENCE_POX + ATTACK_DAMAGE_POX + RATE_OF_FIRE_POX) {
            this.powerUp = Optional.of(PowerUpType.RATE_OF_FIRE);
        } else {
            this.powerUp = Optional.empty();
        }

    }

    /**
     * @return the power up that may spawn after the enemy death.
     */
    @Override
    public Optional<PowerUpType> getPowerUp() {
        return this.powerUp;
    }

    /**
     * 
     * @return the score got after killing the enemy.
     */
    @Override
    public int getScore() {
        return (int) (SCORE * this.getMaxLife() / 4);
    }
}
