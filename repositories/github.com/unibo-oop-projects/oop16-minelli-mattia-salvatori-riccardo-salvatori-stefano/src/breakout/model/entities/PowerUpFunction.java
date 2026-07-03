package breakout.model.entities;

import breakout.model.AdvancedMode;

/**
 * Functional interface for power up effect.
 */
interface PowerUpFunction {

    /**
     * This method determines the effect of the powerup on the game.
     * 
     * @param model
     *            the model in which apply this function
     */
    void action(final AdvancedMode model);
}
