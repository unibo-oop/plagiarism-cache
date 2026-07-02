/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.entity.map;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import it.unibo.battleship.main.common.GlobalProperties;
import it.unibo.battleship.main.entity.ships.Ship;
import it.unibo.battleship.main.entity.shots.Shot;

import java.util.Optional;

/**
 * An implementation of a {@link FieldCell}.
 *
 * @author fabio.urbini
 */
public final class FieldCellImpl implements FieldCell {
  private static final long serialVersionUID = 188175020723853008L;
  private State currentState;
  private Ship ship;

  public FieldCellImpl() {
    this.currentState = State.WATER;
  }

  @Override
  public void placeShip(final Ship ship) {
    if (ship == null) {
      throw new IllegalArgumentException(GlobalProperties.NULL_REFERENCE);
    }
    this.ship = ship;
    this.currentState = State.PRESENT;
  }

  @Override
  public void shoot(final Shot shot) {
    if (shot == null) {
      throw new IllegalArgumentException(GlobalProperties.NULL_REFERENCE);
    }

    switch (this.currentState) {
    case WATER:
      this.currentState = State.MISSED;
      break;

    case MISSED:
      break; // Exception?

    case PRESENT:
      if (this.ship != null) {
        this.currentState = State.HIT;
        this.ship.shoot(shot);
      }
      break;

    case HIT:
      break; // Exception?

    default:
      throw new IllegalArgumentException("Invalid state value");
    }
  }

  @Override
  public boolean isEmpty() {
    return this.currentState == State.WATER;
  }

  @Override
  public boolean isHit() {
    return this.currentState == State.HIT;
  }

  @Override
  public boolean isMissed() {
    return this.currentState == State.MISSED;
  }

  @Override
  public boolean isPresent() {
    return this.currentState == State.PRESENT;
  }

  @Override
  public Optional<Ship> getShip() {
    return Optional.ofNullable(this.ship);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if ((o == null) || (this.getClass() != o.getClass())) {
      return false;
    }

    final FieldCellImpl that = (FieldCellImpl) o;

    return Objects.equal(this.currentState, that.currentState)
        && Objects.equal(this.ship, that.ship);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.currentState, this.ship);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("currentState", this.currentState).add("ship", this.ship)
        .toString();
  }

}
