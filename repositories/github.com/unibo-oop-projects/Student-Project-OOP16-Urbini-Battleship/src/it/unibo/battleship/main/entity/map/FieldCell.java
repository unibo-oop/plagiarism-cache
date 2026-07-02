/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.entity.map;

import it.unibo.battleship.main.entity.ships.Ship;
import it.unibo.battleship.main.entity.shots.Shot;
import java.io.Serializable;
import java.util.Optional;

/**
 * Represents a field cell which can have different states.
 * @see State
 * @author fabio.urbini
 */
public interface FieldCell extends Serializable {

  /**
   * Places a ship in the field cell.
   *
   * @param ship
   *          {@link Ship}
   */
  void placeShip(Ship ship);

  /**
   * Tries to shoot this field cell.
   *
   * @param shot
   *          {@link Shot}
   */
  void shoot(Shot shot);

  /**
   * Returns {@code true} if this field cell is empty.
   *
   * @return {@code true} if this field cell is empty
   */
  boolean isEmpty();

  /**
   * Returns {@code true} if a {@link Ship} is hit.
   *
   * @return {@code true} if a {@link Ship} is hit
   */
  boolean isHit();

  /**
   * Returns {@code true} if the field cell was hit but it was empty.
   *
   * @return {@code true} if the field cell was hit but it was empty
   */
  boolean isMissed();

  /**
   * Returns {@code true} if a {@link Ship} is present, false otherwise.
   *
   * @return {@code true} if a {@link Ship} is present
   */
  boolean isPresent();

  /**
   * Returns an Optional of Ship.
   *
   * @return an Optional of Ship
   */
  Optional<Ship> getShip();
}
