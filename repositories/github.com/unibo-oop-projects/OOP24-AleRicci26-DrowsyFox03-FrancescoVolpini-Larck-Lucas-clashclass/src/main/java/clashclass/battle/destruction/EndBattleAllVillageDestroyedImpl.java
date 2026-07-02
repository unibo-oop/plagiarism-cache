package clashclass.battle.destruction;

import clashclass.battle.battlereport.BattleReportController;
import clashclass.battle.endbattle.AbstractBattleEvent;
import clashclass.battle.manager.BattleManagerController;
import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;


/**
 * Implementation of EndBattleAllVillageDestroyed interface.
 */
public class EndBattleAllVillageDestroyedImpl extends AbstractComponent implements EndBattleAllVillageDestroyed {
    private final BattleManagerController battleManagerController;
    private final BattleReportController battleReportController;

    /**
     * Initialize the flag of the village to not destroyed.
     *
     * @param battleManagerController the battle manager controller
     * @param battleReportController the battle report controller
     */
        public EndBattleAllVillageDestroyedImpl(
            final BattleManagerController battleManagerController,
            final BattleReportController battleReportController) {
        this.battleManagerController = battleManagerController;
        this.battleReportController = battleReportController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFullyDestroyed() {
        return battleReportController.getDestructionPercentage() >= 100.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyDestruction(final GameObject obj) {
        if (isFullyDestroyed()) {
            new AbstractBattleEvent(this.battleManagerController) {
                @Override
                public void endBattle() {
                    endBattleInternal(obj);
                }
            }.endBattle();
        }
    }
}
