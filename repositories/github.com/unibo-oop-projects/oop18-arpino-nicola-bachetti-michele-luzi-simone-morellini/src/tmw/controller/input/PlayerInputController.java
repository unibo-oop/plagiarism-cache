package tmw.controller.input;

import tmw.common.CharacterStates;
import tmw.common.V2d;

/**
 * Interface that handle the interactions between the playerEntity and the
 * user.
 *
 */
public interface PlayerInputController {

    /**
     * return the main character state. Do not specified the direction or the exact
     * item.
     * 
     * @return character's state
     */
    CharacterStates update();

    /**
     * Getter for velocity.
     * 
     * @return velocity of bullet or character
     */
    V2d getVel();

    /**
     * Getter for inventory items position.
     * 
     * @return the item position
     */
    int getItemPos();

    /**
     * Allows to say that last command has been processed.
     */
    void cleanCommand();

}
