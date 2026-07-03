package model.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import model.logic.GameLogic;
import utilities.Pair;
import utilities.Players;

/**
 * 
 * @author : Bacchilega Giulio
 *
 */
public class TestGameLogic {

    /**
     * Test GameLogic class.
     */
    @Test
    public void test() {
        assertTrue(GameLogic.getLog().getActualPlayer().equals(Players.playerWhite));
        GameLogic.getLog().changePlayer();
        assertFalse(GameLogic.getLog().getActualPlayer().equals(Players.playerWhite));
        assertTrue(GameLogic.getLog().getActualPlayer().equals(Players.playerBlack));
        GameLogic.getLog().changePlayer();
        assertFalse(GameLogic.getLog().getActualPlayer().equals(Players.playerBlack));
        assertTrue(GameLogic.getLog().getActualPlayer().equals(Players.playerWhite));
        Set<Pair<Integer, Integer>> set = new HashSet<>(Arrays.asList(new Pair<>(5, 5), new Pair<>(4, 4)));
        assertTrue(GameLogic.getLog().checkFreePath(set));
        set = new HashSet<>(Arrays.asList(new Pair<>(1, 0), new Pair<>(0, 0)));
        assertFalse(GameLogic.getLog().checkFreePath(set));
    }

}
