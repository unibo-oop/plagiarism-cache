package giocoscudetto.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.impl.match.TurnImpl;
import giocoscudetto.model.impl.match.ClubImpl;

/**
 * Test for TestTurn.
 */
class TestTurn {

    private static final String ROMA = "Roma";
    private static final String INTER = "Inter";

    private static final int PAWN_ROMA = 1;
    private static final int PAWN_INTER = 2;

    @Test
    void testChooseStartingPlayer() {

        final Club club1 = new ClubImpl(ROMA, PAWN_ROMA);
        final Club club2 = new ClubImpl(INTER, PAWN_INTER);

        final TurnImpl turn = new TurnImpl(club1, club2);

        assertTrue(
            club1.equals(turn.getCurrentPlayer())
                || club2.equals(turn.getCurrentPlayer())
        );
    }

    @Test
    void testSwitchTurn() {

        final Club club1 = new ClubImpl(ROMA, PAWN_ROMA);
        final Club club2 = new ClubImpl(INTER, PAWN_INTER);

        final TurnImpl turn = new TurnImpl(club1, club2);

        final Club first = turn.getCurrentPlayer();

        turn.switchTurn();

        final Club second = turn.getCurrentPlayer();

        assertNotEquals(first, second);
    }

    @Test
    void testSkipTurn() {

        final Club club1 = new ClubImpl(ROMA, PAWN_ROMA);
        final Club club2 = new ClubImpl(INTER, PAWN_INTER);

        final TurnImpl turn = new TurnImpl(club1, club2);

        final Club current = turn.getCurrentPlayer();

        turn.setSkipTurn(current);

        turn.switchTurn();
        final Club other = turn.getCurrentPlayer();

        turn.switchTurn();

        assertEquals(other, turn.getCurrentPlayer());
    }
}
