package it.dpg.maingame.controller.grid;

import it.dpg.maingame.controller.gamecycle.GameCycle;
import it.dpg.maingame.model.grid.*;
import it.dpg.maingame.view.grid.GridView;
import it.dpg.maingame.view.grid.GridViewImpl;
import it.dpg.maingame.view.grid.GridViewPlat;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GridGenerator {

    public Map<Cell, Pair<Integer, Integer>> gridMap;
    public GridViewPlat view;
    private final GridType gridType;
    private final GameCycle gameCycle;

    public GridGenerator(GridType type, GameCycle gameCycle) {
        this.gridType = type;
        this.gameCycle = gameCycle;
    }

    public Pair<Grid, GridView> generate() {

        /* The grid is initialized */
        GridInitializer gridFact = new GridInitializerImpl();
        Grid grid = gridFact.makeGrid(gridType);

        /* I get the Cells List by Cell and Coordinates to create a List inside View */
        this.gridMap = grid.getCellList();

        /* The View is initialized and passed to View Platform*/
        GridViewImpl viewImpl = new GridViewImpl(gameCycle);
        this.view = new GridViewPlat(viewImpl);

        gridMap.forEach((key, value) -> {
            /* I save the coordinates of the next cells in a new set */
            Set<Pair<Integer, Integer>> nextCell;
            nextCell = key.getNext().stream()
                    .map(Cell::getCoordinates)
                    .collect(Collectors.toSet());

            view.makeCellList(value, key.getType().toString(), nextCell);
        });

        view.startGeneration();
        return new ImmutablePair<>(grid, view);
    }
}
