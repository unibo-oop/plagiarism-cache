/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.entity.map;

import com.google.common.base.Objects;
import it.unibo.battleship.main.common.*;
import it.unibo.battleship.main.entity.ships.Ship;
import it.unibo.battleship.main.entity.ships.ShipDirection;
import it.unibo.battleship.main.entity.shots.Shot;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * Implementation of a Field.
 * @author fabio.urbini
 */
public final class FieldImpl implements Field {
  private static final long serialVersionUID = 4129869956647585285L;
  /*
   * The field cell matrix is like the first quadrant of the cartesian plane,
   * seen upside down.
   */
  private final FieldBound fieldBound;
  private final transient FieldMatrix fieldMatrix;

  private FieldImpl(final FieldBound fieldBound) {
    this.fieldBound = fieldBound;
    this.fieldMatrix = new FieldMatrix(fieldBound);
  }

  public static FieldImpl createField(final FieldBound fieldBound) {
    if ((fieldBound.getColumnsCount() < 0) || (fieldBound.getRowsCount() < 0)) {
      throw new IllegalArgumentException(
          GlobalProperties.BOUNDARY_VALUE_IS_NEGATIVE);
    }
    return new FieldImpl(fieldBound);
  }

  @Override
  public void placeShip(final Ship ship, final Point2d point,
      final ShipDirection direction) {
    this.checkShipPlacement(ship, point);
    ship.place(point, direction);

    for (final Point2d p : ship.getAllPositions()) {
      this.fieldMatrix.getAt(p).placeShip(ship);
    }
  }

  @Override
  public void placeShip(final Ship ship, final Point2d point) {
    this.placeShip(ship, point, ShipDirection.EAST);
  }

  @Override
  public void updateStateWithShot(final Shot shot) {
    Point2dHelper.checkPointWithinBoundaryLimits(shot.getPoint());

    final Point2d p = shot.getPoint();
    this.fieldMatrix.getAt(p).shoot(shot);
  }

  private void checkShipPlacement(final Ship ship, final Point2d point) {
    Point2dHelper.checkPointWithinBoundaryLimits(point);
    this.checkShipPlaceable(ship, point);
    this.checkShipWithinLimits(ship, point);
  }

  private void checkShipWithinLimits(final Ship ship, final Point2d point) {
    if (!Ruleset.isShipWithinLimits(ship, point)) {
      throw new IllegalArgumentException(
          // TODO: add more details
          GlobalProperties.SHIP_NOT_WITHIN_LIMITS
      );
    }
  }

  private void checkShipPlaceable(final Ship ship, final Point2d point) {
    if (!this.isShipPlaceable(ship, point)) {
      throw new IllegalArgumentException(
          // ToDO: add more details to the exception
          GlobalProperties.FIELD_CELLS_NOT_EMPTY
      );
    }
  }

  @Override
  public FieldBound getFieldBound() {
    return this.fieldBound;
  }

  public FieldCell[][] getFieldCells() {
    return this.fieldMatrix.getMatrix();
  }

  public boolean isShipPlaceable(final Ship ship, final Point2d point) {
    return ship
        .getProjectionPoints(point)
        .stream()
        .allMatch(p -> {
          Point2dHelper.checkPointWithinBoundaryLimits(p);
          return this.fieldMatrix.getAt(p).isEmpty();
        });
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o)
      return true;
    if (o == null || this.getClass() != o.getClass())
      return false;

    final FieldImpl that = (FieldImpl) o;

    return Objects.equal(this.fieldMatrix, that.fieldMatrix)
        && Objects.equal(this.fieldBound, that.fieldBound);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.fieldMatrix, this.fieldBound);
  }

  @Override
  public String toString() {
    return "Field { " + this.fieldBound.getRowsCount() + " rows }; { " +
        this.fieldBound.getColumnsCount() + " columns } ";
  }

  private static class FieldMatrix {
    private final FieldCell[] fieldCells;
    private final FieldBound fieldBound;

    private FieldMatrix(final FieldBound fieldBound) {
      this.fieldBound = fieldBound;
      this.fieldCells = new FieldCell[fieldBound.getSize()];
      this.initialize();
    }

    private FieldCell getAt(final int zeroBasedIndex) {
      return this.fieldCells[zeroBasedIndex];
    }

    private FieldCell getAt(final Point2d point) {
      final int idx = Point2dHelper.getIndex(point);
      return this.getAt(idx);
    }

    private FieldCell[][] getMatrix() {
      final int rows = this.fieldBound.getRowsCount();
      final int cols = this.fieldBound.getColumnsCount();
      final FieldCell[][] matrix = new FieldCell[rows][cols];

      for (int row = 0; row < rows; row++) {
        for (int column = 0; column < cols; column++) {
          final Point2d p = new Point2dImpl(column, row);
          final int idx = Point2dHelper.getIndex(p);
          matrix[row][column] = this.fieldCells[idx];
        }
      }
      return matrix;
    }

    private void initialize() {
      for (int i = 0; i < this.fieldBound.getSize(); i++) {
        this.fieldCells[i] = new FieldCellImpl();
      }
    }
  }
}
