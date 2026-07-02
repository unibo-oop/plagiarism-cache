package it.unibo.game.app.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import it.unibo.game.Pair;

/**
 * interface that contains methods to access data that are impelemnted in class
 * ModelImpl.
 */
public interface Model {

  /**
   * method that allows to keep information about the controller in class model.
   * 
   * @param c controller
   */
  void setController(AppController c);

  /**
   * method that allows to get all the bricks, with their characteristics, of the
   * current round.
   * 
   * @return a map where the keys are the positions of the bricks and the values
   *         their resistance
   */
  Map<Pair<Double, Double>, Optional<Integer>> getBrickList();

  /**
   * method that allows to change level.
   * 
   * @param numLevel level identifier
   */
  void chooseLevel(int numLevel);

  /**
   * method which allows to obtain the size of the bricks.
   * 
   * @return a pair with height and width of the bricks
   */
  Pair<Double, Double> getBrickDimension();

  /**
   * method which allows to obtain all the balls in the game.
   * 
   * @return a list with each balls' position
   */
  List<Pair<Double, Double>> getBall();

  /**
   * method which allows to obtain position of pad.
   * 
   * @return position of pad
   */
  Pair<Double, Double> getPad();

  /**
   * method to change pad position.
   * 
   * @param dir new direction called from user
   */
  void setPadPos(Direction dir);

  /**
   * method that allows to change round.
   */
  void nextRound();

  /**
   * method to get pad width.
   * 
   * @return a double that represents pad width
   */
  Double getPadWight();

  /**
   * method to get pad height.
   * 
   * @return a double that represents pad height
   */
  Double getPadHeight();

  /**
   * method to get ball radius.
   * 
   * @return a double that represents ball radius
   */
  Double getRBall();

  /**
   * this method will be called when a player wants to save his score.
   * 
   * @param name     player's name
   * @param passWord player's password
   */
  void updatePoints(String name, String passWord);

  /**
   * method used to know the y of the rows of bricks.
   * 
   * @param x number of row
   * @return y of the row
   */
  Double getRow(Double x);

  /**
   * method that returns the size of the frame according to which the positions
   * and dimensions of the various objects have been set.
   * 
   * @return the size of the world (height and width)
   */
  Pair<Double, Double> getWorldDim();

  /**
   * method that allows to update positions of objects.
   */
  void update();

  /**
   * method that allows to get player's actual score.
   * 
   * @return an int value representing player's score
   */
  int getScore();

  /**
   * method to get all bonus ball informations.
   * 
   * @return a list of bonus ball
   */
  List<MovingObject> getSurprise();

  /**
   * method to get best five players.
   * 
   * @return an ordered list that contains name and points of best five players
   */
  List<Pair<String, Integer>> getBestFive();

  /**
   * method that controls if there is still one more round available and in case
   * changes round.
   * 
   * @return true if there is still one more round available
   */
  boolean checkRound();

  /**
   * method to update player's lives.
   * 
   * @return true if it was possible to increase the player's life
   */
  boolean updateLife();

  // boolean isLevelFinished();

  /**
   * method that allows to get player's lives.
   * 
   * @return an integer representing the lives of the player
   */
  int getLife();

  /**
   * method that allows to put ball in its initial pos.
   */
  void restoreInitialPosition();

  /**
   * 
   * @return the name of the surprise.
   */
  String getString();

  /**
   * delete the name of the surprise.
   */
  void deleteString();

  // List<Ball> getExtraBalls();

}
