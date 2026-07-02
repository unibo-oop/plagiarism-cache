package dungeon.weapon;

import dungeon.attack.Attack;
import dungeon.item.ItemDecorator;
import dungeon.point.Point;
import java.util.Objects;
import java.util.Optional;

/**
 * A skeletal implementation of a {@code Weapon} decorator.
 */
public abstract class WeaponDecorator extends ItemDecorator implements Weapon {

  private final Weapon weapon;

  /**
   * Instantiates a new {@code WeaponDecorator}.
   *
   * @param weapon the weapon
   * @throws NullPointerException if {@code weapon} is {@code null}
   */
  protected WeaponDecorator(final Weapon weapon) {
    super(weapon);
    this.weapon = Objects.requireNonNull(weapon);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getRange() {
    return this.weapon.getRange();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Integer> getAmmo() {
    return this.weapon.getAmmo();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setExtraDamage(final int damage) {
    this.weapon.setExtraDamage(damage);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Attack hit(final String striker, final Point from, final Point to) {
    return this.weapon.hit(striker, from, to);
  }
}
