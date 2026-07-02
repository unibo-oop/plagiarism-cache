package it.unibo.game.app.model.levels;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.game.app.api.Brick;
import it.unibo.game.app.api.Level;
import it.unibo.game.app.api.Round;
import it.unibo.game.app.api.Score;
import it.unibo.game.app.model.ScoreImpl;

/**
 * This abstract class declares variables and defines methods in common with
 * various levels.
 */
public abstract class AbstractLevel implements Level {

  private static final int INITIAL_LIVES = 3;
  private int lives = INITIAL_LIVES;
  private Round currentRound;
  private Score score;
  private int numRoundPassed = 0;
  private int levelId;
  private Brick lastSurpriseBrick;
  private int indexLastSurprise;
  private String bonus;

  /**
   * constructor of this class.
   * 
   * @param levelId represents level identifier
   */
  public AbstractLevel(final int levelId) {
    this.levelId = levelId;
    this.bonus = " ";
    this.score = new ScoreImpl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public abstract void setFirstRound();

  /**
   * {@inheritDoc}
   */
  @Override
  public abstract void setSecondRound();

  /**
   * {@inheritDoc}
   */
  @Override
  public abstract void setThirdRound();

  /**
   * {@inheritDoc}
   */
  @Override
  public void increaseLife() {
    this.lives++;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void decreaseLife() {
    this.lives--;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAlive() {
    return this.lives > 0;
  }

  /**
   * {@inheritDoc}
   */
  
  @Override
  public Round getRound() {
    return this.currentRound;
  }

  /**
   * {@inheritDoc}
   */
  
  @Override
  public void setRound(final Round r) {
    this.currentRound = r;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getNumRoundPassed() {
    return this.numRoundPassed;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void increaseRound() {
    ++this.numRoundPassed;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getLife() {
    return this.lives;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getId() {
    return this.levelId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Score getScore() {
    return this.score;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setLastSurpriseBrick(final Brick surBrick, final int i) {
    this.lastSurpriseBrick = surBrick;
    this.indexLastSurprise = i;
  }

  /*
   * public int getRoundPassed() { return this.numRoundPassed; }
   */

  /**
   * {@inheritDoc}
   */
  @Override
  public Brick getLastSurpriseBrick() {
    return this.lastSurpriseBrick;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getIndexLastSurprise() {
    return this.indexLastSurprise;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setSurpriseString(final String bonus) {
    this.bonus = bonus;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getSurpriseString() {
    return this.bonus;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void resetBonus() {
    this.bonus = " ";
  }
}
