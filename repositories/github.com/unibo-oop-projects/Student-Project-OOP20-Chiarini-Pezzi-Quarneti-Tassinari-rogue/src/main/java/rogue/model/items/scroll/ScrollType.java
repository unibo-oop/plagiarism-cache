package rogue.model.items.scroll;

import java.util.concurrent.ThreadLocalRandom;

import javafx.util.Pair;

/**
 * Represents an enumeration for declaring scroll types.
 * 
 * Each scroll has type can add or remove strength to the player.
 * The first field keeps track of the effect of the scroll (Gain or lose strength)
 * The second filed is the number of turns the effect will last.
 * The last field is the amount of strength added or removed, it's a pair
 * containing the minimum and maximum, a random number between the two will
 * be randomly picked.
 */
public enum ScrollType {

    /**
     * Scroll I, increases the player's strength by a small
     * amount and lasts for 15 turns.
     */
    SCROLL_I(Scroll.ScrollEffect.GAIN, 15, new Pair<>(1, 3)),
    /**
     * Scroll II, increases the player's strength by a small
     * amount and lasts for 25 turns. 
     */
    SCROLL_II(Scroll.ScrollEffect.GAIN, 25, new Pair<>(1, 3)),
    /**
     * Scroll III, increases the player's strength by a medium
     * amount and lasts for 15 turns.
     */
    SCROLL_III(Scroll.ScrollEffect.GAIN, 15, new Pair<>(3, 5)),
    /**
     * Scroll IV, increases the player's strength by a medium
     * amount and lasts for 25 turns.
     */
    SCROLL_IV(Scroll.ScrollEffect.GAIN, 25, new Pair<>(3, 5)),
    /**
     * Scroll V, increases the player's strength by a high
     * amount and lasts for 15 turns.
     */
    SCROLL_V(Scroll.ScrollEffect.GAIN, 15, new Pair<>(5, 7)),
    /**
     * Corrupt Scroll I, decreases the player's strength by
     * a small amount and lasts for 30 turns.
     */
    CORRUPT_SCROLL_I(Scroll.ScrollEffect.LOSE, 30, new Pair<>(1, 3)),
    /**
     * Corrupt Scroll II, decreases the player's strength by
     * a medium amount and lasts for 20 turns.
     */
    CORRUPT_SCROLL_II(Scroll.ScrollEffect.LOSE, 20, new Pair<>(3, 5));

    private final Scroll.ScrollEffect effect;
    private final int effectDuration;
    private final Pair<Integer, Integer> scrollValue;

    ScrollType(final Scroll.ScrollEffect effect, final int effectDuration, final Pair<Integer, Integer> scrollValue) {
        this.effect = effect;
        this.effectDuration = effectDuration;
        this.scrollValue = scrollValue;
    }

    /**
     * Get the scroll's effect.
     * @return the scroll's effect.
     */
    protected Scroll.ScrollEffect getEffect() {
        return effect;
    }
    /**
     * Get the Scroll strength value.
     * @return the amount of strength of the scroll.
     */
    protected int getEffectValue() {
        return ThreadLocalRandom.current().nextInt(this.scrollValue.getKey(), this.scrollValue.getValue() + 1) 
                * (this.effect.equals(Scroll.ScrollEffect.GAIN) ? 1 : -1);
    }

    /**
     * Get the Scroll effect duration.
     * @return the amount of turns the effect will last.
     */
    protected int getEffectDuration() {
        return this.effectDuration;
    }
}
