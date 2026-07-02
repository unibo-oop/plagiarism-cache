package dungeon.test;

import dungeon.Direction;
import dungeon.point.ImmutablePoint;
import dungeon.point.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// You do not silence checkstyle like this. MagicNumber OFF
public final class TestPoint {

  @Test
  public void testNew() {
    final Point point = new ImmutablePoint(4, 7);
    final Point other = new ImmutablePoint(new ImmutablePoint(4, 7));

    assertEquals(point, other);
    assertEquals(point.getX(), 4);
    assertEquals(point.getY(), 7);
  }

  @Test
  public void testNewException() {
    assertThrows(NullPointerException.class,
      () -> new ImmutablePoint(null));
  }

  @Test
  public void testMove() {
    final Point point = new ImmutablePoint(6, 1);
    final Point moved = point.move(3, 2);

    assertNotEquals(point, moved);
    assertEquals(moved, new ImmutablePoint(9, 3));
    assertEquals(moved, point.move(new ImmutablePoint(3, 2)));
  }

  @Test
  public void testMoveException() {
    assertThrows(NullPointerException.class,
      () -> new ImmutablePoint(1, 2).move(null));
  }

  @Test
  public void testInvert() {
    assertEquals(new ImmutablePoint(8, 3).invert(), new ImmutablePoint(-8, -3));
  }

  @Test
  public void testDirection() {
    final Point point = new ImmutablePoint(5, 3);

    assertEquals(point.getDirection(point.move(0, 1)), Direction.RIGHT);
    assertEquals(point.getDirection(point.move(10, 0)), Direction.DOWN);
  }

  @Test
  public void testDirectionException() {
    final Point point = new ImmutablePoint(23, 5);

    assertThrows(NullPointerException.class,
      () -> point.getDirection(null));

    assertThrows(IllegalArgumentException.class,
      () -> point.getDirection(new ImmutablePoint(point)));

    assertThrows(IllegalArgumentException.class,
      () -> point.getDirection(point.move(34, 1)));
  }
}
