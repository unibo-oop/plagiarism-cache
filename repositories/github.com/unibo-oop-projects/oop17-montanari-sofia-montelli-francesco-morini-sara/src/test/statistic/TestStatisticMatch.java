
package test.statistic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import model.basic_component.Cell;
import model.basic_component.CellImpl;
import model.statistic.StatisticMatch;
import model.statistic.StatisticMatchImpl;

/**
 * 
 * test for the {@link StatisticMatchImpl}.
 *
 *
 */
public class TestStatisticMatch {

/**
 * test for MatchSTatistic creation.
 */
     @Test
     public void testMatchStatistic() {
         StatisticMatch statistic1;
         StatisticMatch statistic2;
         final LocalDateTime time = LocalDateTime.now();
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
         statistic1 = new StatisticMatchImpl("Sofia", "Irene", time, Collections.unmodifiableList(listCell1), Collections.unmodifiableList(listCell2));
         statistic2 = new StatisticMatchImpl("Irene", "Sofia", time, Collections.unmodifiableList(listCell2), Collections.unmodifiableList(listCell1));

         assertFalse("at the beginning nobody is the winner", statistic1.isWinner());
         assertFalse("at the beginning nobody is the winner", statistic2.isWinner());

         listCell1.add(cell4);
 
         assertEquals("adding an element to the list of cells, for reference we must also update those of the statistic match",
             statistic1.getShootsPerformed(), Arrays.asList(cell1, cell2, cell3, cell4));
         assertEquals("adding an element to the list of cells, for reference we must also update those of the statistic match",
             statistic2.getShootsReceived(), Arrays.asList(cell1, cell2, cell3, cell4));

         statistic1.setWinner(true);
         assertTrue("Sofia is the winner", statistic1.isWinner());
     }

}
