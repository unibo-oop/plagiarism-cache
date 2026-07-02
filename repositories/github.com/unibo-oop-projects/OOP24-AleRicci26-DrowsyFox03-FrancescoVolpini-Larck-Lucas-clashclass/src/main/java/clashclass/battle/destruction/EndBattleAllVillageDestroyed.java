package clashclass.battle.destruction;

/**
 * This class represent one possible cause of the end of the battle.
 * In this case the complete destruction of the enemy base.
 */
public interface EndBattleAllVillageDestroyed extends DestructionObserver {
   /**
    * Verify if the village is fully destroyed.
    *
    * @return true if the village is fully destroyed
    */
   boolean isFullyDestroyed();
}
