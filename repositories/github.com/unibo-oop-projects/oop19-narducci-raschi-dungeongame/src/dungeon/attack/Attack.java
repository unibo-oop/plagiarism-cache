package dungeon.attack;

import java.util.Optional;

/**
 * An object describing an attack.
 */
public interface Attack {

  /**
   * Returns the result.
   *
   * @return the result
   */
  Result getResult();

  /**
   * Returns the damage if present.
   *
   * @return the empty {@code Optional} if the attack did not hit, otherwise the
   *         full one with the value
   */
  Optional<Integer> getDamage();

  /**
   * Returns the result dependent description.
   *
   * @return the description
   */
  String getDescription();

  /**
   * The result of an {@code Attack}.
   */
  enum Result {

    /** An invalid attack (for example: out of range). */
    INVALID,

    /** The weapon has no ammo. */
    NO_AMMO,

    /** The weapon hit. */
    HIT,

    /** The weapon missed. */
    MISS
  }
}
