/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.common;

import it.unibo.battleship.main.entity.ships.Ship;
import java.util.stream.IntStream;
import static it.unibo.battleship.main.common.GlobalProperties.*;

/**
 * Represents the ruleset of the battlefield game. Some rules can be changed
 * manually.
 */
public final class Ruleset {
  // TODO: make instances of this class in the future, and make it immutable
  public static final FieldBound FIELD_BOUND = FieldBoundImpl.createBoundary(10, 10);
  public static final boolean SHOOT_AGAIN_AFTER_HIT = false;

  private Ruleset() {
  }

  /**
   * Returns {@code true} if the point is within the limits of the boundary,
   * {@code false} otherwise.
   *
   * @param p
   *          a {@link Point2d}
   * @return {@code true} if the point is within the limits
   */
  public static boolean isPointWithinLimits(final Point2d p) {
    return ((p.getY() >= 0) && (p.getY() < FIELD_BOUND.getRowsCount()))
        && ((p.getX() >= 0) && (p.getX() < FIELD_BOUND.getColumnsCount()));
  }

  /**
   * Returns {@code true} if the ship is within the limits of the boundary,
   * {@code false} otherwise. It currently works with standard direction,
   * EAST.
   *
   * @param ship
   *          {@link Ship}
   * @param point
   *          the starting position of the ship
   * @return {@code true} if the ship is within the limits of the boundary
   */
  public static boolean isShipWithinLimits(final Ship ship, final Point2d point) {
    /*
     * todo: refactor in the future to permit using other directions
     * Point2dHelper for the purpose
     */
    final int length = ship.getSize();

    return IntStream.range(point.getX(), (point.getX() + length)).allMatch(
        x -> Ruleset.isPointWithinLimits(new Point2dImpl(x, point.getY())));
  }


  public enum ShipRules {
    SUBMARINE("Submarine", SUBMARINE_SIZE, 3),
    CRUISER("Cruiser", CRUISER_SIZE, 2),
    BATTLESHIP("Battleship", BATTLESHIP_SIZE, 2),
    AIR_CARRIER("Air Carrier", AIR_CARRIER_SIZE, 0);

    // TODO: extract this enum and make a class (?)
    private final String name;
    private final int size;
    private final int instancesNumber;

    ShipRules(final String name, final int size, final int instancesNumber) {
      this.name = name;
      this.size = size;
      this.instancesNumber = instancesNumber;
    }

    @Override
    public String toString() {
      return this.name;
    }

    public int getInstancesNumber() {
      return this.instancesNumber;
    }

    public int getSize() {
      return this.size;
    }
  }

}
