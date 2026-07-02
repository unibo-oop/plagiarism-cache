package dungeon.test;

import dungeon.attack.Attack.Result;
import dungeon.attack.Attack;
import dungeon.point.ImmutablePoint;
import dungeon.point.Point;
import dungeon.template.Template;
import dungeon.weapon.Bow;
import dungeon.weapon.Weapon;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// You do not silence checkstyle like this. MagicNumber OFF
public final class TestBow {

  @Test
  public void testNewExceptions() {
    assertThrows(NullPointerException.class,
      () -> new Bow(null, "maybe", 10, 53, 2, 1));

    assertThrows(NullPointerException.class,
      () -> new Bow("yes", null, 6, 91, 9, 3));

    assertThrows(IllegalArgumentException.class,
      () -> new Bow("no", "awesome", -5, 13, 4, 3));

    assertThrows(IllegalArgumentException.class,
      () -> new Bow("quality", "(tm)", 2, 101, 3, 7));

    assertThrows(IllegalArgumentException.class,
      () -> new Bow("hjkl", "neatvi", 11, 87, -10, 4));

    assertThrows(IllegalArgumentException.class,
      () -> new Bow("best", "editor", 138, 99, 1239, -12));
  }

  @Test
  public void testTemplate() {
    final Template<Weapon> template = new Bow(
      "vortex", "quickscope", 99, 98, 75, 10);

    assertEquals(template.getFrequency(), 98);
    assertNotEquals(
      template.getInstance(Optional.empty()),
      template.getInstance(Optional.empty()));
  }

  @Test
  public void testAmmo() {
    final Weapon bow = new Bow(
      "devastator", "best weapon", 1000000, 1, 29732, 2);

    assertEquals(bow.getAmmo(), Optional.of(2));

    final String striker = "doomguy";
    final Point from = new ImmutablePoint(0, 0);
    final Point to = new ImmutablePoint(-1, 9);
    final Attack first = bow.hit(striker, from, to);

    assertEquals(first.getResult(), Result.HIT);
    assertEquals(bow.getAmmo(), Optional.of(1));

    final Attack second = bow.hit(striker, from, to);

    assertEquals(second.getResult(), Result.HIT);
    assertEquals(bow.getAmmo(), Optional.of(0));

    final Attack last = bow.hit(striker, from, to);

    assertEquals(last.getResult(), Result.NO_AMMO);
    assertEquals(bow.getAmmo(), Optional.of(0));

  }

  @Test
  public void testExtraDamage() {
    final Weapon bow = new Bow("weak", "weapon", 1, 100, 2, 10);
    final Point from = new ImmutablePoint(0, 0);
    final Point to = new ImmutablePoint(1, 1);

    bow.setExtraDamage(16);
    assertEquals(bow.hit("me", from, to).getDamage(), Optional.of(18));
    assertEquals(bow.hit("you", from, to).getDamage(), Optional.of(18));
  }

  @Test
  public void testHitExceptions() {
    assertThrows(NullPointerException.class,
      () -> new Bow("new", "this", 2348, 12, 14, 4)
        .hit(null, new ImmutablePoint(9, 7), new ImmutablePoint(4, 6)));

    assertThrows(NullPointerException.class,
      () -> new Bow("old", "that", 2345, 96, 21, 5)
        .hit("yes him", null, new ImmutablePoint(8, 1)));

    assertThrows(NullPointerException.class,
      () -> new Bow("last", "i hope", 357, 46, 15, 8)
        .hit("maybe his", new ImmutablePoint(7, 5), null));
  }

  @Test
  public void testHit() {
    final Weapon bow = new Bow("no", "u", 2, 100, 9, 3);
    final Point from = new ImmutablePoint(2, 3);

    assertTrue(Result.HIT.equals(
      bow.hit("ok", from, new ImmutablePoint(4, 4)).getResult()));
    assertTrue(Result.INVALID.equals(
      bow.hit("nopz", from, new ImmutablePoint(2, 6)).getResult()));
  }
}
