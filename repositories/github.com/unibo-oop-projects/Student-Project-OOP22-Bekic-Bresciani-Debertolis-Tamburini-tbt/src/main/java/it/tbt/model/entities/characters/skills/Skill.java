package it.tbt.model.entities.characters.skills;

import java.util.Optional;

import it.tbt.model.entities.EntityImpl;
import it.tbt.model.entities.characters.Status;

/**
 * Generic skill.
 */
public class Skill extends EntityImpl {
    private final double attackMultiplier;
    private final int cooldown;
    private int remainingCooldown;
    private final Optional<Status> possibleStatus;
    private final boolean incProbCritical;    // increased probability of critical damage

    /**
     * Default constructor.
     * @param name
     * @param attackMultiplier
     * @param cooldown
     * @param possibleStatus
     * @param incProbCritical
     */
    protected Skill(
        final String name,
        final double attackMultiplier,
        final int cooldown,
        final Optional<Status> possibleStatus,
        final boolean incProbCritical
    ) {
        super(name);
        this.attackMultiplier = attackMultiplier;
        this.cooldown = cooldown;
        this.remainingCooldown = 0;
        this.possibleStatus = possibleStatus;
        this.incProbCritical = incProbCritical;
    }

    /**
     * Get the attack multiplier.
     * @return attack multiplier
     */
    public double getAttackMultiplier() {
        return attackMultiplier;
    }

    /**
     * Get the skill cooldown.
     * @return skill cooldown
     */
    public int getCooldown() {
        return cooldown;
    }

    /**
     * Get the remaining cooldown.
     * @return remaining cooldown
     */
    public int getRemainingCooldown() {
        return remainingCooldown;
    }

    /**
     * Decrease the cooldown by 1.
     */
    public void decreaseCooldown() {
        if (remainingCooldown > 0) {
            remainingCooldown--;
        }
    }

    /**
     * Set the remaining cooldown to the default one.
     */
    public void resetCooldown() {
        remainingCooldown = cooldown;
    }

    /**
     * Get the eventual status inflicted to the enemy.
     * @return Optional<Status>
     */
    public Optional<Status> getPossibleStatus() {
        return possibleStatus;
    }

    /**
     * check if the skill has increased probability of critical damage.
     * @return boolean
     */
    public boolean isIncProbCritical() {
        return incProbCritical;
    }
}
