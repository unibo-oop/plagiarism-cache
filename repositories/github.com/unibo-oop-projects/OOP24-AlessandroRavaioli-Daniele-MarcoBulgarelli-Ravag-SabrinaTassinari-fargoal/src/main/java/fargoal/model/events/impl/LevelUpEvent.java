package fargoal.model.events.impl;

import fargoal.model.events.api.FloorEvent;

/**
 * LevelUpEvent is a class that is called everytime
 * that the player Level Up.
 */
public class LevelUpEvent implements FloorEvent {

    private final int level;

    /**
     * Constructor that assigns to the local field {@link #level}
     * the level given.
     * 
     * @param level - the current level of the Player
     */
    public LevelUpEvent(final Integer level) {
        this.level = level;
    }

    /**
     * Method that returns the current level of the Player
     * after the Level Up.
     * 
     * @return - the current level of the Player
     */
    public Integer whatLevelPlayerIs() {
        return this.level;
    }
}
