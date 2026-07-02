/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.common;

import com.google.common.base.Objects;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * Implementation of {@link FieldBound}
 */
@Immutable
public final class FieldBoundImpl implements FieldBound {
  private static final long serialVersionUID = -5121855953223117935L;
  private final int columnsCount;
  private final int rowsCount;

  private FieldBoundImpl(final int columnsNumber, final int rowsNumber) {
    this.columnsCount = columnsNumber;
    this.rowsCount = rowsNumber;
  }

  /**
   * Creates a boundary.
   *
   * @param columnsCount
   *          Columns count
   * @param rowsCount
   *          Rows count
   * @return a {@link FieldBoundImpl}
   * @throws IllegalArgumentException if values are negative
   */
  public static FieldBoundImpl createBoundary(final int columnsCount,
                                              final int rowsCount) {
    checkParametersValidity(columnsCount, rowsCount);
    return new FieldBoundImpl(columnsCount, rowsCount);
  }

  private static void checkParametersValidity(final int columnsCount, final int rowsCount) {
    if (columnsCount < 0 || rowsCount < 0) {
      throw new IllegalArgumentException(
          GlobalProperties.BOUNDARY_VALUE_IS_NEGATIVE);
    }
  }

  @Override
  public int getColumnsCount() {
    return this.columnsCount;
  }

  @Override
  public int getRowsCount() {
    return this.rowsCount;
  }

  @Override
  public int getSize() {
    return this.rowsCount * this.columnsCount;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if ((o == null) || (this.getClass() != o.getClass())) {
      return false;
    }

    final FieldBoundImpl that = (FieldBoundImpl) o;

    return Objects.equal(this.columnsCount, that.columnsCount)
        && Objects.equal(this.rowsCount, that.rowsCount);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.columnsCount, this.rowsCount);
  }

  @Override
  public String toString() {
    return " x : " + this.columnsCount + " y : " + this.rowsCount;
  }


}
