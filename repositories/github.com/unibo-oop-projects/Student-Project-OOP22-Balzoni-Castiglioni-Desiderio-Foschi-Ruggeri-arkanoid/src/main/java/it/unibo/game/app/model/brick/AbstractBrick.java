package it.unibo.game.app.model.brick;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.game.Pair;
import it.unibo.game.app.api.BoundingBox;
import it.unibo.game.app.api.Brick;
import it.unibo.game.app.api.BrickType;
import it.unibo.game.app.api.Dimension;
import it.unibo.game.app.model.DimensionImpl;
import it.unibo.game.app.model.RectBoundingBox;

/**
 * Abstract class of brick entity.
 */
public abstract class AbstractBrick implements Brick {

  private BrickType type;
  private BoundingBox box;
  private Dimension d;
  private Pair<Double, Double> pos;

  /**
   * Costructor of this class.
   * 
   * @param type type of brick
   * @param d    dimension of brick
   * @param pos  position of brick
   */
  public AbstractBrick(final BrickType type, final Dimension d,
      final Pair<Double, Double> pos) {
    this.type = type;
    this.pos = new Pair<Double, Double>(pos.getX(), pos.getY());
    this.d = new DimensionImpl(d.getHeight(), d.getWidth());
    this.setBoundingBox(new RectBoundingBox(this));

  }

  /**
   * {@inheritDoc}
   */
  public BrickType getType() {
    return this.type;
  }

  /**
   * {@inheritDoc}
   */
  public void changeType(final BrickType type) {
    this.type = type;
  }

  /**
   * {@inheritDoc}
   */
  public Double getBrickH() {
    return this.d.getHeight();
  }

  /**
   * {@inheritDoc}
   */
  public Double getBrickW() {
    return this.d.getWidth();
  }

  /**
   * {@inheritDoc}
   * 
   * @param pos
   */
  @Override
  public void setPos(final Pair<Double, Double> pos) {
    this.pos = pos;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Pair<Double, Double> getPos() {
    return this.pos;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BoundingBox getBoundingBox() {
    return this.box;
  }

  /**
   * {@inheritDoc}
   * 
   * @param box boundingBox of the brick
   */
  @Override
  public void setBoundingBox(final BoundingBox box) {
    this.box = box;
  }

  /**
   * {@inheritDoc}
   */
  
  @Override
  public Dimension getDimension() {
    return this.d;
  }

  /**
   * {@inheritDoc}
   * 
   * @param d dimension of brick set the dimension of brick
   */
  public void setDimension(final Dimension d) {
    this.d = new DimensionImpl(d.getHeight(), d.getWidth());
  }

  /**
   * {@inheritDoc}
   */
  public abstract boolean isDestroyed();

  /**
   * {@inheritDoc}
   */
  public abstract void hit();

  /**
   * {@inheritDoc}
   */
  public abstract Optional<Integer> getRes();

  /**
   * {@inheritDoc}
   * 
   * @param res
   */
  public abstract void increaseRes(int res);

}
