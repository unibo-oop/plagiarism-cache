package it.unibo.game.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.game.app.api.Level;
import it.unibo.game.app.api.Score;
import it.unibo.game.app.api.Speed;
import it.unibo.game.app.model.dynamic.Move;
import it.unibo.game.app.model.dynamic.SpeedImpl;
import it.unibo.game.app.model.levels.FirstLevel;
import it.unibo.game.app.model.levels.SecondLevel;
import it.unibo.game.app.model.levels.ThirdLevel;
import it.unibo.game.Pair;
import it.unibo.game.app.api.Brick;
import it.unibo.game.app.api.BrickType;
import it.unibo.game.app.api.Corner;

/**
 * method to test all surprise methods.
 */
public class SurpriseTest {

  private static final double SPEED_X = 0.5;
  private static final double SPEED_Y = 0.2;
  private static final int NUM = 20;
  private static final int TIME_1 = 10000;
  private static final int TIME_2 = 10500;

  @Test
  void testExtraLife() throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Level level = new FirstLevel();
    level.setFirstRound();
    Surprise surprise = new Surprise(level);

    Method method = Surprise.class.getDeclaredMethod("extraLife");
    method.setAccessible(true);

    int oldLives = level.getLife();
    method.invoke(surprise);
    int newLives = level.getLife();
    assertEquals(oldLives + 1, newLives);
  }

  @Test
  void testChangeHard()
      throws NoSuchMethodException, SecurityException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException, InterruptedException {
    Level level = new SecondLevel();
    level.setFirstRound();
    Surprise surprise = new Surprise(level);
    Timer timer = new Timer();

    Method method = Surprise.class.getDeclaredMethod("changeHard");
    method.setAccessible(true);

    List<Integer> oldHardIndex = new ArrayList<>();
    for (var brick : level.getRound().getBrick()) {
      if (brick.getRes().get() == 2) {
        oldHardIndex.add(level.getRound().getBrick().indexOf(brick));
      }
    }
    method.invoke(surprise);
    for (var i : oldHardIndex) {
      assertEquals(1, level.getRound().getBrick().get(i).getRes().get());
    }
    TimerTask task = new TimerTask() {

      @Override
      public void run() {
        for (var i : oldHardIndex) {
          assertEquals(2, level.getRound().getBrick().get(i).getRes().get());
        }
      }
    };
    timer.schedule(task, TIME_1);

  }

  @Test
  void testExplosiveBomb() throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Level level = new FirstLevel();
    level.setFirstRound();
    Surprise surprise = new Surprise(level);
    Method method = Surprise.class.getDeclaredMethod("explosiveBomb");
    method.setAccessible(true);
    Map<Brick, Integer> mappa = new HashMap<>();
    int oldSize = level.getRound().getBrick().size();

    for (Brick brick : level.getRound().getBrick()) {
      if (brick.getType().equals(BrickType.SURPRISE)) {
        mappa.put(brick, level.getRound().getBrick().indexOf(brick));
      }
    }
    for (Map.Entry<Brick, Integer> element : mappa.entrySet()) {
      level.setLastSurpriseBrick(element.getKey(), element.getValue());
      method.invoke(surprise);
      int newSize = level.getRound().getBrick().size();
      /*
       * In the first round of FirstLevel, the number of surprises is 2. So the bomb
       * can break: 0 bricks if it is not close to any brick (newSize will not
       * change); 1 brick if only one of the bombs is close only to one brick; 2
       * bricks if the bomb is between 2 bricks or both bombs are close to only a
       * brick; 3 bricks if one bomb is between 2 bricks and the other has one close;
       * 4 bricks if both bombs are between 2 bricks
       */
      assertTrue(oldSize >= newSize && oldSize <= newSize + 4);
    }
  }

  @Test
  void initTestAddHardRow() throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Level l1 = new FirstLevel();
    l1.setFirstRound();
    Level l2 = new SecondLevel();
    l2.setFirstRound();
    Level l3 = new ThirdLevel();
    l3.setFirstRound();
    testAddHardRow(l1);
    testAddHardRow(l2);
    testAddHardRow(l3);
  }

  /**
   * method for testing adding a row of hard bricks.
   * 
   * @param l level
   * @throws NoSuchMethodException
   * @throws SecurityException
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException
   */
  void testAddHardRow(final Level l) throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    List<Brick> reverse = new ArrayList<>();
    Surprise surprise = new Surprise(l);
    int count = 0;
    Method method = Surprise.class.getDeclaredMethod("addHardRow");
    method.setAccessible(true);

    reverse.addAll(l.getRound().getBrick());
    Collections.reverse(reverse);
    Double y = reverse.get(0).getPos().getY();
    for (Brick brick : reverse) {
      if (brick.getPos().getY().equals(y)) {
        ++count;
      }
    }
    int oldSize = l.getRound().getBrick().size();
    method.invoke(surprise);
    if (l.getId() == 2) {
      assertEquals(oldSize + count + 2, l.getRound().getBrick().size());
    } else {
      assertEquals(oldSize + count, l.getRound().getBrick().size());
    }
    assertEquals(
        l.getRound().getBrick().get(l.getRound().getBrick().size() - 1).getPos().getX(),
        reverse.get(0).getPos().getX());
  }

  @Test
  void testIncreaseBallSpeed() throws IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, NoSuchMethodException, SecurityException {
    Level level = new ThirdLevel();
    level.setFirstRound();
    Surprise surprise = new Surprise(level);
    Speed initial = new SpeedImpl(level.getRound().getBalls().get(0).getSpeed().getX(),
        level.getRound().getBalls().get(0).getSpeed().getY());
    int num = NUM;
    Method method = Surprise.class.getDeclaredMethod("increaseBallSpeed");
    method.setAccessible(true);
    for (int i = 0; i < num; i++) {
      method.invoke(surprise);
    }
    initial.sum(new SpeedImpl(SPEED_X * num, SPEED_Y * num));
    assertEquals(level.getRound().getBalls().get(0).getSpeed(), initial);
  }

  @Test
  void testDecreaseBallSpeed() throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Level level = new ThirdLevel();
    level.setFirstRound();
    Surprise surprise = new Surprise(level);
    Speed initial = new SpeedImpl(level.getRound().getBalls().get(0).getSpeed().getX(),
        level.getRound().getBalls().get(0).getSpeed().getY());
    int num = NUM;
    Method method = Surprise.class.getDeclaredMethod("decreaseBallSpeed");
    method.setAccessible(true);
    for (int i = 0; i < num; i++) {
      method.invoke(surprise);
    }
    initial.sum(new SpeedImpl(-SPEED_X * num, -SPEED_Y * num));
    assertEquals(level.getRound().getBalls().get(0).getSpeed(), initial);
  }

  @Test
  void testChangeObstacles() throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Level level = new ThirdLevel();
    level.setFirstRound();
    Surprise surprise = new Surprise(level);
    long numObstacles = level.getRound().getBrick().stream()
        .filter(x -> x.getType().equals(BrickType.OBSTACLE)).count();
    assertTrue(numObstacles > 0);
    Method method = Surprise.class.getDeclaredMethod("changeObstacles");
    method.setAccessible(true);
    method.invoke(surprise);
    assertEquals(level.getRound().getBrick().stream()
        .filter(x -> x.getType().equals(BrickType.OBSTACLE)).count(), 0);
  }

  @Test
  void testAddBalls() throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Level level = new ThirdLevel();
    level.setFirstRound();
    Surprise surprise = new Surprise(level);
    Move move = new Move(level);
    Method method = Surprise.class.getDeclaredMethod("addBalls");
    Method method2 = Move.class.getDeclaredMethod("update");
    method.setAccessible(true);
    method2.setAccessible(true);
    assertEquals(1, level.getRound().getBalls().size());
    method.invoke(surprise);
    method2.invoke(move);
    Boolean bool = level.getRound().getBalls().size() > 1;
    assertTrue(bool);

  }

  @Test
  void testIncreaseScore() throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Level level = new ThirdLevel();
    level.setFirstRound();
    Score score = new ScoreImpl();
    // int s = 0;
    Surprise surprise = new Surprise(level);
    /* controllo che il punteggio parta da 0 */
    assertEquals(0, score.getScore());
    score.increaseScore();
    /* controllo che lo score sia aumentato di 1 */
    assertEquals(1, score.getScore());
    Method method = Surprise.class.getDeclaredMethod("increaseScore");
    method.setAccessible(true);
    method.invoke(surprise);
    Timer timer = new Timer();

    TimerTask task = new TimerTask() {

      @Override
      public void run() {
        Integer s = score.getScore();
        score.increaseScore();
        assertEquals(s + 4, score.getScore());
      }
    };
    timer.schedule(task, TIME_1);

  }

  /***
   * 
   * @throws NoSuchMethodException
   * @throws SecurityException
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException test normal enlarge pad
   */
  @Test
  void testEnlargePad() throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Level l = new FirstLevel();
    l.setFirstRound();
    Surprise surprise = new Surprise(l);
    var pad = l.getRound().getPad();
    assertTrue(pad.getDimension().getWidth() == SizeCalculation.getPadDim().getWidth());
    assertTrue(pad.getDimension().getHeight() == SizeCalculation.getPadDim().getHeight());
    Method method = Surprise.class.getDeclaredMethod("enlargeSizePad");
    method.setAccessible(true);
    method.invoke(surprise);
    assertFalse(pad.getDimension().getWidth() == SizeCalculation.getPadDim().getWidth());
    Timer timer = new Timer();

    TimerTask task = new TimerTask() {

      @Override
      public void run() {
        assertTrue(
            pad.getDimension().getWidth() == SizeCalculation.getPadDim().getWidth());
      }
    };
    timer.schedule(task, TIME_2);

  }

  /**
   * 
   * @throws NoSuchMethodException
   * @throws SecurityException
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException test edgecase of enlarge pad
   */
  @Test
  void testEnlargePadHard() throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Level l = new FirstLevel();
    l.setFirstRound();
    Surprise surprise = new Surprise(l);
    var pad = l.getRound().getPad();
    pad.setPos(new Pair<Double, Double>(
        SizeCalculation.getWorldSize().getY() - pad.getDimension().getWidth(),
        l.getRound().getPad().getPos().getY()));

    assertTrue(pad.getDimension().getWidth() == SizeCalculation.getPadDim().getWidth());
    assertTrue(pad.getDimension().getHeight() == SizeCalculation.getPadDim().getHeight());

    var oldWpos = pad.getPos().getX();
    Method method = Surprise.class.getDeclaredMethod("enlargeSizePad");
    method.setAccessible(true);
    method.invoke(surprise);
    pad.setBoundingBox(new RectBoundingBox(pad));
    assertTrue(pad.getBoundingBox().getBox().get(Corner.RIGHT_DOWN)
        .getX() <= SizeCalculation.getWorldSize().getY());

    assertFalse(pad.getPos().getX().equals(oldWpos));
    assertFalse(pad.getPos().getX() > oldWpos);
    assertTrue(pad.getDimension().getWidth() > SizeCalculation.getPadDim().getWidth());
    Timer timer = new Timer();

    TimerTask task = new TimerTask() {

      @Override
      public void run() {
        assertTrue(
            pad.getDimension().getWidth() == SizeCalculation.getPadDim().getWidth());
      }
    };
    timer.schedule(task, TIME_2);
  }

  @Test
  void reducePadDimTest() throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Level l = new FirstLevel();
    l.setFirstRound();
    Surprise surprise = new Surprise(l);
    var pad = l.getRound().getPad();

    assertTrue(pad.getDimension().getWidth() == SizeCalculation.getPadDim().getWidth());
    assertTrue(pad.getDimension().getHeight() == SizeCalculation.getPadDim().getHeight());

    Method method = Surprise.class.getDeclaredMethod("reduceSizePad");
    method.setAccessible(true);
    method.invoke(surprise);

    assertTrue(pad.getDimension().getWidth() < SizeCalculation.getPadDim().getWidth());
    Timer timer = new Timer();

    TimerTask task = new TimerTask() {

      @Override
      public void run() {
        assertTrue(
            pad.getDimension().getWidth() == SizeCalculation.getPadDim().getWidth());
      }
    };
    timer.schedule(task, TIME_2);

  }

  @Test
  void delateRandomBrickTest() throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Level l = new FirstLevel();
    l.setFirstRound();
    Surprise surprise = new Surprise(l);
    var old = l.getRound().getBrick().size();

    Method method = Surprise.class.getDeclaredMethod("deleteRandomBricks");
    method.setAccessible(true);
    method.invoke(surprise);

    assertTrue(old > l.getRound().getBrick().size());

  }
}
