package model.entities.road;

import model.environment.Point;
import model.environment.cell.Cell;
import model.environment.grid.Grid;

import java.util.LinkedList;
import java.util.List;

import constraints.DirOfMovement;
import controller.data.RoadData;

/**
 * 
 */
public class TwoWayRoad extends NWayRoad {

        private List<Point> startPoints;
        private List<Point> endPoints;
        private Grid<Cell> grid;


    /**
     * 
     * @param info of the road
     * @param grid grid of the scenario
     */
    public TwoWayRoad(final RoadData info, final Grid<Cell> grid) {
        super(grid, info);
        this.grid = grid;
        this.startPoints = new LinkedList<>();
        this.endPoints = new LinkedList<>();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    List<Point> findStart(final DirOfMovement sense) {

        if (sense.equals(DirOfMovement.WEST_EAST) || sense.equals(DirOfMovement.EAST_WEST)) {
            this.startPoints.add(Point.of(0, this.distanceRow()));
            this.startPoints.add(Point.of(grid.getNumberColumns() - 1, this.distanceRow() + 1));
        }
        else {
            this.startPoints.add(Point.of(this.distanceCol(), grid.getNumberRows() - 1));
            this.startPoints.add(Point.of(this.distanceCol() + 1, 0));

        }
        return this.startPoints;
    }

    /**
     * {@inheritDoc}
     */
    @Override
     List<Point> findEnd(final DirOfMovement sense) {

        if (sense.equals(DirOfMovement.EAST_WEST) || sense.equals(DirOfMovement.WEST_EAST)) {
            this.endPoints.add(Point.of(grid.getNumberColumns() - 1, this.distanceRow()));
            this.endPoints.add(Point.of(0, this.distanceRow() + 1));
        }
        else {
            this.endPoints.add(Point.of(this.distanceCol(), 0));
            this.endPoints.add(Point.of(this.distanceCol() + 1, grid.getNumberRows() - 1));
        }
        return this.endPoints;
    }
}
