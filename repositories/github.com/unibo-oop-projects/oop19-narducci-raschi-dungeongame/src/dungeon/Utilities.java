package dungeon;

/**
 * Some utilities to do parameter validation similar to
 * {@code Objects.requireNonNull()}.
 */
public final class Utilities {

  private Utilities() {
  }

  /** The Constant ATTACK_MULTIPLIER. */
  public static final double ATTACK_MULTIPLIER = 1.5;

  /**
   * Checks that the specified number is positive.
   *
   * @param number the number to check
   * @return {@code number}
   * @throws IllegalArgumentException if {@code number} is &lt; 0
   */
  public static int requirePositive(final int number) {
    if (number < 0) {
      throw new IllegalArgumentException("number must be positive");
    }

    return number;
  }

  /**
   * Checks that the specified number is greater than zero.
   *
   * @param number the number to check
   * @return {@code number}
   * @throws IllegalArgumentException if {@code number} is &lt; 1
   */
  public static int requireGreaterThanZero(final int number) {
    if (number < 1) {
      throw new IllegalArgumentException("number must be greater than zero");
    }

    return number;
  }

  /**
   * Checks that the specified number is a frequency.
   *
   * @param number the number to check
   * @return {@code number}
   * @throws IllegalArgumentException if {@code number} is &lt; 0 || &gt; 100
   */
  public static int requireFrequency(final int number) {
    if (number < 0 || number > 100) {
      throw new IllegalArgumentException("number must be in range [0..100]");
    }

    return number;
  }
}
