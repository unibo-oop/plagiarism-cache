package test;

import static org.junit.Assert.*;

import org.junit.Test;

import board.Cell;

public class testCell {

  @Test
  public void testSetNeighbors() {
    Cell[] cells = new Cell[8];
    Cell cell = new Cell(false);
    cell.setNeighbors(cells);
    assertArrayEquals(cells, cell.getNeighbors());
  }

  @Test
  public void testGetNeighbors() {
    Cell cell = new Cell(false);
    assertNotNull(cell.getNeighbors());
  }

  @Test
  public void testNumberOfNeighbors() {
    Cell cell = new Cell(false);
    Cell[] cells = new Cell[8];
    cell.setNeighbors(cells);
    assertEquals(Integer.parseInt("8"), cell.numberOfNeighbors());
  }

  @Test
  public void testGetNumberNeighborBombs() {
    Cell cell = new Cell(false);
    Cell[] cells = new Cell[8];
    for(int i = 0; i < cells.length; i++) {
      cells[i] = new Cell(true);
    }
    cell.setNeighbors(cells);
    assertEquals(8, cell.getNumberNeighborBombs());
  }

  @Test
  public void testIsBomb() {
    Cell cell = new Cell(true);
    assertTrue(cell.isBomb());
  }

  @Test
  public void testSetRevealed() {
    Cell cell = new Cell(false);
    cell.setRevealed();
    assertTrue(cell.isRevealed());
  }

  @Test
  public void testIsRevealed() {
    Cell cell = new Cell(false);
    assertFalse(cell.isRevealed());
  }

  @Test
  public void testSetX() {
    Cell cell = new Cell(false);
    cell.setX(1);
    assertEquals(1, cell.getX());
  }

  @Test
  public void testSetY() {
    Cell cell = new Cell(false);
    cell.setY(1);
    assertEquals(1, cell.getY());
  }

  @Test
  public void testGetX() {
    Cell cell = new Cell(false);
    assertEquals(0, cell.getX());
  }

  @Test
  public void testGetY() {
    Cell cell = new Cell(false);
    assertEquals(0, cell.getY());
  }

  @Test
  public void testAddFlag() {
    Cell cell = new Cell(false);
    cell.addFlag();
    assertTrue(cell.isFlag());
  }

  @Test
  public void testRemoveFlag() {
    Cell cell = new Cell(false);
    cell.addFlag();
    cell.removeFlag();
    assertFalse(cell.isFlag());
  }

  @Test
  public void testIsFlag() {
    Cell cell = new Cell(false);
    assertFalse(cell.isFlag());
  }

}
