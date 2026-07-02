/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.common;

import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * Contains the global properties of the battleship game, including ship rules.
 *
 * @author fabio.urbini
 */
@Immutable
public final class GlobalProperties {
  public static final int MAX_SIZE = 5;
  public static final int SUBMARINE_SIZE = 1;
  public static final int CRUISER_SIZE = 2;
  public static final int BATTLESHIP_SIZE = 3;
  public static final int AIR_CARRIER_SIZE = 4;
  public static final String POINT_NOT_WITHIN_LIMITS = "Point not defined within the map limit.";
  public static final String INDEX_NOT_WITHIN_LIMITS = "Index not defined within the boundary.";
  public static final String FIELD_CELLS_NOT_EMPTY = "Field cells are not empty.";
  public static final String SHIP_NOT_WITHIN_LIMITS = "Ship points are not within the boundary of the field.";
  public static final String BOUNDARY_VALUE_IS_NEGATIVE = "Boundary values should be positive (greater than 0).";
  public static final String INVALID_SHOT_TYPE = "Invalid shot type.";
  public static final String INVALID_AI_LEVEL = "Invalid artificial intelligence level.";
  public static final String INVALID_SHIP_SIZE = "Invalid ship size.";
  public static final String INVALID_GENERATED_SHOTS_STATE = "Shots generated were more than the actual map size.";
  public static final String STARTING_POSITION_NOT_DEFINED = "Starting position wasn't defined yet.";
  public static final String NULL_REFERENCE = "A null reference was passed to the method.";

  private GlobalProperties() {
  }
}
