package clashclass.battle.endbattle;

/**
 * Interface for battle events that can end a battle.
 * This interface defines the contract for classes that handle battle ending events.
 */
@FunctionalInterface
public interface BattleEvent {
    /**
     * Ends the current battle.
     * This method should be implemented by concrete classes to define
     * specific behavior when ending a battle.
     */
    void endBattle();
}
