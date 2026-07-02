package it.unibo.game.app.api;

/**
 * differt types of brick in the game.
 */
public enum BrickType {
  /**
   * obstacle is a brick that can't be destroyed.
   */
  OBSTACLE,
  /**
   * normal is a brick that has no specific features.
   */
  NORMAL,
  /**
   * surprise is a brick that let the player to get bonus or malus.
   */
  SURPRISE
}
