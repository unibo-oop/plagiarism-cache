package it.unibo.game.app.model.round;

import it.unibo.game.Pair;
import it.unibo.game.app.api.BrickType;
import it.unibo.game.app.model.DimensionImpl;
import it.unibo.game.app.model.SizeCalculation;
import it.unibo.game.app.model.brick.NormalBrick;

/**
 * Defines how a round easy is designed.
 */
public class RoundEasy extends AbstractRound {

  private Double startX;
  private Double startY;
  private Double endX;
  private Double endY;
  private int numSurpriseBrick = 0;

  /**
   * Constructor of this class.
   * 
   * @param numB num of normal bricks
   * @param numS num of surprise bricks
   * @param size information of where to collocate bricks
   */
  public RoundEasy(final int numB, final int numS, final SizeCalculation size) {
    super(numB, numS, size);
    this.startY = size.getStart().getY();
    this.startX = size.getStart().getX();
    this.endY = size.getStop().getY();
    this.endX = size.getStop().getX();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPosBrick() {
    for (Double i = startX; i < endX; i = i + this.getSizeCalc().getBrickDim().getX()) {
      for (Double j = startY; j <= endY; j = j
          + this.getSizeCalc().getBrickDim().getY()) {
        NormalBrick brick = new NormalBrick(BrickType.NORMAL,
            new DimensionImpl(getSizeCalc().getBrickDim().getX(),
                getSizeCalc().getBrickDim().getY()),
            new Pair<>(j, i), 1);
        brick.setPos(new Pair<>(j, i));
        super.addBrick(brick);
      }
    }
    while (this.numSurpriseBrick < this.getNumSur()) {
      if (this.setBrickSurprise()) {
        this.numSurpriseBrick++;
      }
    }
  }

}
