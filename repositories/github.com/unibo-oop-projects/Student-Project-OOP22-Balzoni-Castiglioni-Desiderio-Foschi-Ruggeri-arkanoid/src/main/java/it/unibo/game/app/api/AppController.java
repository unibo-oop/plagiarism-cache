package it.unibo.game.app.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import it.unibo.game.Pair;

/**
 * Controller interface that handles communication between model and view.
 */
public interface AppController {

  /**
   * calls the method of the view which shows the game situation.
   */
  void play();

  /**
   * invoked from the view to pause the game.
   */
  void onPause();

  /**
   * method that initializes the view.
   */
  void setView();

  /**
   * method that initializes the model.
   */
  void setModel();

  /**
   * 
   * @return the position of the blocks with the relative resistance.
   */
  Map<Pair<Double, Double>, Optional<Integer>> getBrickList();

  /**
   * method that is called from the view when the level is chosen.
   * 
   * @param numLevel selected level number
   */
  void chooseLevel(int numLevel);

  /**
   * 
   * @return brick dimension.
   */
  Pair<Double, Double> getBrickDimension();

  /**
   * 
   * @return model dimension.
   */
  Pair<Double, Double> getWorldDimension();

  /**
   * method for changing rounds or claiming victory.
   */
  void nextRound();

  /**
   * 
   * @return list of balls
   */
  List<Pair<Double, Double>> getBall();

  /**
   * 
   * @return pad position update relative to frame size.
   */
  Pair<Double, Double> getPad();

  /**
   * 
   * @return pad width update relative to frame size.
   */
  Double getPadWight();

  /**
   * 
   * @return pad height update relative to frame size.
   */
  Double getPadHeight();

  /**
   * 
   * @return ball radius update relative to frame size.
   */
  Double getRBall();

  /**
   * method to invoke the repaint on the frame.
   */
  void rPaint();

  /**
   * method used to know the y of the rows of bricks update relative to frame
   * size.
   * 
   * @param x number of row
   * @return y of the row
   */
  Double getRow(Double x);

  /**
   * 
   * @return list of best five.
   */
  List<Pair<String, Integer>> getBestFive();

  /**
   * 
   * @return list of position of surprise ball.
   */
  List<Pair<Double, Double>> getSurprise();

  // List<Pair<Double, Double>> getNewBalls();
  /**
   * 
   * @return the score
   */
  int getScore();

  /**
   * 
   * @return the life of the player.
   */
  int getLife();

  /**
   * method that initializes the game engine.
   */
  void setGameEngine();

  /**
   * method for update points.
   * 
   * @param name     name of the player
   * @param passWord password of the player
   */
  void updatePoints(String name, String passWord);

  /**
   * method to invoke update in model.
   */
  void update();

  /**
   * method to invoke the game over frame change in the view.
   */
  void setGameOver();

  /**
   * method that controls whether to decrease lives and set game over.
   * 
   * @return true if the player missed the ball
   */
  boolean updateLife();

  /**
   * method for returning the ball to the starting point.
   */
  void restoreBall();

  /**
   * move pad right.
   */
  void mvPadR();

  /**
   * move pad Left.
   */
  void mvPadL();

  /**
   * method that informs if the round has been changed.
   * 
   * @return true if the round has been changed
   */
  boolean checkRound();

  /**
   * method to invoke the victory frame change in the view.
   */
  void setVictory();

  /**
   * 
   * @return font size must have GUI font
   */
  int getFontSize();

  /**
   * 
   * @return where draw labels in GUI
   */
  List<Pair<Double, Double>> getLabelPos();

  /**
   * method that resets the win.
   */
  void restartWin();

  /**
   * 
   * @return the name of the surprise.
   */
  String getStringSur();

  /**
   * delete the name of the surprise.
   */
  void deleteString();

}
