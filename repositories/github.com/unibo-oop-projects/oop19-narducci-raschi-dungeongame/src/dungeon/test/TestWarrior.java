package dungeon.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import dungeon.character.CharacterImpl;
import dungeon.character.player.Player;
import dungeon.character.player.Warrior;
import dungeon.character.stats.StatsImpl;

//You do not silence checkstyle like this. MagicNumber OFF
public final class TestWarrior {

  @Test
  public void testNewExceptions() {
    assertThrows(NullPointerException.class,
      () -> new Warrior(null));
  }

  @Test
  public void testException() {
    Player player = new Warrior(new CharacterImpl(
        new StatsImpl(3, 5, 8), "a", "hello"));
    assertThrows(IllegalArgumentException.class,
      () -> player.setExp(-1));

    assertThrows(NullPointerException.class,
      () -> player.setFloor(null));

    assertThrows(NullPointerException.class,
      () -> player.setPoint(null));

    /*assertThrows(NullPointerException.class,
      () -> player.setWeapon(null));*/
  }
}
