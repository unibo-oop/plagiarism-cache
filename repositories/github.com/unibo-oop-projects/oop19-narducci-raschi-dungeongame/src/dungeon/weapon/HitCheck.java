package dungeon.weapon;

import dungeon.Direction;
import dungeon.attack.Attack;
import dungeon.attack.Attack.Result;
import dungeon.attack.ImmutableAttack;
import dungeon.floor.Floor;
import dungeon.item.Item;
import dungeon.point.Point;
import java.util.Objects;
import java.util.Optional;

/**
 * A decorator of {@code Weapon}, it adds a check to the {@code hit()} method,
 * making sure that no blocking items are between the two points.
 */
public final class HitCheck extends WeaponDecorator {

  private final Floor floor;

  /**
   * Instantiates a new {@code HitCheck}.
   *
   * @param floor the floor
   * @param weapon the weapon
   * @throws NullPointerException if one of the input objects is {@code null}
   */
  public HitCheck(final Floor floor, final Weapon weapon) {
    super(weapon);
    this.floor = Objects.requireNonNull(floor);
  }

  private boolean isValid(final Point from, final Point to) {
    final Direction direction = from.getDirection(to);
    Point target = from;

    for (int index = 0; index < this.getRange(); index++) {
      target = target.move(direction.getModifier());

      if (target.equals(to)) {
        return true;
      } else {
        final Optional<? extends Item> item = floor.getItem(target);

        if (item.isPresent()) {
          if (item.get().isBlocking()) {
            return false;
          }
        }
      }
    }

    return true;
  }

  /**
   * {@inheritDoc}
   *
   * @throws NullPointerException if one of the input objects is {@code null}
   * @throws IllegalArgumentException if the input points are the same point
   * @throws IllegalArgumentException if the direction is invalid (oblique)
   */
  @Override
  public Attack hit(final String striker, final Point from, final Point to) {
    Objects.requireNonNull(striker);
    Objects.requireNonNull(from);
    Objects.requireNonNull(to);

    if (this.isValid(from, to)) {
      return super.hit(striker, from, to);
    } else {
      return new ImmutableAttack(Result.INVALID, 0, striker, this.getName());
    }
  }
}
