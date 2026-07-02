package model.entities.lane;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import constraints.DirOfMovement;
import model.environment.Point;
import model.environment.cell.Cell;

public interface Lane {

    /**
     * 
     * @return sense of the lane
     */
    DirOfMovement getSense();

    /**
     * 
     * @return Immutable graph relative a lane
     */
    SimpleDirectedWeightedGraph<Cell, DefaultWeightedEdge> getLane();

    /**
     * @return the start point of the lane
     */
    Point getStart();

    /**
     * @return the end point of the lane
     */
    Point getEnd();
}
