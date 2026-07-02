/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.entity.ships;

import jdk.nashorn.internal.ir.annotations.Immutable;
import java.io.Serializable;

/**
 * Represents a Fleet Factory. It can be used to instantiate new fleets.
 *
 * @author fabio.urbini
 */
@FunctionalInterface
@Immutable
public interface FleetFactory extends Serializable {
  /**
   * Creates a Fleet.
   * @return a Fleet
   */
  Fleet createFleet();
}
