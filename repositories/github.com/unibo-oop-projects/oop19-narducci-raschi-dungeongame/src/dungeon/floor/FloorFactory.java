package dungeon.floor;

/**
 * An object able to create simple {@code Floor} objects.
 */
public interface FloorFactory {

  /**
   * Returns a new floor with type {@code ENTRY}.
   *
   * @return the floor
   */
  Floor getEntryFloor();

  /**
   * Returns a new floor with type {@code EXIT}.
   *
   * @return the floor
   */
  Floor getExitFloor();
}
