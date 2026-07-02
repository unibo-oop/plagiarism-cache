package dungeon.attack;

import java.util.Objects;
import java.util.Optional;

/**
 * An immutable implementation of {@code Attack}.
 */
public final class ImmutableAttack implements Attack {

  private final Result result;
  private final int damage;
  private final String striker;
  private final String weapon;

  /**
   * Instantiates a new {@code ImmutableAttack}.
   *
   * @param result the result
   * @param damage the damage, ignored if did not hit
   * @param striker the striker name
   * @param weapon the weapon name
   * @throws NullPointerException if one of the input objects is {@code null}
   */
  public ImmutableAttack(
    final Result result,
    final int damage,
    final String striker,
    final String weapon) {

    this.result = Objects.requireNonNull(result);
    this.damage = damage;
    this.striker = Objects.requireNonNull(striker);
    this.weapon = Objects.requireNonNull(weapon);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Result getResult() {
    return this.result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Integer> getDamage() {
    if (Result.HIT.equals(this.result)) {
      return Optional.of(this.damage);
    }

    return Optional.empty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription() {
    if (Result.INVALID.equals(this.result)) {
      return striker + " cannot hit there!";
    } else if (Result.NO_AMMO.equals(this.result)) {
      return striker + "'s " + weapon + " has no ammo!";
    } else if (Result.HIT.equals(this.result)) {
      return striker + " hit with " + weapon + " (" + this.damage + ")";
    } else if (Result.MISS.equals(this.result)) {
      return striker + " missed with " + weapon;
    } else {
      throw new IllegalStateException();
    }
  }
}
