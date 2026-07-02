package it.unibo.game.app.model.levels;

import it.unibo.game.app.model.SizeCalculation;
import it.unibo.game.app.model.round.RoundEasy;

/**
 * Define the three round of FirstLevel.
 */
public class FirstLevel extends AbstractLevel {

  private static final int NORMAL_FIRST = 19;
  private static final int NORMAL_SECOND = 27;
  private static final int NORMAL_THIRD = 35;
  private static final int SURPRISE_FIRST = 2;
  private static final int SURPRISE_SECOND = 3;
  private static final int SURPRISE_THIRD = 4;
  private static final int BRICK_COLUMNS = 3;
  private static final int BRICK_ROWS_FIRST = 8;
  private static final int BRICK_ROWS_SECOND = 11;
  private static final int BRICK_ROWS_THIRD = 13;

  private static final int ID = 1;
  private SizeCalculation sizeCalc;

  /**
   * Constructor of the class.
   */
  public FirstLevel() {
    super(ID);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setFirstRound() {
    this.set(NORMAL_FIRST, SURPRISE_FIRST);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setSecondRound() {
    this.set(NORMAL_SECOND, SURPRISE_SECOND);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setThirdRound() {
    this.set(NORMAL_THIRD, SURPRISE_THIRD);
  }

  /**
   * method that return the number of columns for the specific round.
   *
   * @return columns
   */
  private int getCol() {
    if (this.getNumRoundPassed() == 0) {
      return BRICK_ROWS_FIRST;
    } else if (this.getNumRoundPassed() == 1) {
      return BRICK_ROWS_SECOND;
    } else {
      return BRICK_ROWS_THIRD;
    }
  }

  /**
   * method that sets a new round.
   * 
   * @param normal   number of normal bricks in the round
   * @param surprise number of surprise brick in the round
   */
  private void set(final int normal, final int surprise) {
    int rows = this.getCol();
    this.sizeCalc = new SizeCalculation(BRICK_COLUMNS, rows);
    super.setRound(new RoundEasy(normal, surprise, sizeCalc));
    super.getRound().setPosBrick();
  }

}
