package giocoscudetto.model.boxestest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.impl.boxes.SkipTurnBox;
import giocoscudetto.model.impl.match.ClubImpl;
import giocoscudetto.model.impl.match.MatchImpl;

/**
 * Test for SkipTurnBox.
 */
class TestSkipTurnBox {

    @Test
    void testSkipTurnBox() {

        final Club home = new ClubImpl("Roma", 1);
        final Club away = new ClubImpl("Inter", 2);

        final MatchImpl match = new MatchImpl(home, away);

        final Club current = match.getCurrentPlayer();
        final SkipTurnBox box = new SkipTurnBox(21);
        box.event(match);
        final Club opponent = match.getCurrentPlayer();
        match.turn();
        assertEquals(opponent, match.getCurrentPlayer());
        match.turn();
        assertEquals(current, match.getCurrentPlayer());

    }
}
