package rogue.model.items.inventory;

import java.util.Optional;

import javafx.util.Pair;
import rogue.model.creature.Player;
import rogue.model.events.AbstractEventPublisher;
import rogue.model.events.InventoryEvent;
import rogue.model.items.scroll.Scroll;

/**
 * An implementation of a {@link ScrollContainer}.
 *
 */
public class ScrollContainerImpl extends AbstractEventPublisher implements ScrollContainer {

    private Pair<Optional<Scroll>, Integer> scroll;
    private final Player player;

    public ScrollContainerImpl(final Player player) {
        this.player = player;
        scroll = new Pair<>(Optional.empty(), 0);
    }

    /**
     * @param scroll to activate 
     */
    public void activateScroll(final Scroll scroll) {
        /*
         * Use the removeActiveScroll method to remove eventual active scroll.
         * Then activate the given scroll.
         */
        removeActiveScroll();
        this.scroll = new Pair<>(Optional.of(scroll), scroll.getEffectDuration());
        this.post(new InventoryEvent<>(player.getInventory()));
    }

    /**
     * @return true if correctly removed active scroll, false if there's
     * no scroll to remove.
     */
    public boolean removeActiveScroll() {
        if (this.scroll.getKey().isEmpty()) {
            /*
             * No scroll to remove.
             */
            return false;
        } else {
            /*
             * Before removing the scroll, remove the scroll's effect.
             */
            this.scroll.getKey().get().remove(this.player);
            /*
             * Remove the scroll.
             */
            this.scroll = new Pair<>(Optional.empty(), 0);
            this.post(new InventoryEvent<>(player.getInventory()));
            return true;
        }
    }

    /**
     * @return Currently active scroll.
     */
    public Optional<Scroll> getActiveScroll() {
        return this.scroll.getKey();
    }

    /**
     * @return the active scroll duration.
     */
    public int getActiveScrollDuration() {
        return this.scroll.getValue();
    }
    /**
     * @param amount to subtract to scroll's duration
     * @return true if correctly updated the duration, false if
     * there's no active scroll.
     */
    public boolean updateEffectDuration(final int amount) {
        /*
         * Check if active Scroll
         */
        if (!this.scroll.getKey().isEmpty()) {
            /*
             * Check if amount will remove the scroll.
             */
            if (this.scroll.getValue() - amount <= 0) {
                /*
                 * Scroll duration is over, remove scroll.
                 */
                return removeActiveScroll();
            } else {
                /*
                 * Update scroll's duration
                 */
                this.scroll = new Pair<>(this.scroll.getKey(), this.scroll.getValue() - amount);
                this.post(new InventoryEvent<>(player.getInventory()));
                return true;
            }
        } else {
            /*
             * No active scroll, nothing to update
             */
            return false;
        }
    }

}
