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
import java.util.Random;

/**
 * An implementation of {@code Weapon} that represents a sword.
 */
public final class Sword extends GenericWeapon implements Template<Weapon> {

  private static final int BOUND = 101;

  private final Random random;
  private final int frequency;
  private final int hitFrequency;
  private final int minDamage;
  private final int maxDamage;
  private final int damageBound;
  private int extraDamage;

  /**
   * Instantiates a new {@code Sword}.
   *
   * @param name the name
   * @param description the description
   * @param range the range
   * @param frequency the frequency
   * @param hitFrequency the hit frequency
   * @param minDamage the minimum damage
   * @param maxDamage the maximum damage
   * @throws NullPointerException if one of the input objects is {@code null}
   * @throws IllegalArgumentException if {@code range} is &lt; 0
   * @throws IllegalArgumentException if one of
   *         {@code frequency} or
   *         {@code hitFrequency} is &lt; 0 || &gt; 100
   * @throws IllegalArgumentException if
   *         {@code maxDamage} is &lt; {@code minDamage}
   */
  public Sword(
    final String name,
    final String description,
    final int range,
    final int frequency,
    final int hitFrequency,
    final int minDamage,
    final int maxDamage) {

    super(name, description, false, Cell.SWORD, range);

    this.random = new Random();
    this.frequency = Utilities.requireFrequency(frequency);
    this.hitFrequency = Utilities.requireFrequency(hitFrequency);
    this.minDamage = minDamage;
    this.maxDamage = maxDamage;
    this.damageBound = maxDamage - minDamage + 1;
    this.extraDamage = 0;

    if (this.maxDamage < this.minDamage) {
      throw new IllegalArgumentException(
        "maxDamage cannot be < than minDamage");
    }
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
  public Sword getInstance(final Optional<Point> point) {
    return new Sword(
      this.getName(),
      this.getDescription(),
      this.getRange(),
      this.frequency,
      this.hitFrequency,
      this.minDamage,
      this.maxDamage);
  }

  /**
   * {@inheritDoc}
   *
   * This is a sword, it will always return an empty {@code Optional}.
   */
  @Override
  public Optional<Integer> getAmmo() {
    return Optional.empty();
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
    final int damage;

    if (this.isInRange(from, to)) {
      if (this.random.nextInt(BOUND) <= this.hitFrequency) {
        result = Result.HIT;
      } else {
        result = Result.MISS;
      }
    } else {
      result = Result.INVALID;
    }

    if (Result.HIT.equals(result)) {
      damage = this.random.nextInt(this.damageBound)
        + this.minDamage
        + this.extraDamage;
    } else {
      damage = 0;
    }

    return new ImmutableAttack(result, damage, striker, this.getName());
  }
}
