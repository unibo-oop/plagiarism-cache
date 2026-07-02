package talisman.model.battle;

/**
 *  Different states of a battle. 
 *  @author Alice Girolomini
 */

public enum BattleState {
    /**
     *  The battle begins. 
     */
    START,
    /**
     *  First character wins. 
     */
    FIRST,
    /**
     *  Second character wins. 
     */
    SECOND,
    /**
     *  Stand-off. 
     */
    STAND_OFF,
}
