package controller.game;

import game.logics.AlternativeManagerImpl;
import game.logics.Direction;
import game.logics.Game2048;
import game.logics.GameManager;
import game.logics.GameManager.GameStatus;
import game.logics.GameManagerImpl;
import game.logics.GameMode;
import game.logics.Pair;
import game.logics.Tile;
import game.score.RankingManagerImpl;
import java.io.IOException;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import view.utils.FXMLPath;
import view.windows.EndGameViewImpl;
import view.windows.GameBoardImpl;

/**
 * This class control the model and the view of the game.
 */
public class GameControllerImpl extends Pane implements GameController {

  private static final int ALTERNATIVE_TILE_X0 = 0;
  private static final int ALTERNATIVE_TILE_X2 = 2;
  private static final int FIVE_TURN = 5;
  private final int gridSize;
  private int turn;
  private int tileTurn;
  private final int size;
  private boolean isKeyPressed;
  private GameManager gameManager;
  private GameBoardImpl gameBoard;
  private EndGameViewImpl endGameView;
  private GameManager.GameStatus status;
  private final GameMode gameMode;
  private RankingManagerImpl ranking;
  private int score;

  /**
   * Constructor.
   */
  public GameControllerImpl(final int sizeG, final GameMode mode) {
    gridSize = sizeG;
    this.size = sizeG;
    this.gameMode = mode;
    this.score = 0;
    this.gameBoard = new GameBoardImpl(gridSize, this.gameMode, this);
    switch (gameMode) {
      case CLASSIC:
        gameManager = new GameManagerImpl(gridSize);
        gameManager.initGrid();
        gameManager.startGame();
        this.gameBoard.updateGridClassic(gameManager.getGrid());
        break;
      case ALTERNATIVE:
        gameManager = new AlternativeManagerImpl(gridSize);
        gameManager.initGrid();
        gameManager.startGame();
        this.gameBoard.updateGridAlternative(gameManager.getGrid(), 
            ((AlternativeManagerImpl) gameManager).getAlternativeGrid());
        break;
      default:
        System.out.println("NO GAME MODE");
        break;
    }
    this.gameBoard.updateGridClassic(gameManager.getGrid());
    this.getChildren().add(gameBoard);
    this.addKeyListener();
  }

  /**
   * This method listen the commands from the keyboard and set the directions.
   */
  private void addKeyListener() {
    this.setOnKeyPressed(e -> {
      if (!isKeyPressed && status 
          != GameManager.GameStatus.EXIT && status != GameManager.GameStatus.LOST
          && status != GameManager.GameStatus.WIN) {
        isKeyPressed = true;
        switch (e.getCode()) {
          case ESCAPE:
            gameManager.quitGame();
            break;
          case S:
            move(Direction.DOWN);
            break;
          case D:
            move(Direction.RIGHT);
            break;
          case A:
            move(Direction.LEFT);
            break;
          case W:
            move(Direction.UP);
            break;
          default:
            System.out.println("Tasto errato: " 
                + e.getCode() + " usa 'A' 'W' 'S' 'D' per muovere la griglia");
        }
      }
    });
    this.setOnKeyReleased(ke -> {
      isKeyPressed = false;
    });
  }

  @Override
  public void move(final Direction direction) {
    gameManager.moveTiles(direction);
    final var findPosition = gameManager.findRandomFreePosition();
    updateManager(findPosition);
    this.score += gameManager.getScore();
    gameBoard.updateScore(gameManager.getScore());
    if (gameManager.winControl() || gameManager.lostControl()) {
      status = gameManager.getGameStatus();
      endGameControl();
    }
  }

  @Override
  public void updateManager(final Pair<Integer, Integer> position) {
    switch (gameMode) {
      case CLASSIC:
        if (position != null) {
          this.turn = this.turn + 1;
          gameManager.addNewTile(position);
        }
        gameBoard.updateGridClassic(gameManager.getGrid());
        break;
      case ALTERNATIVE:
        this.turn = this.turn + 1;
        if (position != null && this.turn % FIVE_TURN != 0) {
          gameManager.addNewTile(position);
        } else if (position != null) {
          if (tileTurn % 2 == 0) {
            this.tileTurn = this.tileTurn + 1;
            ((AlternativeManagerImpl) gameManager)
            .addAlternativeTile(ALTERNATIVE_TILE_X0, position);
          } else {
            this.tileTurn = this.tileTurn + 1;
            ((AlternativeManagerImpl) gameManager)
            .addAlternativeTile(ALTERNATIVE_TILE_X2, position);
          }
        }
        ((AlternativeManagerImpl) gameManager).controlAlternativePos();
        gameBoard.updateGridAlternative(gameManager.getGrid(), 
            ((AlternativeManagerImpl) gameManager).getAlternativeGrid());
        break;
      default:
        System.out.println("NO GAME MODE");
        break;
    }
  }

  @Override
  public void endGameControl() {
    this.endGameView = new EndGameViewImpl(this);
    if (status == GameManager.GameStatus.LOST) {
      endGameView.setLost(this.score);
      this.getChildren().add(endGameView);
    } else {
      endGameView.setWin(this.score);
      this.getChildren().add(endGameView);
    }
  }

  @Override
  public Map<Pair<Integer, Integer>, Tile> getGameManager() {
    return this.gameManager.getGrid();
  }

  @Override
  public int getGridDim() {
    return gridSize;
  }

  @Override
  public GameStatus getGameStatus() {
    return this.status;
  }

  @Override
  public void addRankingScore(final String name) {
    ranking = new RankingManagerImpl();
    ranking.addScore(name, this.score, this.gameMode, this.size);
  }

  @Override
  public RankingManagerImpl getRankingManager() {
    return this.ranking;
  }

  @Override
  public void pressRT(final ActionEvent e) {
    final Button btn = (Button) e.getSource();
    btn.getScene().getWindow().hide();
    new Game2048().startGame(gridSize, this.gameMode);
  }

  @Override
  public void pressTB() {
    this.getChildren().clear();
    Parent root;
    this.status = GameManager.GameStatus.EXIT;
    if (gridSize == 4) {
      try {
        root = FXMLLoader.load(getClass().getResource(FXMLPath.MODE.getPath()));
        this.getChildren().add(root);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      try {
        root = FXMLLoader.load(getClass().getResource(FXMLPath.CUSTOM.getPath()));
        this.getChildren().add(root);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
