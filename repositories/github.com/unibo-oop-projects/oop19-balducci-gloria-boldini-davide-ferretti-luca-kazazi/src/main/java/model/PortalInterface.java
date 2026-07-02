package model;

import model.player.Player;

public interface PortalInterface {

    /**
     * @return color used to place the portals in the map
     */
    public String getColor();

    /**
     * @param player
     * 
     * teleport in another portal in the map
     */
    public void effect(final Player player);

}
