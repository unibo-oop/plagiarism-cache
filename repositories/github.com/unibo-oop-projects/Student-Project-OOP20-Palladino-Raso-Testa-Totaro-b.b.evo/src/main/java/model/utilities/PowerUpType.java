package model.utilities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PowerUpType {
    /**
     * Used to represent a {@link PowerUpType} 
     * that speeds the ball up.
     */
    SPEED_UP(PowerUpUtilities.POWERUP_ACTIVE_TIME, PowerUpUtilities.SPEED_MODIFIER, 0, 0), 
    /**
     * Used to represent a {@link PowerUpType} 
     * that adds a life to the player's score.
     */
    LIFE_UP(0, 0, PowerUpUtilities.DEFAULT_LIFE_MODIFIER, 0),
    /**
     * Used to represent a {@link PowerUpType} 
     * that increases the damage dealt by the ball.
     */
    DAMAGE_UP(PowerUpUtilities.POWERUP_ACTIVE_TIME, 0,  0, PowerUpUtilities.DAMAGE_MODIFIER),

    /**
     * Used to represent a {@link PowerUpType} 
     * that speeds the ball down.
     */
    SPEED_DOWN(PowerUpUtilities.POWERUP_ACTIVE_TIME, -PowerUpUtilities.SPEED_MODIFIER, 0, 0), 
    /**
     * Used to represent a {@link PowerUpType} 
     * that reduces a life to the player's score.
     */
    LIFE_DOWN(0, 0, -PowerUpUtilities.DEFAULT_LIFE_MODIFIER, 0),
    /**
     * Used to represent a {@link PowerUpType} 
     * that decreases the damage dealt by the ball.
     */
    DAMAGE_DOWN(PowerUpUtilities.POWERUP_ACTIVE_TIME, 0, 0, -PowerUpUtilities.DAMAGE_MODIFIER); 

    private float activeTime;
    private double speedModifier;
    private int lifeModifier;
    private int damageModifier;
    private static final List<PowerUpType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * Constructor for {@link PowerUpType.SPEED_UP} 
     * and {@link PowerUpType.SPEED_DOWN} types.
     * @param speedModifier
     * @param activeTime
     */
    PowerUpType(final float activeTime, final double speedModifier, final int lifeModifier, final int damageModifier) {
        this.speedModifier = speedModifier;
        this.activeTime = activeTime;
        this.lifeModifier = lifeModifier;
        this.damageModifier = damageModifier;
    }

    /**
     * Used to generate a random value between 
     * all the {@link PowerUpType} available.
     * @return {@link PowerUpType}
     */
    public static PowerUpType randomPowerUpType()  {
          return VALUES.get(RANDOM.nextInt(SIZE));
    }

    /**
     * getter for the powerup active time.
     * @return active time
     */
    public float getActiveTime() {
        return activeTime;
    }

    /**
     * getter for the powerup speed modifier.
     * @return speed modifier
     */
    public double getSpeedModifier() {
        return speedModifier;
    }

    /**
     * getter for the powerup life modifier.
     * @return life modifier
     */
    public int getLifeModifier() {
        return lifeModifier;
    }

    /**
     * getter for the powerup damage modifier.
     * @return damage modifier
     * 
     */
    public int getDamageModifier() {
        return damageModifier;
    }
}
