package it.unibo.controller.gamelaunched.impl;

import it.unibo.controller.gamelaunched.api.GameLaunchedController;
import it.unibo.controller.gamelaunched.api.GameLaunchedInputController;
import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.command.impl.EnterPausa;
import it.unibo.model.command.impl.MoveAlienLeft;
import it.unibo.model.command.impl.MoveAlienRight;
import it.unibo.model.command.impl.StopAlienMovement;
import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.gameobj.api.Coin;
import it.unibo.model.gameobj.api.Enemy;
import it.unibo.model.gameobj.api.Gadget;
import it.unibo.model.gameobj.api.Platform;
import it.unibo.model.launchedgame.api.LaunchedGame;
import it.unibo.model.shop.api.Inventory;
import it.unibo.model.world.api.BaseWorld;
import it.unibo.model.world.api.GameWorld;
import it.unibo.model.world.impl.World;

import java.util.List;
import java.util.Optional;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javax.swing.JPanel;

/**
 * Rapresent the implementation of {@link GameLaunchedController} and
 * {@link GameLaunchedInputController}.
 */
public class GameLaunchedControllerImpl implements GameLaunchedController, GameLaunchedInputController {

  private static final long FRAME_TIME_MS = 16;
  private static final long DIVIDER = 1_000_000_000;
  private static final long DIVIDER_MS = 1_000_000;

  /**
   * The {@link Inventory} which provide the active skin and receive the command
   * to update the model.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP2",
      justification = "The inventory is shared across the game and "
          + "it's not modified by the controller, but only read to get the active skin.")
  private final Inventory inventory;

  /**
   * The {@link LaunchedGame} entity which provide the data to render and receive
   * the command to update the model.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP2",
      justification = "The launched game is shared across the game and "
          + "it's not modified by the controller, but only read to get the game state.")
  private final LaunchedGame launchedGame;

  @SuppressFBWarnings(value = "EI_EXPOSE_REP2",
      justification = "The panel is a UI component managed by the view layer "
          + "and must be shared; the controller only triggers repaints")
  private JPanel panel;

  /**
   * Constructor new GameLaunchedControllerImpl.
   *
   * @param launchedGame the launched game entity
   * @param inventory    the inventory entity
   */
  public GameLaunchedControllerImpl(final LaunchedGame launchedGame, final Inventory inventory) {
    this.launchedGame = launchedGame;
    this.inventory = inventory;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getActiveSkin() {
    return this.inventory.getSelectedSkin();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Alien> getAlien() {
    return this.launchedGame.getWorld()
        .map(World::getRealWorld)
        .map(GameWorld::getAlien);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<List<Coin>> getCoins() {
    return this.launchedGame.getWorld()
        .map(World::getRealWorld)
        .map(BaseWorld::getMoneys);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<List<Enemy>> getEnemy() {
    return this.launchedGame.getWorld()
        .map(World::getRealWorld)
        .map(BaseWorld::getMonsters);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<List<Gadget>> getGadgets() {
    return this.launchedGame.getWorld()
        .map(World::getRealWorld)
        .map(BaseWorld::getGadgets);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<List<Platform>> getMovingPlatforms() {
    return this.launchedGame.getWorld()
        .map(World::getRealWorld)
        .map(GameWorld::getMovingPlatforms);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<List<Platform>> getOnTouchPlatform() {
    return this.launchedGame.getWorld()
        .map(World::getRealWorld)
        .map(GameWorld::getOnTouchPlatforms);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<List<Platform>> getStaticPlatforms() {
    return this.launchedGame.getWorld()
        .map(World::getRealWorld)
        .map(GameWorld::getStaticPlatforms);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleMoveRightCommand() {
    this.launchedGame.addCommand(new MoveAlienRight());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleMoveLeftCommand() {
    this.launchedGame.addCommand(new MoveAlienLeft());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handlePauseCommand() {
    this.launchedGame.addCommand(new EnterPausa());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleReleaseMovementCommand() {
    this.launchedGame.addCommand(new StopAlienMovement());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runGame() {
    new Thread(() -> {
      long previousCycleStartTime = System.nanoTime();

      while (launchedGame.isRunning()) {
        final long currentCycleStartTime = System.nanoTime();
        final long elapsedNanos = currentCycleStartTime - previousCycleStartTime;

        final double dt = (double) elapsedNanos / DIVIDER;

        previousCycleStartTime = currentCycleStartTime;

        Optional<RunningCommand> cmd = this.launchedGame.pollCommand();
        while (cmd.isPresent()) {
          cmd.get().execute(launchedGame.getWorld().get().getRealWorld().getAlien(), launchedGame);
          cmd = this.launchedGame.pollCommand();
        }

        this.launchedGame.getState().execute(dt);

        if (this.panel != null) {
          this.panel.repaint();
        }

        java.awt.Toolkit.getDefaultToolkit().sync();

        this.waitForNextFrame(currentCycleStartTime);
      }
    }, "GameLoop").start();
  }

  private void waitForNextFrame(final long currentCycleStartTimeNano) {
    final long elapsedTimeMs = (System.nanoTime() - currentCycleStartTimeNano) / DIVIDER_MS;

    final long sleepTime = FRAME_TIME_MS - elapsedTimeMs;

    if (sleepTime > 0) {
      try {
        Thread.sleep(sleepTime);
      } catch (final InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPanel(final JPanel panel) {
    final JPanel p = panel;
    this.panel = p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCurrentScore() {
    return this.launchedGame.getMenu().getScoreManager().getCurrentScore();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCollectedCoins() {
    return this.launchedGame.getMenu().getScoreManager().getCoins();
  }
}
