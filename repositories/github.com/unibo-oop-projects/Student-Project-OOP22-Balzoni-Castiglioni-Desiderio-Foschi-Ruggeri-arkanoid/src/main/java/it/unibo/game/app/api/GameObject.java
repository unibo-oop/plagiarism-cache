package it.unibo.game.app.api;

import it.unibo.game.Pair;

/**
 * game object must implement all methods of the game objects considering that.
 * they are static.
 */
public interface GameObject {

  /**
   * set pos of game obj.
   * 
   * @param pos
   */
  void setPos(Pair<Double, Double> pos);

  /**
   * 
   * @return pos of game obj
   */
  Pair<Double, Double> getPos();

  /**
   * @return bounding box area of game obj
   */
  BoundingBox getBoundingBox();

  /**
   * set bounding box of game obj.
   * 
   * @param box
   */
  void setBoundingBox(BoundingBox box);

  /**
   * @return dimension of obj
   */
  Dimension getDimension();

  /**
   * set dimension of obj.
   * 
   * @param d
   */
  void setDimension(Dimension d);
}
