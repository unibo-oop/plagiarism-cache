package rogue.model.items.potion;

import rogue.model.creature.Player;

/**
 * Represents an implementation for a game {@link Potion}.
 *
 */
public class PotionImpl implements Potion {

    private final PotionType potion;
    private final int hpValue;

    public PotionImpl(final PotionType potion) {
        this.potion = potion;
        this.hpValue = potion.getHpValue();
    }

    /**
     * Use this method to consume to potion on the player.
     * @param player to which apply the potion.
     * @return true if the potion was correctly used, false otherwise
     */
    public boolean use(final Player player) {
        /*
         * The first thing to check is the potion effect.
         * If the potion effect is HEAL we have to check not to
         * increase over the MAXIMUM_HEALTH value-
         * if the potion effect is HURT we have to check not to 
         * lower it under 0, we just set it to 0 and the player dies.
         */
        if (this.potion.getEffect().equals(Potion.PotionEffect.HEAL)) {
            final int increase = this.hpValue;
            /*
             * HEAL
             */
            if (player.getLife().getHealthPoints() != player.getLife().getMaxHealthPoints()) {
                if (player.getLife().getHealthPoints() + increase > player.getLife().getMaxHealthPoints()) {
                    /*
                     * I can't increase the player's health over the maximum health points
                     * so i just set it to max.
                     */
                    player.getLife().powerUp(player.getLife().getMaxHealthPoints() - player.getLife().getHealthPoints());
                } else {
                    /*
                     * Simply updates the player's health points.
                     */
                    player.getLife().powerUp(increase);
                }
                return true;
            } else {
                /*
                 * Player's life already full, cannot use item.
                 */
                return false;
            }
        } else {
            /*
             * HURT, always used since they can kill the player.
             */
            final int decrease = this.hpValue;
            if (player.getLife().getHealthPoints() + decrease < 0) {
                /*
                 * Player's health can't be below 0.
                 * Update player's health to exactly 0.
                 */
                player.getLife().powerUp(-player.getLife().getHealthPoints());
                return true;
            } else {
                /*
                 * Simply update player's health points.
                 */
                player.getLife().powerUp(decrease);
                return true;
            }
        }
    }

    /**
     * @return return the amount of health the Potion will add or remove
     * to the player.
     */
    public int getHpValue() {
        return this.hpValue;
    }

    /**
     * @return the potion's effect.
     */
    public Potion.PotionEffect getEffect() {
        return this.potion.getEffect();
    }

    /**
     * @return hashCode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + hpValue;
        result = prime * result + ((potion == null) ? 0 : potion.hashCode());
        return result;
    }

    /**
     * Equals.
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
        final PotionImpl other = (PotionImpl) obj;
        if (hpValue != other.hpValue) {
            return false;
        }
        return potion != other.potion ? false : true;
    }

    /**
     * Potion's toString method.
     */
    @Override
    public String toString() {
        return "PotionImpl [potion=" + potion + ", hpValue=" + hpValue + "]";
    }

}
