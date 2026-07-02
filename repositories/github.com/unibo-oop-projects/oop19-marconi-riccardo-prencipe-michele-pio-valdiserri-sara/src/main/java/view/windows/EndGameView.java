package view.windows;

public interface EndGameView {

  /**
   * This method set the final scene into the win scene if the user win the
   * game.
   */
  void setWin(int score);

  /**
   * This method, contrary to the win, set the scene to 'Game Over' because the
   * user lost the game.
   */
  void setLost(int score);
}
