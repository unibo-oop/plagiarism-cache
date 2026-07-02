package giocoscudetto.model.match;

import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.impl.match.ClubImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.match.ClubImpl}.
 */
class TestClub {

    private final Club club1 = new ClubImpl("inter", 1);

    /**
     * Testing the initial values of the new club's object.
     */
    @Test
    void testInitialValues() {
        assertEquals(club1.getName(), "inter");
        assertNotNull(club1.getPawn());
        assertEquals(club1.getPoints(), 0);
        assertEquals(club1.getNetDiff(), 0);
    }

    /**
     * Testing adding points throught fictitious matches.
     */
    @Test
    void testAddPoints() {
        club1.incrementPoints(2);
        assertEquals(2, club1.getPoints());
        club1.incrementPoints(0);
        assertEquals(2, club1.getPoints());

        //Testing an incoerent case where nothing should be done
        final int actualPoints = club1.getPoints();
        club1.incrementPoints(-5);
        assertEquals(actualPoints, club1.getPoints());
    }

    /**
     * Testing the net difference throught fictitious matches.
     */
    @Test
    void testNetDiff() {
        club1.changeNetDiffs(5, 4);
        assertEquals(1, club1.getNetDiff());

        club1.changeNetDiffs(3, 3);
        assertEquals(1, club1.getNetDiff());

        club1.changeNetDiffs(0, 2);
        assertEquals(-1, club1.getNetDiff());

        //Testing an incoerent case where nothing should be done
        final int actualNetDiff = club1.getNetDiff();
        club1.changeNetDiffs(-4, 6);
        assertEquals(actualNetDiff, club1.getNetDiff());

        club1.changeNetDiffs(0, -1);
        assertEquals(actualNetDiff, club1.getNetDiff());
    }
}
