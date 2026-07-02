package it.unibo.game.app.model.levels;

import it.unibo.game.app.model.SizeCalculation;
import it.unibo.game.app.model.round.RoundMedium;

/**
 * class that represents the second level of the game which has the
 * characteristic of having also hardBrick as bricks.
 */
public class SecondLevel extends AbstractLevel {
  private static final int BRICKROW = 15;
  private static final int JUMP = 4;

  private static final int GRAY1 = 8;
  private static final int NORMAL1 = 37;
  private static final int SURPRISE1 = 3;
  private static final int BRICKCOL1 = 4;

  private static final int GRAY2 = 12;
  private static final int NORMAL2 = 56;
  private static final int SURPRISE2 = 4;
  private static final int BRICKCOL2 = 6;

  private static final int GRAY3 = 14;
  private static final int NORMAL3 = 65;
  private static final int SURPRISE3 = 5;
  private static final int BRICKCOL3 = 7;

  private static final int ID = 2;
  private SizeCalculation sizeC;

  /**
   * constructor of this class.
   */
  public SecondLevel() {
    super(ID);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setFirstRound() {
    this.set(NORMAL1, SURPRISE1, GRAY1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setSecondRound() {
    this.set(NORMAL2, SURPRISE2, GRAY2);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setThirdRound() {
    this.set(NORMAL3, SURPRISE3, GRAY3);
  }

  /**
   * method that return the number of column for the specific round.
   *
   * @return rows
   */
  private int getCol() {
    if (this.getNumRoundPassed() == 0) {
      return BRICKCOL1;
    } else if (this.getNumRoundPassed() == 1) {
      return BRICKCOL2;
    } else {
      return BRICKCOL3;
    }
  }

  /**
   * method that sets a new round.
   * 
   * @param normal    number of normal bricks in the round
   * @param surprise  number of surprise brick in the round
   * @param hardBrick number of hard brick in the round
   */
  private void set(final int normal, final int surprise, final int hardBrick) {
    int col = this.getCol();
    this.sizeC = new SizeCalculation(col, BRICKROW);
    super.setRound(new RoundMedium(JUMP, normal, surprise, hardBrick, sizeC));
    super.getRound().setPosBrick();
  }
}
