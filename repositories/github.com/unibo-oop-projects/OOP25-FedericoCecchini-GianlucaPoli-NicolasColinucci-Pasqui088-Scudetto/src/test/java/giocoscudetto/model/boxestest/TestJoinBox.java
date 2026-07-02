package giocoscudetto.model.boxestest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.impl.boxes.JoinBox;
import giocoscudetto.model.impl.match.ClubImpl;
import giocoscudetto.model.impl.match.MatchImpl;

/**
 * Tests for JoinBox.
 */
class TestJoinBox {

    private static final int HOME_POSITION = 5;
    private static final int AWAY_POSITION = 12;
    private static final int BOX_POSITION = 17;

    @Test
    void testJoinBox() {

        final Club home = new ClubImpl("Roma", 1);
        final Club away = new ClubImpl("Inter", 2);

        final MatchImpl match = new MatchImpl(home, away);
        home.getPawn().setPosition(HOME_POSITION);
        away.getPawn().setPosition(AWAY_POSITION);

        final JoinBox box = new JoinBox(BOX_POSITION);

        box.event(match);
        match.turn();
        assertEquals(away.getPawn().getPosition(), home.getPawn().getPosition());
    }
}
