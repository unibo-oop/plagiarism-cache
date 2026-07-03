package model.bonus;

import model.player.Player;

/**
 *
 */
public interface PowerBonus {
/**
 * 
 */
    int BASE_BONUS = 1;

    /**
     * This method power the bonus for the selected player.
     * 
     * @param player who received bonus.
     */
    void power(Player player);

}
