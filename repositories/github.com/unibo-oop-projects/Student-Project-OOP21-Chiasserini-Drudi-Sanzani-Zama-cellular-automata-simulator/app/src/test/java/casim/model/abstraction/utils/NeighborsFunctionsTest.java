package casim.model.abstraction.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.builder.utils.CoDiCellSupplier;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.Coordinates3D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid;
import casim.utils.grid.Grid2DImpl;
import casim.utils.grid.Grid3DImpl;


/**
 * Test class for {@link NeighborsFunctions}.
 */
class NeighborsFunctionsTest {

    private static final int ROWS = 5;
    private static final int COLUMNS = 5;
    private static final int DEPTH = 5;
    private static final int X = 2;
    private static final int OUT_X = 5;
    private static final int Y = 3;
    private static final int Z = 3;

    private final CoDiCellSupplier supplier = new CoDiCellSupplier();
    private final Grid<Coordinates2D<Integer>, CoDiCell> grid2D = 
            new Grid2DImpl<>(ROWS, COLUMNS, () -> supplier.get());
    private final Grid<Coordinates3D<Integer>, CoDiCell> grid3D = 
            new Grid3DImpl<>(ROWS, COLUMNS, DEPTH, () -> supplier.get());

    /**
     * Test for {@link NeighborsFunctions#neighbors2DFunction(Grid)}.
     */
    @Test
    void testNeighbors2DFunction() {
        Coordinates2D<Integer> coord = CoordinatesUtil.of(X, Y);
        Pair<Coordinates2D<Integer>, CoDiCell> pair = Pair.of(coord, supplier.get());
        final var neighborsPair = NeighborsFunctions.neighbors2DFunction(pair, grid2D);
        final var neighborsCoord = neighborsPair.stream().map(e -> e.getKey()).collect(Collectors.toList());
        final var list = CoordinatesUtil.get2DNeighbors(coord);
        Assert.assertEquals(neighborsCoord, list);
        coord = CoordinatesUtil.of(OUT_X, Y);
        pair = Pair.of(coord, supplier.get());
        Assert.assertTrue(NeighborsFunctions.neighbors2DFunction(pair, grid2D)
                .stream()
                .map(p -> p.getKey())
                .noneMatch(c -> c.equals(CoordinatesUtil.of(OUT_X + 1, Y))));
    }

    /**
     * Test for {@link NeighborsFunctions#mooreNeighborsFunction(Pair, Grid)}.
     */
    @Test
    void testMooreNeighborsFunction() {
        final Coordinates2D<Integer> coord = CoordinatesUtil.of(X, Y);
        Pair<Coordinates2D<Integer>, CoDiCell> pair = Pair.of(coord, supplier.get());
        final var neighborsPair = NeighborsFunctions.mooreNeighborsFunction(pair, grid2D);
        final var neighborsCoord = neighborsPair.stream().map(e -> e.getKey()).collect(Collectors.toList());
        final List<Coordinates2D<Integer>> list = Stream.of(CoordinatesUtil.of(1, 0), CoordinatesUtil.of(0, 1), CoordinatesUtil.of(0, -1), CoordinatesUtil.of(-1, 0),
                CoordinatesUtil.of(1, 1), CoordinatesUtil.of(-1, 1), CoordinatesUtil.of(1, -1), CoordinatesUtil.of(-1, -1))
                .map(c -> CoordinatesUtil.sumInt(coord, c))
                .filter(grid2D::isCoordValid)
                .collect(Collectors.toList());
        Assert.assertEquals(neighborsCoord, list);
        final Coordinates2D<Integer> outCoord = CoordinatesUtil.of(OUT_X, Y);
        pair = Pair.of(outCoord, supplier.get());
        Assert.assertTrue(NeighborsFunctions.mooreNeighborsFunction(pair, grid2D)
                .stream()
                .map(p -> p.getKey())
                .noneMatch(c -> c.equals(CoordinatesUtil.of(OUT_X + 1, Y))));
    }

    /**
     * Test for {@link NeighborsFunctions#neighbors3DFunction(Grid)}.
     */
    @Test
    void testNeighbors3DFunction() {
        Coordinates3D<Integer> coord = CoordinatesUtil.of(X, Y, Z);
        Pair<Coordinates3D<Integer>, CoDiCell> pair = Pair.of(coord, supplier.get());
        final var neighborsPair = NeighborsFunctions.neighbors3DFunction(pair, grid3D);
        final var neighborsCoord = neighborsPair.stream().map(e -> e.getKey()).collect(Collectors.toList());
        final var list = CoordinatesUtil.get3DNeighbors(coord);
        Assert.assertEquals(neighborsCoord, list);
        coord = CoordinatesUtil.of(OUT_X, Y, Z);
        pair = Pair.of(coord, supplier.get());
        Assert.assertTrue(NeighborsFunctions.neighbors3DFunction(pair, grid3D)
                .stream()
                .map(p -> p.getKey())
                .noneMatch(c -> c.equals(CoordinatesUtil.of(OUT_X + 1, Y, Z))));
    }
}
