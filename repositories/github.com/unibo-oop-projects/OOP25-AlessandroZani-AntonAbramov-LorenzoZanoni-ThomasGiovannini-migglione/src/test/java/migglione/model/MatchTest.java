package migglione.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import migglione.model.api.CardDraw;
import migglione.model.api.Player;
import migglione.model.impl.DeckImpl;
import migglione.model.impl.Match;
import migglione.model.impl.StandardCardDrawImpl;
import migglione.model.impl.User;

/**
 * Test class to ascertain the Match class's integrity.
 * 
 * <p>
 * In particular, it tests the class's ability to correctly compare the players' chosen values
 * and have them draw, along with handling ties, correctly identifying if the match has ended 
 * and declaring a winner.
 * </p>
 * 
 */
class MatchTest {

    private static final String BASE_ATTR = "Attk";
    private static final int TIE_VAL = 5;
    private static final int TRIPLE_WIN_SCORE = 6;
    private static final int INIT_DECK_SIZE = 24;
    private final User u = new User(new LinkedList<>(), "P1");
    private final User v = new User(new LinkedList<>(), "P2");
    private final CardDraw d = new StandardCardDrawImpl(new DeckImpl());
    private final Match match = new Match(u, v, d);

    @Test
    void testCreate() {
        assertEquals(List.of(u, v), match.getPlayers());
        for (final Player x : Set.of(u, v)) {
            assertEquals(x.getHand().size(), Match.HAND_SIZE);
            assertEquals(match.getScore(x), 0);
        }
        assertFalse(match.matchEnded());
        assertEquals(match.getWinner(), Optional.empty());
        assertEquals(match.getTurnLeader(), u);
        assertEquals(INIT_DECK_SIZE, d.getSizeDeck() + u.getHand().size() + v.getHand().size());
    }

    @Test
    void testTurn() {
        u.playCard(BASE_ATTR, u.getHand().getFirst());
        v.playCard(BASE_ATTR, u.getHand().getFirst());
        assertFalse(match.playTurn(10, 0));
        assertEquals(match.getScore(u), 2);
        assertEquals(match.getScore(v), 0);
        assertEquals(match.getTurnLeader(), u);
        for (final Player p : Set.of(u, v)) {
            assertEquals(p.getPile(new LinkedList<>()).size(), match.getScore(p));
            assertEquals(p.getHand().size(), Match.HAND_SIZE);
        }
        final boolean end = d.isDeckEmpty() && u.getHand().isEmpty() && v.getHand().isEmpty();
        assertEquals(match.matchEnded(), end);
        assertEquals(
            INIT_DECK_SIZE - d.getSizeDeck() - (match.getScore(u) + match.getScore(v)), 
            u.getHand().size() + v.getHand().size()
        );
        assertEquals(u.getHand().size(), v.getHand().size());
    }

    @Test
    void testChangeTurn() {
        u.playCard(BASE_ATTR, u.getHand().getFirst());
        v.playCard(BASE_ATTR, u.getHand().getFirst());
        for (int i = 1; i < 3; i++) {
            match.playTurn(0, 10);
            assertEquals(match.getTurnLeader(), v);
        }
        match.playTurn(0, 10);
        assertEquals(match.getScore(v), TRIPLE_WIN_SCORE);
        assertEquals(match.getTurnLeader(), u);
        match.playTurn(0, 10);
        assertEquals(match.getTurnLeader(), v);
    }

    @Test
    void testTie() {
        final int uScore = match.getScore(u);
        final int vScore = match.getScore(v);
        u.playCard(BASE_ATTR, u.getHand().getFirst());
        v.playCard(BASE_ATTR, u.getHand().getFirst());
        match.playTurn(TIE_VAL, TIE_VAL);
        for (final Player p : Set.of(u, v)) {
            assertEquals(match.getScore(p), p.equals(u) ? uScore : vScore);
            assertEquals(match.getScore(p), p.getPile(new LinkedList<>()).size());
        }
        match.playTurn(10, 0);
        assertEquals(match.getScore(u), uScore + 4);
    }

    @Test
    void testEnd() {
        int prevU = match.getScore(u);
        u.playCard(BASE_ATTR, u.getHand().getFirst());
        v.playCard(BASE_ATTR, u.getHand().getFirst());
        while (!d.isDeckEmpty()) {
            match.playTurn(10, 0);
            assertEquals(match.getScore(u), prevU + 2);
            prevU += 2;
        }
        while (!u.getHand().isEmpty()) {
            u.playCard(BASE_ATTR, u.getHand().getFirst());
            v.playCard(BASE_ATTR, v.getHand().getFirst());
            match.playTurn(10, 0);
        }
        assertTrue(u.getHand().isEmpty());
        assertTrue(v.getHand().isEmpty());
        assertTrue(match.matchEnded());
        assertNotNull(match.getWinner().get());
        assertEquals(match.getWinner().get(), u.getName());
        assertEquals(match.getScore(u) + match.getScore(v), INIT_DECK_SIZE);
    }

}
