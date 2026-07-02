package test;

import static org.junit.Assert.*;

import org.junit.Test;

import board.Board;
import board.Cell;

public class testBoard {

  @Test
  public void testGetRows() {
    Board board = new Board(5,5,5);
    assertEquals(5, board.getRows());
  }

  @Test
  public void testGetColumns() {
    Board board = new Board(5,5,5);
    assertEquals(5, board.getColumns());
  }

  @Test
  public void testGetBombs() {
    Board board = new Board(5,5,5);
    assertEquals(5, board.getBombs());
  }

  @Test
  public void testGetCells() {
    Board board = new Board(5,5,5);
    Cell[][] cells = new Cell[5][5];
    assertEquals(cells.length, board.getCells().length); 
  }

}
