package fargoal.model.events.impl;

import fargoal.model.events.api.FloorEvent;

/**
 * PickUpGoldEvent is a class called everytime the player decides
 * to interactor with a sack of gold on the ground.
 */
public class PickUpGoldEvent implements FloorEvent {
    private final Integer gold;

    /**
     * Constructor that assigns to the local field gold the 
     * corresponding amount of gold that the player found
     * in the sack.
     * 
     * @param gold - the amount of gold that has been found
     */
    public PickUpGoldEvent(final Integer gold) {
        this.gold = gold;
    }

    /**
     * Method that returns the amount of gold
     * found in the sack on the ground.
     * 
     * @return - the amount of gold that has been found
     */
    public Integer goldFound() {
        return this.gold;
    } 
}
