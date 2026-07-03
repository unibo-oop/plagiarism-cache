package utils.enumerations;

/**
 * Contains the possible game status.
 */
public enum Status {
    /**
     * Initial-phase of the game. Assignment of the territories to the players.
     * Deployment of the the totality of the initial tanks.
     */
    INITIALIZATION, 

    /**
     * Deployment of the beginning turn tanks for a single player.
     */
    DEPLOYMENT, 

    /**
     * Attack-phase for a single player.
     */
    ATTACK, 

    /**
     * Movement of the final tanks just before the end of the turn.
     */
    MOVEMENT;
}
