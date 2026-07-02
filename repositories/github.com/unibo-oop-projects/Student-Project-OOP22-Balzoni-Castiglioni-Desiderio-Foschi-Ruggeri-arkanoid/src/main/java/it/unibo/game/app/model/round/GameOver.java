package it.unibo.game.app.model.round;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.game.app.api.Brick;
import it.unibo.game.app.api.BrickType;
import it.unibo.game.app.api.Round;

/**
 * This class checks round states.
 */
public class GameOver {

  private Round round;

  /**
   * constructor of this class.
   * 
   * @param round represents the playing round
   */
  
  public GameOver(final Round round) {
    this.round = round;
  }

  /**
   * @return boolean value that indicates if the pad has missed the ball.
   */
  public boolean hasMissedBall() {
    for (var ball : this.round.getBalls()) {
      if (!(ball.getPos().getY() > this.round.getPad().getPos().getY())) {
        return false;
      }
    }
    return true;
  }

  /**
   * @return boolean value that indicates if the round is finished.
   */
  public boolean isRoundFinished() {
    return this.round.getBrick().size() < 1 || remainsOnlyObstacles();
  }

  /**
   * @return boolean value that indicates if remains only obstacles in round.
   */
  private boolean remainsOnlyObstacles() {
    for (Brick element : this.round.getBrick()) {
      if (element.getType() != BrickType.OBSTACLE) {
        return false;
      }
    }
    return true;
  }
}
