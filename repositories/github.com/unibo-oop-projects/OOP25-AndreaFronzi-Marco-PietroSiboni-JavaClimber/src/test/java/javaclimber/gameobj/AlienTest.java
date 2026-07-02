package javaclimber.gameobj;

import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.impl.AlienImpl;
import it.unibo.model.physics.api.Vector2d;
import it.unibo.model.physics.impl.Vector2dImpl;
import it.unibo.model.shop.impl.ActiveUpgradesImpl;
import it.unibo.model.shop.impl.InventoryImpl;
import it.unibo.model.shop.impl.ShopItemFactoryImpl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for the {@link AlienImpl} game object.
 */
class AlienTest {

  private static final double EPSILON = 0.001;
  private static final double X = 10;
  private static final double Y = 20;
  private static final double SPEED_X = 0;
  private static final double SPEED_Y = 0;
  private static final double NEW_X = 30;
  private static final double NEW_Y = 40;
  private static final double WIDTH = 50;
  private static final double HEIGHT = 50;

  /**
   * Tests the {@link Alien#getPosX()} method.
   */
  @Test
  void testGetPosX() {
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT,
        new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
    assertEquals(X, alien.getPosX(), EPSILON);
  }

  /**
   * Tests the {@link Alien#getPosY()} method.
   */
  @Test
  void testGetPosY() {
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT,
        new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
    assertEquals(Y, alien.getPosY(), EPSILON);
  }

  /**
   * Tests the {@link Alien#getWidth()} method.
   */
  @Test
  void testGetWidth() {
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT,
        new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
    assertEquals(WIDTH, alien.getWidth(), EPSILON);
  }

  /**
   * Tests the {@link Alien#getHeight()} method.
   */
  @Test
  void testGetHeight() {
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT,
        new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
    assertEquals(HEIGHT, alien.getHeight(), EPSILON);
  }

  /**
   * Tests the {@link Alien#getSpeedX()} method.
   */
  @Test
  void testGetSpeedX() {
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT,
        new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
    assertEquals(SPEED_X, alien.getSpeedX(), EPSILON);
  }

  /**
   * Tests the {@link Alien#getSpeedY()} method.
   */
  @Test
  void testGetSpeedY() {
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT,
        new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
    assertEquals(SPEED_Y, alien.getSpeedY(), EPSILON);
  }

  /**
   * Tests the {@link Alien#setPosition(Vector2d)} method.
   */
  @Test
  void testSetPosition() {
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT,
        new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
    alien.setPosition(new Vector2dImpl(NEW_X, NEW_Y));
    assertEquals(NEW_X, alien.getPosX(), EPSILON);
    assertEquals(NEW_Y, alien.getPosY(), EPSILON);
  }

  /**
   * Tests the {@link Alien#setSpeed(Vector2d)} method.
   */
  @Test
  void testSetSpeed() {
    final Alien alien = new AlienImpl(new Vector2dImpl(X, Y), new Vector2dImpl(SPEED_X, SPEED_Y), WIDTH, HEIGHT,
        new ActiveUpgradesImpl(new InventoryImpl(new ShopItemFactoryImpl())));
    alien.setSpeed(new Vector2dImpl(NEW_X, NEW_Y));
    assertEquals(NEW_X, alien.getSpeedX(), EPSILON);
    assertEquals(NEW_Y, alien.getSpeedY(), EPSILON);
  }

}
