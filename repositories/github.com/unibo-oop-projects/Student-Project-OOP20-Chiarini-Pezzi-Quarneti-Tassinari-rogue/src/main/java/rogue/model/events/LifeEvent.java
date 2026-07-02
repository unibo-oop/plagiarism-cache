package rogue.model.events;

import java.util.Set;

import javafx.util.Pair;
import rogue.model.creature.Life;
import rogue.model.creature.PlayerAttribute;

/**
 * A class representing the change of life.
 *
 * @param <L> the type of life affected by the change
 */
public final class LifeEvent<L extends Life> implements Event {

    private final Set<Pair<PlayerAttribute, Integer>> changed;
    private final L life;

    /**
     * Creates a new LifeEvent.
     * @param changed
     *          a {@link Pair} describing the player attribute which changed and the new value
     * @param life
     *          the life which changed
     */
    public LifeEvent(final L life, final Set<Pair<PlayerAttribute, Integer>> changed) {
        this.changed = changed;
        this.life = life;
    }

    /**
     * @return a {@link Pair} describing the player attribute which changed and the new value
     */
    public Set<Pair<PlayerAttribute, Integer>> getChanged() {
        return this.changed;
    }

    /**
     * 
     * @return the life
     */
    public L getLife() {
        return this.life;
    }

    @Override
    public String toString() {
        return life.toString();
    }

}
