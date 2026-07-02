package dungeon.weapon;

import dungeon.Cell;
import dungeon.Utilities;
import dungeon.item.GenericItem;
import dungeon.point.Point;
import java.util.Objects;

/**
 * A skeletal implementation of {@code Weapon}.
 */
public abstract class GenericWeapon extends GenericItem implements Weapon {

  private final int range;

  /**
   * Instantiates a new {@code GenericWeapon}.
   *
   * @param name the name
   * @param description the description
   * @param blocking true, if is blocking
   * @param cell the cell
   * @param range the range
   * @throws NullPointerException if one of the input objects is {@code null}
   * @throws IllegalArgumentException if {@code range} is &lt; 0
   */
  protected GenericWeapon(
    final String name,
    final String description,
    final boolean blocking,
    final Cell cell,
    final int range) {

    super(name, description, blocking, cell);

    this.range = Utilities.requirePositive(range);
  }

  /**
   * Checks if the target point is in range, given the source.
   *
   * @param from the source point
   * @param to the target point
   * @return true, if is in range
   * @throws NullPointerException if one of the input objects is {@code null}
   */
  protected final boolean isInRange(final Point from, final Point to) {
    Objects.requireNonNull(from);
    Objects.requireNonNull(to);

    final Point distance = from.move(to.invert());

    return Math.abs(distance.getX()) <= this.range
      && Math.abs(distance.getY()) <= this.range;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getRange() {
    return this.range;
  }
}
