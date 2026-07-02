package it.unibo.game.app.model;

import it.unibo.game.app.api.Score;
/**
 * Class that manages the score.
 */
public class ScoreImpl implements Score {
  private static final int ONE_COLLISION_POINTS = 1;
  private int points;
  private int score;
  private int bonus = 1;
/**
   * constructor  of this class.
   */
  public ScoreImpl() {
    this.points = ONE_COLLISION_POINTS;
    this.score = 0;
  }
/**
   * {@inheritDoc}
   */
  public Integer getScore() {
    return this.score;
  }
/**
   * {@inheritDoc}
   */
  public void increaseScore() {
    this.score = this.score + (this.points * this.bonus);
    this.points = 2;
  }
/**
   * {@inheritDoc}
   */
  public void resetPoints() {
    this.points = ONE_COLLISION_POINTS;
  }
/**
   * {@inheritDoc}
   */
  public void enableBonus(final Boolean bool) {
    this.bonus = bool  ? 2 : 1;
  }
}
