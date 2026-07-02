package it.unibo.michelito.model.enemy.api.ai;

/**
 * A factory for the various movement that an Enemy can have.
 */
public interface MovementFactory {
    /**
     * @return a slower Movement.
     */
    Movement chilling();

    /**
     * @return a Movement where the enemy don't move as if he were sleeping.
     */
    Movement sleeping();

    /**
     * @return a Movement where the enemy is more active.
     */
    Movement searching();
}
