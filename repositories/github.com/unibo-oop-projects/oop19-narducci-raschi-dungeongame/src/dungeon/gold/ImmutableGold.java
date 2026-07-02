package dungeon.gold;

import dungeon.Cell;
import dungeon.Utilities;
import dungeon.item.GenericItem;
import dungeon.point.Point;
import dungeon.template.Template;
import java.util.Optional;

/**
 * An immutable implementation of {@code Gold}.
 */
public final class ImmutableGold extends GenericItem
  implements Template<Gold>, Gold {

  private final int frequency;
  private final int amount;

  /**
   * Instantiates a new {@code ImmutableGold}.
   *
   * @param frequency the frequency
   * @param amount the amount
   * @throws IllegalArgumentException if {@code frequency} is &lt; 0 || &gt; 100
   * @throws IllegalArgumentException if {@code amount} is &lt; 0
   */
  public ImmutableGold(final int frequency, final int amount) {
    super("Gold", "Some gold coins", false, Cell.GOLD);

    this.frequency = Utilities.requireFrequency(frequency);
    this.amount = Utilities.requirePositive(amount);
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
  public Gold getInstance(final Optional<Point> point) {
    return new ImmutableGold(this.frequency, this.amount);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getAmount() {
    return this.amount;
  }
}
