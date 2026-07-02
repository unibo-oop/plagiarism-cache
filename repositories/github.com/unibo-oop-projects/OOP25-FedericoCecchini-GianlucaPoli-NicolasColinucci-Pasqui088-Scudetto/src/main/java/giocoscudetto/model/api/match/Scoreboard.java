package giocoscudetto.model.api.match;

/**
 * This Class rappresent the scoreboard of the Match.
 */
public interface Scoreboard {

  /**
   * This method returns the score of the guest team.
   * 
   * 
   * @return the guest team score of the game.
   */
  int getGuestScore();

  /**
   * this method returns the score of the home team.
   * 
   * @return the home team score of the game.
   */
  int getHomeScore();

  /**
   * this method sets the score of the home team.
   * 
   * @param goals the score of the home team.
   */
  void setHomeScore(int goals);

  /**
   * this method sets the score of the guest team.
   * 
   * @param goals the score of the guest team.
   */
  void setGuestScore(int goals);

  /**
   * this method increase the score of the home team by 1.
   */
  void increaseHomeScore();

  /**
   * this method increase the score of the guest team by 1.
   */
  void increaseGuestScore();

  /**
   * this method decrease the score of the home team by 1.
   */
  void decreaseHomeScore();

  /**
   * this method decrease the score of the guest team by 1.
   */
  void decreaseGuestScore();

}
