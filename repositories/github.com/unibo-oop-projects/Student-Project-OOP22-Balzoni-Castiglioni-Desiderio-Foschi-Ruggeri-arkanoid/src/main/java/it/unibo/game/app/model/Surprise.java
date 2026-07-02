package it.unibo.game.app.model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.game.Pair;
import it.unibo.game.app.api.Brick;
import it.unibo.game.app.api.BrickType;
import it.unibo.game.app.api.Level;
import it.unibo.game.app.api.MovingObject;
import it.unibo.game.app.model.ball.Ball;
import it.unibo.game.app.model.brick.NormalBrick;
import it.unibo.game.app.model.dynamic.SpeedImpl;

/**
 * class that contains methods for bonuses or maluses.
 */
public class Surprise {

  private static final int NUM_TOT_SURSPRISE = 12;
  private static final int EXTRA_LIFE = 1;
  private static final int EXPLOSIVE_BOMB = 2;
  private static final int DELETE_RANDOM_BRICKS = 3;
  private static final int REDUCE_SIZE_PAD = 4;
  private static final int ENLARGE_SIZE_PAD = 5;
  private static final int INCREASE_BALL_SPEED = 6;
  private static final int DECREASE_BALL_SPEED = 7;
  private static final int CHANGE_OBSTACLES = 8;
  private static final int INCREASE_SCORE = 9;
  private static final int ADD_BALLS = 10;
  private static final int CHANGE_ROW = 11;
  private static final int CHANGE_HARD = 12;
  private static final int NUM_BALLS = 1;
  private static final int PERCENTUAL = 50;
  private static final int BONUS_DURATION = 10000;
  // private static final int FIX_START_Y = 5;

  private static final double SPEED_X = 0.5;
  private static final double SPEED_Y = 0.2;
  private static final double LENGTH = 0.1;

  private Map<Integer, Runnable> map = new HashMap<>();
  private Random random = new Random();
  private Level level;
  private boolean padModified = false;

  /**
   * costructor of this class.
   * 
   * @param level current level
   */
  
  public Surprise(final Level level) {
    this.level = level;
  }

  /**
   * method that adds a life. Simone Ruggeri
   */
  private void extraLife() {
    this.level.setSurpriseString("extraLife");
    System.out.println("extraLife");
    this.level.increaseLife();

  }

  /**
   * explosive bomb method that eliminates the two blocks next to it. Simone
   * Ruggeri
   */
  private void explosiveBomb() {
    this.level.setSurpriseString("explosive Bomb");
    System.out.println("explosiveBomb");
    Brick lastBrick = this.level.getLastSurpriseBrick();
    int index = this.level.getIndexLastSurprise();

    if (this.isIndexPositive(index - 1) && this.isThereLeftBrick(index - 1, lastBrick)
        && !this.isObstacle(index - 1) && !this.isSursprise(index - 1)) {
      this.deleteBrick(index - 1);
      if (this.isIndexNotTheLast(index - 1)
          && this.isThereRightBrick(index - 1, lastBrick) && !this.isObstacle(index - 1)
          && !this.isSursprise(index - 1)) {
        this.deleteBrick(index - 1);
      }
    }
    if (this.isIndexPositive(index - 1) && this.isIndexNotTheLast(index)
        && this.isThereRightBrick(index, lastBrick) && !this.isObstacle(index - 1)
        && !this.isSursprise(index - 1)) {
      this.deleteBrick(index);
    }

  }

  /**
   * method that checks if there is a brick to the left of the bomb.
   * 
   * @param index
   * @param lastBrick
   * @return true if there is a brick on the left
   */
  private boolean isThereLeftBrick(final int index, final Brick lastBrick) {
    return (this.level.getRound().getBrick().get(index).getPos().getY() == lastBrick
        .getPos().getY()
        && (lastBrick.getPos().getX() - lastBrick.getBrickW())
            - this.level.getRound().getBrick().get(index).getPos().getX() < LENGTH);
  }

  /**
   * method that checks if there is a brick to the right of the bomb.
   * 
   * @param index
   * @param lastBrick
   * @return true if there is a brick on the right
   */
  private boolean isThereRightBrick(final int index, final Brick lastBrick) {
    return (this.level.getRound().getBrick().get(index).getPos().getY() == lastBrick
        .getPos().getY()
        && (this.level.getRound().getBrick().get(index).getPos().getX()
            - lastBrick.getBrickW()) - lastBrick.getPos().getX() < LENGTH);
  }

  /**
   * method that checks if the brick is an obstacle.
   * 
   * @param index
   * @return true if it is an obstacle.
   */
  private boolean isObstacle(final int index) {
    return this.level.getRound().getBrick().get(index).getType()
        .equals(BrickType.OBSTACLE);
  }

