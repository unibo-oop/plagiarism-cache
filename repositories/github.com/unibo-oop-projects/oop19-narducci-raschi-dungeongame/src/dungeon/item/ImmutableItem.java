package dungeon.item;

import dungeon.Cell;
import dungeon.Utilities;
import dungeon.point.Point;
import dungeon.template.Template;
import java.util.Optional;

/**
 * An immutable implementation of {@code Item}.
 */
public final class ImmutableItem extends GenericItem implements Template<Item> {

  private final int frequency;

  /**
   * Instantiates a new {@code ImmutableItem}.
   *
   * @param name the name
   * @param description the description
   * @param blocking true, if is blocking
   * @param cell the cell
   * @param frequency the frequency
   * @throws NullPointerException if one of input objects is {@code null}
   * @throws IllegalArgumentException if {@code frequency} is &lt; 0 || &gt; 100
   */
  public ImmutableItem(
    final String name,
    final String description,
    final boolean blocking,
    final Cell cell,
    final int frequency) {

    super(name, description, blocking, cell);

    this.frequency = Utilities.requireFrequency(frequency);
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
  public Item getInstance(final Optional<Point> point) {
    return new ImmutableItem(
      this.getName(),
      this.getDescription(),
      this.isBlocking(),
      this.getCell(),
      this.frequency);
  }
}
