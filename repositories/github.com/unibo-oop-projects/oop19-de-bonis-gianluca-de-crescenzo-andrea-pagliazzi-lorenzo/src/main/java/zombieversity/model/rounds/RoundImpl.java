package zombieversity.model.rounds;

import zombieversity.model.entities.zombie.Tiers;
import zombieversity.model.entities.zombie.ZombieModel;

/**
 * Implementation of {@link Round}.
 *
 */
public class RoundImpl implements Round {

    /**
     * Multiplier for zombies spawns.
     */
    private static final double SPAWN_FACTOR = 1.5;
    /**
     * Zombies to spawn in the first round divided by spawn factor.
     */
    private static final int FIRST_ROUND_ZOMBIES = 10;
    /**
     * Length of break time in milliseconds.
     */
    private static final int TOTAL_BREAK_TIME = 5000;
    /**
     * Last round to spawn Weak zombies.
     */
    private static final int TIER_1_LIMIT = 7;
    /**
     * Last round to spawn Low zombies.
     */
    private static final int TIER_2_LIMIT = 15;
    /**
     * Last round to spawn Average zombies.
     */
    private static final int TIER_3_LIMIT = 30;
    /**
     * Last round to spawn Strong zombies.
     */
    private static final int TIER_4_LIMIT = 50;

    private final ZombieModel zombies;
    private int roundCount;
    private int zombiesToSpawn;
    private boolean roundIsRunning;
    private double breakStartInstant;
    private double timeToStart;
    private Tiers zombieTier;

    public RoundImpl(final ZombieModel zombies) {
        this.zombies = zombies;
        this.roundCount = 0;
        this.zombiesToSpawn = FIRST_ROUND_ZOMBIES;
        this.roundIsRunning = false;
        this.breakStartInstant = System.currentTimeMillis();
        this.timeToStart = 0;
        this.zombieTier = Tiers.WEAK;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (roundIsRunning && this.zombies.getZombiesCount() == 0) {
            this.endRound();
        }
        if (!roundIsRunning) {
            this.timeToStart = TOTAL_BREAK_TIME - (System.currentTimeMillis() - breakStartInstant);
            if (this.timeToStart <= 0) {
                this.startRound();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getTimeToStart() {
        return this.timeToStart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRoundRunning() {
        return this.roundIsRunning;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getCurrentRound() {
        return this.roundCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getZombiesToSpawn() {
        return this.zombiesToSpawn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Tiers getZombieTier() {
        this.checkTier();
        return this.zombieTier;
    }

    /**
     * Increments the rounds counter and computes number of zombies to spawn in next round.
     */
    private void incrementRound() {
        this.roundCount++;
        this.computeZombiesToSpawn();
    }

    /**
     * Starts the round.
     */
    private void startRound() {
        this.roundIsRunning = true;
        this.incrementRound();
        this.zombies.setZombiesToSpawn(this.getZombiesToSpawn());
        this.zombies.setZombieTier(this.getZombieTier());
    }

    /**
     * Stops game at the end of the round to start break.
     */
    private void endRound() {
        this.breakStartInstant = System.currentTimeMillis();
        this.roundIsRunning = false;
    }

    /**
     * Compute next round zombies number.
     */
    private void computeZombiesToSpawn() {
        this.zombiesToSpawn *= SPAWN_FACTOR;
    }

    /**
     * Checks which tier will belong next round zombies.
     */
    private void checkTier() {
        if (this.roundCount > TIER_1_LIMIT) {
            this.zombieTier = Tiers.LOW;
        }
        if (this.roundCount > TIER_2_LIMIT) {
            this.zombieTier = Tiers.AVERAGE;
        }
        if (this.roundCount > TIER_3_LIMIT) {
            this.zombieTier = Tiers.STRONG;
        }
        if (this.roundCount > TIER_4_LIMIT) {
            this.zombieTier = Tiers.BRUTAL;
        }
    }

}
