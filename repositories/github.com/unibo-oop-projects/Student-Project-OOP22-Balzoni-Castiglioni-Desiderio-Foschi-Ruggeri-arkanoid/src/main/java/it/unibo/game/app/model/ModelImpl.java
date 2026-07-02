package it.unibo.game.app.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.game.Pair;
import it.unibo.game.app.api.AppController;
import it.unibo.game.app.api.Direction;
import it.unibo.game.app.api.Level;
import it.unibo.game.app.model.dynamic.Move;
import it.unibo.game.app.model.leaderb.LeaderBoard;
import it.unibo.game.app.model.leaderb.LeaderBoardImpl;
import it.unibo.game.app.model.levels.FirstLevel;
import it.unibo.game.app.model.levels.SecondLevel;
import it.unibo.game.app.model.levels.ThirdLevel;
import it.unibo.game.app.model.round.GameOver;
import it.unibo.game.app.api.Model;
import it.unibo.game.app.api.MovingObject;

/**
 * class that contains methods to access data and application logics.
 */
public class ModelImpl implements Model {

  private AppController control;
  private Level level;
  private Move move;
  private LeaderBoard board = new LeaderBoardImpl();
  private GameOver gameOver;

  /**
   * {@inheritDoc}
   */
  
  @Override
  public void setController(final AppController c) {
    this.control = c;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<Pair<Double, Double>, Optional<Integer>> getBrickList() {
    return Collections.unmodifiableMap(this.level.getRound().getBrick().stream()
        .collect(Collectors.toMap(b -> b.getPos(), b -> b.getRes(), (x, y) -> y)));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void chooseLevel(final int numLevel) {
    switch (numLevel) {
    case 1:
      this.level = new FirstLevel();
      this.level.setFirstRound();
      break;
    case 2:
      this.level = new SecondLevel();
      this.level.setFirstRound();
      break;
    case 3:
      this.level = new ThirdLevel();
      this.level.setFirstRound();
      break;
    default:
      break;
    }
    this.move = new Move(level);
    this.gameOver = new GameOver(level.getRound());
    this.control.restartWin();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Pair<Double, Double> getBrickDimension() {
    return this.level.getRound().getSizeCalc().getBrickDim();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Pair<Double, Double>> getBall() {
    return Collections.unmodifiableList(this.level.getRound().getPosBall());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Pair<Double, Double> getPad() {
    return this.level.getRound().getPad().getPos();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPadPos(final Direction dir) {
    this.move.nextPad(dir);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Double getPadWight() {
    return this.level.getRound().getPad().getDimension().getWidth();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getScore() {
    return this.move.getScore();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Double getPadHeight() {
    return this.level.getRound().getPad().getDimension().getHeight();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Double getRBall() {
    return this.level.getRound().getBalls().get(0).getDimension().getHeight();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Double getRow(final Double x) {
    return this.level.getRound().getSizeCalc().getRowCordinate(x);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatePoints(final String name, final String passWord) {
    this.board.updatePoints(name, passWord, this.getScore(), this.level.getId());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void nextRound() {
    if (this.level.getNumRoundPassed() <= 2) {
      if (this.level.getNumRoundPassed() == 1) {
        this.level.setSecondRound();
        this.move = new Move(level);
        this.gameOver = new GameOver(level.getRound());
      } else if (this.level.getNumRoundPassed() == 2) {
        this.level.setThirdRound();
        this.move = new Move(level);
        this.gameOver = new GameOver(level.getRound());
      }
    } else {
      this.control.setVictory();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Pair<Double, Double> getWorldDim() {
    return SizeCalculation.getWorldSize();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update() {
    move.update();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Pair<String, Integer>> getBestFive() {
    return Collections.unmodifiableList(board.getBestFive());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean checkRound() {
    if (gameOver.isRoundFinished()) {
      this.level.increaseRound();
      return true;
    } else {
      return false;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean updateLife() {
    if (this.gameOver.hasMissedBall()) {
      this.level.decreaseLife();
      if (!this.level.isAlive()) {
        this.control.setGameOver();
        return false;
      }
      return true;
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void restoreInitialPosition() {
    this.level.getRound().restart();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<MovingObject> getSurprise() {
    return Collections.unmodifiableList(this.level.getRound().getSurprise());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getLife() {
    return this.level.getLife();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getString() {
    return level.getSurpriseString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteString() {
    this.level.resetBonus();
  }
}
