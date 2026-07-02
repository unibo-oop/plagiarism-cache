/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.control;

import it.unibo.battleship.main.common.FieldBound;
import it.unibo.battleship.main.common.Point2d;
import it.unibo.battleship.main.common.Point2dImpl;
import it.unibo.battleship.main.common.Ruleset;
import it.unibo.battleship.main.entity.extra.AbstractArtificialIntelligence;
import it.unibo.battleship.main.entity.extra.AbstractArtificialIntelligence.Level;
import it.unibo.battleship.main.entity.extra.ArtificialIntelligence;
import it.unibo.battleship.main.entity.map.Field;
import it.unibo.battleship.main.entity.map.FieldHelper;
import it.unibo.battleship.main.entity.map.FieldImpl;
import it.unibo.battleship.main.entity.ships.Fleet;
import it.unibo.battleship.main.entity.ships.FleetFactoryImpl;
import it.unibo.battleship.main.entity.ships.Ship;
import it.unibo.battleship.main.entity.shots.Shot;
import it.unibo.battleship.main.entity.shots.ShotImpl;

import java.util.List;
import java.util.Optional;

/**
 * Singleton of a control of the battleship game.
 *
 * @author fabio.urbini
 */
public enum BattleshipControlImpl implements BattleshipControl {
  CONTROLLER;

  /* TODO: create a temporary wrapper of field + fleet for each player */
  // TODO: refactor all the following methods/fields to generify
  private Fleet aiFleet;
  private Field aiField;
  private ArtificialIntelligence ai;
  private Level aiLevel;
  private boolean isSetup;

  private final Fleet playerFleet;
  private final Field playerField;

  BattleshipControlImpl() {
    this.playerFleet = FleetFactoryImpl.INSTANCE.createFleet();
    this.playerField = FieldImpl.createField(Ruleset.FIELD_BOUND);
    this.isSetup = false;
  }

  @Override
  public boolean isGameNotFinished() {
    return !this.isAiFleetSunk() && !this.isPlayerFleetSunk();
  }

  public boolean isAiFleetSunk() {
    return this.aiFleet.isSunk();
  }

  public boolean isPlayerFleetSunk() {
    return this.playerFleet.isSunk();
  }

  public void initializeAi() {
    if (!this.isSetup) {
      throw new IllegalStateException("The AI level wasn't initialized yet");
    }

    if (ai != null) {
      throw new IllegalStateException("The AI was setup already");
    }

    this.ai = AbstractArtificialIntelligence.createArtificialIntelligence(
        aiLevel,
        Ruleset.FIELD_BOUND
    );
    this.aiFleet = this.ai.getFleetFactory().createFleet();
    this.aiField = FieldImpl.createField(Ruleset.FIELD_BOUND);
    placeFleetDiagonally(this.aiField, this.aiFleet);
  }

  /**
   * Places a fleet in diagonal lines.
   * @param field the current field used.
   * @param fleet the fleet that will be placed.
   */
  public static void placeFleetDiagonally(final Field field,
                                           final Fleet fleet) {
    int i = 0, j = 0;

    while (!fleet.isReady()) {
      if (fleet.getNextNonPlacedShip().isPresent()) {
        final Ship ship = fleet.getNextNonPlacedShip().get();
        field.placeShip(ship, new Point2dImpl(i++, j++));
      }
    }
  }

  @Override
  public void shootAiField(final Point2d point) {
    final Shot shot = ShotImpl.createShot(point);
    this.aiField.updateStateWithShot(shot);
  }

  @Override
  public void shootPlayerField() {
    this.playerField.updateStateWithShot(this.ai.getShotFactory().createShot());
  }

  @Override
  public List<List<Character>> getFieldView(final PlayerType playerType, final ViewerType viewerType) {
    return this.getViewList(viewerType, this.getField(playerType));
  }

  private List<List<Character>> getViewList(final ViewerType viewerType, final Field field) {

    switch(viewerType) {
      case OWNER:
        return FieldHelper.getViewByOwner(field);
      case ENEMY:
        return FieldHelper.getViewByEnemy(field);
      default :
        throw new IllegalStateException("Invalid state for ViewerType");
    }
  }

  private Field getField(final PlayerType playerType) {
    switch(playerType) {
      case HUMAN:
        return this.playerField;
      case AI:
        return this.aiField;
      default :
        throw new IllegalStateException("Invalid state for PlayerType");
    }
  }

  @Override
  public FieldBound getBoundary() {
    return Ruleset.FIELD_BOUND;
  }

  @Override
  public String getNextPlaceableShip() {
    final Optional<Ship> ship = this.playerFleet.getNextNonPlacedShip();
    return ship.map(Object::toString).orElse("No ship left to place");
  }

  @Override
  public void placeNextPlaceableShip(final Point2d startingPosition) {
    // TODO: controllo della posizione con RULESET
    final Optional<Ship> ship = this.playerFleet.getNextNonPlacedShip();
    ship.ifPresent(ship1 -> this.playerField.placeShip(ship1, startingPosition));
  }

  @Override
  public boolean isPlayerFleetNotPlaced() {
    return !this.playerFleet.isReady();
  }

  @Override
  public void setUpAiLevelSuperEasy() {
    this.setUpAiLevel(Level.SUPER_EASY);
  }

  @Override
  public void setUpAiLevelEasy() {
    this.setUpAiLevel(Level.EASY);
  }

  @Override
  public boolean isPointWithinBoundaryLimits(final Point2d point) {
    return Ruleset.isPointWithinLimits(point);
  }

  private void setUpAiLevel(final Level level) {
    if (this.isSetup ) {
      throw new IllegalStateException("The AI Level was setup already");
    }
    this.aiLevel = level;
    this.isSetup = true;
  }
}
