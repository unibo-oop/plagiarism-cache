/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.boundary;

import it.unibo.battleship.main.common.Point2d;
import it.unibo.battleship.main.common.Point2dImpl;
import it.unibo.battleship.main.common.Ruleset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Helper class for writing/reading using the console.
 * @author fabio.urbini
 */
public final class IoHelper {
  private final BufferedReader br;
  /*
  Make this an utility class in the future?
  Consider refactoring all methods
   */

  /**
   * Creates an instance of IoHelper
   */
  public IoHelper() {
    this.br = new BufferedReader(new InputStreamReader(System.in));
  }

  /**
   * Returns {@code true} if the value is contained in the range.
   * @param input int value
   * @param minRangeInclusive minimum range included
   * @param maxRangeInclusive maximum range included
   * @return {@code true} if the value is contained in the range.
   */
  public boolean isValid(final int input,
                                final int minRangeInclusive,
                                final int maxRangeInclusive) {
    return input <= maxRangeInclusive && input >= minRangeInclusive;
  }

  /**
   * Writes a message in the console and tries to read an int.
   * @param message message that will be printed in the console output
   * @return an int value
   */
  public int writeMessageAndReadInt(final String message) {
    System.out.println(message);
    return readInt();
  }

  /**
   * Reads an int value. It will cycle until the value is valid.
   * @return an int value.
   * @throws NumberFormatException if the input is not a number.
   */
  public int readInt() throws NumberFormatException {
    boolean check = false;
    while (!check) {
      try {
        check = true;
        return Integer.parseInt(br.readLine());
      } catch (final NumberFormatException nfe) {
        check = false;
        System.err.println("Invalid Format! It's not a number!");
        System.out.println("Reinsert value");
      } catch (final IOException e) {
        e.printStackTrace();
      }
    }

    return -1;
  }

  /**
   * Returns a valid {@link Point2d} using the console.
   * @return a valid {@link Point2d}.
   */
  public Point2d getValidPoint2d() {
    Point2d p;
    boolean pointOk;
    do {
      p = readPoint2d();
      pointOk = Ruleset.isPointWithinLimits(p);
      if (!pointOk) {
        System.out.println("Invalid values for a point, reinsert them");
      }
    } while(!pointOk);
    return p;
  }

  private Point2d readPoint2d() {
    final int row = writeMessageAndReadInt("Enter row ( y ) to create a point");
    final int column = writeMessageAndReadInt("Enter column ( x ) to create a point");
    return new Point2dImpl(column, row);
  }

  // todo: ask method (message) : String
}