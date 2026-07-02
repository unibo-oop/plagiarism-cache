package model.managers;

import java.util.Map;

import model.player.Player;
import model.resources.Resource;

/**
 * The manager of the resources of each player.
 */
public interface ResourceManager {

    /**
     * @param player   the player from which increase
     * 
     * @param resource the resource to be increased
     * 
     * @param quantity the quantity to be added
     */
    void increaseResource(Player player, Resource resource, int quantity);

    /**
     * @param player   the player from which decrease
     * 
     * @param resource the resource to be decreased
     * 
     * @param quantity the quantity to be removed
     */
    void decreaseResource(Player player, Resource resource, int quantity);

    /**
     * Increases the max for each quantity.
     * 
     * @param player   the player owning the resources
     * 
     * @param quantity the limit for each resource
     */
    void increaseMax(Player player, int quantity);

    /**
     * Increases the max for each quantity.
     * 
     * @param player   the player owning the resources
     * 
     * @param quantity the limit for each resource
     * 
     */
    void decreaseMax(Player player, int quantity);

    /**
     * @param player the player to be reset max resources
     */
    void resetMax(Player player);

    /**
     * @param player the player from which get the resources
     * 
     * @return the resources of this player in form of a map
     */
    Map<Resource, Integer> getPlayerResourceMap(Player player);

    /**
     * @param player the player from which get the resources
     * 
     * @return the max quantity of the resources of this player in form of a map
     */
    Map<Resource, Integer> getPlayerMaxResourceMap(Player player);

    /**
     * @param player the player from which get the resource map
     * 
     * @return the string representation of the player's resources
     */
    String getPlayerResourcesInfo(Player player);

}
