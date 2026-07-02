package it.unibo.game.app.api;

import java.util.List;

import it.unibo.game.Pair;
import it.unibo.game.app.model.SizeCalculation;

/**
 * interface that contains useful methods to manage different rounds in a game.
 */
public interface Round {

  /**
   * 
   * @return object of class SizeCalculation.
   */
  SizeCalculation getSizeCalc();

  /**
   * 
   * @return number of normal bricks.
   */
  int getNumBrick();

  /**
   * 
   * @return number of brick surprise.
   */
  int getNumSur();

  /**
   * returns the ball to its initial position.
   */
  void restart();

  /**
   * method to get all the bricks in the game.
   * 
   * @return list of all bricks.
   */
  List<Brick> getBrick();

  /**
   * method to set ball position.
   * 
   * @param pos   new position of ball
   * @param index ball position in the list
   */
  void setPosBall(Pair<Double, Double> pos, int index);

  /**
   * method to set pos of pad.
   * 
   * @param pos new position of pad
   */
  void setPosPad(Pair<Double, Double> pos);

  /**
   * method to get all bonus ball in the game.
   * 
   * @return list of surprise balls.
   */
  List<MovingObject> getSurprise();

  /**
   * method to get positions of all balls in the game.
   * 
   * @return a list that contains positions of all balls
   */
  List<Pair<Double, Double>> getPosBall();

  /**
   * 
   * @return pad.
   */
  MovingObject getPad();

  /**
   * 
   * @return balls.
   */
  List<MovingObject> getBalls();

  /**
   * method to remove a brick when is hitten.
   * 
   * @param index position of brick in the list
   */
  void remove(int index);

  /**
   * 
   * @return list of extra balls.
   */
  List<MovingObject> getExtraBalls();

  /**
   * method to set brick in right position.
   */
  void setPosBrick();

}
