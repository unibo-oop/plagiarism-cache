package model.utils;

import java.util.Optional;

import model.board.Tile;
import model.players.Player;

/**
 * This class represent the tile with eventually the Player on it.
 */

public interface TilePlayerPair {

    /**
     * @return Optional<Player> 
     */
    Optional<Player> getPlayer();

    /**
     * @return a Tile
     */
    Tile getTile();

    /**
     * @param tTile
     *                Tile To set
     */
    void setTile(Tile tTile);

    /**
     * @param player
     *                Player To Set
     */
    void setPlayer(Player player);

}
