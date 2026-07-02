package dungeon.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import dungeon.character.CharacterImpl;
import dungeon.character.stats.Stats;
import dungeon.character.stats.StatsImpl;

//You do not silence checkstyle like this. MagicNumber OFF
public class TestCharacter {

  @Test
  public void testNewExceptions() {
    assertThrows(NullPointerException.class,
      () -> new CharacterImpl(null, "a", "v"));

    assertThrows(NullPointerException.class,
      () -> new CharacterImpl(new StatsImpl(5, 8, 8), null, "v"));

    assertThrows(NullPointerException.class,
      () -> new CharacterImpl(new StatsImpl(5, 8, 8), "a", null));
  }
  @Test
  public void testItem() {
    final String name = "a name";
    final String description = "asd";
    final Stats stats = new StatsImpl(5, 6, 7);
    final CharacterImpl breed = new CharacterImpl(stats, name, description);

    assertEquals(breed.getName(), name);
    assertEquals(breed.getDescription(), description);
    assertEquals(breed.getStats(), stats);
  }

  @Test
  public void testCharacterMethod() {
    CharacterImpl breed = new CharacterImpl(new StatsImpl(5, 8, 8), "a", "v");
    assertThrows(IllegalArgumentException.class,
      () -> breed.setHealthRemained(-5));
    assertEquals(breed.isDead(), false);
    breed.setHealthRemained(80);
    assertEquals(breed.isDead(), true);

  }
}
