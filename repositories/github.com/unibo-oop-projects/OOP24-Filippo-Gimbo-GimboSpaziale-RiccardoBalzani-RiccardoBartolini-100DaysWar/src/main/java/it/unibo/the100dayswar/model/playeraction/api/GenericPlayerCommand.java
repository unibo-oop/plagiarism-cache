package it.unibo.the100dayswar.model.playeraction.api;

import java.io.Serializable;

import it.unibo.the100dayswar.model.player.api.Player;

/**
 * An extension of the command pattern that represents a generic action
 * performed by a player on a generic object.
 * 
 * @param <T> the type of the object that the command is used on.
 */
public interface GenericPlayerCommand<T> extends Serializable {
    /** 
     * Applies the command in relation to the player and the object.
     *
     * @param player the player that performs the command
     * @param object the object used in the command
     */
    void execute(Player player, T object);
}
