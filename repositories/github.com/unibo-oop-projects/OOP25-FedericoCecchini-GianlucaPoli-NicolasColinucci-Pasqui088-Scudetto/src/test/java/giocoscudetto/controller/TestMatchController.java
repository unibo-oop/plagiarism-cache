package giocoscudetto.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.api.MatchController;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;
import giocoscudetto.controller.impl.MatchControllerImpl;
import giocoscudetto.model.api.match.Match;

/**
 * Test for {@link giocoscudetto.controller.impl.MatchControllerImpl}.
 */
class TestMatchController {

    private static final String ROMA = "roma";
    private static final String INTER = "inter";
    private static final String NAPOLI = "napoli";
    private static final String JUVENTUS = "juventus";

    private final CreateUpdateController updateController = new CreateUpdateControllerImpl();
    private MatchController matchController;

    @BeforeEach
    void setUp() {
        final List<String> listOfClubs = new ArrayList<>();
        listOfClubs.add(ROMA);
        listOfClubs.add(INTER);
        listOfClubs.add(NAPOLI);
        listOfClubs.add(JUVENTUS);

        final List<Integer> listOfColors = new ArrayList<>();
        listOfColors.add(1);
        listOfColors.add(2);
        listOfColors.add(3);
        listOfColors.add(4);

        this.updateController.createClubs(listOfClubs, listOfColors);
        this.matchController = new MatchControllerImpl(updateController.getFixture());
        matchController.setMatch();
    }

    @Test
    void testInfo() {
        assertEquals(updateController.getFixture().getCurrentMatch().getClubHome().getName(),
                    this.matchController.getHomeName());
        assertEquals(updateController.getFixture().getCurrentMatch().getClubAway().getName(), 
                    this.matchController.getGuestName());
    }

    @Test
    void testGameModeFinished() {
        matchController.gameModeFinished();
        assertEquals(Match.GameMode.NONE.name(), matchController.getGameMode());
    }

    @Test
    void testPawnColors() {
        assertTrue(this.matchController.getHomePawnRGB() >= 0);
        assertTrue(this.matchController.getGuestPawnRGB() >= 0);
    }

    @Test
    void testGetScore() {
        final String score = this.matchController.getScore();
        assertNotNull(score);
    }

    @Test
    void testHelpFlag() {
        assertFalse(this.matchController.isHelpFlag());
        this.matchController.setHelpFlag(true);
        assertTrue(this.matchController.isHelpFlag());
        this.matchController.setHelpFlag(false);
        assertFalse(this.matchController.isHelpFlag());
    }

    @Test
    void testMove() {
        final int dice = this.matchController.move();
        assertEquals(this.updateController.getFixture().getCurrentMatch().getCurrentPlayer().getPawn().getPosition(), dice);
    }
}
