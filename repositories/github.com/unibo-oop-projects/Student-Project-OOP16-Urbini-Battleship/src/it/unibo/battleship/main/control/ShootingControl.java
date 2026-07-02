/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.control;

import it.unibo.battleship.main.common.Point2d;

/**
 * Provides the control for shooting.
 * @author fabio.urbini
 */
public interface ShootingControl {

  void shoot(Point2d shootingPoint);
}
