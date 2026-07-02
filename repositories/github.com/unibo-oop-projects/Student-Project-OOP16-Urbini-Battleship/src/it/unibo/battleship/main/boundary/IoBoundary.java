/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.boundary;

/**
 * Represents the I/O Boundary, which is supposed to use the console,
 * or a simple i/o system.
 * @author fabio.urbini
 */
public interface IoBoundary {

  /**
   * Returns a reply after asking a question.
   * @param question question to be asked.
   * @return a reply.
   */
  String ask(String question);

  /**
   * Shows a message.
   * @param message the message to be shown.
   */
  void showMessage(String message);

  /**
   * Simply quits.
   */
  void quit();
}
