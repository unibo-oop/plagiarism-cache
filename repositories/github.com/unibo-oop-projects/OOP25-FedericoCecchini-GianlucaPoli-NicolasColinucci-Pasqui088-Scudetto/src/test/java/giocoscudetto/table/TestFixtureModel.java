package giocoscudetto.table;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;
import giocoscudetto.model.api.match.Match;
import giocoscudetto.view.impl.result.FixtureModel;

/**
 * Simple test for {@link FixtureModel}.
 */
class TestFixtureModel {

    private static final String ROMA = "roma";
    private static final String INTER = "inter";
    private static final String NAPOLI = "napoli";
    private static final String JUVENTUS = "juventus";

    private CreateUpdateController updateController;
    private List<String> listOfClubs;
    private FixtureModel fixtureModel;

    @BeforeEach
    void setUp() {
        listOfClubs = new ArrayList<>();
        final List<Integer> listOfPawns = new ArrayList<>();
        listOfClubs.add(ROMA);
        listOfClubs.add(INTER);
        listOfClubs.add(NAPOLI);
        listOfClubs.add(JUVENTUS);

        listOfPawns.add(1);
        listOfPawns.add(1);
        listOfPawns.add(1);
        listOfPawns.add(1);

        updateController = new CreateUpdateControllerImpl();
        updateController.createClubs(listOfClubs, listOfPawns);
        fixtureModel = new FixtureModel(updateController);
    }

    /**
     * Tests the getCount method.
     */
    @Test
    void testGetCount() {
        assertEquals(2, fixtureModel.getColumnCount());
        assertEquals(listOfClubs.size() * (listOfClubs.size() - 1), fixtureModel.getRowCount());
    }

    /**
     * Tests the getColumnName method.
     */
    @Test
    void testGetColumnName() {
        assertEquals("Match", fixtureModel.getColumnName(0));
        assertEquals("Score", fixtureModel.getColumnName(1));
    }

    /**
     * Tests the getValueAt method.
     */
    @Test
    void testGetValueAt() {
        int count = 0;
        updateController.getFixture().nextMatch();
        Match match = updateController.getFixture().getCurrentMatch();
        while (updateController.getFixture().seeNextMatch(match) != null) {
            assertEquals(match.toString(), fixtureModel.getValueAt(count, 0));
            updateController.getFixture().nextMatch();
            match = updateController.getFixture().getCurrentMatch();
            count++;
        }
    }

}
