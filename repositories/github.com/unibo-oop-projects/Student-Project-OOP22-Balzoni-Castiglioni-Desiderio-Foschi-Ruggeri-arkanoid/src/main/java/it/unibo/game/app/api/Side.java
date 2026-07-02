package it.unibo.game.app.api;

/**
 * Side where the box collide.
 */
public enum Side {
  /**
   * Side top and bottom.
   */
  UP_DOWN,
  /**
   * Side left and right.
   */
  LEFT_RIGHT,
  /**
   * Collision occurs in the corner.
   */
  CORNER,
  /**
   * Collision occurs in the centre of the pad.
   */
  PAD_CENTRE

}
