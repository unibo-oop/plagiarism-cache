/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.entity.shots;

import com.google.common.base.Objects;
import it.unibo.battleship.main.common.Point2d;
import it.unibo.battleship.main.common.Point2dHelper;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * Implementation of a Shot.
 * @author fabio.urbini
 */
@Immutable
public final class ShotImpl implements Shot {
  private static final long serialVersionUID = 7357162445793535579L;
  private final Point2d point;

  private ShotImpl(final Point2d p) {
    this.point = p;
  }

  /**
   * Creates a {@code ShotImpl}.
   * @param point a valid point which must be within the Boundary limit
   * {@link it.unibo.battleship.main.common.Ruleset#FIELD_BOUND}
   * @return a {@code ShotImpl}.
   */
  public static ShotImpl createShot(final Point2d point) {
    Point2dHelper.checkPointWithinBoundaryLimits(point);
    return new ShotImpl(point);
  }

  @Override
  public Point2d getPoint() {
    return this.point;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if ((o == null) || (this.getClass() != o.getClass())) {
      return false;
    }

    final ShotImpl that = (ShotImpl) o;

    return Objects.equal(this.point, that.point);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.point);
  }

  @Override
  public String toString() {
    return this.point.toString();
  }

}
