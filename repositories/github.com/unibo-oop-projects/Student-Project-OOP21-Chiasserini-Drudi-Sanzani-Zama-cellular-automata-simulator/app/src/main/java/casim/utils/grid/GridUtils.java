package casim.utils.grid;

import java.util.stream.Stream;

import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.Coordinates3D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.range.Ranges;

/**
 * Grid utily class.
 */
public final class GridUtils {
    /**
     * Returns a stream with every coordinate that indexes a rows x columns grid.
     * 
     * @param rows number of rows of the grid.
     * @param columns number of columns of the grid.
     * @return the stream of {@link Coordinates2D}.
     */
    public static Stream<Coordinates2D<Integer>> get2dCoordStream(final int rows, final int columns) {
        final var rangeRows = Ranges.of(0, rows);
        final var rangeCols = Ranges.of(0, columns);
        return rangeRows.stream()
            .flatMap(r -> rangeCols.stream().map(c -> CoordinatesUtil.of(r, c)));
    }

    /**
     * Returns a stream with every coordinate that indexes a rows x columns x depth grid.
     * 
     * @param rows number of rows of the grid.
     * @param columns number of columns of the grid.
     * @param depth depth of the grid.
     * @return the stream of {@link Coordinates3D}.
     */
    public static Stream<Coordinates3D<Integer>> get3dCoordStream(final int rows, final int columns, final int depth) {
        final var rangeRows = Ranges.of(0, rows);
        final var rangeCols = Ranges.of(0, columns);
        final var rangeDepth = Ranges.of(0, depth);
        return rangeRows.stream()
            .flatMap(x -> rangeCols.stream()
                .flatMap(y -> rangeDepth.stream().map(z -> CoordinatesUtil.of(x, y, z))));
    }

    private GridUtils() {

    }
}
