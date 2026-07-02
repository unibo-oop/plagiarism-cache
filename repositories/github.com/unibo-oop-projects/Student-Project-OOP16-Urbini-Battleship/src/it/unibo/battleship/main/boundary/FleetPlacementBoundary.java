/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.boundary;

import it.unibo.battleship.main.common.Point2d;

/**
 * Represents the boundary used to place a fleet.
 * @author fabio.urbini
 */
public interface FleetPlacementBoundary extends IoBoundary {

  /**
   * Shows the fleet.
   */
  void showFleet(); // maybe showField? like ShootingBoundary

  /**
   * Places a ship in the current field.
   * @param startingPoint point where the ship will start
   */
  void placeShip(Point2d startingPoint); // todo: move to its controller

}
