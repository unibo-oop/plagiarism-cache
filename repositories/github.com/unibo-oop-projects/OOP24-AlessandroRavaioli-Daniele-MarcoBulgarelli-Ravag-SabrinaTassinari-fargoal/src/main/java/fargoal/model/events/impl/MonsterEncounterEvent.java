package fargoal.model.events.impl;

import fargoal.model.commons.FloorElement;
import fargoal.model.events.api.FloorEvent;

/**
 * MonsterEncounterEvent is a class called everytime that the player
 * finds a monster near to him.
 */
public class MonsterEncounterEvent implements FloorEvent {
    private final FloorElement floorElement;

    /**
     * Constructor that assigns to the local floorElement
     * the floorElement encountered by the player.
     * 
     * @param floorElement - the monster encountered
     */
    public MonsterEncounterEvent(final FloorElement floorElement) {
        this.floorElement = floorElement;
    }

    /**
     * Method that returns the monster that the player encountered
     * walking around the map.
     * 
     * @return - the monster encountered
     */
    public FloorElement monsterEncountered() {
        return this.floorElement;
    }
}
