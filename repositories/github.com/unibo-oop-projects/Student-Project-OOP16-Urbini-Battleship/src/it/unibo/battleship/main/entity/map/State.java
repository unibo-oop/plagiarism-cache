/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.entity.map;

/**
 * Represents the State of a {@link FieldCell}. A field cell can either have the
 * state Water, Missed, Hit or Present.
 * <ul>
 * <li>Water represents an empty field cell.</li>
 * <li>Present represents a field cell occupied by a ship.</li>
 * <li>Hit represents a field ship which was hit.</li>
 * <li>Missed represents a shot which didn't hit anything.</li>
 * </ul>
 *
 * @author fabio.urbini
 */
public enum State {
  /** Water, nothing happened yet. */
  WATER,

  /** A ship has been hit. */
  HIT,

  /** There was water here and the cell was shot. */
  MISSED,

  /** A ship is present. */
  PRESENT
}
