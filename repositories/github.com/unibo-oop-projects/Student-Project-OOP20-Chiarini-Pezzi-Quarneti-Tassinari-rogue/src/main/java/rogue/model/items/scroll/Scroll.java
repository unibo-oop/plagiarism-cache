package rogue.model.items.scroll;

import rogue.model.creature.Player;
import rogue.model.items.Item;

/**
 * An interface modeling a game's Scroll.
 *
 */
public interface Scroll extends Item {

    /**
     * Enumeration that represents the effect of a scroll.
     *
     */
    enum ScrollEffect {
        GAIN, LOSE;
    }

    /**
     * Remove the effect of a scroll from a player.
     * @param player to remove the scroll's effect from.
     */
    void remove(Player player);

    /**
     * Get the scroll's effect value.
     * @return scroll's effect value.
     */
    int getEffectValue();

    /**
     * Get the scroll's effect duration.
     * @return the number of turns the scroll's effect lasts.
     */
    int getEffectDuration();

}
