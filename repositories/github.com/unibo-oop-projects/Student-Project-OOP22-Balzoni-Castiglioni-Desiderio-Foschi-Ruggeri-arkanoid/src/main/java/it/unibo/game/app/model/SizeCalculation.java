package it.unibo.game.app.model;

import it.unibo.game.Pair;
import it.unibo.game.app.api.Dimension;

/**
 * Class that calculates the size of the entity based on the size of the model.
 */
public class SizeCalculation {
  private static final Double WORLD_HEIGHT = 400d; /*
                                                    * Height of the world inside the model
                                                    */
  private static final Double WORLD_WIDTH = 300d; /*
                                                   * Width of the world inside the model
                                                   */
  private static final int DIVIDER_X = 6;
  private static final int NUMCOL = 6;
  private static final double MUL_X1 = 1.75;
  private static final double MUL_X2 = 1.5;
  private static final double DIVEDER_PAD_X = 60;
  private static final double DIVEDER_BALL = 30;

  private int numBrickCol;
  private Double startX;
  private Double startY;
  private Double stopX;
  private Double stopY;
  private Double brickL;
  private Double brickH;

  /**
   * costructor of this class.
   * 
   * @param numBrickCol number of bricks in a column
   * @param numBrickRow number of brick in a row
   */
  public SizeCalculation(final int numBrickCol, final int numBrickRow) {
    this.numBrickCol = numBrickCol;
    this.startX = (WORLD_HEIGHT / 2) / DIVIDER_X;
    this.stopX = this.getStopX();
    this.brickL = WORLD_WIDTH / numBrickRow;
    this.brickH = (stopX - startX) / numBrickCol;
    this.stopY = WORLD_WIDTH - (3 * (this.brickL / 2));
    this.startY = (this.brickL / 2);
  }

  /**
   * 
   * @return returns the height at which to stop the placement of the bricks.
   */
  private Double getStopX() {
    if (numBrickCol > NUMCOL) {
      return (((WORLD_HEIGHT / 2) / 3) * MUL_X1);
    } else if (numBrickCol > 4) {
      return (((WORLD_HEIGHT / 2) / 3) * MUL_X2);
    } else {
      return ((WORLD_HEIGHT / 2) / 3);
    }
  }

  /**
   * 
   * @return the size of the model.
   */
  public static Pair<Double, Double> getWorldSize() {
    return new Pair<Double, Double>(WORLD_HEIGHT, WORLD_WIDTH);
  }

  /**
   * 
   * @return the starting coordinates from which to start the positioning of the
   *         bricks.
   */
  public Pair<Double, Double> getStart() {
    return new Pair<Double, Double>(startX, startY);
  }

  /**
   * 
   * @return the end coordinates from which to stop the positioning of the bricks.
   */
  public Pair<Double, Double> getStop() {
    return new Pair<Double, Double>(stopX, stopY);
  }

  /**
   * 
   * @return the brick dimension.
   */
  public Pair<Double, Double> getBrickDim() {
    return new Pair<Double, Double>(brickH, brickL);
  }

  /**
   * 
   * @param x number of row.
   * @return method used to know the y of the rows of bricks
   */
  public Double getRowCordinate(final Double x) {
    return this.startX + (x * this.brickH);
  }

  /**
   * 
   * @return the dimension of pad method that calculates the size of the pad
   */
  public static Dimension getPadDim() {
    return new DimensionImpl(WORLD_HEIGHT / DIVEDER_PAD_X, WORLD_WIDTH / 4);
  }

  /**
   * 
   * @return the dimension of ball method that calculates the size of the ball.
   */
  public static Dimension getBallDim() {
    return new DimensionImpl(WORLD_WIDTH / DIVEDER_BALL, WORLD_WIDTH / DIVEDER_BALL);
  }
}
