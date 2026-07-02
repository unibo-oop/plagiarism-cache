package javawulf.model.player;

/**
 *  PlayerHealth is a class which manages all that is related to the player's health
 *  including its loss (by damage) and its recovery (by obtaining certain items).
 */
public interface PlayerHealth {

    /**
     * ShieldStatus defines the current status of the player's shield.
     * If FULL it protects the player from 2 hits, if HALF only from 1 hit.
     */
    enum ShieldStatus {
        /**
         * NONE is the default state of the shield. It does not protect
         * the player from any damage
         */
        NONE(0),
        /**
         * HALF is state of the shield after it got hit once. It protects
         * the player from a hit
         */
        HALF(1),
        /**
         * FULL is the state of the shield after the right item
         * has been collected. It protects the player from 2 hits
         */
        FULL(2);

        private final int strength;

        ShieldStatus(final int strength) {
            this.strength = strength;
        }

        /**
         * @return The amount of hits the shield can still withstand
         */
        public int getStrength() {
            return this.strength;
        }
    }

    /**
     * @return The current health the player has
     */
    int getHealth();

    /**
     * @return The maximum health the player can currently have
     */
    int getMaxHealth();

    /**
     * @return The current shield status 
     */
    ShieldStatus getShieldStatus();

    /**
     * Update the player's health by adding/removing a determined amount.
     * If it is greater than the player's maxHealth then it will be
     * equal to maxHealth
     * 
     * @param health The variation the player's health
     */
    void setHealth(int health);

    /**
     * Increase the maximum amount of health the player can have at once.
     * 
     * @param increase The amount by which the maximum health of player has to increase 
     */
    void increaseMaxHealth(int increase);

    /**
     * @param status The status the shield must now have 
     */
    void setShieldStatus(ShieldStatus status);

}
