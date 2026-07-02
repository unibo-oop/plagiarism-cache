/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.common;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.io.Serializable;

/**
 * Represents the bound of a field in the
 * vertical and horizontal direction.
 *
 * @author fabio.urbini
 */
@Immutable
public interface FieldBound extends Serializable {

  /**
   * Returns the horizontal bound, which is also the number of columns.
   *
   * @return the Horizontal bound (x)
   */
  int getColumnsCount();

  /**
   * Returns the vertical bound, which is also the number of rows.
   *
   * @return the Vertical bound (y)
   */
  int getRowsCount();

  /**
   * Returns the size of the boundary.
   * @return the size of the boundary.
   */
  int getSize();
}
