package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.google.common.base.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class SudokuLogicImplTest {

    private static final int SUDOKU_SIZE = 9;
    private static final int SUDOKU_SQUARE_SIZE = 3;
    private final int[] initialGrid =  {0, 0, 7, 4, 0, 0, 0, 0, 0, 
                                        0, 0, 0, 0, 0, 0, 0, 7, 0,
                                        0, 0, 0, 0, 1, 0, 0, 0, 0,
                                        8, 3, 0, 0, 0, 0, 0, 0, 0,
                                        0, 1, 4, 0, 0, 0, 0, 2, 8,
                                        0, 0, 0, 2, 0, 0, 3, 6, 0, 
                                        5, 0, 0, 0, 0, 3, 7, 0, 0,
                                        3, 0, 0, 0, 0, 4, 5, 1, 0,
                                        4, 8, 0, 0, 5, 6, 0, 3, 0};

    private final int[] solution = {1, 5, 7, 4, 8, 2, 6, 9, 3,
                                    2, 4, 8, 6, 3, 9, 1, 7, 5, 
                                    9, 6, 3, 5, 1, 7, 8, 4, 2, 
                                    8, 3, 2, 9, 6, 1, 4, 5, 7, 
                                    6, 1, 4, 3, 7, 5, 9, 2, 8, 
                                    7, 9, 5, 2, 4, 8, 3, 6, 1, 
                                    5, 2, 6, 1, 9, 3, 7, 8, 4, 
                                    3, 7, 9, 8, 2, 4, 5, 1, 6, 
                                    4, 8, 1, 7, 5, 6, 2, 3, 9};
 
    //initialize new board
    private final SudokuLogic model = new SudokuLogicImpl(initialGrid, solution, SUDOKU_SIZE, SUDOKU_SQUARE_SIZE);

    @Test
    public void testGetInitial() {
        // test initial board 
        final String[] initial = model.getInitialGrid();
        for (int i = 0; i < initialGrid.length; i++) {
            assertEquals(initial[i], String.valueOf(initialGrid[i]));
        }
    }

    @Test
    public void testHit() {
        model.hit(0, 0, 1);
        model.hit(0, 1, 3);
        model.hit(3, 1, 5);
        assertEquals(model.getValues().get(0).getKey().get(), 1);
        assertEquals(model.getValues().get(1).getKey().get(), 3);
        //hitting an initial value does not change board
        assertEquals(model.getValues().get(28).getKey().get(), 3);
    }

    @Test
    public void testRemove() {
        model.remove(0, 0);
        model.remove(0, 1);
        model.remove(3, 1);
        assertEquals(model.getValues().get(0).getKey(), Optional.absent());
        assertEquals(model.getValues().get(1).getKey(), Optional.absent());
        //removing an initial value does not change board
        assertEquals(model.getValues().get(28).getKey(), Optional.of(3));
    }

    @Test
    public void testIsDone() {
        assertEquals(model.isDone(), false);
        int c = 0;
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            for (int j = 0; j < SUDOKU_SIZE; j++) {
                model.hit(i, j, solution[c]);
                c++;
            }
        }
        assertEquals(model.isDone(), true);
    }
}
