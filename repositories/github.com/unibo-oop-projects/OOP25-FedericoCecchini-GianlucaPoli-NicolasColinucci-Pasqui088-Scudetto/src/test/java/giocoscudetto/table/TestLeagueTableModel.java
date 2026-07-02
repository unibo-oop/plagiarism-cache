package giocoscudetto.table;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import giocoscudetto.controller.api.CreateUpdateController;
import giocoscudetto.controller.impl.CreateUpdateControllerImpl;
import giocoscudetto.view.impl.result.LeagueTableModel;

/*
 * CHECKSTYLE: MagicNumber OFF
 * The above comment shuts down checkstyle: in a test suite, magic numbers may be tolerated.
 */
/**
 * Simple test for {@link LeagueTableModel}.
 */
class TestLeagueTableModel {

    private static final String ROMA = "roma";
    private static final String INTER = "inter";
    private static final String NAPOLI = "napoli";
    private static final String JUVENTUS = "juventus";

    private LeagueTableModel leagueTableModel;
    private CreateUpdateController updateController;

    @BeforeEach
    void setUp() {

        final List<String> listOfClubs = new ArrayList<>();
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
        updateController.getClubs().getLast().incrementPoints(5);
        leagueTableModel = new LeagueTableModel(updateController);
    }

    /**
     * Tests the getCount method.
     */
    @Test
    void testGetCount() {
        assertEquals(4, leagueTableModel.getRowCount());
        assertEquals(3, leagueTableModel.getColumnCount());

    }

    /**
     * Tests the getColumnName method.
     */
    @Test
    void testGetColumnName() {
        assertEquals("Club", leagueTableModel.getColumnName(0));
        assertEquals("Points", leagueTableModel.getColumnName(1));
        assertEquals("Net Difference", leagueTableModel.getColumnName(2));
    }

    /**
     * 
     */
    @Test
    void testGetValueAt() {
        for (int i = 0; i < 4; i++) {
            assertEquals(updateController.getTable().showPosition().get(i).getName(), leagueTableModel.getValueAt(i, 0));
            assertEquals(updateController.getTable().showPosition().get(i).getPoints(), leagueTableModel.getValueAt(i, 1));
            assertEquals(updateController.getTable().showPosition().get(i).getNetDiff(), leagueTableModel.getValueAt(i, 2));
        }
    }
}
