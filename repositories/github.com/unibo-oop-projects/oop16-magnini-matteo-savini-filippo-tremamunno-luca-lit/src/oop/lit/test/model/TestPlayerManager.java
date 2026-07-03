package oop.lit.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

import oop.lit.model.PlayerModel;
import oop.lit.model.game.Player;
import oop.lit.model.game.PlayerManager;
import oop.lit.model.game.PlayerManagerImpl;
import oop.lit.test.model.TestElements.GE;
import oop.lit.test.model.TestGroups.SG;

//CHECKSTYLE:OFF
/**
 * A class for testing a PlayerManager.
 */
public class TestPlayerManager {
    @Test
    public void testPlayerManager() {
        final PlayerManager<PlayerModel, SG<GE>> pm = new PlayerManagerImpl<>();
        final PlayerModel p1 = new Player("p1");
        final PlayerModel p2 = new Player("p2");
        final SG<GE> group = new SG<>();
        final NotifyCounter counter = new NotifyCounter();
        pm.attach(counter);

        assertTrue(pm.getPlayers().isEmpty());
        assertTrue(pm.addPlayer(p1));
        assertFalse(pm.addPlayer(p1));
        assertTrue(pm.addPlayer(p2));
        assertEquals(2, counter.getCount());

        assertEquals(Arrays.asList(p1, p2), pm.getPlayers());
        assertTrue(pm.getActivePlayers().isEmpty());
        pm.setActivePlayer(p2);
        assertEquals(Arrays.asList(p2), pm.getActivePlayers());
        pm.setActivePlayer(p2, p1);
        assertEquals(Arrays.asList(p2, p1), pm.getActivePlayers());
        assertTrue(pm.isPlayerTurn(p1));
        pm.setActivePlayer(p1);
        assertEquals(Arrays.asList(p1), pm.getActivePlayers());
        assertFalse(pm.isPlayerTurn(p2));
        try {
            pm.setActivePlayer(new Player("p"));
            fail("Can't use a player that was not added");
        } catch (Exception e) {
            if (!e.getClass().equals(IllegalArgumentException.class)) {
                fail("Wrong exception thrown");
            }
        }
        assertEquals(Arrays.asList(p1), pm.getActivePlayers());
        assertEquals(5, counter.getCount());

        assertFalse(pm.getPlayingPlayer().isPresent());
        assertTrue(pm.setPlayingPlayer(p2));
        assertFalse(pm.setPlayingPlayer(p2));
        assertEquals(p2, pm.getPlayingPlayer().get());
        assertTrue(pm.setPlayingPlayer(p1));
        try {
            pm.setPlayingPlayer(new Player("p"));
            fail("Can't use a player that was not added");
        } catch (Exception e) {
            if (!e.getClass().equals(IllegalArgumentException.class)) {
                fail("Wrong exception thrown");
            }
        }
        assertEquals(p1, pm.getPlayingPlayer().get());
        assertEquals(7, counter.getCount());

        assertFalse(pm.getPlayerHand(p1).isPresent());
        pm.addPlayerHand(p2, group);
        assertSame(group, pm.getPlayerHand(p2).get());
        pm.addPlayerHand(p1, group);
        assertSame(group, pm.getPlayerHand(p1).get());
        assertSame(group, pm.getPlayerHand(p2).get());
        try {
            pm.addPlayerHand(new Player("p"), group);
            fail("Can't use a player that was not added");
        } catch (Exception e) {
            if (!e.getClass().equals(IllegalArgumentException.class)) {
                fail("Wrong exception thrown");
            }
        }
        assertEquals(9, counter.getCount());

        pm.setActivePlayer(p1, p2);
        pm.removePlayer(p1);
        assertEquals(Arrays.asList(p2), pm.getActivePlayers());
        assertFalse(pm.getPlayingPlayer().isPresent());
        assertFalse(pm.getPlayerHand(p1).isPresent());
        assertSame(group, pm.getPlayerHand(p2).get());
        assertEquals(11, counter.getCount());
    }
}
