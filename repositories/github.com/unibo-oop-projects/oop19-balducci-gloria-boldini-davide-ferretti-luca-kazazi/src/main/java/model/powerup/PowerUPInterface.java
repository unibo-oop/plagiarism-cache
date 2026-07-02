package model.powerup;

import controllers.timer.LimitedTimer;
import model.player.Player;

public interface PowerUPInterface {

    /**
     * @param player
     * 
     *               effect powerUP on player
     */
    void effect(Player player);

    /**
     * @return powerUP timer
     * 
     *         getter powerUPtimer
     */
    LimitedTimer getTimer();
}
