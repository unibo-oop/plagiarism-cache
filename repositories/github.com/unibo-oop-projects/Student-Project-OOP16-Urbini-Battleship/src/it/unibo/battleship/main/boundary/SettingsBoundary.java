/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.boundary;

/**
 * Represents the boundary used to set all
 * the environment variables for the game.
 * @author fabio.urbini
 */
public interface SettingsBoundary extends IoBoundary {

  /**
   * Shows the current settings.
   */
  void showSettings();

  /* Move to a control
  void setDifficulty(int difficulty);

  void enableShootingAgainAfterHit() throws UnsupportedOperationException;

  void disableShootingAgainAfterHit() throws UnsupportedOperationException;
  */
}
