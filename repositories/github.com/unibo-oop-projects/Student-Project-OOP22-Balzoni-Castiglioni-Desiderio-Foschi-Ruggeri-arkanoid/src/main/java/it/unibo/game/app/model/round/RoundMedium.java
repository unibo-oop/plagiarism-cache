package it.unibo.game.app.model.round;

import java.util.Random;

import it.unibo.game.Pair;
import it.unibo.game.app.api.BrickType;
import it.unibo.game.app.model.DimensionImpl;
import it.unibo.game.app.model.SizeCalculation;
import it.unibo.game.app.model.brick.NormalBrick;

/**
 * class that contains informations about different rounds in second level.
 */
public class RoundMedium extends AbstractRound {

  private int countBrick = 0;
  private int numHard;
  private int numSur = 0;
  private int numH = 0;
  private Double startY;
  private Double startX;
  private Double stopY;
  private Double stopX;
  private int jump;
  private Random random = new Random();

  /**
   * Constructor of this class.
   * 
   * @param jump    number of bricks per column.
   * @param numB    num of normal bricks
   * @param numS    num of surprise bricks
   * @param numHard num of hard bricks
   * @param sizeC   information of where to collocate bricks
   */
  public RoundMedium(final int jump, final int numB, final int numS, final int numHard,
      final SizeCalculation sizeC) {
    super(numB, numS, sizeC);
    this.jump = jump;
    this.numHard = numHard;
    this.startY = sizeC.getStart().getY();
    this.startX = sizeC.getStart().getX();
    this.stopY = sizeC.getStop().getY();
    this.stopX = sizeC.getStop().getX();
  }

  /**
   * method that to randomly places hard bricks.
   * 
   * @return true if a brick to replace is found
   */
  private boolean setBrickHard() {

    int idx = this.random.nextInt(this.getBrick().size());

    if (this.getBrick().get(idx).getType() == BrickType.NORMAL
        && this.getBrick().get(idx).getRes().get() == 1) {
      this.getBrick().get(idx).increaseRes(this.getBrick().get(idx).getRes().get());
      return true;
    } else {
      return false;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPosBrick() {
    for (Double i = this.startX; i < this.stopX; i = i
        + this.getSizeCalc().getBrickDim().getX()) {
      countBrick = 0;
      for (Double j = this.startY; j <= this.stopY; j = j
          + this.getSizeCalc().getBrickDim().getY()) {
        if (countBrick != this.jump) {
          NormalBrick b = new NormalBrick(BrickType.NORMAL,
              new DimensionImpl(this.getSizeCalc().getBrickDim().getX(),
                  this.getSizeCalc().getBrickDim().getY()),
              new Pair<>(j, i), 1);
          b.setPos(new Pair<Double, Double>(j, i));
          super.addBrick(b);
          countBrick++;
        } else {
          countBrick = 0;
        }
      }
    }

    while (numSur < this.getNumSur()) {
      if (this.setBrickSurprise()) {
        numSur++;
      }
    }

    while (numH < this.numHard) {
      if (this.setBrickHard()) {
        numH++;
      }
    }
  }

}
