package model.environment.cell;

import model.environment.Point;
import constraints.CellConstraints;

/**
 *
 */
public interface Cell {

    /**
     * 
     * @return the position of the cell
     */
    Point getPosition();

    /**
     * 
     * @param point position of the cell
     */
    void setPosition(Point point);


  /**
    * 
    * @return the type of the cell
    */
  CellConstraints getType();

  /**
    * 
    * @param type set the type of the cell
    */
  void setType(CellConstraints type);


}
