/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.entity.extra;

import it.unibo.battleship.main.common.FieldBound;
import it.unibo.battleship.main.entity.ships.FleetFactory;
import it.unibo.battleship.main.entity.shots.ShotFactory;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.io.Serializable;

/**
 * Represents an Artificial Intelligence which can be used for creating a new
 * Fleet or for creating new shots. The strategy is decided by implementations.
 *
 * @author fabio.urbini
 */
@Immutable
public interface ArtificialIntelligence extends Serializable {

  /**
   * Returns a {@link FleetFactory}.
   * @return a {@link FleetFactory}
   */
  FleetFactory getFleetFactory();

  /**
   * Returns a {@link ShotFactory}.
   * @return a {@link ShotFactory}
   */
  ShotFactory getShotFactory();

  /**
   * Returns the boundary.
   * @return the boundary.
   */
  FieldBound getFieldBound();
}
