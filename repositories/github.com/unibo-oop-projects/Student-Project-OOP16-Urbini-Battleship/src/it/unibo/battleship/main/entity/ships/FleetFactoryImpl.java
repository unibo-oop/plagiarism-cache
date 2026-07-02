/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.entity.ships;

import com.google.common.base.Objects;
import it.unibo.battleship.main.common.GlobalProperties;
import it.unibo.battleship.main.common.Ruleset.ShipRules;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of a Fleet Factory
 *
 * @author fabio.urbini
 */
public enum FleetFactoryImpl implements FleetFactory {
  INSTANCE;

  private static final long serialVersionUID = -3012942531548787956L;

  @Override
  public Fleet createFleet() {
    final Fleet fleet = new FleetImpl();

    for (int i = 0; i < ShipRules.SUBMARINE.getInstancesNumber(); i++) {
      fleet.addShip(fleet.getFactory().createShip(
          GlobalProperties.SUBMARINE_SIZE));
    }

    for (int i = 0; i < ShipRules.CRUISER.getInstancesNumber(); i++) {
      fleet.addShip(fleet.getFactory()
          .createShip(GlobalProperties.CRUISER_SIZE));
    }

    for (int i = 0; i < ShipRules.BATTLESHIP.getInstancesNumber(); i++) {
      fleet.addShip(fleet.getFactory().createShip(
          GlobalProperties.BATTLESHIP_SIZE));
    }

    return fleet;
  }

  /**
   * Private implementation of a Fleet
   */
  private static final class FleetImpl implements Fleet {

    private static final long serialVersionUID = -5734887827627519552L;
    private final List<Ship> ships;

    private FleetImpl() {
      this.ships = new ArrayList<>();
    }

    @Override
    public void addShip(final Ship s) {
      this.ships.add(s);
    }

    @Override
    public void resetFleetPlacement() {
      this.getAllShips().forEach(Ship::resetPlacement);
    }

    @Override
    public List<Ship> getAllNonPlacedShips() {
      final List<Ship> nonPlacedShips = this.getAllShips()
          .stream()
          .filter(ship -> !ship.isPlaced())
          .collect(Collectors.toList());

      return Collections.unmodifiableList(nonPlacedShips);
    }

    @Override
    public List<Ship> getAllShips() {
      return Collections.unmodifiableList(this.ships);
    }

    @Override
    public List<Ship> getAllShipsByType(final ShipRules shipType) {
      // todo toString filter is not flexible, consider refactoring
      final List<Ship> ships = this.ships
          .stream()
          .filter(ship -> ship.toString().equals(shipType.toString()))
          .filter(ship -> !ship.isPlaced()).collect(Collectors.toList());

      return Collections.unmodifiableList(ships);
    }

    @Override
    public Optional<Ship> getNextNonPlacedShip() {
      Optional<Ship> ship = Optional.empty();

      if (!this.getAllNonPlacedShips().isEmpty()) {
        ship = Optional.of(this.getAllNonPlacedShips().get(0));
      }

      return ship;
    }

    @Override
    public Optional<Ship> getNextShipByType(final ShipRules shipType) {
      if (!this.getAllShipsByType(shipType).isEmpty()) {
        return Optional.of(this.getAllShipsByType(shipType).get(0));
      }
      return Optional.empty();
    }

    @Override
    public boolean isReady() {
      return this.ships.stream().allMatch(Ship::isPlaced);
    }

    @Override
    public boolean isSunk() {
      return this.ships.stream().allMatch(Ship::isSunk);
    }

    @Override
    public ShipFactory getFactory() {
      return ShipFactoryImpl.INSTANCE;
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) {
        return true;
      }

      if ((o == null) || (this.getClass() != o.getClass())) {
        return false;
      }

      final FleetImpl that = (FleetImpl) o;

      return Objects.equal(this.ships, that.ships);
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(this.ships);
    }

    @Override
    public String toString() {
      return "FleetImpl [isReady()=" + this.isReady() + ", isSunk()="
          + this.isSunk() + ']';
    }
  }
}
