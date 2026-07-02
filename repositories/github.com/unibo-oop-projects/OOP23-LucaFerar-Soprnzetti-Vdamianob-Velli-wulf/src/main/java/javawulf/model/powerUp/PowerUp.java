package javawulf.model.powerup;

import javawulf.model.Collectable;
import javawulf.model.player.Player;

/**
 * PowerUp represents the powerUp stats.
 */
public interface PowerUp extends Collectable {

    /**
     * @return If PowerUp is still active
     */
    boolean stillActive();

    /**
     * Updates the duration of the PowerUp.
     */
    void updateDuration();

    /**
     * @return The points given to the player
     */
    int getPoints();

    /**
     * @return The duration left
     */
    int getDuration();

    /**
     * @return The type of the PowerUp
     */
    String getType();

    /**
     * Consumes the PowerUp and updates the player.
     * @param p The player to Update
     */
    void finishEffect(Player p);

}
