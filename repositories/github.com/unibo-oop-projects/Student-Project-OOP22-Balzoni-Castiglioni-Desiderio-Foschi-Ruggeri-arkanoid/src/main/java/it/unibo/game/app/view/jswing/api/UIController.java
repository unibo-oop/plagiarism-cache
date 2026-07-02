package it.unibo.game.app.view.jswing.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import it.unibo.game.Pair;
import it.unibo.game.app.api.AppController;

/**
 * interface that interacts between the Controller and the GameView.
 */
public interface UIController {
  /**
   * types of pages that may appear while the application is running.
   */
  enum PAGES {
    /**
     * start menu.
     */
    START_MENU("START MENU"),
    /**
     * pause menu.
     */
    PAUSE_MENU("PAUSE MENU"),
    /**
     * game view.
     */
    GAME("ARKANOID"),
    /**
     * leaderboard.
     */
    TOP_5("TOP FIVE"),
    /**
     * victory.
     */
    VICTORY("VICTORY"),
    /**
     * game over.
     */
    GAME_OVER("GAME_OVER"),
    /**
     * game commands.
     */
    GAME_COMMANDS("GAME_COMMANDS");

    private final String name;

    /**
     * method that names the page.
     * 
     * @param name name of the page.
     */
    PAGES(final String name) {
      this.name = name;
    }

    /**
     * method that get names of the page.
     * 
     * @return name of the page.
     */
    public String getName() {
      return this.name;
    }
  };

  /**
   * method that sets the controller and creates a new Frame also initializes the
   * page map and calls the initialView method.
   * 
   * @param control controller of the application.
   */
  void set(AppController control);

  /**
   * method that brings up the main menu page.
   */
  void initialView();

  /**
   * method that brings up the game commands page.
   */
  void gameCommands();

  /**
   * method that brings up the pause menu page and stops the gameLoop.
   */
  void pauseMenu();

  /**
   * method that brings up the game page and starts the gameLoop.
   */
  void gameView();

  /**
   * method that brings up the leaderboard page.
   */
  void leaderBoardView();

  /**
   * method that brings up the game over page and stops the gameLoop.
   */
  void gameOver();

  /**
   * method that brings up the victory page and stops the gameLoop.
   */
  void victory();

  /**
   * method that is called when the user chooses the level.
   * 
   * @param numLevel level identification number
   */
  void level(int numLevel);

  /**
   * 
   * @return map of the positions of the bricks and their relative resistance.
   */
  Map<Pair<Double, Double>, Optional<Integer>> getList();

  /**
   * 
   * @return dimensions of the world in the model.
   */
  Pair<Double, Double> getDimension();

  /**
   * 
   * @return dimension of brick.
   */
  Pair<Double, Double> getDimensionBrick();

  /**
   * 
   * @return list of ball positions.
   */
  List<Pair<Double, Double>> getBall();

  /**
   * 
   * @return pad position.
   */
  Pair<Double, Double> getPadPos();

  /**
   * 
   * @return list of surprise ball positions.
   */
  List<Pair<Double, Double>> getSurprise();

  /**
   * 
   * @return width of pad.
   */
  Double getPadWight();

  /**
   * 
   * @return height of pad.
   */
  Double getPadHeight();

  /**
   * 
   * @return radius of ball.
   */
  Double getRBall();

  /**
   * method for update points.
   * 
   * @param name     of the player
   * @param passWord of the player
   */
  void updatePoints(String name, String passWord);

  /**
   * method that calls the repaint on the frame.
   */
  void rPaint();

  /**
   * method used to know the y of the row of bricks.
   * 
   * @param x number of row
   * @return y of the row
   */
  Double getRowC(Double x);

  /**
   * 
   * @return list of best five.
   */
  List<Pair<String, Integer>> getBestFive();

  /**
   * method to update the pad position by going left.
   */
  void movePadLeft();

  /**
   * method to update the pad position by going right.
   */
  void movePadRight();

  /**
   * 
   * @return dimension of the frame.
   */
  Pair<Double, Double> windowDim();

  /**
   * 
   * @return score.
   */
  int getScore();

  /**
   * 
   * @return life of the player.
   */
  int getLife();

  /**
   * @return all label pos to print in a resizable GUI
   */
  List<Pair<Double, Double>> getLabelPos();

  /**
   * 
   * @return size of a resizable font
   */
  int getSizeFont();

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
