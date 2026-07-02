package it.unibo.game.app.controller;

import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.game.app.api.AppController;

/**
 * GameLoop of the project.
 */
public class GameEngine {
  private static final long PERIOD = 30;
  private long period = PERIOD;
  private AppController controller;
  private boolean thread = false;
  private boolean winCondition = false;

  /**
   * Constructor of the class.
   * 
   * @param contr controller interacting with the model
   */
  
  public GameEngine(final AppController contr) {
    this.controller = contr;
  }

  /**
   * GameLoop is done in the backGround to have a multi-threaded process.
   */
  public final void processInBackGround() {
    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

      @Override
      protected Void doInBackground() throws Exception {
        while (thread && !winCondition) {
          long currentCycleStartTime = System.currentTimeMillis();
          update();
          if (checkRound()) {
            pause();
            controller.nextRound();
            render();
            TimeUnit.SECONDS.sleep(1);
            restart();
          }

          if (updateLife()) {
            pause();
            controller.restoreBall();
            render();
            TimeUnit.SECONDS.sleep(1);
            restart();
          }
          render();
          waitForNextFrame(currentCycleStartTime);
        }
        return null;
      }

    };
    worker.execute();
  }

  /**
   * method used to make the gameLoop take the same time on different PC.
   * 
   * @param cycleStartTime
   * @throws InterruptedException
   */
  private void waitForNextFrame(final long cycleStartTime) throws InterruptedException {
    long dt = System.currentTimeMillis() - cycleStartTime;
    if (dt < period) {
      Thread.sleep(period - dt);
    }
  }

  /**
   * method to set win.
   */
  public void setWin() {
    this.winCondition = true;
  }

  /**
   * method to reset win.
   */
  public void resetWin() {
    this.winCondition = false;
  }

  /**
   * method that restarts the gameLoop.
   */
  private void restart() {
    this.thread = true;
  }

  /**
   * method that invokes the repait on the Controller.
   */
  private void render() {
    this.controller.rPaint();
  }

  /**
   * method that calls the update on the controller.
   */
  private void update() {
    this.controller.update();
  }

  /**
   * method that stop the gameLoop.
   */
  public void pause() {
    this.thread = false;
  }

  /**
   * method that starts the gameLoop from the beginning.
   */
  public void resume() {
    this.restart();
    this.processInBackGround();
  }

  /**
   * method that asks if the round is over.
   * 
   * @return true if the round is over
   */
  private boolean checkRound() {
    return this.controller.checkRound();
  }

  /**
   * method that asks if the ball has been lost.
   * 
   * @return true if the player has missed the ball
   */
  private boolean updateLife() {
    return this.controller.updateLife();
  }

}
