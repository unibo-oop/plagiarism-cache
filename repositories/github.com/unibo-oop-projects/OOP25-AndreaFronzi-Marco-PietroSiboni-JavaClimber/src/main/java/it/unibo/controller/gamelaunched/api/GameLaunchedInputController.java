package it.unibo.controller.gamelaunched.api;

import it.unibo.model.gameobj.api.Alien;

/**
 * Rapresent the controller which handle input from user and give to the
 * model the command to execute.
 */
public interface GameLaunchedInputController {

  /**
   * Handle the input to move the Alien entity to the right. This method
   * signals the model to update the {@link Alien}'s movement state, allowing
   * the Alien to begin or continue moving in the rightward direction.
   */
  void handleMoveRightCommand();

  /**
   * Handle the input to move the Alien entity to the left. This method
   * signals the model to update the {@link Alien}'s movement state, allowing
   * the Alien to begin or continue moving in the leftward direction.
   */
  void handleMoveLeftCommand();

  /**
   * Handle the input to pause the {@link it.unibo.model.LaunchedGame.api.LaunchedGame}.
   */
  void handlePauseCommand();

  /**
   * Handle the release of left or right movement command for the {@link
   * Alien} entity. This method signals the model to update the {@link
   * Alien}'s movement state, allowing the Alien to stop moving when the user
   * releases the movement key.
   */
  void handleReleaseMovementCommand();
}
