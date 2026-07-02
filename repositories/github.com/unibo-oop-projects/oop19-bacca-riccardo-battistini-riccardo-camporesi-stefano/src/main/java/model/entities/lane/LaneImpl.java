package model.entities.lane;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import constraints.DirOfMovement;
import constraints.GridConstraints;
import model.environment.Point;
import model.environment.cell.Cell;
import constraints.CellConstraints;
import model.environment.grid.Grid;

/**
 * this class employs a fluent interface, it means every method returns an
 * instance of the class itself that you are trying to modify.
 *
 */
public class LaneImpl implements Lane {

    private static final int WEIGHT = 1;
    private final Point start;
    private final Point end;
    private DirOfMovement sense;
    private final Grid<Cell> grid;
    private final SimpleDirectedWeightedGraph<Cell, DefaultWeightedEdge> lane;
    private final int lanesNumber;

    /**
     *
     * @param start       Point of the lane
     * @param end         Point of the lane
     * @param grid        of the scenario
     * @param lanesNumber number of the lane on the road
     *
     */
    public LaneImpl(final Point start, final Point end, final Grid<Cell> grid, final int lanesNumber) {
        this.start = start;
        this.end = end;
        this.lanesNumber = lanesNumber;
        this.grid = grid;
        lane = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        findSense();
        createLane();
    }

    /**
     *
     * @return the sense of the lane.
     */
    private void findSense() {

        if (start.getX() == end.getX()) {
            sense = start.getY() < end.getY() ? DirOfMovement.SOUTH_NORTH : DirOfMovement.NORTH_SOUTH;
        }
        if (start.getY() == end.getY()) {
            sense = start.getX() < end.getX() ? DirOfMovement.WEST_EAST : DirOfMovement.EAST_WEST;
        }

    }

    /**
     * @return a lane where cell start type and end type are set.
     */
    private Lane setStartEnd() {

        grid.getElement(start).setType(CellConstraints.START);
        grid.getElement(end).setType(CellConstraints.END);
        return this;
    }

    /**
     * @return the cell that need to be set.
     */
    private Lane setCrossroad() {

        if (sense.equals(DirOfMovement.EAST_WEST) || sense.equals(DirOfMovement.WEST_EAST)) {
            switch (lanesNumber) {

            case 1:
                grid.getElement(Point.of((int) Math.ceil((double) GridConstraints.GRID_HEIGHT / (2)), start.getY()))
                        .setType(CellConstraints.CROSSROAD);
                break;

            case 2:
                grid.getElement(Point.of((int) Math.ceil((double) GridConstraints.GRID_HEIGHT / (2)), start.getY()))
                        .setType(CellConstraints.CROSSROAD);
                grid.getElement(Point.of((int) Math.ceil((double) GridConstraints.GRID_HEIGHT / (2)) + 1, start.getY()))
                        .setType(CellConstraints.CROSSROAD);
                break;
            default:
                break;
            }
        } else {
            switch (lanesNumber) {
            case 1:
                grid.getElement(Point.of(start.getX(), (int) Math.ceil((double) GridConstraints.GRID_WIDTH / (2))))
                        .setType(CellConstraints.CROSSROAD);
                break;
            case 2:
                grid.getElement(Point.of(start.getX(), (int) Math.ceil((double) GridConstraints.GRID_WIDTH / (2))))
                        .setType(CellConstraints.CROSSROAD);
                grid.getElement(Point.of(start.getX(), (int) Math.ceil((double) GridConstraints.GRID_WIDTH / (2)) + 1))
                        .setType(CellConstraints.CROSSROAD);
                break;
            default:
                break;
            }
        }
        return this;
    }

    /**
     * @return the parameters needed to create the graph.
     */
    private Point setParameters() {

        int x;
        int y;

        if (sense.equals(DirOfMovement.EAST_WEST) || sense.equals(DirOfMovement.WEST_EAST)) {
            x = start.getX();
            y = end.getX();
        } else {
            x = start.getY();
            y = end.getY();
        }
        return Point.of(x, y);
    }

    /**
     * create vertices of the graph.
     */
    private void createVertices() {
        final Point p = setParameters();

        for (int i = p.getX(); i <= p.getY(); i++) {
            if (sense.equals(DirOfMovement.WEST_EAST)) {
                lane.addVertex(grid.getElement(Point.of(i, end.getY())));
            }
            if (sense.equals(DirOfMovement.SOUTH_NORTH)) {
                lane.addVertex(grid.getElement(Point.of(end.getX(), i)));
            }
        }

        for (int i = p.getX(); i >= p.getY(); i--) {
            if (sense.equals(DirOfMovement.EAST_WEST)) {
                lane.addVertex(grid.getElement(Point.of(i, end.getY())));
            }
            if (sense.equals(DirOfMovement.NORTH_SOUTH)) {
                lane.addVertex(grid.getElement(Point.of(end.getX(), i)));
            }
        }
    }

    /**
     * create edges of the graph.
     */
    private void createEdges() {
        final Point p = setParameters();

        for (int i = p.getX(); i < p.getY(); i++) {
            if (sense.equals(DirOfMovement.WEST_EAST)) {

                final DefaultWeightedEdge d = new DefaultWeightedEdge();
                lane.addEdge(grid.getElement(Point.of(i, end.getY())), grid.getElement(Point.of(i + 1, end.getY())), d);
                lane.setEdgeWeight(d, WEIGHT);
            }
            if (sense.equals(DirOfMovement.SOUTH_NORTH)) {

                final DefaultWeightedEdge d = new DefaultWeightedEdge();
                lane.addEdge(grid.getElement(Point.of(end.getX(), i)), grid.getElement(Point.of(end.getX(), i + 1)), d);
                lane.setEdgeWeight(d, WEIGHT);
            }
        }

        for (int i = p.getX(); i > p.getY(); i--) {
            if (sense.equals(DirOfMovement.EAST_WEST)) {
                final DefaultWeightedEdge d = new DefaultWeightedEdge();
                lane.addEdge(grid.getElement(Point.of(i, end.getY())), grid.getElement(Point.of(i - 1, end.getY())), d);
                lane.setEdgeWeight(d, WEIGHT);
            }
            if (sense.equals(DirOfMovement.NORTH_SOUTH)) {
                final DefaultWeightedEdge d = new DefaultWeightedEdge();
                lane.addEdge(grid.getElement(Point.of(end.getX(), i)), grid.getElement(Point.of(end.getX(), i - 1)), d);
                lane.setEdgeWeight(d, WEIGHT);
            }
        }
    }

    /**
     * create the lane.
     */
    private void createLane() {
        setCrossroad();
        setStartEnd();
        createVertices();
        createEdges();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DirOfMovement getSense() {
        return sense;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleDirectedWeightedGraph<Cell, DefaultWeightedEdge> getLane() {
        return lane;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getStart() {
        return start;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getEnd() {
        return end;
    }

}
