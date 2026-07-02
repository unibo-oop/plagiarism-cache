package it.unibo.isaccoop.model.item;

import it.unibo.isaccoop.model.common.MapElement;
import it.unibo.isaccoop.model.player.PlayerStat;

/**
 * Interface that models items.
 * */
public interface Item extends MapElement {
    /**
     *  Method for interacting with player stats.
     *  @param p reference to player.
     * */
    void interact(PlayerStat p);
}
