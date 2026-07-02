package talisman.view.battle;

import talisman.controller.battle.BattleController;

/**
 * An interface for the top view of the battle.
 * 
 * @author Alice Girolomini
 *
 */
public interface BattleTopView {
    /**
     * Gets the attack score from the specified opponent.
     * 
     * @param character - the opponent
     * @return the value
     */
    int getAttackScore(int character);

    /**
     * Sets the opponent's attack score.
     * 
     * @param character - the opponent
     * @param value - the value to be set
     */
    void setAttackScore(int character, int value);

    /**
     * creates the top view of the battle.
     * 
     * @param controller - the controller of the battle
     *@return the top view
     */
    static BattleTopView create(BattleController controller) {
        return new BattleTopViewImpl(controller);
    }
}
