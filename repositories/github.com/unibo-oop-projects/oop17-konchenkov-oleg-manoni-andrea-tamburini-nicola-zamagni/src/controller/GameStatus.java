package controller;

/**
 *
 * @author Oleg
 *
 */
public enum GameStatus {

    /**
     * Load json.
     */
    LOAD,

    /**
     * Get user inputs and start new game.
     */
    START_GAME,

    /**
     * Starts a demo of the game.
     */
    START_DEMO,

    /**
     * shoot a projectile.
     */
    SHOOT_PROJECTILE,

    /**
     * set combo inventory.
     */
    SET_COMBO_INVENTORY,

    /**
     * sets power of the slider whit previous shoot power data.
     */
    SET_POWER_OF_SLIDER,

    /**
     * sets angle of the slider whit previous shoot angle data.
     */
    SET_ANGLE_OF_SLIDER,

    /**
     * set current turret angle.
     */
    SET_PROJECTILE_ELEVATION_ANGLE,

    /**
     * end of the match and score scene load.
     */
    GAMEOVER,

    /**
     * stops the turn manager.
     */
    STOP,

    /**
     * close the game.
     */
    EXIT;

}
