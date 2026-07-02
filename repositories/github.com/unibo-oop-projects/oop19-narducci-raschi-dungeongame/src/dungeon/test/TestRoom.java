package dungeon.test;

import dungeon.point.ImmutablePoint;
import dungeon.point.Point;
import dungeon.room.ImmutableRoom;
import dungeon.room.Room;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// You do not silence checkstyle like this. MagicNumber OFF
public final class TestRoom {

  @Test
  public void testNew() {
    final Point base = new ImmutablePoint(1, 3);
    final Point size = new ImmutablePoint(5, 4);
    final Room room = new ImmutableRoom(base, size);

    assertEquals(room.getBase(), base);
    assertEquals(room.getSize(), size);
    assertTrue(room.contains(base));
    assertTrue(room.contains(new ImmutablePoint(2, 4)));
    assertFalse(room.contains(base.move(size)));
  }

  @Test
  public void testNewExceptions() {
    assertThrows(NullPointerException.class,
      () -> new ImmutableRoom(null, new ImmutablePoint(1, 1)));

    assertThrows(NullPointerException.class,
      () -> new ImmutableRoom(new ImmutablePoint(1, 1), null));

    assertThrows(IllegalArgumentException.class,
      () -> new ImmutableRoom(
        new ImmutablePoint(-1, 1),
        new ImmutablePoint(2, 3)));

    assertThrows(IllegalArgumentException.class,
      () -> new ImmutableRoom(
        new ImmutablePoint(3, -1),
        new ImmutablePoint(7, 1)));

    assertThrows(IllegalArgumentException.class,
      () -> new ImmutableRoom(
        new ImmutablePoint(10, 4),
        new ImmutablePoint(0, 4)));

    assertThrows(IllegalArgumentException.class,
      () -> new ImmutableRoom(
        new ImmutablePoint(6, 3),
        new ImmutablePoint(9, 0)));
  }
}
