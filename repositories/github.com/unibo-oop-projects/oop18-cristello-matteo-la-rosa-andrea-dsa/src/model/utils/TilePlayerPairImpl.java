package model.utils;
import java.util.Optional;

import model.board.Tile;
import model.players.Player;

/**
 * Implementation of TilePLayer Interface.
 *
 */
public class TilePlayerPairImpl implements TilePlayerPair {

    private Tile tile;
    private Player player;

    /**
     * @param tile The tile
     * @param player The Player on it
     */
    public TilePlayerPairImpl(final Tile tile, final Player player) {
        this.tile = tile;
        this.player = player;
    }

    /**
     * @param tile The tile
     */
    public TilePlayerPairImpl(final Tile tile) {
        this(tile, null);
    }

    @Override
    public final Optional<Player> getPlayer() throws NullPointerException {
        return Optional.ofNullable(this.player);
    }

    @Override
    public final Tile getTile() {
        return tile;
    }

    @Override
    public final void setTile(final Tile tTile) {
        this.tile = tTile;
    }

    @Override
    public final void setPlayer(final Player player) {
        this.player = player;
    }

}
