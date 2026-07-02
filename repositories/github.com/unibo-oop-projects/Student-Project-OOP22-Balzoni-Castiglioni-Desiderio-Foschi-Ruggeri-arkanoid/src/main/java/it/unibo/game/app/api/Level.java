package it.unibo.game.app.api;

/**
 * interface Level that contains method that can be useful when a new Level
 * starts.
 */
public interface Level {

  /**
   * place items(pad,ball,bricks) within the first round.
   */
  void setFirstRound();

  /**
   * place items(pad,ball,bricks) within the second round.
   */
  void setSecondRound();

  /**
   * place items(pad,ball,bricks) within the third round.
   */
  void setThirdRound();

  /**
   * increment lives.
   */
  void increaseLife();

  /**
   * decrement lives.
   */
  void decreaseLife();

  /**
   * 
   * @return true if has other lives.
   */
  boolean isAlive();

  /**
   * 
   * @return current round.
   */
  Round getRound();

  /**
   * sets new round.
   * 
   * @param r
   */
  void setRound(Round r);

  /**
   * 
   * @return number of rounds passed.
   */
  int getNumRoundPassed();

  /**
   * pass to next round.
   */
  void increaseRound();

  /**
   * 
   * @return number of lives.
   */
  int getLife();

  /**
   * 
   * @return level identifier.
   */
  int getId();

  /**
   * 
   * @return player current score.
   */
  Score getScore();

  /**
   * 
   * @param suBrick
   * @param i
   */
  void setLastSurpriseBrick(Brick suBrick, int i);

  /**
   * 
   * @return the last surprise brick destroyed.
   */
  Brick getLastSurpriseBrick();

  /**
   * 
   * @return the index of the last surprise brick destroyed.
   */
  int getIndexLastSurprise();

  /**
   * set the name of the called surprise method.
   * 
   * @param surprise name of the surprise method.
   */
  void setSurpriseString(String surprise);

  /**
   * 
   * @return name of the surprise method.
   */
  String getSurpriseString();

  /**
   * Reset the string of the surprise called empty.
   */
  void resetBonus();
}
