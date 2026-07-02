package dungeon.test;

import dungeon.attack.Attack.Result;
import dungeon.attack.Attack;
import dungeon.attack.ImmutableAttack;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

// You do not silence checkstyle like this. MagicNumber OFF
public final class TestAttack {

  @Test
  public void testNewInvalid() {
    final Attack attack = new ImmutableAttack(
      Result.INVALID, -1, "monkey", "banana");

    assertEquals(attack.getResult(), Result.INVALID);
    assertEquals(attack.getDamage(), Optional.empty());
    assertNotNull(attack.getDescription());
  }

  @Test
  public void testNewNoAmmo() {
    final Attack attack = new ImmutableAttack(
      Result.NO_AMMO, 3082, "flowerpot", "bazooka");

    assertEquals(attack.getResult(), Result.NO_AMMO);
    assertEquals(attack.getDamage(), Optional.empty());
    assertNotNull(attack.getDescription());
  }

  @Test
  public void testNewHit() {
    final Attack attack = new ImmutableAttack(
      Result.HIT, 19, "warrior", "sarissa");

    assertEquals(attack.getResult(), Result.HIT);
    assertEquals(attack.getDamage(), Optional.of(19));
    assertNotNull(attack.getDescription());
  }

  @Test
  public void testNewMiss() {
    final Attack attack = new ImmutableAttack(
      Result.MISS, -1098, "cheeseburger", "mayonnaise");

    assertEquals(attack.getResult(), Result.MISS);
    assertEquals(attack.getDamage(), Optional.empty());
    assertNotNull(attack.getDescription());
  }

  @Test
  public void testNewExceptions() {
    assertThrows(NullPointerException.class,
      () -> new ImmutableAttack(null, 3, "hi", "bye"));

    assertThrows(NullPointerException.class,
      () -> new ImmutableAttack(Result.HIT, -10, null, "hi again"));

    assertThrows(NullPointerException.class,
      () -> new ImmutableAttack(Result.INVALID, 0, "nope", null));
  }
}
