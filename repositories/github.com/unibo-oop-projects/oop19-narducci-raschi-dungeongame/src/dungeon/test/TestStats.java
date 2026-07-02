package dungeon.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import dungeon.character.stats.StatsImpl;

//You do not silence checkstyle like this. MagicNumber OFF
public class TestStats {

  @Test
  public void testNewExceptions() {
    assertThrows(IllegalArgumentException.class,
      () -> new StatsImpl(-2, 5, 8));

    assertThrows(IllegalArgumentException.class,
      () -> new StatsImpl(2, -5, 8));

    assertThrows(IllegalArgumentException.class,
      () -> new StatsImpl(2, 5, -8));
  }
}
