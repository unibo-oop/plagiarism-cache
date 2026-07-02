/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.entity.ships;

import it.unibo.battleship.main.common.Point2d;
import it.unibo.battleship.main.entity.shots.Shot;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Represents a Ship which can have a direction,
 * a size, and it can be sunk.
 *
 * @author fabio.urbini
 */
public interface Ship extends Serializable {

  /**
   * Returns {@code true} if this ship contains a point specified by the
   * parameter.
   *
   * @param point
   *          {@link Point2d}
   * @return {@code true} if this ship contains the specified point
   */
  boolean containsPosition(Point2d point);

  /**
   * Places a ship where specified.
   * It's suggested to let the field do the job.
   * Calling this method will give the ship a position
   * without the knowledge of the field.
   * Any misuse of this method can lead to Exceptions
   * of the system.
   * @param startingPosition
   *          {@link Point2d}
   * @param direction
   *          {@link ShipDirection}
   */
  void place(Point2d startingPosition, ShipDirection direction);

  /**
   * Places a ship where specified with standard Direction
   * (EAST). It's suggested to let the field do the job.
   * Calling this method will give the ship a position
   * without the knowledge of the field.
   * Any misuse of this method can lead to Exceptions
   * of the system.
   * @param startingPosition
   *          {@link Point2d}
   */
  void place(Point2d startingPosition);

  /**
   * Resets the placement of this ship.
   */
  void resetPlacement();

  /**
   * Tries to shoot this ship. Returns {@code true} if the ship was hit,
   * {@code false} otherwise.
   *
   * @param shot
   *          {@link Shot}
   * @return {@code true} if the ship was hit, {@code false} otherwise
   */
  boolean shoot(Shot shot);

  /**
   * Gets all points used by this ship.
   *
   * @return all points used by this ship
   */
  List<Point2d> getAllPositions();

  /**
   * Returns {@code true} if this ship was placed.
   *
   * @return {@code true} if this ship was placed
   */
  boolean isPlaced();

  /**
   * Gets the starting position of this ship.
   *
   * @return the starting position of this ship
   */
  Optional<Point2d> getStartingPosition();

  /**
   * Returns temporary projection points of the ship determined by the point
   * given in the input.
   *
   * @param point
   *          point where the ship will start
   * @return a List of {@link Point2d}
   */
  List<Point2d> getProjectionPoints(Point2d point);

  /**
   * Returns the size of the current ship.
   *
   * @return the size of the current ship
   */
  int getSize();

  /**
   * Returns {@code true} if the ship was sunk.
   *
   * @return {@code true} if the ship was sunk
   */
  boolean isSunk();

  /**
   * Switches the direction of the ship if possible.
   * @param direction {@link ShipDirection}
   */
  void switchDirection(ShipDirection direction);
}
