package it.dpg.gridTest;

import it.dpg.maingame.model.grid.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class gridInitializerTest {

    @Test
    public void createGrid(){
        GridInitializer gridFact = new GridInitializerImpl();

        Assertions.assertThrows( IllegalStateException.class, gridFact::getGrid);

        Grid grid = gridFact.makeGrid(GridType.GRID_ONE);

        Set<Cell> cell = grid.getCellByCoordinates(1,0).getNext();

        Assertions.assertEquals(CellType.START, grid.getCellByCoordinates(1, 0).getType());
        Assertions.assertTrue(cell.contains(grid.getCellByCoordinates(1, 1)));
        Assertions.assertThrows(IllegalStateException.class, () -> grid.getCellByCoordinates(0,0));
        Assertions.assertNotEquals(null, grid.getFirst());

        Cell tempCell = grid.getCellByCoordinates(1,1);
        Cell tempCell2 = grid.getCellByCoordinates(0,4);

        Assertions.assertEquals(tempCell, tempCell2.getPrevious());
    }


}
