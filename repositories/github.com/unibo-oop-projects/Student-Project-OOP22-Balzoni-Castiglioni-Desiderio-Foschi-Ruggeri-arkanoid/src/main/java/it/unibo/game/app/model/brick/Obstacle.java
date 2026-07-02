package it.unibo.game.app.model.brick;

import java.util.Optional;

import it.unibo.game.Pair;
import it.unibo.game.app.api.BrickType;
import it.unibo.game.app.api.Dimension;

/**
 * an obstacle is a brick that is undestroyable.
 */
public class Obstacle extends AbstractBrick {

  /**
   * constructor of this class.
   * 
   * @param type type of brick
   * @param d    dimension of brick
   * @param pos  position of upper-left corner
   */
  public Obstacle(final BrickType type, final Dimension d,
      final Pair<Double, Double> pos) {
    super(type, d, pos);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDestroyed() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void hit() {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Integer> getRes() {
    return Optional.empty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void increaseRes(final int res) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void decreaseRes(final int res) {
  }

}
