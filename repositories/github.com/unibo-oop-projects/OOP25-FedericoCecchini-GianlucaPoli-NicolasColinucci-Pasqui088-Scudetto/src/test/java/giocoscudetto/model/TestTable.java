package giocoscudetto.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import giocoscudetto.model.api.Table;
import giocoscudetto.model.api.match.Club;
import giocoscudetto.model.impl.TableImpl;
import giocoscudetto.model.impl.match.ClubImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.model.impl.TableImpl}.
 */
class TestTable {

    private final Club inter = new ClubImpl("Inter", 1);
    private final Club juve = new ClubImpl("Juve", 2);
    private final Club bologna = new ClubImpl("Bologna", 3);
    private final Table rank = new TableImpl();

    private void setUp(final int juveP, final int interP, final int bolognaP,
                       final int juveS, final int interS, final int bolognaS,
                       final int juveC, final int interC, final int bolognaC) {

        rank.addAllClubs(List.of(bologna, inter, juve));

        //Setting inter's point and net diff
        inter.incrementPoints(interP);
        inter.changeNetDiffs(interS, interC);

        //Setting juve's point and net diff
        juve.incrementPoints(juveP);
        juve.changeNetDiffs(juveS, juveC);

        //Setting bologna's point and net diff
        bologna.incrementPoints(bolognaP);
        bologna.changeNetDiffs(bolognaS, bolognaC);
    }

    /**
     * Testing initial clubs position, after the first table configuaration.
     */
    @Test
    void testInitialClubsPosition() {

        setUp(5, 6, 3,
              2, 3, 2,
              1, 2, 0);

        //After getting the initial configuration, we update Clubs rank position
        rank.updateClubRank();

        assertEquals(List.of(inter, juve, bologna), rank.showPosition());

        //If points are the same net diff matter
        bologna.incrementPoints(3);
        rank.updateClubRank();

        assertEquals(List.of(bologna, inter, juve), rank.showPosition());
    }

    /**
     * Testing that negative net diff work correctky.
     */
    @Test
    void testNegativeNetDiff() {

        setUp(3, 3, 1,
              0, 1, 2,
              1, 3, 0);

        rank.updateClubRank();

        assertEquals(List.of(juve, inter, bologna), rank.showPosition());

    }

    /**
     * Testing that if points and net diff are the same, clubs name matter.
     */
    @Test
    void testEqualPointsAndNetDiff() {

        setUp(2, 2, 3,
              1, 1, 2,
              0, 0, 0);

        //If point and net diff are the same we consider the name of the club
        rank.updateClubRank();

        assertEquals(List.of(bologna, juve, inter), rank.showPosition());

    }
}
