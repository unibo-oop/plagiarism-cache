/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.entity.ships;

import it.unibo.battleship.main.common.Ruleset;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Represents a fleet in the battlefield. A fleet is composed of a defined
 * number of ships.
 *
 * @author fabio.urbini
 */
@Immutable
public interface Fleet extends Serializable {

  /**
   * Adds a ship to the fleet.
   *
   * @param ship
   *          ship to add
   */
  void addShip(Ship ship);

  /**
   * Resets the fleet placement.
   */
  void resetFleetPlacement();

  /**
   * Returns all non placed ships.
   *
   * @return all non placed ships
   */
  List<Ship> getAllNonPlacedShips();

  /**
   * Returns a list of all ships.
   *
   * @return a list of all ships
   */
  List<Ship> getAllShips();

  /**
   * Returns all ships of a type.
   *
   * @param shipType
   *          {@link Ruleset.ShipRules}
   * @return all ships of a type
   */
  List<Ship> getAllShipsByType(Ruleset.ShipRules shipType);

  /**
   * Returns the next non placed ship.
   *
   * @return the next non placed ship
   */
  Optional<Ship> getNextNonPlacedShip();

  /**
   * Returns the next ship of a type.
   *
   * @param shipType
   *          {@link Ruleset.ShipRules}
   * @return the next ship of a type
   */
  Optional<Ship> getNextShipByType(Ruleset.ShipRules shipType);

  /**
   * Returns {@code true} if all the ships of the current fleet were placed and
   * the game can start. {@code false} otherwise
   *
   * @return {@code true} if the fleet is ready
   */
  boolean isReady();

  /**
   * Returns {@code true} if the fleet was sunk. So all the ships it contains
   * were sunk.
   *
   * @return {@code true} if the fleet was sunk
   */
  boolean isSunk();

  /**
   * Returns a ship factory.
   *
   * @return a {@link ShipFactory}
   */
  ShipFactory getFactory();
}
