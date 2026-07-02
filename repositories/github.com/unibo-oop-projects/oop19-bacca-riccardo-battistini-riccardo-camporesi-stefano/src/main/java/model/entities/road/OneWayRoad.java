package model.entities.road;

import java.util.LinkedList;
import java.util.List;

import constraints.DirOfMovement;
import controller.data.RoadData;
import model.environment.Point;
import model.environment.cell.Cell;
import model.environment.grid.Grid;

/**
 * 
 */
public class OneWayRoad extends NWayRoad  {

    private List<Point> startPoint;
    private List<Point> endPoint;
    private Grid<Cell> grid;

    /**
     * 
     * @param info of the road
     * @param grid of the scenario
     */
    public OneWayRoad(final RoadData info, final Grid<Cell> grid) {
        super(grid, info);
        this.grid = grid;
        this.startPoint = new LinkedList<>();
        this.endPoint = new LinkedList<>();
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    List<Point> findStart(final DirOfMovement sense) {
        switch (sense) {

        case WEST_EAST : this.startPoint.add(Point.of(0, this.distanceRow()));
        break;

        case EAST_WEST : this.startPoint.add(Point.of(grid.getNumberColumns() - 1, this.distanceRow())); 
        break;

        case NORTH_SOUTH : this.startPoint.add(Point.of(this.distanceCol(), grid.getNumberRows() - 1));
        break;

        case SOUTH_NORTH : this.startPoint.add(Point.of(this.distanceCol(), 0));
        break;
	
        default:
        break;
        }
        return this.startPoint;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    List<Point> findEnd(final DirOfMovement sense) {

        switch (sense) {

            case WEST_EAST : this.endPoint.add(Point.of(grid.getNumberColumns() - 1, this.distanceRow()));
            break;

            case EAST_WEST : this.endPoint.add(Point.of(0, this.distanceRow())); 
            break;

            case NORTH_SOUTH : this.endPoint.add(Point.of(this.distanceCol(), 0));
            break;

            case SOUTH_NORTH : this.endPoint.add(Point.of(this.distanceCol(), grid.getNumberRows() - 1));
	    break;

            default:
            break;
        }
        return this.endPoint;
    }
}
