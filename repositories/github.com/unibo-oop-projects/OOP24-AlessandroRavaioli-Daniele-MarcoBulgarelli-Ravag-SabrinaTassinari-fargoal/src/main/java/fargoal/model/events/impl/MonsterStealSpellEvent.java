package fargoal.model.events.impl;

import fargoal.model.commons.FloorElement;
import fargoal.model.events.api.FloorEvent;
import fargoal.model.interactable.pickupable.inside_chest.api.ChestItem;

/**
 * MonsterStealSpellEvent is a class called everytime that a monster
 * instead of attacking the player steals him a spell.
 */
public class MonsterStealSpellEvent implements FloorEvent {
    private final FloorElement monster;
    private final ChestItem item;

    /**
     * Constructor that assign to the local field monster the
     * corresponding monster that stole the player, and to the field
     * item the corresponding spell that the monster stole.
     * 
     * @param item - the item that has been stolen
     * @param monster - the monster who stole
     */
    public MonsterStealSpellEvent(final ChestItem item, final FloorElement monster) {
        this.item = item;
        this.monster = monster;
    }

    /**
     * Method that returns the item that the monster
     * stole from the player.
     * 
     * @return - the item that has been stolen
     */
    public ChestItem whatMonsterStole() {
        return this.item;
    }

    /**
     * Method that returns the monster that stole
     * from the player.
     * 
     * @return - the monster who stole
     */
    public FloorElement whoStole() {
        return this.monster;
    }

}
