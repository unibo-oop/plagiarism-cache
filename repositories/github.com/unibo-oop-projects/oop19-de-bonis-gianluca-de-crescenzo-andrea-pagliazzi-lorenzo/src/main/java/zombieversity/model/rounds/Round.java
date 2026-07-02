package zombieversity.model.rounds;

import zombieversity.model.entities.zombie.Tiers;

/**
 * Interface to manage round flow.
 *
 */
public interface Round {

    /**
     * Used to update rounds.
     */
    void update();

    /**
     * 
     * @return true if round is active, false otherwise.
     */
    boolean isRoundRunning();

    /**
     * 
     * @return time to start next round after break.
     */
    double getTimeToStart();
    /**
     * 
     * @return current round number.
     */
    int getCurrentRound();

    /**
     * 
     * @return zombies tier in incoming round
     */
    Tiers getZombieTier();

    /**
     * 
     * @return number of zombies to spawn at the beginning of the round.
     */
    int getZombiesToSpawn();

}
