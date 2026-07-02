package rogue.model.items.inventory;

import java.util.Optional;

import rogue.model.events.EventPublisher;
import rogue.model.items.scroll.Scroll;

/**
 * An interface for a Scroll container.
 */
public interface ScrollContainer extends EventPublisher {

    /**
     * Activate a scroll.
     * @param scroll to activate.
     */
    void activateScroll(Scroll scroll);

    /**
     * Remove an active scroll.
     * @return true if active scroll was successfully removed, false if
     * there's no active scroll to remove.
     */
    boolean removeActiveScroll();

    /**
     * Return the active scroll remaining turns.
     * @return the active scroll duration.
     */
    int getActiveScrollDuration();

    /**
     * Get the currently active scroll.
     * @return Optional.empty() if no active scroll, Optional.of(Scroll) otherwise
     */
    Optional<Scroll> getActiveScroll();

    /**
     * Update the currently active scroll duration.
     * @return true if correctly updated, false if there's no active scroll to update.
     * @param amount to subtract to the scroll's duration.
     */
    boolean updateEffectDuration(int amount);

}
