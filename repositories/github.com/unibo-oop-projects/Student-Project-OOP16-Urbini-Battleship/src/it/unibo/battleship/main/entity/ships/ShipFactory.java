/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.entity.ships;

import jdk.nashorn.internal.ir.annotations.Immutable;
import java.io.Serializable;

/**
 * Represents a Ship factory. Implement it to decide how to create a ship and
 * when, depending on the size.
 *
 * @author fabio.urbini
 */
@FunctionalInterface
@Immutable
public interface ShipFactory extends Serializable {
  /**
   * Creates a ship.
   * @param size size of the ship
   * @return a ship
   */
  Ship createShip(final int size);
}
