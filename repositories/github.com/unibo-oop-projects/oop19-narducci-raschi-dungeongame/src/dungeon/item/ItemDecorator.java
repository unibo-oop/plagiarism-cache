package dungeon.item;

import dungeon.Cell;
import java.util.Objects;

/**
 * A skeletal implementation of a {@code Item} decorator.
 */
public class ItemDecorator implements Item {

  private final Item item;

  /**
   * Instantiates a new {@code ItemDecorator}.
   *
   * @param item the item
   * @throws NullPointerException if {@code item} is {@code null}
   */
  protected ItemDecorator(final Item item) {
    this.item = Objects.requireNonNull(item);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return this.item.getName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getDescription() {
    return this.item.getDescription();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBlocking() {
    return this.item.isBlocking();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Cell getCell() {
    return this.item.getCell();
  }
}
