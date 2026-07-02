package player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import login.Custom;
import login.CustomImpl;
import login.Player;
import login.PlayerImpl;
import pair.Pair;
import utility.UtilsPlayer;

/**
 * Test class of a player.
 */
public class PlayerTest {

    private static final int NUMBER1_PAIR = 4;
    private static final int NUMBER2_PAIR = 5;
    private final Player testP = new PlayerImpl("Pippo", "pluto", "paperino", 32);

    /**
     * Test about the management of the player's customPiece field.
     */
    @Test
    public void testCustomPieces() {
        final List<Custom> tempList = new ArrayList<>();
        final Pair<Integer, Integer> center = new Pair<>(NUMBER1_PAIR, NUMBER2_PAIR);
        final List<Pair<Integer, Integer>> tempCoords = new ArrayList<>();
        tempCoords.add(new Pair<>(4, 4));
        tempCoords.add(center);

        assertEquals(Optional.empty(), this.testP.getCustomPiece());

        this.testP.setCustomPiece(Optional.ofNullable(tempList));

        assertEquals(0, this.testP.getCustomPiece().get().size());

        tempList.add(new CustomImpl(Color.RED, tempCoords, center));

        this.testP.setCustomPiece(Optional.of(tempList));

        assertEquals(1, this.testP.getCustomPiece().get().size());

    }

    /**
     * Test of the player's writeOnFile method.
     */
    @Test
    public void testWriteOnFile() {

        // If "..." is present in the database of the application the follow assertFalse
        // will not run properly.
        assertFalse(UtilsPlayer.isPlayerInDatabes("..."));

        this.testP.writeOnFile();

        assertTrue(UtilsPlayer.isPlayerInDatabes(this.testP.getName()));

    }

    /**
     * Test readPlayerFromJSON method of UtilsPlayer class.
     */
    @Test
    public void testReadPlayerFromJSON() {

        final Player testPEqual = UtilsPlayer.readPlayerFromJSON(this.testP.getName());

        assertEquals(this.testP.toString(), testPEqual.toString());
    }

}
