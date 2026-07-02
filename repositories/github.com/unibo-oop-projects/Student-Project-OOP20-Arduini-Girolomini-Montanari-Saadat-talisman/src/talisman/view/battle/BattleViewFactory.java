package talisman.view.battle;

import talisman.controller.battle.BattleController;

/**
 * Factory used to create the view of the battle.
 * 
 * @author Alice Girolomini
 *
 */
public final class BattleViewFactory {

    private BattleViewFactory() {
    }

    /**
     * Creates the top view.
     * 
     * @param controller - the controller of the battle
     * @return  the view
     */
    public static BattleTopView createTopView(final BattleController controller) {
        BattleTopView view = BattleTopView.create(controller);
        return view;
    }

    /**
     * Creates the center view.
     * 
     * @param controller - the controller of the battle
     * @param topView - the top view of the battle
     * @param bottomView - the bottom view of the battle
     * @return  the view
     */
    public static BattleCenterView createCenterView(final BattleController controller, final BattleTopView topView, final BattleBottomView bottomView) {
        BattleCenterView view = BattleCenterView.create(controller, topView, bottomView);
        return view;
    }

    /**
     * Creates the bottom view.
     * 
     * @param controller - the controller of the battle
     * @return  the view
     */
    public static BattleBottomView createBottomView(final BattleController controller) {
        BattleBottomView view = BattleBottomView.create(controller);
        return view;
    }

}
