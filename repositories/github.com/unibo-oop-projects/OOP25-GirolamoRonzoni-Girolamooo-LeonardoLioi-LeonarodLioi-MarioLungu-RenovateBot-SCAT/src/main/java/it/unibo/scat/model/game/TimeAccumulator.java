package it.unibo.scat.model.game;

import java.util.concurrent.atomic.AtomicInteger;

import it.unibo.scat.common.Constants;

/**
 * Manages time accumulation for different game entities.
 */
public final class TimeAccumulator {
    private final DifficultyManager difficultyManager;
    private final AtomicInteger invadersAccMs = new AtomicInteger(0);
    private final AtomicInteger bonusInvaderAccMs = new AtomicInteger(0);
    private final AtomicInteger shotsAccMs = new AtomicInteger(0);

    /**
     * Constructor that initializes the time accumulator.
     * 
     * @param difficultyManager the manager used to calculate invader speed, invader
     *                          shot speed and frequency.
     */
    public TimeAccumulator(final DifficultyManager difficultyManager) {
        this.difficultyManager = difficultyManager;
    }

    /**
     * Consumes time after a logical step has been
     * performed.
     */
    private void consumeAccumulators() {
        if (invadersAccMs.get() >= difficultyManager.getInvadersStepMs()) {
            invadersAccMs.set(invadersAccMs.get() - difficultyManager.getInvadersStepMs());
        }

        if (bonusInvaderAccMs.get() >= Constants.BONUSINVADER_STEP_MS) {
            bonusInvaderAccMs.set(bonusInvaderAccMs.get() - Constants.BONUSINVADER_STEP_MS);
        }

        if (shotsAccMs.get() >= Constants.SHOT_STEP_MS) {
            shotsAccMs.set(shotsAccMs.get() - Constants.SHOT_STEP_MS);
        }
    }

    /**
     * Increments the time accumulators, by adding the fixed game step duration.
     */
    public void incrementTimeAccumulators() {
        consumeAccumulators();

        invadersAccMs.set(invadersAccMs.get() + Constants.GAME_STEP_MS);
        bonusInvaderAccMs.set(bonusInvaderAccMs.get() + Constants.GAME_STEP_MS);
        shotsAccMs.set(shotsAccMs.get() + Constants.GAME_STEP_MS);
    }

    /**
     * @return the current accumulated time in milliseconds for the invaders'
     *         movement.
     */
    public int getInvadersAccMs() {
        return invadersAccMs.get();
    }

    /**
     * @return the current accumulated time in milliseconds for the bonus invader.
     */
    public int getBonusInvaderAccMs() {
        return bonusInvaderAccMs.get();
    }

    /**
     * @return the current accumulated time in milliseconds for the shots' movement.
     */
    public int getShotsAccMs() {
        return shotsAccMs.get();
    }

}
