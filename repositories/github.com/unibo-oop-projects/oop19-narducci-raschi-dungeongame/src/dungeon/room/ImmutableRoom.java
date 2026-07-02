package dungeon.room;

import dungeon.Utilities;
import dungeon.point.Point;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Objects;

/**
 * An immutable implementation of {@code Room}.
 */
public final class ImmutableRoom extends SimpleImmutableEntry<Point, Point>
  implements Room {

  private static final long serialVersionUID = -4340683089996560455L;

  /**
   * Instantiates a new {@code ImmutableRoom}.
   *
   * @param base the base point
   * @param size the size
   * @throws NullPointerException if one of the input objects is {@code null}
   * @throws IllegalArgumentException if one of {@code base}
   *         {@code x} or
   *         {@code y} is &lt; 0
   * @throws IllegalArgumentException if one of {@code size}
   *         {@code x} or
   *         {@code y} are &lt; 1
   */
  public ImmutableRoom(final Point base, final Point size) {
    super(Objects.requireNonNull(base), Objects.requireNonNull(size));

    Utilities.requirePositive(base.getX());
    Utilities.requirePositive(base.getY());
    Utilities.requireGreaterThanZero(size.getX());
    Utilities.requireGreaterThanZero(size.getY());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Point getBase() {
    return this.getKey();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Point getSize() {
    return this.getValue();
  }

  /**
   * {@inheritDoc}
   *
   * @throws NullPointerException if {@code point} is {@code null}
   */
  @Override
  public boolean contains(final Point point) {
    Objects.requireNonNull(point);

    final Point base = this.getBase();
    final Point end = base.move(this.getSize());

    return point.getX() >= base.getX()
      && point.getY() >= base.getY()
      && point.getX() < end.getX()
      && point.getY() < end.getY();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Room [base=" + this.getBase() + ", size=" + this.getSize() + "]";
  }
}
