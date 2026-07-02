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
import it.unibo.battleship.main.common.Point2d;
import it.unibo.battleship.main.common.Point2dImpl;
import it.unibo.battleship.main.entity.shots.Shot;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This abstract class is to represent a Ship.
 *
 * @author fabio.urbini
 */
public abstract class AbstractShip implements Ship {
  /*
   * This class hasn't got any abstract method yet except for toString. It was
   * made abstract for future purposes. ie: a ship implementation can take
   * more/less damage than other ships.
   */
  private static final long serialVersionUID = 4917741645660917676L;
  private ShipDirection direction;
  private Point2d pos;
  private boolean placed;
  private final List<Point2d> hitPoints;

  protected AbstractShip() {
    this.placed = false;
    this.direction = ShipDirection.EAST;
    this.hitPoints = new ArrayList<>();
  }

  protected AbstractShip(final Point2d startingPosition) {
    this();
    this.checkStartingPositionNullity(startingPosition);
    this.pos = startingPosition;
    this.placed = true;
  }

  protected AbstractShip(final Point2d startingPosition,
                         final ShipDirection direction) {
    this(startingPosition);
    this.direction = direction;
  }

  @Override
  public boolean containsPosition(final Point2d point) {
    return this
        .getAllPositions()
        .stream()
        .anyMatch(
            p -> ((p.getX() == point.getX()) && (p.getY() == point.getY())));
  }

  @Override
  public void place(final Point2d startingPosition,
      final ShipDirection direction) {
    this.checkStartingPositionNullity(startingPosition);

    if (!this.placed) {
      this.placed = true;
    }
    this.pos = startingPosition;
    this.direction = direction;
  }

  @Override
  public void place(final Point2d startingPosition) {
    this.place(startingPosition, ShipDirection.EAST);
  }

  @Override
  public void resetPlacement() {
    this.placed = false;
    this.pos = null; // consider refactoring to Optional
  }

  @Override
  public boolean shoot(final Shot shot) {
    if (this.containsPosition(shot.getPoint())) {
      this.hitPoints.add(shot.getPoint());
      return true;
    }
    return false;
  }

  @Override
  public List<Point2d> getAllPositions() {
    // TODO: Use ship direction. Currently using ShipDirection.EAST
    this.checkStartingPositionNullity(this.pos);
    return IntStream.range(0, this.getSize())
        .mapToObj(i -> new Point2dImpl(this.pos.getX() + i, this.pos.getY()))
        .collect(Collectors.toList());
  }

  @Override
  public boolean isPlaced() {
    return this.placed;
  }

  @Override
  public Optional<Point2d> getStartingPosition() {
    return Optional.ofNullable(this.pos);
  }

  @Override
  public List<Point2d> getProjectionPoints(final Point2d point) {
    // TODO: Use ship direction. Currently using ShipDirection.EAST

    return IntStream.range(point.getX(), (point.getX() + this.getSize()))
        .mapToObj(x -> new Point2dImpl(x, point.getY()))
        .collect(Collectors.toList());
  }

  @Override
  public boolean isSunk() {
    return this.hitPoints.size() >= this.getSize();
    // TODO: make an event in the future
  }

  /**
   * Not supported yet.
   * @param direction {@link ShipDirection}
   * @throws UnsupportedOperationException unsupported
   */
  @Override
  public void switchDirection(final ShipDirection direction) {
    if (this.direction != direction) {
      // TODO: switch direction, not implemented yet
      throw new UnsupportedOperationException("Operation not implemented yet");
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if ((o == null) || (this.getClass() != o.getClass())) {
      return false;
    }

    final AbstractShip that = (AbstractShip) o;

    return Objects.equal(this.direction, that.direction)
        && Objects.equal(this.pos, that.pos)
        && Objects.equal(this.placed, that.placed)
        && Objects.equal(this.hitPoints, that.hitPoints);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.direction, this.pos, this.placed,
        this.hitPoints);
  }

  @Override
  public abstract String toString();

  private void checkStartingPositionNullity(final Point2d startingPosition) {
    if (startingPosition == null) {
      throw new IllegalArgumentException(
          GlobalProperties.STARTING_POSITION_NOT_DEFINED);
    }
  }
}
