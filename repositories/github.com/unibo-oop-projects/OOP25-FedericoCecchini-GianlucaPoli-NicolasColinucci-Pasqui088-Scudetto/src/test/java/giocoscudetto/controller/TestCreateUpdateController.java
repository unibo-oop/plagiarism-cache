package giocoscudetto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Test for {@link giocoscudetto.controller.impl.CreateUpdateControllerImpl}.
 */
class TestCreateUpdateController {

    private static final String INTER = "Inter";
    private static final String BOLOGNA = "Bologna";
    private static final String IMOLESE = "Imolese";
    private static final String ROMA = "Roma";
    private static final String JUVENTUS = "Juventus";

    private final CreateUpdateController controller = new CreateUpdateControllerImpl();

    /**
     * Testing some basic condition that sould be avoid before allowing users to create clubs.
     */
    @Test
    void testClubsCorrectness() {

        //Spaces are not allowed in the name, so "Bologna" and " Bolo gna " are the same name
        assertFalse(controller.isClubInfoComplete(List.of(BOLOGNA, " Bolo gna "), List.of(true, true)));

        //Each Club's name can not be empty
        assertFalse(controller.isClubInfoComplete(List.of(BOLOGNA, ""), List.of(true, true)));

        //Each Club's pawn color has to be selected
        assertFalse(controller.isClubInfoComplete(List.of(BOLOGNA, INTER), List.of(false, false)));
        assertFalse(controller.isClubInfoComplete(List.of(BOLOGNA, INTER), List.of(true, false)));

        //If all the info have been correctly choosen, you can proceed creating the clubs
        assertTrue(controller.isClubInfoComplete(List.of(BOLOGNA, INTER), List.of(true, true)));
    } 

    /**
     * Testing club creation goes well, not letting anything null.
     */
    @Test
    void testClubCreation() {
        controller.createClubs(List.of(INTER, BOLOGNA, IMOLESE),
                               List.of(32, 48, 53));

        //Testing that clubs have been correctly added to the controller list
        assertEquals(3, this.controller.getClubs().size());
        assertTrue(this.controller.getClubs().stream().anyMatch(p -> INTER.equals(p.getName())));
        assertTrue(this.controller.getClubs().stream().anyMatch(p -> BOLOGNA.equals(p.getName())));
        assertTrue(this.controller.getClubs().stream().anyMatch(p -> IMOLESE.equals(p.getName())));

        //Testing that clubs have been correctly added to the controller table
        assertEquals(3, this.controller.getTable().showPosition().size());
        assertTrue(this.controller.getClubActualRank().stream().anyMatch(p -> INTER.equals(p.getName())));
        assertTrue(this.controller.getClubActualRank().stream().anyMatch(p -> BOLOGNA.equals(p.getName())));
        assertTrue(this.controller.getClubActualRank().stream().anyMatch(p -> IMOLESE.equals(p.getName()))); 

        //Testing that clubs have been correctly added to the controller fixture
        assertEquals(6, this.controller.getFixture().getListOfMatches().size());
        assertTrue(this.controller.getFixture().getListOfMatches().stream()
            .anyMatch(m -> INTER.equals(m.getClubHome().getName())));
        assertTrue(this.controller.getFixture().getListOfMatches().stream()
            .anyMatch(m -> BOLOGNA.equals(m.getClubHome().getName())));
        assertTrue(this.controller.getFixture().getListOfMatches().stream()
            .anyMatch(m -> IMOLESE.equals(m.getClubHome().getName())));
        assertTrue(this.controller.getFixture().getListOfMatches().stream()
            .anyMatch(m -> INTER.equals(m.getClubAway().getName())));
        assertTrue(this.controller.getFixture().getListOfMatches().stream()
            .anyMatch(m -> BOLOGNA.equals(m.getClubAway().getName())));
        assertTrue(this.controller.getFixture().getListOfMatches().stream()
            .anyMatch(m -> IMOLESE.equals(m.getClubAway().getName()))); 
    }

    /**
     * Tests that it restart the league correctly with the same teams.
     */
    @Test
    void testRestartLeague() {
        controller.createClubs(List.of(INTER, ROMA, JUVENTUS),
                               List.of(25, 3, 14));
        controller.getClubs().get(0).getPawn().setPosition(20);
        controller.getClubs().get(1).getPawn().setPosition(18);
        controller.getClubs().get(2).getPawn().setPosition(36);
        controller.getClubs().get(0).changeNetDiffs(5, 0);
        controller.getClubs().get(1).changeNetDiffs(2, 4);
        controller.getClubs().get(2).changeNetDiffs(1, 4);
        controller.updateClubActualRank();
        final String firstFixture = controller.getFixture().getListOfMatches().toString();
        final String firstTable = controller.getTable().showPosition().toString();
        controller.restartLeague();
        assertNotEquals(firstFixture, controller.getFixture().getListOfMatches().toString());
        assertNotEquals(firstTable, controller.getTable().showPosition().toString());
        assertTrue(controller.getFixtureMatchesString().contains("Inter - Juventus"));
        assertTrue(controller.getFixtureMatchesString().contains("Inter - Roma"));
        assertTrue(controller.getFixtureMatchesString().contains("Juventus - Roma"));
        assertTrue(controller.getFixtureMatchesString().contains("Juventus - Inter"));
        assertTrue(controller.getFixtureMatchesString().contains("Roma - Inter"));
        assertTrue(controller.getFixtureMatchesString().contains("Roma - Juventus"));
        final List<String> clubsName = new ArrayList<>();
        controller.getTable().showPosition().forEach(club -> clubsName.add(club.getName()));
        assertTrue(clubsName.contains(INTER));
        assertTrue(clubsName.contains(ROMA));
        assertTrue(clubsName.contains(JUVENTUS));
    }
}
