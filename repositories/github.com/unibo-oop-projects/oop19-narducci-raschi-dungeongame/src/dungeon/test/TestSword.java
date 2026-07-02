package dungeon.test;

import dungeon.attack.Attack.Result;
import dungeon.point.ImmutablePoint;
import dungeon.point.Point;
import dungeon.template.Template;
import dungeon.weapon.Sword;
import dungeon.weapon.Weapon;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// You do not silence checkstyle like this. MagicNumber OFF
public final class TestSword {

  @Test
  public void testNewExceptions() {
    assertThrows(NullPointerException.class,
      () -> new Sword(null, "excalibur", 100000, 10, 100, 9, 23));

    assertThrows(NullPointerException.class,
      () -> new Sword("a sword", null, 230, 57, 65, -1, 2));

    assertThrows(IllegalArgumentException.class,
      () -> new Sword("negative", "yes", -19, 75, 1, 8, 8));

    assertThrows(IllegalArgumentException.class,
      () -> new Sword("cpu", "frequency", 9, 102, 8, 34, 76));

    assertThrows(IllegalArgumentException.class,
      () -> new Sword("how", "can hit?", 4903, 100, 254, 20, 30));

    assertThrows(IllegalArgumentException.class,
      () -> new Sword("quality", "stats", 437, 11, 66, 97, 10));
  }

  @Test
  public void testTemplate() {
    final Template<Weapon> template = new Sword(
      "best", "mine", 17, 56, 34, 90, 100);

    assertEquals(template.getFrequency(), 56);
    assertNotEquals(
      template.getInstance(Optional.empty()),
      template.getInstance(Optional.empty()));
  }

  @Test
  public void testAmmo() {
    assertEquals(new Sword("ammo", "?", 87, 45, 23, 10, 11).getAmmo(),
      Optional.empty());
  }

  @Test
  public void testExtraDamage() {
    final Weapon sword = new Sword("NULL", "ptr++", 6, 57, 100, 0, 2);
    final int damage = 4;
    final Point from = new ImmutablePoint(8, 1);
    final Point to = new ImmutablePoint(8, 2);

    sword.setExtraDamage(damage);
    assertTrue(sword.hit("diesel", from, to).getDamage().get() >= damage);
    assertTrue(sword.hit("gasoline", from, to).getDamage().get() >= damage);
  }

  @Test
  public void testHitExceptions() {
    assertThrows(NullPointerException.class,
      () -> new Sword("no", "name", 43, 78, 57, 9, 10).hit(
        null, new ImmutablePoint(65, 34), new ImmutablePoint(46, 28)));

    assertThrows(NullPointerException.class,
      () -> new Sword("no", "from", 573, 46, 67, 76, 90).hit(
        "?", null, new ImmutablePoint(37, 26)));

    assertThrows(NullPointerException.class,
      () -> new Sword("not", "last", 234, 47, 88, 23, 55).hit(
        "!", new ImmutablePoint(73, 26), null));
  }

  @Test
  public void testHit() {
    final Weapon sword = new Sword("gonna", "hit", 3, 50, 50, 0, 1);
    final Point from = new ImmutablePoint(8, 1);
    final Point to = new ImmutablePoint(8, 2);
    final List<Result> list = new ArrayList<>();

    list.add(Result.HIT);
    list.add(Result.MISS);

    for (int index = 0; index < 16; index++) {
      assertTrue(list.contains(sword.hit("apple", from, to).getResult()));
    }

    assertTrue(list.contains(
      sword.hit("in range", from, new ImmutablePoint(11, 4)).getResult()));

    assertTrue(Result.INVALID.equals(
      sword.hit("not in range", from, new ImmutablePoint(7, 5)).getResult()));
  }
}
