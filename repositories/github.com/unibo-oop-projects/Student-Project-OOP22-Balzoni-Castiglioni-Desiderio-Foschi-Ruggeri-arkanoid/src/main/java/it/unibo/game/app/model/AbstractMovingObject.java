package it.unibo.game.app.model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.game.Pair;
import it.unibo.game.app.api.BoundingBox;
import it.unibo.game.app.api.Dimension;
import it.unibo.game.app.api.MovingObject;
import it.unibo.game.app.api.Physics;
import it.unibo.game.app.api.Speed;
import it.unibo.game.app.model.dynamic.SpeedImpl;

/** part common to any moving obj. */
public abstract class AbstractMovingObject implements MovingObject {

  private Pair<Double, Double> pos;
  private BoundingBox box;
  private Speed speed;
  private Dimension d;
  private Physics pysic;

  /**
   * @param pos
   * @param d   costructors accept position and dimension of moving obj.
   */
  public AbstractMovingObject(final Pair<Double, Double> pos, final Dimension d) {
    this.pos = new Pair<Double, Double>(pos.getX(), pos.getY());
    this.d = new DimensionImpl(d.getHeight(), d.getWidth());
    this.speed = new SpeedImpl(3, 3);
  }

  /** {@inheritDoc} */
  @Override
  public void setDimension(final Dimension d) {
    this.d = new DimensionImpl(d.getHeight(), d.getWidth());
  }

  /** {@inheritDoc} */
  @Override
  public Pair<Double, Double> getPos() {
    return this.pos;
  }

  /** {@inheritDoc} */
  @Override
  public BoundingBox getBoundingBox() {
    return this.box;
  }

  /** {@inheritDoc} */
  @Override
  public void setBoundingBox(final BoundingBox box) {
    this.box = box;
  }

  /** {@inheritDoc} */
  @Override
  public void setPos(final Pair<Double, Double> pos) {
    this.pos = pos;
  }

  /** {@inheritDoc} */
  @Override
  public Speed getSpeed() {
    return this.speed;
  }

  /** {@inheritDoc} */
  @Override
  public void setSpeed(final Speed vel) {

    this.speed = vel;
  }

  /** {@inheritDoc} */
  
  @Override
  public Dimension getDimension() {

    return this.d;
  }

  /** {@inheritDoc} */
  @Override
  public Physics getPhysics() {
    return this.pysic;
  }

  /** {@inheritDoc} */
  @Override
  public void setPhysics(final Physics phsycs) {
    this.pysic = phsycs;
  }
}
