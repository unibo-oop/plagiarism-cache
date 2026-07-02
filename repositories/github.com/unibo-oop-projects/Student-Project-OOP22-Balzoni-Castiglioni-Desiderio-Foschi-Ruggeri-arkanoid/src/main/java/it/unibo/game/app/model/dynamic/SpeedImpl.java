package it.unibo.game.app.model.dynamic;

import it.unibo.game.app.api.Speed;

/**
 * class that contains informations about objects' speed.
 */
public class SpeedImpl implements Speed {

  private double x;
  private double y;
  private static final double EPSILON = 0.00000001;

  /**
   * constructor of the class.
   * 
   * @param x coordinate x of speed
   * @param y coordinate y of speed
   */
  public SpeedImpl(final double x, final double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getX() {
    return this.x;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getY() {
    return this.y;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void sum(final Speed vel) {
    this.x = this.x + vel.getX();
    this.y = this.y + vel.getY();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((Double) x).hashCode();
    result = prime * result + ((Double) y).hashCode();
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Speed other = (Speed) obj;
    if (Math.abs(other.getX() - x) < EPSILON && Math.abs(other.getY() - y) < EPSILON) {
      return true;
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "[" + this.x + "," + this.y + "]";
  }

}
