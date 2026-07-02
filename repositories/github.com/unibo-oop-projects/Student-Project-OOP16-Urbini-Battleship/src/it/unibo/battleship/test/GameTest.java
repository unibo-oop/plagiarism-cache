/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.test;

import it.unibo.battleship.main.common.FieldBound;
import it.unibo.battleship.main.common.FieldBoundImpl;
import it.unibo.battleship.main.common.Ruleset;
import it.unibo.battleship.main.entity.extra.AbstractArtificialIntelligence;
import it.unibo.battleship.main.entity.extra.ArtificialIntelligence;
import it.unibo.battleship.main.control.BattleshipControlImpl;
import it.unibo.battleship.main.entity.map.Field;
import it.unibo.battleship.main.entity.map.FieldImpl;
import it.unibo.battleship.main.entity.ships.Fleet;
import it.unibo.battleship.main.entity.ships.FleetFactoryImpl;
import it.unibo.battleship.main.entity.ships.Ship;
import it.unibo.battleship.main.entity.ships.ShipFactoryImpl;
import it.unibo.battleship.main.entity.shots.Shot;
import it.unibo.battleship.main.entity.shots.ShotImpl;
import org.junit.Before;
import org.junit.Test;

import static it.unibo.battleship.main.common.Point2dHelper.createPoint2d;
import static org.junit.Assert.assertTrue;

/**
 * This test is designed to test some of the game features,
 * partially.
 * @author fabio.urbini
 */
public final class GameTest {
  /*
   * These tests are designed to check if a game works properly.
   * It won't test all classes designed.
   * todo: rename methods, adds throws Exception signature
   * todo: make specific packages for tests, one for each feature if possible
   */
  ArtificialIntelligence ai;
  Fleet aiFleet;
  Field aiField;

  @Before
  public void initialize() {
    ai = AbstractArtificialIntelligence.createArtificialIntelligence(
        AbstractArtificialIntelligence.Level.EASY,
        Ruleset.FIELD_BOUND
    );
    aiFleet = ai.getFleetFactory().createFleet();
    aiField = FieldImpl.createField(Ruleset.FIELD_BOUND);
    BattleshipControlImpl.placeFleetDiagonally(aiField, aiFleet);
  }

  @Test
  public void createShots() {
    final FieldBound bounds = FieldBoundImpl.createBoundary(10, 10);
    final int maxIndex = bounds.getColumnsCount() * bounds.getRowsCount();
    for (int i = 0; i < maxIndex; i++) {
      final Shot s = ShotImpl.createShot(createPoint2d(i));
      assertTrue(Ruleset.isPointWithinLimits(s.getPoint()));
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void createShips() {
    final Fleet f = FleetFactoryImpl.INSTANCE.createFleet();
    f.getNextNonPlacedShip().ifPresent(
        (Ship ship) -> ship.place(createPoint2d(Ruleset.FIELD_BOUND.getSize()))
    );
  }

  @Test
  public void createAiFleet() {
    assertTrue(aiFleet.isReady());
  }

  @Test
  public void hitShips() {
    while (!this.aiFleet.isSunk()) {
      final Shot shot = ai.getShotFactory().createShot();
      this.aiField.updateStateWithShot(shot);
    }
    assertTrue(this.aiFleet.isSunk());
  }

  @Test(expected = IllegalArgumentException.class)
  public void createShip() {
    ShipFactoryImpl.INSTANCE.createShip(
        Ruleset.ShipRules.AIR_CARRIER.getSize() + 1
    );
  }
}
