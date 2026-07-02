package it.unibo.scat.model.game;

import java.util.concurrent.atomic.AtomicInteger;

import it.unibo.scat.common.Constants;

/**
 * This class handles the difficulty of the game.
 */
public final class DifficultyManager {
    private static final int MIN_INVADERS_STEP_MS = 500;
    private static final int STEP_SPEED_INCREMENT = 130;
    private static final int MAX_INVADERS_COOLDOWN = 2000;
    private static final int MIN_STEP_LIMIT = 80;
    private static final int MIN_COOLDOWN_LIMIT = 300;
    private static final int MIN_INVADERS_SHOTS = 1;
    private static final int INVADERS_FOR_EXTRA_SHOT = 15;
    private static final int LEVELS_FOR_EXTRA_SHOT = 1;
    private static final int LAST_FEW_INVADERS = 10;
    private static final int LAST_INVADERS_COOLDOWN = 600;
    private final AtomicInteger level = new AtomicInteger(1);

    /**
     * Returns the coefficient used for level incrementation (for steps AND for
     * shots cooldown).
     * 
     * @param factor level multiplier.
     * @return the coefficient used for level incrementation.
     */
    private int calculateIncrementLevel(final int factor) {
        return (level.get() - 1) * factor;
    }

    /**
     * Calculates and returns the new speed of invaders steps.
     * 
     * @return invaders speed.
     */
    public int getInvadersStepMs() {
        final int reduction = calculateIncrementLevel(STEP_SPEED_INCREMENT);
        final int currentStep = MIN_INVADERS_STEP_MS - reduction;

        return Math.max(MIN_STEP_LIMIT, currentStep);
    }

    /**
     * Calculates and returns the cooldown of the shooting of the invaders. When the
     * level increases, the shots get fired more often.
     * When there are 10 or less invaders, the shooting cooldown is bigger.
     * 
     * @param invadersCounter the number of alive invaders at the given frame.
     * @return the invader shooting cooldown value.
     */
    public int getInvadersShootingCooldown(final int invadersCounter) {
        int currentCooldown = MAX_INVADERS_COOLDOWN / level.get();

        if (invadersCounter <= LAST_FEW_INVADERS) {
            currentCooldown += LAST_INVADERS_COOLDOWN;
        }

        return Math.max(MIN_COOLDOWN_LIMIT, currentCooldown);
    }

    /**
     * Calculates and returns the max number of shots that the invaders can shot at
     * a certain time.
     * 
     * @param invadersCounter the number of invaders that are alive.
     * @return the max number of shots.
     */
    public int getMaxInvadersShots(final int invadersCounter) {
        int shots = MIN_INVADERS_SHOTS;

        if (invadersCounter > Constants.ZERO) {
            shots += invadersCounter / INVADERS_FOR_EXTRA_SHOT;
        }

        shots += (level.get() - 1) / LEVELS_FOR_EXTRA_SHOT;

        return shots;
    }

    /**
     * Level getter.
     * 
     * @return the current level.
     */
    public int getLevel() {
        return level.get();
    }

    /**
     * Increases the level.
     */
    public void incrementLevel() {
        level.incrementAndGet();
    }

    /**
     * Resets the level to 1.
     */
    public void resetLevel() {
        level.set(1);
    }
}
