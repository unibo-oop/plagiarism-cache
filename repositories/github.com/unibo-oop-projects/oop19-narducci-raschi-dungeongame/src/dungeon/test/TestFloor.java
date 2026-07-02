package dungeon.test;

import dungeon.Cell;
import dungeon.floor.Floor.Type;
import dungeon.floor.Floor;
import dungeon.floor.FloorFactory;
import dungeon.floor.ImmutableFloorFactory;
import dungeon.point.ImmutablePoint;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// You do not silence checkstyle like this. MagicNumber OFF
public final class TestFloor {

  @Test
  public void testFactory() {
    final FloorFactory factory = new ImmutableFloorFactory();
    final List<Floor> list = new ArrayList<>();
    final Floor entry = factory.getEntryFloor();
    final Floor exit = factory.getExitFloor();

    assertTrue(Type.ENTRY.equals(entry.getType()));
    assertTrue(Type.EXIT.equals(exit.getType()));

    list.add(entry);
    list.add(exit);
    list.forEach(floor -> {
      assertNotNull(floor.getName());
      assertNotNull(floor.getDescription());
      assertFalse(floor.isBlocking());
      assertTrue(Cell.EXIT.equals(floor.getCell()));

      assertThrows(UnsupportedOperationException.class,
        () -> floor.getPlayers());

      assertThrows(UnsupportedOperationException.class,
        () -> floor.getMonsters());

      assertThrows(UnsupportedOperationException.class,
        () -> floor.getItems());

      assertThrows(UnsupportedOperationException.class,
        () -> floor.getWeapons());

      assertThrows(UnsupportedOperationException.class,
        () -> floor.getGolds());

      assertThrows(UnsupportedOperationException.class,
        () -> floor.getExits());

      assertThrows(UnsupportedOperationException.class,
        () -> floor.getRooms());

      assertThrows(UnsupportedOperationException.class,
        () -> floor.isBlocking(new ImmutablePoint(1, 2)));

      assertThrows(UnsupportedOperationException.class,
        () -> floor.remove(new ImmutablePoint(0, 5)));

      assertThrows(UnsupportedOperationException.class,
        () -> floor.move(new ImmutablePoint(3, 2), new ImmutablePoint(3, 4)));
    });
  }
}
