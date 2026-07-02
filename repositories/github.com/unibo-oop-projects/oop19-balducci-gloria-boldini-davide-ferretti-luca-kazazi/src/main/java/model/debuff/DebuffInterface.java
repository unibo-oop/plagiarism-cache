package model.debuff;

import controllers.timer.LimitedTimer;
import model.player.Player;

public interface DebuffInterface {

    /**
     * @param player
     * 
     *               effect Debuff on player
     */
    void effect(Player player);

    /**
     * @return debuffTimer
     * 
     *         getter debuffTimer
     */
    LimitedTimer getTimer();

}
