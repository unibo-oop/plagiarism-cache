package test.statistic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import model.basic_component.Cell;
import model.basic_component.CellImpl;
import model.data_access.FileDataAccessManager;
import model.data_access.FileDataAccessManagerImpl;
import model.statistic.StatisticMatch;
import model.statistic.StatisticMatchImpl;

/**
 * 
 * Test for saving {@link StatisticMatch} through {@link FileDataAccessManager}.
 *
 */
public class TestStatisticMatchDataBase {
    private StatisticMatch s1;
    private StatisticMatch s2;
    private StatisticMatch s3;
    private final LocalDateTime time = LocalDateTime.now();
    private final FileDataAccessManager<StatisticMatch> database = new FileDataAccessManagerImpl<StatisticMatch>("prova.bin");

     /**
     * initialize {@link MatchStatisticImpl}.
     */
    @Before
    public void init() {
          final Cell cell1 = new CellImpl(0, 0, Cell.Status.OCCUPIED_AND_TARGETED);
          final Cell cell2 = new CellImpl(2, 2, Cell.Status.TARGETED);
          final Cell cell3 = new CellImpl(3, 3, Cell.Status.OCCUPIED_AND_TARGETED);
          final Cell cell4 = new CellImpl(1, 2, Cell.Status.TARGETED);
          final Cell cell5 = new CellImpl(1, 3, Cell.Status.OCCUPIED_AND_TARGETED);
          final Cell cell6 = new CellImpl(1, 1, Cell.Status.TARGETED);
          final List<Cell> listCell1 = new ArrayList<>();
          listCell1.add(cell1);
          listCell1.add(cell2);
          listCell1.add(cell3);
          final List<Cell> listCell2 = new ArrayList<>();
          listCell2.add(cell4);
          listCell2.add(cell5);
          listCell2.add(cell6);
          s1 = new StatisticMatchImpl("Sofia", "Irene", time, listCell1, listCell2);
          s2 = new StatisticMatchImpl("Irene", "Anonymous", time, listCell2, listCell1);
          s3 = new StatisticMatchImpl("Anonymous", "Irene", time, listCell1, listCell2); 
    }

    /**
     * test the efficiency of writing, reading and removing from files.
     * @throws IOException exception
     */
    @Test
    public void testOne() throws IOException {
         database.removeElement(s1);
         Set<StatisticMatch> result = database.getSet();
         final int initialSize = database.getSet().size();
         database.saveNewElement(s1);
         result = database.getSet();

         assertEquals("verify the size of what the file contains", result.size(), initialSize + 1);


         assertTrue("verify is s1 is contained in the file", result.contains(s1));
    }

    /**
     * test the efficiency of writing, reading and removing from files.
     * @throws IOException exception
     */
    @Test
    public void testTwo() throws IOException {
        database.removeElement(s2);
        Set<StatisticMatch> result = database.getSet();
        final int initialSize = result.size();
        database.saveNewElement(s2);
        result = database.getSet();
        assertEquals("verify the size of what the file contains", result.size(), initialSize + 1);
        assertTrue("verify is s2 is contained in the file", result.contains(s2));

    }
    /**
     * test the efficiency of writing, reading and removing from files.
     * @throws IOException exception
     */
    @Test
    public void testThree() throws IOException {
         database.removeElement(s3);
         Set<StatisticMatch> result = database.getSet();
         final int initialSize = result.size();
         database.saveNewElement(s3);
         result = database.getSet();
         assertEquals("verify the size of what the file contains", result.size(), initialSize + 1);
         assertTrue("verify is s3 is contained in the file", result.contains(s3));

    }

}
