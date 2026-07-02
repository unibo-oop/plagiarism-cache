package dungeon.test;

import dungeon.gold.Gold;
import dungeon.gold.ImmutableGold;
import dungeon.template.Template;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// You do not silence checkstyle like this. MagicNumber OFF
public final class TestGold {

  @Test
  public void testNew() {
    final Template<Gold> template = new ImmutableGold(72, 10921);
    final Gold gold = template.getInstance(Optional.empty());

    assertNotEquals(template.getInstance(Optional.empty()), gold);
    assertEquals(template.getFrequency(), 72);
    assertEquals(gold.getAmount(), 10921);
  }

  @Test
  public void testNewExceptions() {
    assertThrows(IllegalArgumentException.class,
      () -> new ImmutableGold(23, -202));

    assertThrows(IllegalArgumentException.class,
      () -> new ImmutableGold(-10, 64));
  }
}
