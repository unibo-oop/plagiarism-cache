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
 * Represents a 2 dimension point.
 *
 * @author fabio.urbini
 */
@Immutable
public interface Point2d extends Serializable {

  /**
   * Returns the X coordinate (column).
   *
   * @return the x coordinate (column)
   */
  int getX();

  /**
   * Returns the y coordinate (row).
   *
   * @return the y coordinate (row)
   */
  int getY();

  /**
   * Returns a new Point2d instance with x incremented by 1.
   * @return a new Point2d instance with x incremented by 1.
   */
  Point2d incrementX();

  /**
   * Returns a new Point2d instance with y incremented by 1.
   * @return a new Point2d instance with y incremented by 1.
   */
  Point2d incrementY();
}
