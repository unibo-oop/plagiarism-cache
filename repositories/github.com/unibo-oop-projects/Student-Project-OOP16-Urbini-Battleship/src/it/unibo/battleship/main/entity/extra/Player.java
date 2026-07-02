/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.entity.extra;

/**
 * Represents a Player.
 * Not supported yet.
 * @author fabio.urbini
 */
public final class Player {
  private final String name;

  /**
   * Not usable yet.
   * @param name name of the player.
   */
  private Player(final String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
