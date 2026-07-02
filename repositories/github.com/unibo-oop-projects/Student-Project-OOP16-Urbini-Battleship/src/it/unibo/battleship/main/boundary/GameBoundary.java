/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.boundary;

/**
 * Created by fabio.urbini on 05/07/2017.
 */
public interface GameBoundary extends IoBoundary {

  /**
   * Shows the current game field.
   */
  void showField();

  /**
   * Shows the current game settings.
   */
  void showSettings();
}
