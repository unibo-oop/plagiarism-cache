package dungeon.test;

import dungeon.Cell;
import dungeon.item.ImmutableItem;
import dungeon.item.Item;
import dungeon.template.Template;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// You do not silence checkstyle like this. MagicNumber OFF
public final class TestItem {

  @Test
  public void testNewExceptions() {
    assertThrows(NullPointerException.class,
      () -> new ImmutableItem(null, "a", true, Cell.WALL, 1));

    assertThrows(NullPointerException.class,
      () -> new ImmutableItem("item", null, false, Cell.DOOR, 56));

    assertThrows(NullPointerException.class,
      () -> new ImmutableItem("yes", "?", false, null, 23));

    assertThrows(IllegalArgumentException.class,
      () -> new ImmutableItem("mmh", "ok", true, Cell.EXIT, 395));
  }

  @Test
  public void testItem() {
    final String name = "a name";
    final String description = "asd";
    final boolean blocking = true;
    final Cell cell = Cell.GOLD;
    final Item item = new ImmutableItem(name, description, blocking, cell, 0);

    assertEquals(item.getName(), name);
    assertEquals(item.getDescription(), description);
    assertEquals(item.isBlocking(), blocking);
    assertEquals(item.getCell(), cell);
  }

  @Test
  public void testTemplate() {
    final int frequency = 76;
    final Template<Item> template = new ImmutableItem(
      "let's", "test", false, Cell.SWORD, frequency);

    assertEquals(template.getFrequency(), frequency);
    assertNotEquals(
      template.getInstance(Optional.empty()),
      template.getInstance(Optional.empty()));
  }
}
