package it.unibo.game.app.model.ball;

import it.unibo.game.app.api.Physics;
import it.unibo.game.Pair;
import it.unibo.game.app.api.Direction;
import it.unibo.game.app.api.Side;
import it.unibo.game.app.model.dynamic.DirectionImpl;

/**
 * Class that manages the change of direction of the Ball.
 */
public class BallPhysicsImpl implements Physics {

  private Direction d = new DirectionImpl();
  private Pair<Integer, Integer> temp;
  private boolean centre = false;

  /**
   * constructor of this class.
   */
  public BallPhysicsImpl() {
    this.temp = d.getDirection();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void changeDirection(final Side side) {
    this.d.setDirection(temp);
    if (centre) {
      d.setDirectionUp();
      centre = false;
    }

    if (side.equals(Side.CORNER)) {
      if (this.d.isDirectionLeft()) {
        this.d.setDirectionRight();
      } else {
        this.d.setDirectionLeft();
      }

      if (this.d.isDirectionUp()) {
        this.d.setDirectionDown();
      } else {
        this.d.setDirectionUp();
      }
      temp = d.getDirection();
    } else if (side.equals(Side.LEFT_RIGHT)) {
      if (this.d.isDirectionLeft()) {
        this.d.setDirectionRight();
      } else {
        this.d.setDirectionLeft();
      }
      temp = d.getDirection();

    } else if (side.equals(Side.UP_DOWN)) {
      if (this.d.isDirectionUp()) {
        this.d.setDirectionDown();
      } else {
        this.d.setDirectionUp();
      }
      temp = d.getDirection();
    } else {
      centre = true;
      d.setCentre();
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Direction getDir() {
    Direction dir = new DirectionImpl();
    dir.setDirection(this.d.getDirection());
    return dir;
  }

}
