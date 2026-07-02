package model.staticObject;

import controllers.timer.LimitedTimer;
import model.player.Player;

public interface ActiveStaticObjectInterface {

    /**
     * @param player
     * deletes old static object if present and calls the method to
     * remove it from the screen
     */
    void replaceStaticObject(Player player);

    /**
     * 
     * @param player
     * 
     * method called to terminate the effect of the powerup/debuff
     */
    void terminateEffect(Player player);

    /**
     * 
     * @return the object LimitedTimer
     */
    LimitedTimer getTimer();
}
