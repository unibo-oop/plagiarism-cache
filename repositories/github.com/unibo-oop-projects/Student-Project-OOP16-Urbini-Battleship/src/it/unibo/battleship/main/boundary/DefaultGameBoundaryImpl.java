/*
 * Copyright (c) 2017 Fabio Urbini.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 */

package it.unibo.battleship.main.boundary;

import it.unibo.battleship.main.common.Point2d;
import it.unibo.battleship.main.control.BattleshipControl.PlayerType;
import it.unibo.battleship.main.control.BattleshipControl.ViewerType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static it.unibo.battleship.main.control.BattleshipControl.PlayerType.AI;
import static it.unibo.battleship.main.control.BattleshipControl.PlayerType.HUMAN;
import static it.unibo.battleship.main.control.BattleshipControl.ViewerType.ENEMY;
import static it.unibo.battleship.main.control.BattleshipControl.ViewerType.OWNER;
import static it.unibo.battleship.main.control.BattleshipControlImpl.CONTROLLER;

/**
 * Boundary of a battleship game. It uses the console.
 * @author fabio.urbini
 */
public final class DefaultGameBoundaryImpl {
  private static final String CLEAR_CONSOLE = "\033[2J\033[;H";
  /* actually hard coded - refactor legenda*/
  private static final String LEGENDA =
      "Legenda : \n" +
      "E = Empty\n" +
      "M = Missed\n" +
      "@ = Ship\n" +
      "* = Hit\n";
  private static final String AI_LEVEL_MESSAGES =
      "Choose AI level. Type one of the following values\n"+
      "1 : Super easy, guaranteed win, for beginners\n"+
      "2 : Easy, no need to think about moves yet";
  private static final String GAME_STARTED_MESSAGES =
      "Battleship game start\n"+
      "CARE! Don't insert values out of the current boundary\n"+
      "Don't try to place ships that will be out of the boundaries\n"+
      "Don't make ships overlap, the game will end";
  private static final IoHelper IO_HELPER = new IoHelper();
  /*
  Future refactorings :
  make an instantiable class controlling the console
  instantiate and use it in main
  Refactor ai/player methods so that they are generified
  Catch exceptions when creating a new ship or a new shot

  Make it easy to play with different boundaries
  place a ship or a shot with an index instead of row and column
  Note : the code has to be refactored and polished.
   */
  private DefaultGameBoundaryImpl() {
    System.out.println(GAME_STARTED_MESSAGES);
    System.out.println();
    System.out.println(LEGENDA);
    initializeAi();
    placePlayerFleet();

    do {
      shootAiField();
      shootPlayerField();
      System.out.println(LEGENDA);
      printField(HUMAN, OWNER);
      printField(AI, ENEMY);
    } while (CONTROLLER.isGameNotFinished());

    printAfterGameEnded();
  }

  public static void main(final String[] args) {
    new DefaultGameBoundaryImpl();
  }

  private void initializeAi() {
    chooseAiLevel();
    CONTROLLER.initializeAi();
  }

  private void printAfterGameEnded() {
    String s = "";
    if (CONTROLLER.isAiFleetSunk()) {
      s = "Congratulations! You won!";
    }
    if (CONTROLLER.isPlayerFleetSunk()) {
      s = "Sorry! You lost!";
    }
    System.out.println(s);
  }

  private void shootAiField() {
    System.out.println("Create a new shot and point at the enemy fleet!");
    final Point2d p = IO_HELPER.getValidPoint2d();
    CONTROLLER.shootAiField(p);
  }

  private void shootPlayerField() {
    CONTROLLER.shootPlayerField();
  }

  private String getHeaderForConsole(final int columnsCount) {
    final StringBuilder sb = new StringBuilder("   ");
    final String values = "0123456789ABCDEFGH"; // raw method to show header

    for (int i = 0; i < columnsCount; i++) {
      sb.append(' ').append(values.charAt(i)).append(' ');
    }

    return sb.toString();
  }

  private void placePlayerFleet() {
    System.out.println("Place your fleet first");
    printField(HUMAN, OWNER);
    do {
      place();
      printField(HUMAN, OWNER);
    } while (CONTROLLER.isPlayerFleetNotPlaced());
  }

  private void chooseAiLevel() {
    boolean valid;
    do {
      System.out.println(AI_LEVEL_MESSAGES);
      final int aiLevel = IO_HELPER.readInt();
      valid = IO_HELPER.isValid(aiLevel, 1, 2);
      if (!valid) {
        System.out.println("Invalid number");
      } else {
        setUpAiLevel(aiLevel);
      }
    } while (!valid);
  }

  private void setUpAiLevel(final int aiLevel) {
    switch(aiLevel) {
      case 1 : {
        CONTROLLER.setUpAiLevelSuperEasy();
        System.out.println("Super easy AI!");
      }
      break;
      case 2 : {
        CONTROLLER.setUpAiLevelEasy();
        System.out.println("Easy AI!");
      }
      break;
      default : break;
    }
  }

  private void printField(final PlayerType playerType, final ViewerType viewerType) {
    final StringBuilder headerBuilder = new StringBuilder("\n\n");
    headerBuilder.append(
        playerType == AI ? "--- AI FIELD ---" : "--- PLAYER FIELD ---"
    );
    headerBuilder.append('\n');
    headerBuilder.append(getHeaderForConsole(
        CONTROLLER.getBoundary().getColumnsCount())
    );
    System.out.println(headerBuilder);

    getFieldRowsToPrint(playerType, viewerType).forEach(System.out::println);

  }

  private List<String> getFieldRowsToPrint(final PlayerType playerType, final ViewerType viewerType) {
    final List<String> rowsToWrite = new ArrayList<>();
    final List<List<Character>> charMapList = CONTROLLER.getFieldView(playerType, viewerType);

    IntStream.range(0, charMapList.size())
        .forEach( idx -> {
              final StringBuilder sb = new StringBuilder(" " + idx + ' ');
              charMapList.get(idx).forEach( (Character c) -> sb.append(" " + c + ' '));
              rowsToWrite.add(sb.toString());
            }
        );

    return rowsToWrite;
  }

  private void place() {
    final String shipToPlace = CONTROLLER.getNextPlaceableShip();
    System.out.println("Trying to place a " + shipToPlace);
    final Point2d p = IO_HELPER.getValidPoint2d();
    CONTROLLER.placeNextPlaceableShip(p);
  }
}
