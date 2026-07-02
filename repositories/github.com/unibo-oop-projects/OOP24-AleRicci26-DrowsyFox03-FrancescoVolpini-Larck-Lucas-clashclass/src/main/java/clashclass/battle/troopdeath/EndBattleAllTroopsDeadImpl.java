package clashclass.battle.troopdeath;

import clashclass.battle.endbattle.AbstractBattleEvent;
import clashclass.ecs.AbstractComponent;
import clashclass.ecs.GameObject;

/**
 * Implementation of EndBattleAllTroopsDead interface
 * Ends the battle when all troops are dead.
 */
public class EndBattleAllTroopsDeadImpl extends AbstractComponent implements EndBattleAllTroopsDead {
    private int troopCount;
    private int deadTroopCount;

    /**
     * Initialize the flag of all troops dead to false
     * and set the initial troop count to 0.
     */
    public EndBattleAllTroopsDeadImpl() {
        this.troopCount = 0;
        this.deadTroopCount = 0;
    }

    /**
     * Check if all troops are dead.
     *
     * @return true if all troops are dead, false otherwise
     */
    @Override
    public boolean isAllTroopsDead() {
        return troopCount > 0 && troopCount == deadTroopCount;
    }

    /**
     * Set the total number of troops in the battle.
     *
     * @param count the number of troops
     */
    @Override
    public void setTroopCount(final int count) {
        this.troopCount = count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyDeath(final GameObject troop) {
        deadTroopCount++;

        if (isAllTroopsDead()) {
            new AbstractBattleEvent(null) {
                @Override
                public void endBattle() {
                    endBattleInternal(troop);
                }
            }.endBattle();
        }
    }
}
