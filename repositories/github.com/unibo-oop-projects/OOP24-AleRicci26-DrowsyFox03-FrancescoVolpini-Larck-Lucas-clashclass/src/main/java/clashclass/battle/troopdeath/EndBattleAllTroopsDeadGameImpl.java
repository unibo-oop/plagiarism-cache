package clashclass.battle.troopdeath;

import clashclass.battle.endbattle.AbstractBattleEvent;
import clashclass.battle.manager.BattleManagerController;
import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;


/**
 * Represents a {@link EndBattleAllTroopsDead} implementation.
 */
public class EndBattleAllTroopsDeadGameImpl extends AbstractComponent implements EndBattleAllTroopsDead {
    private final BattleManagerController battleManagerController;

    /**
     * Constructs the observer.
     *
     * @param battleManagerController the battle manager controller
     */
        public EndBattleAllTroopsDeadGameImpl(final BattleManagerController battleManagerController) {
        this.battleManagerController = battleManagerController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAllTroopsDead() {
        return this.battleManagerController.areAllTroopsDead();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTroopCount(final int count) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyDeath(final GameObject troop) {
        if (this.isAllTroopsDead()) {
            new AbstractBattleEvent(this.battleManagerController) {
                @Override
                public void endBattle() {
                    endBattleInternal(troop);
                }
            }.endBattle();
        }
    }
}
