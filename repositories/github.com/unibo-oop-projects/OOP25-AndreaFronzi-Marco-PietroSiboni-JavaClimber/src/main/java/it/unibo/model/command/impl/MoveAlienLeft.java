package it.unibo.model.command.impl;

import it.unibo.model.command.api.RunningCommand;
import it.unibo.model.gameobj.api.Alien;
import it.unibo.model.launchedgame.api.LaunchedGame;

/**
 * Rapresent an implementation of {@link RunningCommand} that move {@link Alien} left.
 */
public class MoveAlienLeft implements RunningCommand {

  /**
   * Move the {@link Alien} left.
   *
   * @param alien the {@link Alien} to move.
   */
  @Override
  public void execute(final Alien alien, final LaunchedGame launchedGame) {
    alien.moveLeft();
  }
}
