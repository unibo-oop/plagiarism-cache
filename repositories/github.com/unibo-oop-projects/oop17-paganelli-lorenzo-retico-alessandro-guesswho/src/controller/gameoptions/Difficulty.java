package controller.gameoptions;

import java.util.Locale;

import model.ai.Ability;

/**
 * Enumeration representing different game's difficulties.
 */
public enum Difficulty {
     /**
      * Normal stage.
      */
    NORMAL(Ability.BEGINNER, 3, true),
    /**
     * Master stage.
     */
    HARD(Ability.ADVANCED, 1, false);

    private final Ability cpuAbility;
    private final int attempts;
    private final boolean questionsDoUpdate;

    Difficulty(final Ability cpuAbility, final int attempts, final boolean questionsDoUpdate) {
        this.cpuAbility = cpuAbility;
        this.attempts = attempts;
        this.questionsDoUpdate = questionsDoUpdate;
    }

    /**
     * @return the level of Cpu's ability
     */
    public Ability getCpuAbility() {
        return cpuAbility;
    }

    /**
     * @return the number of attempts to guess opponent's Character
     */
    public int getAttempts() {
        return attempts;
    }

    /**
     * @return a boolean that indicates whether questions should be updated or not in the View
     */
    public boolean questionsDoUpdate() {
        return questionsDoUpdate;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ITALIAN);
    }
}
