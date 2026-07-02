package fargoal.model.events.impl;

import fargoal.model.commons.FloorElement;
import fargoal.model.events.api.FloorEvent;

/**
 * ReceiveAttackEvent is a class called everytime that the player
 * receives an attack from a monster.
 */
public class ReceiveAttackEvent implements FloorEvent {
    private final FloorElement floorElement;

    /**
     * Constructor that assigns to the local field floorElement the
     * corresponding FloorElement monster that attacks the player.
     * 
     * @param floorElement - the monster who attacked the player
     */
    public ReceiveAttackEvent(final FloorElement floorElement) {
        this.floorElement = floorElement;
    }

    /**
     * Method that returns the FloorElement monster
     * who attacked the player.
     * 
     * @return - the monster who attacked the player
     */
    public FloorElement attackedFrom() {
        return this.floorElement;
    }
}
