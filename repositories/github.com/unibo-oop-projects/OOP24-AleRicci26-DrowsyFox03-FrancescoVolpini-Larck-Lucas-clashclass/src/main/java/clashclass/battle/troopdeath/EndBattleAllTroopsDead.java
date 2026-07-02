package clashclass.battle.troopdeath;

/**
 * This class represents one possible cause of the end of the battle.
 * In this case, the complete death of all troops.
 */
public interface EndBattleAllTroopsDead extends TroopDeathObserver {

    /**
     * Verify if all troops are dead.
     *
     * @return true if all troops are dead, false otherwise
     */
    boolean isAllTroopsDead();

    /**
     * Set the total number of troops in the battle.
     *
     * @param count the number of troops
     */
    void setTroopCount(int count);
}
