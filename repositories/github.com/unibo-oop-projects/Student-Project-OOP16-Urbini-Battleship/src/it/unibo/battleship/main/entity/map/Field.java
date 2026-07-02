/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.entity.map;

import it.unibo.battleship.main.common.FieldBound;
import it.unibo.battleship.main.common.Point2d;
import it.unibo.battleship.main.entity.ships.Ship;
import it.unibo.battleship.main.entity.ships.ShipDirection;
import it.unibo.battleship.main.entity.shots.Shot;

import java.io.Serializable;

/**
 * Represents the field of the battleship game. You can either place a ship or
 * shoot. Any field has a Boundary which describes the space it uses and it
 * starts from origin(0,0).
 *
 * @author fabio.urbini
 */
public interface Field extends Serializable {

  /**
   * Places a ship in the field.
   *
   * @param ship
   *          ship to place. The field cells which will be occupied
   *          by the ship must be empty
   * @param point
   *          position where the ship starts. The field cell must be
   *          empty.
   * @param direction
   *          direction of the ship
   * @throws IllegalArgumentException if the field cells are not empty
   */
  void placeShip(Ship ship, Point2d point, ShipDirection direction);

  /**
   * Places a ship in the field with a standard direction (EAST).
   *
   * @param ship ship to place. The field cells which will be occupied
   *             by the ship must be empty
   *
   * @param point position where the ship starts. The field cell
   *              must be empty
   * @throws IllegalArgumentException if the field cells are not empty
   */
  void placeShip(Ship ship, Point2d point);


  /**
   * Returns true if the ship is placeable.
   * @param ship the current ship to place
   * @param point position where the ship will start. It must be within
   *              the boundary.
   * @return {@code true} if the ship is placeable
   * @throws IllegalArgumentException if the point is not within the boundary
   */
  boolean isShipPlaceable(final Ship ship, final Point2d point);

  /**
   * Updates the field with a shot.
   *
   * @param shot
   *          any kind of shot {@link Shot}. It must be within
   *          the boundary limit.
   * @throws IllegalArgumentException if the point is not within the boundary.
   */
  void updateStateWithShot(Shot shot);

  /**
   * Returns the boundary of the field.
   *
   * @return the boundary of the field
   */
  FieldBound getFieldBound();

  /**
   * Returns the field cells matrix.
   *
   * @return the field cells matrix
   */
  FieldCell[][] getFieldCells();
  // TODO: consider wrapping FieldCell[][] or using List<List<>>
}
