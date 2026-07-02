package powpaw.powerup.model.api;

import javafx.scene.shape.Circle;
import powpaw.player.model.api.PlayerStats;

/**
 * Power Up interface.
 * 
 * @author Simone Collorà
 */
public interface PowerUp {

    /**
     * Return Hurtbox.
     * 
     * @return Hurtbox.
     */
    Circle getHurtbox();

    /**
     * Set visibility.
     * 
     * @param b
     */
    void setVisible(boolean b);

    /**
     * Return visibility.
     * 
     * @return visibility.
     */
    boolean isVisible();

    /**
     * Increase stat of a player.
     * 
     * @param stats
     */
    void statPowerUp(PlayerStats stats);

}
