package it.unibo.game.app.api;

/**
 * Interface that manages the score.
 */
public interface Score {
  /**
   * @return the current score.
   */
  Integer getScore();

  /**
   * method that increase the score.
   */
  void increaseScore();

  /**
   * method that resets the score for each brick to ONE_COLLISION_POINTS.
   */
  void resetPoints();

  /**
   * 
   * @param bool
   */
  void enableBonus(Boolean bool);
}
