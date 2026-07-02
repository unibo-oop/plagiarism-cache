package talisman.view.battle;


import talisman.controller.battle.BattleController;

/**
 * An interface for the center view of the battle.
 * 
 * @author Alice Girolomini
 *
 */
public interface BattleCenterView {
    /**
     * Creates the center view of the battle.
     * 
     * @param controller - the controller of the battle
     * @param topView - the top view of the battle
     * @param bottomView - the bottom view of the battle
     * @return the center view
     */
    static BattleCenterView create(BattleController controller, BattleTopView topView, BattleBottomView bottomView) {
        return new BattleCenterViewImpl(controller, topView, bottomView);
    }
}
