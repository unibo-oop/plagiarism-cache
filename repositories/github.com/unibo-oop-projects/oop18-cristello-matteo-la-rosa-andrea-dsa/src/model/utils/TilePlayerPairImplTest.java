package model.utils;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Test;

import controller.PlayerColor;
import model.board.EmptyTile;
import model.board.Tile;
import model.board.TreasureTile;
import model.players.EmptyPlayerSingleton;
import model.players.Player;
import model.players.PlayerImpl;

/**
 * Test Class for TilePlayer Pair.
 */
public class TilePlayerPairImplTest {

    private final Tile treasure = new TreasureTile(4);
    private final Player player = new PlayerImpl("Mario", PlayerColor.RED);
    private final EmptyTile empty = new EmptyTile();
    private TilePlayerPair tPP;

    /**
     * Test of simple operation on the tested class.
     */
    @Test(expected = NoSuchElementException.class)
    public void testPlayer() {
        this.tPP = new TilePlayerPairImpl(treasure, player);
        assertEquals("Something gone bad, player not as expected", tPP.getPlayer().get(), player);
        tPP.setPlayer(null);
        tPP.getPlayer().get();
    }

    /**
     * Test of emptyPlayer class in TilePlayerPairImpl.
     */
    @Test
    public void testEmptyPlayer() {
        this.tPP = new TilePlayerPairImpl(treasure, player);
        assertEquals("Something gone bad, player not as expected", tPP.getPlayer().get(), player);
        tPP.setPlayer(null);
        assertEquals("Something gone bad, player not as expected",
                tPP.getPlayer().orElse(EmptyPlayerSingleton.getInstance()), EmptyPlayerSingleton.getInstance());
    }

    /**
     * Test of emptyTile class in TilePlayerPairImpl.
     */
    @Test
    public void testEmptyTile() {
        this.tPP = new TilePlayerPairImpl(treasure, player);
        assertEquals("Something gone bad, tile not as expected", tPP.getTile(), treasure);
        tPP.setTile(empty);
        assertEquals("Something gone bad, tile not as expected",
                tPP.getTile(), empty);
    }
}
