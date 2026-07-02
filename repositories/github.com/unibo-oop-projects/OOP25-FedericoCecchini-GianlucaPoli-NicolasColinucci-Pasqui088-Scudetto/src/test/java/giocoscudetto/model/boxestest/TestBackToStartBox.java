package giocoscudetto.model.boxestest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.impl.boxes.BackToStartBox;
import giocoscudetto.model.impl.match.ClubImpl;
import giocoscudetto.model.impl.match.MatchImpl;

/**
 * Tests for BackToStartBox.
 */
class TestBackToStartBox {

    private static final int START_POSITION = 0;
    private static final int PLAYER_POSITION = 12;
    private static final int BOX_POSITION = 15;

    @Test
    void testBackToStartBox() {

        final Club home = new ClubImpl("Roma", 1);
        final Club away = new ClubImpl("Inter", 2);

        final MatchImpl match = new MatchImpl(home, away);

        final Club current = match.getCurrentPlayer();
        current.getPawn().setPosition(PLAYER_POSITION);
        final BackToStartBox box = new BackToStartBox(BOX_POSITION);
        box.event(match);
        assertEquals(START_POSITION, current.getPawn().getPosition());
    }
}
