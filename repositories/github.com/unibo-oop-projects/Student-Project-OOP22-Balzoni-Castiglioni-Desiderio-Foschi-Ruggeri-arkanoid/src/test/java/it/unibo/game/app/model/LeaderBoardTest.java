package it.unibo.game.app.model;

import org.junit.jupiter.api.Test;

import it.unibo.game.app.model.leaderb.LeaderBoard;
import it.unibo.game.app.model.leaderb.LeaderBoardImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.UUID;

/**
 * class to test if the saving and loading of the points effected by the player
 * take place correctly.
 */
public class LeaderBoardTest {
  private static final int NUM = 10;
  private static final int VAL = 6;
  private static final int TOT = 38;

  @Test
  void leaderBoardTest() throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    LeaderBoard l = new LeaderBoardImpl();
    String name;
    String pass;
    while (true) {
      name = getString();
      pass = getString();
      if (l.getPoints(name, pass).isEmpty()) {
        break;
      }
    }
    l.updatePoints(name, pass, NUM, 1);
    assertEquals(l.getPoints(name, pass), Optional.of(NUM));
    l.updatePoints(name, pass, NUM * 2, 1);
    assertEquals(l.getPoints(name, pass), Optional.of(NUM * 2));
    l.updatePoints(name, pass, NUM * 4, 2);
    assertEquals(l.getPoints(name, pass), Optional.of(NUM * VAL));
    l.updatePoints(name, pass, VAL, 2);
    assertEquals(l.getPoints(name, pass), Optional.of(NUM * 2 + VAL));
    l.updatePoints(name, pass, NUM * 3 + 2, 1);
    assertEquals(l.getPoints(name, pass), Optional.of(TOT));
  }

  private String getString() {
    UUID rand = UUID.randomUUID();
    return rand.toString().replaceAll("_", "").substring(0, NUM / 2);
  }
}
