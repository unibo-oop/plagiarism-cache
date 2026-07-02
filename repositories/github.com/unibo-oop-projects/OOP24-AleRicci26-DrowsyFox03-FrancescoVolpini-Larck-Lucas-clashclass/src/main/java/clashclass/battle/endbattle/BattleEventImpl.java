package clashclass.battle.endbattle;

import clashclass.battle.manager.BattleManagerController;


/**
 * Represents a {@link BattleEvent} implementation.
 */
public class BattleEventImpl implements BattleEvent {
    private final BattleManagerController battleManagerController;

    /**
     * Constructs the battle event.
     *
     * @param battleManagerController the battle manager controller
     */
        public BattleEventImpl(final BattleManagerController battleManagerController) {
        this.battleManagerController = battleManagerController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endBattle() {
        this.battleManagerController.endBattle();
    }
}
