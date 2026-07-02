package model.objects.structures;

import java.util.Optional;

import model.player.Player;

/**
 * 
 * Stuffs that can be built on the terrain and has a owner.
 */
public interface OwnableStructure extends Structure {

    /**
     * This method tells that a player just step on this structure and is not its
     * owner.
     * 
     * @param player the player of the unit that step on the structure
     */
    void initiateConquer(Player player);

    /**
     * @return the player that is trying to conquer the structure
     */
    Optional<Player> getConqueror();

    /**
     * Resets the conqueror.
     */
    void endConquer();

}
