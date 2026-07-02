package dungeon.point;

import dungeon.Direction;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Objects;

/**
 * An immutable implementation of {@code Point}.
 */
public final class ImmutablePoint extends SimpleImmutableEntry<Integer, Integer>
  implements Point {

  private static final long serialVersionUID = 7232980868315814176L;

  /**
   * Instantiates a new {@code ImmutablePoint} using the input coordinates.
   *
   * @param x the x
   * @param y the y
   */
  public ImmutablePoint(final int x, final int y) {
    super(x, y);
  }

  /**
   * Instantiates a new {@code ImmutablePoint} using the input point
   * coordinates.
   *
   * @param point the point
   * @throws NullPointException if {@code point} is {@code null}
   */
  public ImmutablePoint(final Point point) {
    this(Objects.requireNonNull(point).getX(), point.getY());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getX() {
    return this.getKey();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getY() {
    return this.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Point move(final int x, final int y) {
    return new ImmutablePoint(this.getX() + x, this.getY() + y);
  }

  /**
   * {@inheritDoc}
   *
   * @throws NullPointerException if {@code point} is {@code null}
   */
  @Override
  public Point move(final Point point) {
    return this.move(Objects.requireNonNull(point).getX(), point.getY());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Point invert() {
    return new ImmutablePoint(this.getX() * -1, this.getY() * -1);
  }

  /**
   * {@inheritDoc}
   *
   * @throws NullPointerException if {@code point} is {@code null}
   * @throws IllegalArgumentException if the input point equals to this one
   * @throws IllegalArgumentException if the direction is invalid (oblique)
   */
  @Override
  public Direction getDirection(final Point point) {
    if (this.equals(Objects.requireNonNull(point))) {
      throw new IllegalArgumentException("the input points are the same point");
    }

    if (this.getX() == point.getX()) {
      if (this.getY() < point.getY()) {
        return Direction.RIGHT;
      } else {
        return Direction.LEFT;
      }
    } else if (this.getY() == point.getY()) {
      if (this.getX() < point.getX()) {
        return Direction.DOWN;
      } else {
        return Direction.UP;
      }
    } else {
      throw new IllegalArgumentException("invalid direction");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Point [x=" + this.getX() + ", y=" + this.getY() + "]";
  }
}
