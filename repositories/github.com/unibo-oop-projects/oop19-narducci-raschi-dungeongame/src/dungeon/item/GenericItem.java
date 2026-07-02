package dungeon.item;

import dungeon.Cell;
import java.util.Objects;

/**
 * A skeletal implementation of {@code Item}.
 */
public abstract class GenericItem implements Item {

  private final String name;
  private final String description;
  private final boolean blocking;
  private final Cell cell;

  /**
   * Instantiates a new {@code GenericItem}.
   *
   * @param name the name
   * @param description the description
   * @param blocking true, if is blocking
   * @param cell the cell
   * @throws NullPointerException if one of input objects is {@code null}
   */
  protected GenericItem(
    final String name,
    final String description,
    final boolean blocking,
    final Cell cell) {

    this.name = Objects.requireNonNull(name);
    this.description = Objects.requireNonNull(description);
    this.blocking = blocking;
    this.cell = Objects.requireNonNull(cell);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getName() {
    return this.name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getDescription() {
    return this.description;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isBlocking() {
    return this.blocking;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final Cell getCell() {
    return this.cell;
  }
}