  /**
   * method that checks if the brick is a Surprise.
   * 
   * @param index
   * @return true if it is a surprise.
   */
  private boolean isSursprise(final int index) {
    return this.level.getRound().getBrick().get(index).getType()
        .equals(BrickType.SURPRISE);
  }

  /**
   * method that removes a brick.
   * 
   * @param index
   */
  private void deleteBrick(final int index) {
    this.level.getRound().getBrick().remove(index);
  }

  /**
   * method that checks if the index is positive.
   * 
   * @param index
   * @return true if index is greater equals than zero
   */
  private boolean isIndexPositive(final int index) {
    return index >= 0;
  }

  /**
   * method that checks if the index is less than the size of the list.
   * 
   * @param index
   * @return true if index minor than the size of the list
   */
  private boolean isIndexNotTheLast(final int index) {
    return index < this.level.getRound().getBrick().size();
  }

  /**
   * method that randomly deletes bricks. Edoardo Desiderio
   */
  private void deleteRandomBricks() {
    try {
      var i = random.nextInt(0, Integer.max(2, level.getRound().getBrick().size() / 2));
      var label = String.format("delete Random Bricks: %d", i);
      this.level.setSurpriseString(label);
      System.out.println("-------------------------------------------------------------");
      while (i > 0) {
        this.deleteBrick(random.nextInt(0, level.getRound().getBrick().size()));
        // System.out.println("bricks to delate: " + i);
        i--;
      }
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  /**
   * method that reduces the size of the pad for a certain time. Edoardo Desiderio
   */
  private void reduceSizePad() {
    if (!padModified) {
      this.level.setSurpriseString("reduce Size Pad");
      System.out.println("reduceSizePad");
      padModified = true;
      var pad = level.getRound().getPad();
      pad.getDimension().setWidth((pad.getDimension().getWidth() * this.delta()));

      Timer tm = new Timer();
      TimerTask tmTask = new TimerTask() {

        @Override
        public void run() {
          pad.setDimension(SizeCalculation.getPadDim());
          padModified = false;
        }
      };
      tm.schedule(tmTask, BONUS_DURATION);
    }

  }

  private double delta() {
    return (100d - PERCENTUAL) / 100d;
  }

  /**
   * 
   * method that enlarge the size of the pad for a certain time. Edoardo Desiderio
   */
  private void enlargeSizePad() {
    if (!padModified) {
      this.level.setSurpriseString("enlarge Size Pad");
      System.out.println("enlargeSizePad");
      padModified = true;
      var pad = level.getRound().getPad();
      pad.getDimension().setWidth(pad.getDimension().getWidth() * (this.delta() + 1));
      var rightBorder = pad.getPos().getX() + pad.getDimension().getWidth();
      if (rightBorder > SizeCalculation.getWorldSize().getY()) {
        pad.setPos(new Pair<Double, Double>(
            pad.getPos().getX()
                - ((rightBorder - SizeCalculation.getWorldSize().getY() + 0.5)),
            pad.getPos().getY()));
      }

      Timer tm = new Timer();
      TimerTask tmTask = new TimerTask() {

        @Override
        public void run() {
          padModified = false;
          pad.setDimension(SizeCalculation.getPadDim());
        }
      };
      tm.schedule(tmTask, BONUS_DURATION);
    }
  }

  /**
   * method that increases the speed of the ball. Virginia Foschi
   */
  private void increaseBallSpeed() {
    this.level.setSurpriseString("increase Ball Speed");
    System.out.println("increaseBallSpeed");
    this.level.getRound().getBalls()
        .forEach(x -> x.getSpeed().sum(new SpeedImpl(SPEED_X, SPEED_Y)));

  }

  /**
   * method that decrease the speed of the ball. Virginia Foschi
   */
  private void decreaseBallSpeed() {
    this.level.setSurpriseString("decrease Ball Speed");
    System.out.println("decreaseBallSpeed");
    this.level.getRound().getBalls()
        .forEach(x -> x.getSpeed().sum(new SpeedImpl(-SPEED_X, -SPEED_Y)));

  }

  /**
   * method that replaces obstacles with normal bricks. Virginia Foschi
   */
  private void changeObstacles() {
    this.level.setSurpriseString("change Obstacles");
    System.out.println("changeObstacles");
    this.level.getRound().getBrick().replaceAll(x -> {
      if (x.getType().equals(BrickType.OBSTACLE)) {
        Brick brick = new NormalBrick(BrickType.NORMAL,
            new DimensionImpl(x.getBrickH(), x.getBrickW()), x.getPos(), 1);
        return brick;
      } else {
        return x;
      }
    });
  }

  /**
   * method that increases the score for each individual brick for a certain time.
   * Margherita Balzoni
   */
  private void increaseScore() {
    this.level.setSurpriseString("increase Score");
    System.out.println("increaseScore");
    Timer time = new Timer();
    TimerTask task = new TimerTask() {

      @Override
      public void run() {
        level.getScore().enableBonus(true);
      }

    };
    time.schedule(task, BONUS_DURATION);
    this.level.getScore().enableBonus(false);
  }

  /**
   * method that increases the number of balls in play. Margherita Balzoni
   */
  private void addBalls() {
    this.level.setSurpriseString("add Ball");
    System.out.println("addBalls");
    for (int i = 0; i < NUM_BALLS; i++) {
      MovingObject ball = new Ball(SizeCalculation.getBallDim());
      this.level.getRound().getExtraBalls().add(ball);
    }
  }

  /**
   * method that adds a row of hard bricks. Chiara Castiglioni
   */
  private void addHardRow() {
    this.level.setSurpriseString("add Hard Row");
    System.out.println("addHardRow");
    double lastY = this.level.getRound().getBrick()
        .get(this.level.getRound().getBrick().size() - 1).getPos().getY();
    double brickH = this.level.getRound().getBrick()
        .get(this.level.getRound().getBrick().size() - 1).getBrickH();
    double brickW = this.level.getRound().getBrick()
        .get(this.level.getRound().getBrick().size() - 1).getBrickW();
    double start = (brickW / 2);
    double stop = (SizeCalculation.getWorldSize().getY()) - (3 * (brickW / 2));
    for (double x = start; x <= stop; x = x + brickW) {
      NormalBrick brick = new NormalBrick(BrickType.NORMAL,
          new DimensionImpl(brickH, brickW), new Pair<>(x, lastY + brickH), 2);
      this.level.getRound().getBrick().add(brick);
    }
  }

  /**
   * method that replaces hard bricks with normal ones for a certain time. Chiara
   * Castiglioni
   */
  private void changeHard() {
    this.level.setSurpriseString("change Hard");
    System.out.println("changeHard");
    List<Brick> hard = new ArrayList<>();
    Timer timer = new Timer();
    for (Brick brick : this.level.getRound().getBrick()) {
      if (brick.getRes().isPresent() && brick.getRes().get() == 2) {

        hard.add(brick);
        brick.decreaseRes(brick.getRes().get());
      }
    }
    TimerTask task = new TimerTask() {

      @Override
      public void run() {
        for (Brick brick : hard) {
          int indx = level.getRound().getBrick().indexOf(brick);
          if (indx != -1) {
            level.getRound().getBrick().get(indx).increaseRes(brick.getRes().get());
          }
        }
      }
    };
    timer.schedule(task, BONUS_DURATION);
  }

  /**
   * method that sets up the map by numbering the methods.
   */
  public void setMap() {
    this.map = new HashMap<>(Map.ofEntries(Map.entry(EXTRA_LIFE, () -> this.extraLife()),
        Map.entry(EXPLOSIVE_BOMB, () -> this.explosiveBomb()),
        Map.entry(DELETE_RANDOM_BRICKS, () -> this.deleteRandomBricks()),
        Map.entry(REDUCE_SIZE_PAD, () -> this.reduceSizePad()),
        Map.entry(ENLARGE_SIZE_PAD, () -> this.enlargeSizePad()),
        Map.entry(INCREASE_BALL_SPEED, () -> this.increaseBallSpeed()),
        Map.entry(DECREASE_BALL_SPEED, () -> this.decreaseBallSpeed()),
        Map.entry(CHANGE_OBSTACLES, () -> this.changeObstacles()),
        Map.entry(INCREASE_SCORE, () -> this.increaseScore()),
        Map.entry(ADD_BALLS, () -> this.addBalls()),
        Map.entry(CHANGE_ROW, () -> this.addHardRow()),
        Map.entry(CHANGE_HARD, () -> this.changeHard())));
  }

  /**
   * method that randomly chooses which bonus or malus to invoke.
   */
  public void chooseSurprise() {
    final int method = random.nextInt(NUM_TOT_SURSPRISE) + 1;
    CompletableFuture<Void> future = CompletableFuture
        .runAsync(() -> this.map.get(method).run());
    try {
      future.get();
    } catch (InterruptedException e) {
      System.out.println(e.toString());
    } catch (ExecutionException e) {
      System.out.println(e.toString());

    }
    future.join();
  }
}
