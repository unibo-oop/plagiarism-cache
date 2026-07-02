package it.unibo.jurassiko.core.api;

/**
 * Interface for the GamePhase, manage the phase of the game.
 */
public interface GamePhase {
    /**
     * The Phase of the game.
     */
    enum Phase {
        /**
         * The placement phase, the player has to place
         * his bonus dino at the start of every turn.
         */
        PLACEMENT,
        /**
         * The first attack phase, the player can attack other territories
         * in this phase or can decide to not attack, you have to select one
         * of your territory to attack.
         */
        ATTACK_FIRST_PART,
        /**
         * The second attack phase, the player have to select one of the
         * enemy territory that's adjacent from the first part selected territory.
         */
        ATTACK_SECOND_PART,
        /**
         * The first movement phase, the player can move the dino from
         * one of his territoriy to other adj territories, you have to
         * select an ally territory who has more than one dino.
         */
        MOVEMENT_FIRST_PART,
        /**
         * The second movement phase, you the player have to select one of
         * the ally territory that's adjacent from the first part selected movement.
         */
        MOVEMENT_SECOND_PART;
    }

    /**
     * Get the Phase of the game.
     * 
     * @return the Phase of the game.
     */
    Phase getPhase();

    /**
     * Change into specific Phase.
     * 
     * @param phase of the game to set
     */
    void setPhase(Phase phase);

    /**
     * Go to the next Phase.
     */
    void goNext();

}
