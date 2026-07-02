package it.unibo.game.app.api;

/**
 * interface that contains useful methods for managing the speed of objects.
 */
public interface Speed {

  /**
   * method to get coordinate x of speed.
   * 
   * @return coordinate x
   */
  double getX();

  /**
   * method to get coordinate y of speed.
   * 
   * @return coordinate y
   */
  double getY();

  /**
   * method to increase or decrease speed.
   * 
   * @param vel speed by which we want to increase or decrease
   */
  void sum(Speed vel);

}
