package talisman.view.battle;

import talisman.controller.battle.BattleController;

/**
 * An interface for the bottom view of the battle.
 * 
 * @author Alice Girolomini
 *
 */
public interface BattleBottomView {
    /**
     * Gets the attack roll from the specified opponent.
     * 
     * @param character - the opponent
     * @return the value
     */
    int getAttackRoll(int character);

    /**
     * Sets the opponent's attack roll.
     * 
     * @param character - the opponent
     * @param value - the result of the dice roll
     */
    void setAttackRoll(int character, int value);

    /**
     * creates the bottom view of the battle.
     * 
     * @param controller - the controller of the battle
     * @return the bottom view
     */
    static BattleBottomView create(BattleController controller) {
        return new BattleBottomViewImpl(controller);
    }

}
