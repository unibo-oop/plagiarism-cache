/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.gui;

import it.unibo.battleship.main.common.Point2d;

import javax.swing.*;

/**
 * Wrapper of a JButton.
 *
 * @author fabio
 *
 */
public final class FieldButton {
  private final JButton btn;
  private final Point2d point;

  public FieldButton(final Point2d point) {
    this.point = point;
    this.btn = new JButton("" + point.getX() + ':' + point.getY());
  }

  /**
   * Returns the JButton contained in the object.
   *
   * @return the JButton
   */
  public JButton getButton() {
    return this.btn;
  }

  /**
   * Returns the {@link Point2d}.
   * @return the {@link Point2d}
   */
  public Point2d getPosition() {
    return this.point;
  }
}
