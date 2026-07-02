package rogue.model.items.scroll;

import rogue.model.creature.Player;

/**
 * Represents an implementation for a game {@link Scroll}.
 *
 */
public class ScrollImpl implements Scroll {

    private final ScrollType scroll;
    private final int effectValue;

    public ScrollImpl(final ScrollType scroll) {
        this.scroll = scroll;
        this.effectValue = scroll.getEffectValue();
    }

    /**
     * Use this method to consume the Scroll on the given Player.
     * @param player to which apply the scroll to.
     * @return true if the item was correctly used, false otherwise.
     */
    public boolean use(final Player player) {
        /*
         * The minimum value for strength is 0,
         * There is no maximum value for strength.
         * Check the effect of the scroll
         */
        if (this.scroll.getEffect().equals(Scroll.ScrollEffect.GAIN)) {
            /*
             * Since there's no maximum strength for the player
             * simply increase the player's strength.
             */
            player.getLife().addStrength(this.effectValue);
            return true;
        } else {
            /*
             * LOSE effect, player's strength can't go below 0
             */
            final int decrease = this.effectValue;
            if (player.getLife().getStrength() + decrease < 0) {
                /*
                 * Just set the player's strength to 0.
                 */
                player.getLife().addStrength(-player.getLife().getStrength());
            } else {
                /*
                 * Simply decrease player's strength.
                 */
                player.getLife().addStrength(decrease);
            }
            return true;
        }
    }

    /**
     * Removes the scroll effect.
     * @param player on which to remove the effect.
     */
    public void remove(final Player player) {
        /*
         * removes the scroll's effect. both GAIN and LOSE.
         * Strength cannot be below 0
         */
        if (player.getLife().getStrength() - this.scroll.getEffectValue() < 0) {
            /*
             * Set player's strength to 0.
             */
            player.getLife().addStrength(-player.getLife().getStrength());
        } else {
            /*
             * Remove scroll's value to the player.
             */
            player.getLife().addStrength(-this.effectValue);
        }
    }

    /**
     * @return The scroll's effect.
     */
    public Scroll.ScrollEffect getEffect() {
        return this.scroll.getEffect();
    }
    /**
     * @return amount of strength to add or remove to the player.
     */
    public int getEffectValue() {
        return this.effectValue;
    }

    /**
     * @return amount of turns the effect will last.
     */
    public int getEffectDuration() {
        return this.scroll.getEffectDuration();
    }

    /**
     * @return hashCode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + effectValue;
        result = prime * result + ((scroll == null) ? 0 : scroll.hashCode());
        return result;
    }

    /**
     * equals.
     * @param obj to apply the equals.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ScrollImpl other = (ScrollImpl) obj;
        if (effectValue != other.effectValue) {
            return false;
        }
        return scroll != other.scroll ? false : true;
    }

    /**
     * Scroll's toString method.
     */
    @Override
    public String toString() {
        return "ScrollImpl [scroll=" + scroll + ", effectValue=" + effectValue + "]";
    }

}
