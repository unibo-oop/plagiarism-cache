package dungeon.room;

import dungeon.point.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Some utilities related to {@code Room}.
 */
public final class Rooms {

  private Rooms() {
  }

  /**
   * Returns the room that contains point.
   *
   * @param rooms the rooms
   * @param point the point
   * @return the empty {@code Optional} if the room was not found, otherwise the
   *         full one with the value
   * @throws NullPointerException if one of the input objects is {@code null}
   * @throws IllegalStateException if the number of rooms found is &gt; 1
   */
  public static Optional<Room> findRoom(
    final List<Room> rooms,
    final Point point) {

    final List<Room> found = new ArrayList<>();

    rooms.forEach(room -> {
      if (room.contains(point)) {
        found.add(room);
      }
    });

    if (found.size() == 0) {
      return Optional.empty();
    } else if (found.size() == 1) {
      return Optional.of(found.get(0));
    } else {
      throw new IllegalStateException("more than 1 room found");
    }
  }
}
