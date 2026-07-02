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
 * Implementation of a 2 dimension point {@link Point2d}.
 *
 * @author fabio.urbini
 */
@Immutable
public final class Point2dImpl implements Point2d {
  private static final long serialVersionUID = -551958507759199801L;
  private final int x;
  private final int y;

  /**
   * @param x
   *          column
   * @param y
   *          row
   */
  public Point2dImpl(final int x, final int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public Point2dImpl incrementX() {
    return new Point2dImpl(this.x + 1, this.y);
  }

  @Override
  public Point2dImpl incrementY() {
    return new Point2dImpl(this.x, this.y + 1);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if ((o == null) || (this.getClass() != o.getClass())) {
      return false;
    }

    final Point2dImpl that = (Point2dImpl) o;

    return Objects.equal(this.x, that.x) && Objects.equal(this.y, that.y);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.x, this.y);
  }

  @Override
  public String toString() {
    return " x = " + this.x + ", y = " + this.y;
  }

}
