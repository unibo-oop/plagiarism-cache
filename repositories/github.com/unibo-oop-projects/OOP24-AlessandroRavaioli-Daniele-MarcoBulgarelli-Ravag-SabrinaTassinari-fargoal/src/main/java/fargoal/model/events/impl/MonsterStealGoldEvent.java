package fargoal.model.events.impl;

import fargoal.model.commons.FloorElement;
import fargoal.model.events.api.FloorEvent;

/**
 * MonsterStealGoldEvent is a class called everytime that a 
 * monster instead of attacking the player steals him some gold.
 */
public class MonsterStealGoldEvent implements FloorEvent {
    private final int gold;
    private final FloorElement monster;

    /**
     * Constructor that assigns to the local gold field the amount of 
     * gold that the monster stole, and to the local field monster the monster
     * that stole to the player the gold.
     * 
     * @param gold - the gold stolen
     * @param monster - the monster that stole the gold
     */
    public MonsterStealGoldEvent(final int gold, final FloorElement monster) {
        this.gold = gold;
        this.monster = monster;
    }

    /**
     * Method that returns how much gold the monster
     * stole from the player.
     * 
     * @return - the amount of gold that has been stolen
     */
    public Integer howMuchGold() {
        return this.gold;
    }

    /**
     * Method that returns the monster who stole the player
     * the gold.
     * 
     * @return - the monster who stole the gold
     */
    public FloorElement whoStole() {
        return this.monster;
    }
}
