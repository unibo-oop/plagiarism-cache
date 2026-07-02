package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constraints.CellConstraints;
import model.entities.lane.Lane;
import model.entities.lane.LaneImpl;
import model.environment.Point;
import model.environment.cell.Cell;
import model.environment.cell.CellImpl;
import model.environment.grid.Grid;
import model.environment.grid.GridFactory;
import model.environment.grid.GridFactoryImpl;

import static constraints.GridConstraints.GRID_HEIGHT;
import static constraints.GridConstraints.GRID_WIDTH;
 /**
  *  class to test the correct function of the lanes.
  * 
  */
public class TestLane {
    private GridFactory gridFac;
    private Grid<Cell> grid;
    private Lane westEast;
    private Lane southNorth;
    private SimpleDirectedWeightedGraph<Cell, DefaultWeightedEdge> lanes;

   /**
    * parameters initialization.
    */
    @BeforeEach
    public final void initialize() {

        this.gridFac = new GridFactoryImpl();
        this.grid = gridFac.create(GRID_WIDTH, GRID_HEIGHT);
        for (int i = 0; i< 78; i++) {
           this.grid.setElement(Point.of(i, 39), new CellImpl(CellConstraints.SIMPLE, Point.of(i, 39)));
           this.grid.setElement(Point.of(39, i), new CellImpl(CellConstraints.SIMPLE, Point.of(39, i)));
        }
        this.westEast = new LaneImpl(Point.of(0, 39), Point.of(77, 39), grid, 1);
        this.southNorth = new LaneImpl(Point.of(39, 0), Point.of(39, 77), grid, 1);
        this.lanes = new SimpleDirectedWeightedGraph<Cell, DefaultWeightedEdge>(DefaultWeightedEdge.class);

        Graphs.addGraph(lanes, this.westEast.getLane());
        Graphs.addGraph(lanes, this.southNorth.getLane());
   }

    /**
     * check if the merge of the lane and if the new graph is oriented.
     */
    @Test()
    public void mergeLane() {

         assertEquals(true, lanes.containsEdge(grid.getElement(Point.of(39, 39)), grid.getElement(Point.of(39, 40))));
         assertEquals(false, lanes.containsEdge(grid.getElement(Point.of(39, 39)),grid.getElement(Point.of(39, 39))));
         assertEquals(false, lanes.containsEdge(grid.getElement(Point.of(39, 39)), grid.getElement(Point.of(38, 39))));

    }

    /**
     * check that the cells are set correctly.
     */
    public void setCell() {
        assertEquals(CellConstraints.START, grid.getElement(Point.of(0, 39)).getType());
        assertEquals(CellConstraints.START, grid.getElement(Point.of(39, 0)).getType());
        assertEquals(CellConstraints.END, grid.getElement(Point.of(77, 39)).getType());
        assertEquals(CellConstraints.END, grid.getElement(Point.of(39, 77)).getType());
        assertEquals(CellConstraints.CROSSROAD, grid.getElement(Point.of(39, 39)).getType());
    }
}
