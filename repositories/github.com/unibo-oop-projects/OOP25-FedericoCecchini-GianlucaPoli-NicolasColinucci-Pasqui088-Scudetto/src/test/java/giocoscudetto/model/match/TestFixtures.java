package giocoscudetto.model.match;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.api.match.Match;
import giocoscudetto.model.impl.FixturesImpl;
import giocoscudetto.model.impl.match.ClubImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Simple test for {@link FixturesImpl}.
 */
class TestFixtures {

    private static final String ROMA = "roma";
    private static final String INTER = "inter";
    private static final String NAPOLI = "napoli";
    private static final String JUVENTUS = "juventus";

    private Club roma;
    private Club inter;
    private Club napoli;
    private List<Club> listOfClubs;
    private FixturesImpl fixture;

    @BeforeEach
    void setUp() {
        roma = new ClubImpl(ROMA, 1);
        inter = new ClubImpl(INTER, 2);
        napoli = new ClubImpl(NAPOLI, 3);
        listOfClubs = new ArrayList<>();
        listOfClubs.add(roma);
        listOfClubs.add(inter);
        listOfClubs.add(napoli);
        listOfClubs.add(new ClubImpl(JUVENTUS, 4));
        fixture = new FixturesImpl();
    }

    /** 
     * Tests that the fixture has n*(n-1) matches with n clubs so that each pair of teams plays twice.
     */
    @Test
    void testFixtureGenerationMatchCount() {
        fixture.fixtureGeneration(listOfClubs);
        assertEquals(listOfClubs.size() * (listOfClubs.size() - 1), fixture.getListOfMatches().size());
        listOfClubs = new ArrayList<>();
        listOfClubs.add(roma);
        listOfClubs.add(inter);
        listOfClubs.add(napoli);
        fixture = new FixturesImpl();
        fixture.fixtureGeneration(listOfClubs);
        assertEquals(listOfClubs.size() * (listOfClubs.size() - 1), fixture.getListOfMatches().size());
        listOfClubs.clear();
        listOfClubs = List.of(roma, inter);
        fixture = new FixturesImpl();
        fixture.fixtureGeneration(listOfClubs);
        assertEquals(listOfClubs.size() * (listOfClubs.size() - 1), fixture.getListOfMatches().size());
    }

    /**
     * Tests that in the fixture each club appears the number of time needed to play two matches against each club.
     */
    @Test
    void testFixturesGenerationClubCount() {
        int intercount = 0;
        int romacount = 0;
        int napolicount = 0;
        int juventuscount = 0;
        fixture.fixtureGeneration(listOfClubs);
        assertNotNull(fixture);
        assertEquals(4, listOfClubs.size());
        while (fixture.nextMatch() != null) {
            if (INTER.equals(fixture.getCurrentMatch().getClubHome().getName()) 
                || INTER.equals(fixture.getCurrentMatch().getClubAway().getName())) {
                intercount++;
            }
            if (ROMA.equals(fixture.getCurrentMatch().getClubHome().getName()) 
                || ROMA.equals(fixture.getCurrentMatch().getClubAway().getName())) {
                romacount++;
            }
            if (NAPOLI.equals(fixture.getCurrentMatch().getClubHome().getName()) 
                || NAPOLI.equals(fixture.getCurrentMatch().getClubAway().getName())) {
                napolicount++;
            }
            if (JUVENTUS.equals(fixture.getCurrentMatch().getClubHome().getName()) 
                || JUVENTUS.equals(fixture.getCurrentMatch().getClubAway().getName())) {
                juventuscount++;
            }
        }
        assertEquals(6, intercount);
        assertEquals(6, napolicount);
        assertEquals(6, romacount);
        assertEquals(6, juventuscount);
    }

    /**
     * Tests the function IsEmpty to see if the fixture is empty.
     */
    @Test
    void testIsEmpty() {
        assertTrue(fixture.isEmpty());
        fixture.fixtureGeneration(listOfClubs);
        assertFalse(fixture.isEmpty());
        fixture.resetFixture();
        assertTrue(fixture.isEmpty());
    }

    /**
     * Tests that is possible to make the fixture empty.
     */
    @Test
    void testResetFixture() {
        fixture.fixtureGeneration(listOfClubs);
        fixture.resetFixture();
        assertTrue(fixture.isEmpty());
    }

    /**
     * Tests the possibility of look at the next match without going through the iterator.
     */
    @Test
    void testNextMatch() {
        fixture.fixtureGeneration(listOfClubs);
        final Match match = fixture.getCurrentMatch();
        assertEquals(fixture.seeNextMatch(match), fixture.nextMatch());
        fixture.resetFixture();
        fixture.fixtureGeneration(listOfClubs);
        int count = 0;
        while (fixture.nextMatch() != null) {
            count++;
        }
        assertEquals(12, count);
    }

}
