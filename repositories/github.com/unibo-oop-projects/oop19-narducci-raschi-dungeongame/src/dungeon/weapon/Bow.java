package dungeon.weapon;

import dungeon.Cell;
import dungeon.Utilities;
import dungeon.attack.Attack.Result;
import dungeon.attack.Attack;
import dungeon.attack.ImmutableAttack;
import dungeon.point.Point;
import dungeon.template.Template;
import java.util.Objects;
import java.util.Optional;

/**
 * An implementation of {@code Weapon} that represents a bow.
 */
public final class Bow extends GenericWeapon implements Template<Weapon> {

  private final int frequency;
  private final int damage;
  private int ammo;
  private int extraDamage;

  /**
   * Instantiates a new {@code Bow}.
   *
   * @param name the name
   * @param description the description
   * @param range the range
   * @param frequency the frequency
   * @param damage the damage
   * @param ammo the ammo
   * @throws NullPointerException if one of the input objects is {@code null}
   * @throws IllegalArgumentException if {@code range} is &lt; 0
   * @throws IllegalArgumentException if {@code frequency} is &lt; 0 || &gt; 100
   * @throws IllegalArgumentException if one of
   *         {@code damage} or
   *         {@code ammo} is &lt; 0
   */
  public Bow(
    final String name,
    final String description,
    final int range,
    final int frequency,
    final int damage,
    final int ammo) {

    super(name, description, false, Cell.BOW, range);

    this.frequency = Utilities.requireFrequency(frequency);
    this.damage = Utilities.requirePositive(damage);
    this.ammo = Utilities.requirePositive(ammo);
    this.extraDamage = 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getFrequency() {
    return this.frequency;
  }

  /**
   * {@inheritDoc}
   *
   * This implementation ignores {@code point}.
   */
  @Override
  public Bow getInstance(final Optional<Point> point) {
    return new Bow(
      this.getName(),
      this.getDescription(),
      this.getRange(),
      this.frequency,
      this.damage,
      this.ammo);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Integer> getAmmo() {
    return Optional.of(this.ammo);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setExtraDamage(final int damage) {
    this.extraDamage = damage;
  }

  /**
   * {@inheritDoc}
   *
   * @throws NullPointerException if one of the input objects is {@code null}
   */
  @Override
  public Attack hit(final String striker, final Point from, final Point to) {
    Objects.requireNonNull(striker);
    Objects.requireNonNull(from);
    Objects.requireNonNull(to);

    final Result result;

    if (this.ammo == 0) {
      result = Result.NO_AMMO;
    } else if (this.isInRange(from, to)) {
      this.ammo--;
      result = Result.HIT;
    } else {
      result = Result.INVALID;
    }

    return new ImmutableAttack(result, this.damage + this.extraDamage,
      striker, this.getName());
  }
}
