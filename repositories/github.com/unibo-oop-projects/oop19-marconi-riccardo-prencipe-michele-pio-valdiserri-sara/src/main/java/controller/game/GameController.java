package controller.game;

import game.logics.Direction;
import game.logics.GameManager.GameStatus;
import game.logics.Pair;
import game.logics.Tile;
import game.score.RankingManagerImpl;
import java.util.Map;
import javafx.event.ActionEvent;

public interface GameController {

  /**
   * This method manages the model and the view.
   */
  void move(Direction direction);

  /**
   * This method upgrade the model according to the GameMode.
   */
  void updateManager(Pair<Integer, Integer> position);

  /**
   * This method set the EndGameView when the game finish.
   */
  void endGameControl();

  /**
   * This method return the grid of the game.
   * 
   * @return gameManager
   */
  Map<Pair<Integer, Integer>, Tile> getGameManager();

  /**
   * This method return the size of the grid.
   * 
   * @return grid
   */
  int getGridDim();

  /**
   * This method return the game status.
   * 
   * @return GameStatus
   */
  GameStatus getGameStatus();

  /**
   * This method add the name of the player in a RankingManager.
   */
  void addRankingScore(String name);

  /**
   * This method return the ranking.
   * 
   * @return RankingManager
   */
  RankingManagerImpl getRankingManager();

  /**
   * This method start a new Game with the same size and mode.
   */
  void pressRT(ActionEvent e);

  /**
   * This method allow to return to the mode selection or custom mode selection
   * windows.
   */
  void pressTB();
}
