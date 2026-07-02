package it.unibo.view.gamelaunchedview.input.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import it.unibo.controller.gamelaunched.api.GameLaunchedInputController;

/**
 * Rapresents {@link KeyListener} which handle user's action and convert it
 * input for the game.
 */
public class LaunchedGameInputHandlerImpl implements KeyListener {

  /**
   * The {@link GameLaunchedInputController} which handle the input.
   */
  private final GameLaunchedInputController gameLaunchedController;

  /**
   * A mapping between key codes and their corresponding actions to be executed
   * when the keys are pressed.
   * Each key code is associated with a {@link Runnable} task, which represents
   * the command that needs
   * to be executed in response to a specific key press.
   */
  private final Map<Integer, Runnable> onPressedActions;
  /**
   * A mapping between key codes and their corresponding actions to be executed
   * when the keys are released.
   * Each key code is associated with a {@link Runnable} task, which represents
   * the command that needs
   * to be executed in response to a specific key release.
   */
  private final Map<Integer, Runnable> onReleasedActions;

  /**
   * Constructs a new {@link LaunchedGameInputHandlerImpl} with the specified
   * {@link GameLaunchedInputController}.
   *
   * @param gameLaunchedController the {@link GameLaunchedInputController} which
   *                               handle the input.
   */
  public LaunchedGameInputHandlerImpl(final GameLaunchedInputController gameLaunchedController) {
    this.gameLaunchedController = gameLaunchedController;
    this.onPressedActions = new HashMap<>();
    this.onReleasedActions = new HashMap<>();
    this.initKeyBindings();
  }

  /**
   * Initializes the key bindings between specific key codes and their
   * corresponding actions for both key press and key release events.
   */
  private void initKeyBindings() {
    this.onPressedActions.put(KeyEvent.VK_LEFT, this.gameLaunchedController::handleMoveLeftCommand);
    this.onPressedActions.put(KeyEvent.VK_RIGHT, this.gameLaunchedController::handleMoveRightCommand);
    this.onReleasedActions.put(KeyEvent.VK_ESCAPE, this.gameLaunchedController::handlePauseCommand);

    this.onReleasedActions.put(KeyEvent.VK_LEFT, this.gameLaunchedController::handleReleaseMovementCommand);
    this.onReleasedActions.put(KeyEvent.VK_RIGHT, this.gameLaunchedController::handleReleaseMovementCommand);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void keyPressed(final KeyEvent e) {
    final Runnable action = this.onPressedActions.get(e.getKeyCode());
    if (action != null) {
      action.run();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void keyReleased(final KeyEvent e) {
    final Runnable action = this.onReleasedActions.get(e.getKeyCode());
    if (action != null) {
      action.run();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void keyTyped(final KeyEvent e) {
  }
}
