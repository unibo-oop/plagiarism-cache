package it.unibo.game.app.model.dynamic;

import it.unibo.game.Pair;
import it.unibo.game.app.api.Direction;

/**
 * Class that manages the direction of MovingObject.
 */
public class DirectionImpl implements Direction {

  private static final int STARTER_Y = 2;
  private static final int LEFT = -1;
  private static final int RIGHT = 1;
  private static final int UP = -1;
  private static final int DOWN = 1;
  private Pair<Integer, Integer> d;

  /**
   * constructor of this class.
   */
  public DirectionImpl() {
    this.d = new Pair<Integer, Integer>(LEFT, UP);
  }

  /**
   * {@inheritDoc}
   */
  public void setDirection(final Pair<Integer, Integer> newD) {
    this.d = newD;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setDirectionUp() {
    this.d = new Pair<Integer, Integer>(this.d.getX(), UP);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setDirectionDown() {
    this.d = new Pair<Integer, Integer>(this.d.getX(), DOWN);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setDirectionLeft() {
    this.d = new Pair<Integer, Integer>(LEFT, this.d.getY());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setDirectionRight() {
    this.d = new Pair<Integer, Integer>(RIGHT, this.d.getY());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDirectionUp() {
    return this.d.getY() == UP;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDirectionLeft() {
    return this.d.getX() == LEFT;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isDirectionRight() {
    return this.d.getX().equals(RIGHT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Pair<Integer, Integer> getDirection() {
    return this.d;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void resetDirection() {
    this.d = new Pair<Integer, Integer>(LEFT, UP);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setCentre() {
    this.d = new Pair<Integer, Integer>(0, -STARTER_Y);
  }

}
