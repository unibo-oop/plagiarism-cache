package it.unibo.game.app.model.brick;

import java.util.Optional;
import it.unibo.game.Pair;
import it.unibo.game.app.api.BrickType;
import it.unibo.game.app.api.Dimension;

/**
 * class of normal and hard bricks.
 */
public class NormalBrick extends AbstractBrick {

  private int brickResistence;

  /**
   * costructor of this class.
   * 
   * @param type
   * @param d
   * @param pos
   * @param resistence resistence of the brick
   */
  public NormalBrick(final BrickType type, final Dimension d,
      final Pair<Double, Double> pos, final int resistence) {
    super(type, d, pos);
    this.brickResistence = resistence;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Integer> getRes() {
    return Optional.of(this.brickResistence);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void hit() {
    --this.brickResistence;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDestroyed() {
    return this.brickResistence == 0;
  }

  /**
   * {@inheritDoc}
   * 
   * @param res
   */
  @Override
  public void increaseRes(final int res) {
    this.brickResistence = res + 1;
  }

  /**
   * {@inheritDoc}
   * 
   * @param res
   */
  public void decreaseRes(final int res) {
    this.brickResistence = res - 1;
  }

}
