/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.entity.map;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for the Field.
 *
 * @author fabio.urbini
 */
public final class FieldHelper {

  private enum PlayerState {
    OWNER, ENEMY
  }

  private FieldHelper() {
  }

  private static char getValueByPlayerState(final PlayerState playerState,
      final FieldCell cell) {
    if (cell.isEmpty()) {
      return 'E';
    }

    if (cell.isMissed()) {
      return 'M';
    }

    if (cell.isHit()) {
      return '*';
    }

    if (cell.isPresent()) {
      switch (playerState) {
      case OWNER:
        return '@';

      case ENEMY:
        return 'E';

      default:
        throw new IllegalStateException();
      }
    }

    throw new IllegalStateException("Invalid state");
  }

  /**
   * Returns a representation of the field viewed by the enemy. E stands for
   * Empty; M stands for Missed; @ stands for ship present;
   * * stands for ship hit
   * @param field the field that wants to be viewed
   * @return a representation of the field seen by the enemy
   */
  public static List<List<Character>> getViewByEnemy(final Field field) {
    return getViewByPlayerStateList(field, PlayerState.ENEMY);
  }

  /**
   * Returns a representation of the field viewied by the owner of the field. E
   * stands for Empty; M stands for Missed. @ stands for ship present
   * * stands for ship hit
   * @param field the field that wants to be viewed
   * @return a representation of the field viewed by the owner of the field
   */
  public static List<List<Character>> getViewByOwner(final Field field) {
    return getViewByPlayerStateList(field, PlayerState.OWNER);
  }

  private static List<List<Character>> getViewByPlayerStateList(
      final Field field,
      final PlayerState playerState ) {
    final int rows = field.getFieldBound().getRowsCount();
    final int columns = field.getFieldBound().getColumnsCount();
    final List<List<Character>> view = new ArrayList<>(rows);

    for (int row = 0; row < rows; row++) {
      addColumnsByPlayerState(
          field.getFieldCells()[row],
          playerState,
          columns,
          view,
          row
      );
    }
    return view;
  }

  private static void addColumnsByPlayerState(final FieldCell[] fieldCells,
                                              final PlayerState playerState,
                                              final int columns,
                                              final List<List<Character>> view,
                                              final int row) {
    view.add(new ArrayList<>());
    for (int col = 0; col < columns; col++) {
      view.get(row).add(getValueByPlayerState(playerState, fieldCells[col]));
    }
  }
}
