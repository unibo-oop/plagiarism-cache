package it.unibo.game.app.model.round;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.game.Pair;
import it.unibo.game.app.api.Brick;
import it.unibo.game.app.api.BrickType;
import it.unibo.game.app.api.MovingObject;
import it.unibo.game.app.api.Round;

import it.unibo.game.app.model.SizeCalculation;
import it.unibo.game.app.model.ball.Ball;
import it.unibo.game.app.model.dynamic.SpeedImpl;
import it.unibo.game.app.model.pad.Pad;

/**
 * class that implements the methods common to different rounds.
 */
public abstract class AbstractRound implements Round {

  private int numBrick;
  private int numSurprise;
  private List<Brick> brick = new ArrayList<>();
  private List<MovingObject> balls = new ArrayList<>();
  private List<MovingObject> extraBalls = new ArrayList<>();
  private MovingObject pad;
  private SizeCalculation sizeC;
  private List<MovingObject> surprise = new ArrayList<>();
  private Random random = new Random();

  /**
   * constructor of the class.
   * 
   * @param numB number of normal bricks
   * @param numS number of surprise bricks
   * @param size objects that contains information of where collocate bricks
   */
  public AbstractRound(final int numB, final int numS, final SizeCalculation size) {
    this.numBrick = numB;
    this.numSurprise = numS;
    this.sizeC = size;
    pad = new Pad(SizeCalculation.getPadDim());
    balls.add(new Ball(SizeCalculation.getBallDim()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void restart() {
    this.balls.clear();
    MovingObject ball = new Ball(SizeCalculation.getBallDim());
    ball.getPhysics().getDir().resetDirection();
    this.balls.add(ball);
    this.surprise.clear();
  }

  /**
   * {@inheritDoc}
   */
  
  @Override
  public List<MovingObject> getSurprise() {
    return this.surprise;
  }

  /**
   * method to add a brick in the list.
   * 
   * @param brick that we want to add
   */
  protected void addBrick(final Brick brick) {
    this.brick.add(brick);
  }

  /**
   * method that add a surprise a new ball to the list.
   * 
   * @param ball the ball to add
   */
  private void addSurprise(final MovingObject ball) {
    this.surprise.add(ball);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SizeCalculation getSizeCalc() {
    return this.sizeC;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getNumBrick() {
    return this.numBrick;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getNumSur() {
    return this.numSurprise;
  }

  /**
   * {@inheritDoc}
   */
  
  @Override
  public List<Brick> getBrick() {
    return this.brick;
  }

  /**
   * method that to randomly places surprise bricks.
   * 
   * @return true if a brick to replace is found
   */
  protected boolean setBrickSurprise() {
    int idx = this.random.nextInt(brick.size());

    if (brick.get(idx).getType() == BrickType.NORMAL) {
      brick.get(idx).changeType(BrickType.SURPRISE);
      return true;
    } else {
      return false;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPosBall(final Pair<Double, Double> pos, final int index) {
    this.balls.get(index).setPos(pos);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPosPad(final Pair<Double, Double> pos) {
    pad.setPos(pos);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Pair<Double, Double>> getPosBall() {
    return this.balls.stream().map(ball -> ball.getPos()).collect(Collectors.toList());
  }

  /**
   * {@inheritDoc}
   */
  
  @Override
  public MovingObject getPad() {
    return this.pad;
  }

  /**
   * {@inheritDoc}
   */
  
  @Override
  public List<MovingObject> getBalls() {
    return this.balls;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remove(final int index) {
    Brick brick = this.brick.get(index);
    if (brick.getType().equals(BrickType.SURPRISE)) {
      this.addSurprise(this.addSurprise(brick));
    }
    brick.hit();
    if (brick.isDestroyed()) {
      this.brick.remove(index);
    }
  }

  /**
   * method that sets features of a new BonusBall.
   * 
   * @param brick the size of the brick is used to set the initial position of the
   *              ball
   * @return the ball to add to the list of bonus balls
   */
  private MovingObject addSurprise(final Brick brick) {
    MovingObject b = new Ball(SizeCalculation.getBallDim());
    b.setPos(new Pair<>(brick.getPos().getX() + brick.getBrickW() / 2,
        brick.getPos().getY() + brick.getBrickH()));
    b.setSpeed(new SpeedImpl(0, 1));
    return b;
  }

  /**
   * method that returns list of extra balls.
   * 
   * @return a list of extra balls
   */
  
  public List<MovingObject> getExtraBalls() {
    return this.extraBalls;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public abstract void setPosBrick();
}
