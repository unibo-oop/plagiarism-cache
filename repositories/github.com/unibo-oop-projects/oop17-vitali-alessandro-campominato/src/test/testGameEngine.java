package test;

import static org.junit.Assert.*;

import org.junit.Test;

import board.Cell;
import board.GameEngine;
import enumeration.Action;

public class testGameEngine {

  @Test
  public void testNewGame() {
    GameEngine gameEngine = new GameEngine();
    Cell[] cells = new Cell[5];
    gameEngine.newGame(5, 5, 5);
    assertEquals(cells.length, gameEngine.getCells().length);
  }

  @Test
  public void testClick() {
    GameEngine gameEngine = new GameEngine();
    gameEngine.newGame(5, 5, 5);
    Cell[][] cells = gameEngine.getCells();
    gameEngine.click(cells[1][1], Action.SET_FLAG);
    assertTrue(cells[1][1].isFlag());
    gameEngine.click(cells[1][1], Action.REMOVE_FLAG);
    assertFalse(cells[1][1].isFlag());
    gameEngine.click(cells[1][1], Action.PLAY);
    assertTrue(cells[1][1].isRevealed());
  }

  @Test
  public void testGetCells() {
    GameEngine gameEngine = new GameEngine();
    gameEngine.newGame(5,5,5);
    Cell[][] cells = new Cell[5][5];
    assertEquals(cells.length, gameEngine.getCells().length);
  }

}
