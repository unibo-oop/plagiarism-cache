package it.unibo.cluedolite.model.gameboard.api;

import java.util.List;

import it.unibo.cluedolite.model.player.api.Player;

/**
 * Model for the game board.
 * Tracks room layout, adjacency, and player positions.
 */
public interface GameBoardModel {

    /**
     * Returns a copy of the list of all rooms in the game board.
     *
     * @return a list containing all the rooms
     */
    List<Room> getRooms();

    /**
     * Returns the room with the given name, or {@code null} if not found.
     *
     * @param name the name of the room to search for
     * @return the matching room, or {@code null} if no room has that name
     */
    Room getRoomByName(String name);

    /**
     * Returns the current room of the given player.
     * Returns {@code null} if the player has no position yet (start of the game).
     *
     * @param p the player whose position is requested
     * @return the room where the player is located, or {@code null} if not yet placed
     */
    Room getPlayerPosition(Player p);

    /**
     * Sets the position of the given player to the specified room.
     *
     * @param p the player whose position is to be set
     * @param r the room where the player will be placed
     */
    void setPlayerPosition(Player p, Room r);
}
