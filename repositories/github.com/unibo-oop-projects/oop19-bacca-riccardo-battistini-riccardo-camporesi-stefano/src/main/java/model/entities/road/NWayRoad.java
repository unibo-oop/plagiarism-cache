package model.entities.road;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import constraints.DirOfMovement;
import controller.data.RoadData;
import model.entities.lane.Lane;
import model.entities.lane.LaneImpl;
import model.environment.Point;
import model.environment.cell.Cell;
import model.environment.cell.CellImpl;
import constraints.CellConstraints;
import model.environment.grid.Grid;

/**
 *
 */
public abstract class NWayRoad implements Road {
    private final Grid<Cell> grid;
    private final RoadData info;
    private List<Point> startPoint;
    private List<Point> endPoint;
    private final Map<String, Set<Lane>> road;

    /**
     *
     * @param grid of the scenario
     * @param info of the road
     */
    public NWayRoad(final Grid<Cell> grid, final RoadData info) {
        this.grid = grid;
        this.info = info;
        this.startPoint = new LinkedList<>();
        this.endPoint = new LinkedList<>();
        this.road = new HashMap<>();
    }

    /**
     *
     * @return the central row of the grid
     */
    public int distanceRow() {
        int dist;
        dist = (int) Math.ceil((double) this.grid.getNumberRows() / (2));
        return dist;
    }

    /**
     *
     * @return the central column of the grid
     */
    public int distanceCol() {
        int dist;
        dist = (int) Math.ceil((double) this.grid.getNumberColumns() / (2));
        return dist;
    }

    /**
     * set all cells that need to create the lane.
     *
     * @param startPoints list of the start points of the road
     * @param info info of the road
     */
    public void setCell(final List<Point> startPoints, final RoadData info) {
        for (final Point i : startPoints) {
            if (info.getSense().equals(DirOfMovement.EAST_WEST) || info.getSense().equals(DirOfMovement.WEST_EAST)) {
                for (int k = 0; k < this.grid.getNumberColumns(); k++) {
                    this.grid.setElement(Point.of(k, i.getY()), new CellImpl(CellConstraints.SIMPLE, Point.of(k, i.getY())));
                }
            } else {
                for (int k = 0; k < this.grid.getNumberColumns(); k++) {
                    this.grid.setElement(Point.of(i.getX(), k), new CellImpl(CellConstraints.SIMPLE, Point.of(i.getX(), k)));
                }
            }
        }
    }

    /**
     * Method to create a road with 1 lane.
     */
    @Override
    public void createRoad() {
        final Set<Lane> lanes = new HashSet<>();
        this.startPoint = this.findStart(this.info.getSense());
        this.endPoint = this.findEnd(this.info.getSense());
        this.setCell(this.startPoint, this.info);
        for (int l = 0; l < this.info.getLanesNumber(); l++) {

            lanes.add(new LaneImpl(this.startPoint.get(l), this.endPoint.get(l), this.grid, this.info.getLanesNumber()));
        }
        this.road.put(this.info.getName(), lanes);


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Set<Lane>> getRoads() {
        return this.road;
    }

    /**
     * 
     * @param sense of the road
     * @return list of the start points of the road
     */
    abstract List<Point> findStart(DirOfMovement sense);

    /**
     * 
     * @param sense of the road
     * @return list of the end points of the road
     */
    abstract List<Point> findEnd(DirOfMovement sense);
}
