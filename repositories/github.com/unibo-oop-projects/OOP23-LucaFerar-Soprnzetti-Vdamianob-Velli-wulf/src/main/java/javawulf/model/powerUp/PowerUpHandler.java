package javawulf.model.powerup;

import java.util.Optional;

import javawulf.model.player.Player;

/**
 * PowerUpHandler represents the handler for power Up collection and updating
 * the interaction between player and power Ups.
 */
public interface PowerUpHandler {

    /**
     * PowerUpType defines the power Up type of the current 
     * power Up active.
     */
    enum PowerUpType {
        /**
         * The type of the power Up active is Attack. 
         */
        ATTACK("Attack"),
        /**
         * The type of the power Up active is Invincibility. 
         */
        INVINCIBILITY("Invincibility"),
        /**
         * The type of the power Up active is Double Points. 
         */
        DOUBLE_POINTS("DoublePoints"),
        /**
         * The type of the power Up active is Speed. 
         */
        SPEED("Speed");

        private final String type;

        PowerUpType(final String type) {
            this.type = type;
        }

        /**
         * 
         * @return The type of Power Up active
         */
        public String getType() {
            return this.type;
        }
    }

    /**
     * Collect a power Up and activated for player.
     * 
     * @param powerUpPicked The power Up picked
     * @param player The player who picked up the power Up
     */
    void collectPowerUp(PowerUp powerUpPicked, Player player);

    /**
     * Updates player power Up status.
     * @param player The player who picked up the power Up
     */
    void update(Player player);

    /**
     * 
     * @return The active power Up
     */
    Optional<PowerUp> getPowerUpActive();

}
