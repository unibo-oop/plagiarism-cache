package it.unibo.game.app.api;

import it.unibo.game.Pair;

/**
 * Interface that manages the direction of MovingObject.
 */
public interface Direction {
  /**
   * Set the direction of the MovingObject Down.
   */
  void setDirectionDown();

  /**
   * Set the direction of the MovingObject Up.
   */
  void setDirectionUp();

  /**
   * Set the direction of the MovingObject Left.
   */
  void setDirectionLeft();

  /**
   * Set the direction of the MovingObject Right.
   */
  void setDirectionRight();

  /**
   * sets the direction of the MovingObject vertically without horizontal
   * component.
   */
  void setCentre();

  /**
   * 
   * @return true if the direction of the Object is Up.
   */
  boolean isDirectionUp();

  /**
   * 
   * @return true if the direction of the Object is Left.
   */
  boolean isDirectionLeft();

  /**
   * 
   * @return true if the obj go to the right.
   */
  boolean isDirectionRight();

  /**
   * Set the direction of the ball to the initial one.
   */
  void resetDirection();

  /**
   * 
   * @return the direction.
   */
  Pair<Integer, Integer> getDirection();

  /**
   * 
   * @param newD set the direction.
   */
  void setDirection(Pair<Integer, Integer> newD);

}
