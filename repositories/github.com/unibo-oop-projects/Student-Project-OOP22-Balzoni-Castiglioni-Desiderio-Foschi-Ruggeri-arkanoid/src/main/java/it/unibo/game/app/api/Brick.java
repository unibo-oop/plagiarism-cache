package it.unibo.game.app.api;

import java.util.Optional;

/**
 * Brick entity interface.
 */
public interface Brick extends GameObject {
  /**
   * 
   * @return return the type of brick.
   */
  BrickType getType();

  /**
   * method for changing the type to the brick.
   * 
   * @param type
   */
  void changeType(BrickType type);

  /**
   * 
   * @return the height of brick.
   */
  Double getBrickH();

  /**
   * 
   * @return the width of brick.
   */
  Double getBrickW();

  /**
   * 
   * @return the resistence of the brick.
   */
  Optional<Integer> getRes();

  /**
   * 
   * @return if a brick is destructible.
   */
  boolean isDestroyed();

  /**
   * method that decrease the resistence if is present of brick when hit.
   */
  void hit();

  /**
   * method to increase resistence of brick.
   * 
   * @param res resistence of brick
   */
  void increaseRes(int res);

  /**
   * method to decrease the resistence of brick.
   * 
   * @param res resistence of brick
   */
  void decreaseRes(int res);
}
