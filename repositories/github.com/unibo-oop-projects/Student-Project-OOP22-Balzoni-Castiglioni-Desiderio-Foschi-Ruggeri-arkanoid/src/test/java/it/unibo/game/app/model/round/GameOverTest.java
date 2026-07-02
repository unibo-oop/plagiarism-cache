package it.unibo.game.app.model.round;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import it.unibo.game.app.api.Level;
import it.unibo.game.app.model.levels.FirstLevel;

/**
 * method to test if isRoundFinished is working.
 */
public class GameOverTest {
  @Test
  void testIsRoundFinished() throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Level level = new FirstLevel();
    level.setFirstRound();
    GameOver gameover = new GameOver(level.getRound());
    Method method = GameOver.class.getDeclaredMethod("isRoundFinished");

    while (!level.getRound().getBrick().isEmpty()) {
      level.getRound().getBrick().remove(0);
    }
    assertEquals(0, level.getRound().getBrick().size());
    assertTrue((boolean) method.invoke(gameover));
  }
}
