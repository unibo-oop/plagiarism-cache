/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.entity.ships;

import it.unibo.battleship.main.common.GlobalProperties;
import it.unibo.battleship.main.common.Point2d;
import it.unibo.battleship.main.common.Ruleset.ShipRules;

import static it.unibo.battleship.main.common.GlobalProperties.*;

/**
 * Implementation of a {@link ShipFactory}. There is a single instance.
 *
 * @author fabio.urbini
 */
public enum ShipFactoryImpl implements ShipFactory {
  /** Singleton of ShipFactoryImpl */
  INSTANCE;

  private static final long serialVersionUID = -1375681121821315440L;

  /**
   * Creates a ship.
   * @param size
   *          size of the ship. The value must be between 0 and {@link
   *          GlobalProperties#MAX_SIZE}
   * @return the ship created
   * @throws IllegalArgumentException if the size is invalid
   */
  @Override
  public Ship createShip(final int size) {
    this.checkSize(size);

    switch (size) {
    case SUBMARINE_SIZE:
      return new Submarine();

    case CRUISER_SIZE:
      return new Cruiser();

    case BATTLESHIP_SIZE:
      return new Battleship();

    case AIR_CARRIER_SIZE:
      return new AirCarrier();

    default:
      throw new IllegalArgumentException(this.getExceptionMessage(size));
    }
  }

  private void checkSize(final int size) {
    if ((size < 0) || (size > MAX_SIZE)) {
      throw new IllegalArgumentException(this.getExceptionMessage(size));
    }
  }

  private String getExceptionMessage(final int size) {
    return INVALID_SHIP_SIZE + " current size " + size +
        " - Maximum size : " + MAX_SIZE;
  }


  /*
   * Private concrete classes which extend AbstractShip.
   * Actually there's little use of these classes, so depending on
   * new features/ideas, they can evolve or turn into anonymous classes.
   * There might be one single concrete Ship and different instances, like
   * ShipImpl with private fields (size, name).
   */
  private static final class AirCarrier extends AbstractShip {
    private static final long serialVersionUID = -8323321815851042898L;

    private AirCarrier() {
      super();
    }

    @SuppressWarnings("unused")
    private AirCarrier(final Point2d start) {
      super(start);
    }

    @Override
    public String toString() {
      return ShipRules.AIR_CARRIER.toString();
    }

    @Override
    public int getSize() {
      return AIR_CARRIER_SIZE;
    }
  }

  private static final class Battleship extends AbstractShip {
    private static final long serialVersionUID = 8043537272411631772L;

    private Battleship() {
      super();
    }

    @SuppressWarnings("unused")
    private Battleship(final Point2d start) {
      super(start);
    }

    @Override
    public String toString() {
      return ShipRules.BATTLESHIP.toString();
    }

    @Override
    public int getSize() {
      // return GlobalProperties.ShipRules.BATTLESHIP.getSize();
      return BATTLESHIP_SIZE;
    }
  }

  private static final class Cruiser extends AbstractShip {
    private static final long serialVersionUID = -5532557604937632667L;

    private Cruiser() {
      super();
    }

    @SuppressWarnings("unused")
    private Cruiser(final Point2d start) {
      super(start);
    }

    @Override
    public String toString() {
      return ShipRules.CRUISER.toString();
    }

    @Override
    public int getSize() {
      // return GlobalProperties.ShipRules.CRUISER.getSize();
      return CRUISER_SIZE;
    }
  }

  private static final class Submarine extends AbstractShip {
    private static final long serialVersionUID = -2784639518931814680L;

    private Submarine() {
      super();
    }

    @SuppressWarnings("unused")
    private Submarine(final Point2d start) {
      super(start);
    }

    @Override
    public String toString() {
      return ShipRules.SUBMARINE.toString();
    }

    @Override
    public int getSize() {
      // return GlobalProperties.ShipRules.SUBMARINE.getSize();
      return SUBMARINE_SIZE;
    }
  }

  /*
  Consider creating ShipImpl and make instances of different ships (size, name)
  instead of using static nested classes.
   */
}
