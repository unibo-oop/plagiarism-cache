package casim.utils.grid;

import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import casim.utils.range.Ranges;

/**
 * Test class for {@link RowGrid}.
 */
class RowGridTest {

    private static final int DEFAULT_VALUE = 1;
    private static final int FIRST_ROW_VALUE = 0;
    private static final int NEW_VALUE = 2;
    private static final int ROWS = 3;
    private static final int COLS = 2;

    private Grid2D<Integer> getGrid() {
        return new Grid2DImpl<>(ROWS, COLS, () -> DEFAULT_VALUE);
    }

    private RowGrid<Integer> getGridWithValues() {
        final var grid = new RowGrid<>(this.getGrid());
        for (final var x : Ranges.of(0, ROWS)) {
            final var val = x % 2;
            for (final var y : Ranges.of(0, COLS)) {
                grid.set(x, y, x + val);
            }
        }
        return grid;
    }

    /**
     * Test for {@link RowGrid#getRow(int)}.
     */
    @Test
    void testGetRow() {
        final var grid = this.getGridWithValues();
        Assert.assertEquals(Ranges.of(0, COLS).stream()
                .map(x -> FIRST_ROW_VALUE)
                .collect(Collectors.toList()),
                grid.getRow(0));
    }

    /**
     * Test for {@link RowGrid#setRow(int, java.util.List)}.
     */
    @Test
    void testSetRow() {
        final var newRow = Ranges.of(0, COLS).stream()
                .map(x -> NEW_VALUE)
                .collect(Collectors.toList());
        final var grid = this.getGridWithValues();
        grid.setRow(0, newRow);
        Assert.assertEquals(newRow, grid.getRow(0));
    }
}
