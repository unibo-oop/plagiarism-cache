package it.unibo.game.app.model.round;

import java.util.Collections;

import it.unibo.game.Pair;
import it.unibo.game.app.api.Brick;
import it.unibo.game.app.api.BrickType;
import it.unibo.game.app.model.DimensionImpl;
import it.unibo.game.app.model.SizeCalculation;
import it.unibo.game.app.model.brick.NormalBrick;
import it.unibo.game.app.model.brick.Obstacle;

/**
 * class that contains informations about different rounds in third level.
 */
public class RoundDifficult extends AbstractRound {

  private final int height;

  /**
   * constructor of this class.
   * 
   * @param numB      num of normal bricks
   * @param numS      num of surprise bricks
   * @param size      information of where to collocate bricks
   * @param obstacles num of obstacles
   */
  public RoundDifficult(final int numB, final int numS, final SizeCalculation size,
      final int obstacles) {
    super(numB, numS, size);
    this.height = (int) Math.sqrt((double) (2 * (obstacles + numB + numS)));
  }

  /**
   * method that set position of bricks from bottom.
   */
  public void setPosBrick() {
    int lines = 0;
    int insert = 0;
    int num = height;
    double stopY = super.getSizeCalc().getStop().getY();
    double stopX = super.getSizeCalc().getStop().getX();
    for (double i = stopX; lines < height; lines++, i = i
        - getSizeCalc().getBrickDim().getX()) {
      insert = 0;
      for (double j = stopY
          - (lines * (getSizeCalc().getBrickDim().getY() / 2)); insert < num; j = j
              - getSizeCalc().getBrickDim().getY()) {
        Brick b = new NormalBrick(BrickType.NORMAL,
            new DimensionImpl(getSizeCalc().getBrickDim().getX(),
                getSizeCalc().getBrickDim().getY()),
            new Pair<>(j, i), 1);
        super.addBrick(b);
        insert++;
      }
      num--;
    }
    Collections.reverse(super.getBrick());
    setPosObstacles();
    setSurprise();
  }

  /**
   * method that collocates surprise bricks.
   */
  private void setSurprise() {
    int num = 0;
    while (num < super.getNumSur()) {
      if (super.setBrickSurprise()) {
        ++num;
      }
    }
  }

  /**
   * method that collocates obstacles symmetrically in the last line of bricks.
   */
  private void setPosObstacles() {
    int first = super.getBrick().size() - height;
    int last = super.getBrick().size() - 1;
    while (first <= last) {
      replace(first);
      first = first + 2;
    }
  }

  /**
   * method that replace a normal brick with an obstacle.
   * 
   * @param val position of the brick to replace in the list
   */
  private void replace(final int val) {
    Brick oldB = super.getBrick().get(val);
    Brick newB = new Obstacle(BrickType.OBSTACLE,
        new DimensionImpl(oldB.getBrickH(), oldB.getBrickW()), oldB.getPos());
    super.getBrick().set(val, newB);
  }

}
