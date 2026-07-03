package model;

import lands.Lands.MyJButton;

/**
 * 
 * this is an interface .
 *
 */
public interface Actions {

    /**
     * this method is called at the beginning of every turn .
     * @param combo is the number of armies to add by cards.
     */
    void newTurn(int combo);

    /**
     * this method is used to set the intent to move after the move_botton is pressed .
     */
    void setIntentMove();

    /**
     * @param pressedbotton is the action of the player .
     * this method choose the way to take .
     */
    void switchCase(MyJButton pressedbotton);

}