package it.unibo.game.app.model.levels;

import it.unibo.game.app.model.SizeCalculation;
import it.unibo.game.app.model.round.RoundDifficult;

/**
 * class that represents the third level of the game which has the
 * characteristic of having also obstacles as bricks.
 */
public final class ThirdLevel extends AbstractLevel {

  private static final int NORMAL1 = 6;
  private static final int SURPRISE1 = 2;
  private static final int OBSTACLE1 = 2;

  private static final int NORMAL2 = 13;
  private static final int SURPRISE2 = 5;
  private static final int OBSTACLE2 = 3;

  private static final int NORMAL3 = 18;
  private static final int SURPRISE3 = 6;
  private static final int OBSTACLE3 = 4;

  private static final int ID = 3;
  private SizeCalculation sizeC;

  /**
   * constructor of this class.
   */
  public ThirdLevel() {
    super(ID);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setFirstRound() {
    this.set(NORMAL1, SURPRISE1, OBSTACLE1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setSecondRound() {
    this.set(NORMAL2, SURPRISE2, OBSTACLE2);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setThirdRound() {
    this.set(NORMAL3, SURPRISE3, OBSTACLE3);
  }

  /**
   * method that calculates the height of the pyramid of bricks.
   * 
   * @param normal
   * @param surprise
   * @param obstacles
   * @return height of pyramid
   */
  private int getCol(final int normal, final int surprise, final int obstacles) {
    return (int) Math.sqrt((double) (2 * (normal + surprise + obstacles)));
  }

  /**
   * method that sets a new round.
   * 
   * @param normal    number of normal bricks in the round
   * @param surprise  number of surprise brick in the round
   * @param obstacles number of obstacle in the round
   */
  private void set(final int normal, final int surprise, final int obstacles) {
    int h = getCol(normal, surprise, obstacles);
    this.sizeC = new SizeCalculation(h, h + 1);
    super.setRound(new RoundDifficult(normal, surprise, sizeC, obstacles));
    super.getRound().setPosBrick();
  }

}
