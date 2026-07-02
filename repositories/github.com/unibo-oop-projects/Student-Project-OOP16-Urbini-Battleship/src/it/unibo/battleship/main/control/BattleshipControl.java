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

import java.util.List;

/**
 * Battleship control for a Human vs AI game.
 * @author fabio.urbini
 */
public interface BattleshipControl {

  /**
   * Returns {@code true} if the game is not finished yet.
   * @return {@code true} if the game is not finished yet.
   */
  boolean isGameNotFinished();

  /**
   * Returns {@code true} if the player fleet is sunk.
   * @return {@code true} if the player fleet is sunk.
   */
  boolean isPlayerFleetSunk();

  /**
   * Returns {@code true} if the AI fleet is sunk.
   * @return {@code true} if the AI fleet is sunk.
   */
  boolean isAiFleetSunk();

  /**
   * Tries to shoot the current cell selected.
   * @param point a valid point
   * @throws IllegalArgumentException if point isn't valid for the
   * current boundary.
   */
  void shootAiField(Point2d point);

  /**
   * Tries to shoot the player field. It will generate a
   * suitable {@link Point2d}.
   */
  void shootPlayerField();

  /**
   * Get the proper field view depending on the player who owns it and
   * who is actually accessing it.
   * @param playerType the type of player who owns the field.
   * @param viewerType who is accessing the field.
   * @return the field view.
   */
  List<List<Character>> getFieldView(PlayerType playerType, ViewerType viewerType);

  /**
   * Returns the actual boundary used.
   * @return the actual boundary used.
   */
  FieldBound getBoundary();

  /**
   * Returns the name of the next placeable ship.
   * @return the name of the next placeable ship.
   */
  String getNextPlaceableShip();

  /**
   * Tries to place the next placeable ship on the player
   * field.
   * @param startingPosition the position where the ship will start.
   * @throws IllegalArgumentException if the Point2d is invalid
   */
  void placeNextPlaceableShip(Point2d startingPosition);

  /**
   * Returns {@code true} if the player fleet isn't placed yet.
   * @return {@code true} if the player fleet isn't placed yet.
   */
  boolean isPlayerFleetNotPlaced();

  /**
   * Returns {@code true if the point is within the boundary limits},
   * false otherwise.
   * @param point the point to check
   * @return {@code true if the point is within the boundary limits}.
   */
  boolean isPointWithinBoundaryLimits(Point2d point);

  /**
   * Sets the AI level to super easy
   * @throws IllegalStateException if the level was setup already
   */
  void setUpAiLevelSuperEasy();

  /**
   * Sets the AI level to easy
   * @throws IllegalStateException if the level was setup already
   */
  void setUpAiLevelEasy();

  /**
   * Initializes the Ai
   * @throws IllegalStateException if the Ai was setup already
   */
  void initializeAi();

  /**
   * Player type enumeration for Human and Ai game.
   */
  enum PlayerType {
    /** Human type of player */
    HUMAN,

    /** Artificial intelligence / computer */
    AI
  }

  /**
   * Represents the types of viewers
   */
  enum ViewerType {
    /** The viewer of the field is the owner himself */
    OWNER,

    /** The viewer of the field is the enemy */
    ENEMY
  }
}
