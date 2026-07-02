package it.unibo.game.app.api;

/**
 * interface that contains contains methods to modify and get objects'
 * dimension.
 */
public interface Dimension {

  /**
   * method that returns the height of an object.
   * 
   * @return a double representing its height
   */
  double getHeight();

  /**
   * method that returns the width of an object.
   * 
   * @return a double representing its width.
   */
  double getWidth();

  /**
   * method to modify an object height.
   * 
   * @param height value that we want to assign to the height of the object
   */
  void setHeight(double height);

  /**
   * method to modify an object width.
   * 
   * @param width value that we want to assign to the width of the object
   */
  void setWidth(double width);

  /**
   * method to increase and object width.
   * 
   * @param width value that we want to add to the width
   */
  void increaseWidth(double width);

}
