package it.unibo.game.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.game.app.api.Brick;
import it.unibo.game.app.api.BrickType;
import it.unibo.game.app.api.Level;
import it.unibo.game.app.model.levels.SecondLevel;
import it.unibo.game.app.model.levels.ThirdLevel;

/**
 * class that contains tests to test obstacles and hard bricks.
 */
public class BrickTest {

  @Test
  void testObstacle() {
    Level l = new ThirdLevel();
    l.setFirstRound();
    int maxRes = l.getRound().getBrick().stream().filter(x -> x.getRes().isPresent())
        .mapToInt(x -> x.getRes().get()).max().getAsInt();
    long obstacles = l.getRound().getBrick().stream()
        .filter(x -> x.getType().equals(BrickType.OBSTACLE)).count();
    int i = 0;
    Iterator<Brick> it;
    while (i < maxRes) {
      it = l.getRound().getBrick().iterator();
      while (it.hasNext()) {
        Brick b = it.next();
        b.hit();
        if (b.isDestroyed()) {
          it.remove();
        }
      }
      i++;
    }
    assertTrue(l.getRound().getBrick().size() == (int) obstacles && l.getRound()
        .getBrick().stream().allMatch(x -> x.getType().equals(BrickType.OBSTACLE)));

  }

  @Test
  void testHardBrick() {
    Level l = new SecondLevel();
    l.setFirstRound();
    final List<Brick> hardBrick = l.getRound().getBrick().stream()
        .filter(b -> b.getRes().get() == 2).toList();
    int oldSize = l.getRound().getBrick().size();

    for (var b : hardBrick) {
      l.getRound().remove(l.getRound().getBrick().indexOf(b));
      assertEquals(1,
          l.getRound().getBrick().get(l.getRound().getBrick().indexOf(b)).getRes().get());
      assertEquals(oldSize, l.getRound().getBrick().size());
    }
    for (var b : hardBrick) {
      l.getRound().remove(l.getRound().getBrick().indexOf(b));
      assertEquals(-1, l.getRound().getBrick().indexOf(b));
    }
    assertEquals(oldSize - hardBrick.size(), l.getRound().getBrick().size());
  }
}
