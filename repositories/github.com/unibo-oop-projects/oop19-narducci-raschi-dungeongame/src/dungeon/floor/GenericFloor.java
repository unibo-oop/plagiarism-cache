package dungeon.floor;

import dungeon.Cell;
import dungeon.item.GenericItem;
import java.util.Objects;

/**
 * A skeletal implementation of {@code Floor}.
 */
public abstract class GenericFloor extends GenericItem implements Floor {

  private final Type type;

  /**
   * Instantiates a new {@code GenericFloor}.
   *
   * @param name the name
   * @param description the description
   * @param type the type
   * @throws NullPointerException if one of the input objects is {@code null}
   */
  protected GenericFloor(
    final String name,
    final String description,
    final Type type) {

    super(name, description, false, Cell.EXIT);

    this.type = Objects.requireNonNull(type);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final Type getType() {
    return this.type;
  }
}
