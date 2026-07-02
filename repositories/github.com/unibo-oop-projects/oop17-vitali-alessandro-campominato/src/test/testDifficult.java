package test;

import static org.junit.Assert.*;

import org.junit.Test;

import other.Difficult;

public class testDifficult {

  @Test
  public void testSetDifficultEasy() {
    Difficult difficult = new Difficult();
    difficult.setDifficultEasy();
    assertEquals("Easy", difficult.getDifficult());
  }

  @Test
  public void testSetDifficultMedium() {
    Difficult difficult = new Difficult();
    difficult.setDifficultMedium();
    assertEquals("Medium", difficult.getDifficult());
  }

  @Test
  public void testSetDifficultHard() {
    Difficult difficult = new Difficult();
    difficult.setDifficultHard();
    assertEquals("Hard", difficult.getDifficult());
  }

  @Test
  public void testSetDifficultCustom() {
    Difficult difficult = new Difficult();
    difficult.setDifficultCustom(1,1,1);
    assertEquals("Custom", difficult.getDifficult());
    assertEquals(Integer.parseInt("1"), difficult.getRows());
    assertEquals(Integer.parseInt("1"), difficult.getColumns());
    assertEquals(Integer.parseInt("1"), difficult.getBombs());
  }

  @Test
  public void testGetDifficult() {
    Difficult difficult = new Difficult();
    assertEquals("Easy", difficult.getDifficult());
  }

  @Test
  public void testGetRows() {
    Difficult difficult = new Difficult();
    assertEquals(Integer.parseInt("8"), difficult.getRows());
  }

  @Test
  public void testGetColumns() {
    Difficult difficult = new Difficult();
    assertEquals(Integer.parseInt("8"), difficult.getColumns());
  }

  @Test
  public void testGetBombs() {
    Difficult difficult = new Difficult();
    assertEquals(Integer.parseInt("10"), difficult.getBombs());
  }

  @Test
  public void testIsChange() {
    Difficult difficult = new Difficult();
    switch(difficult.getDifficult()) {
    case "Easy":
      difficult.setDifficultMedium();
      assertTrue(difficult.isChange());
      break;
    case "Medium":
      difficult.setDifficultHard();
      assertTrue(difficult.isChange());
      break;
    case "Hard":
      difficult.setDifficultCustom(1,1,1);
      assertTrue(difficult.isChange());
    break;
    case "Custom":
      difficult.setDifficultEasy();
      assertTrue(difficult.isChange());
     break;
    default:
      break;
    }
  }

  @Test
  public void testSetNotChange() {
    Difficult difficult = new Difficult();
    difficult.setDifficultMedium();
    assertTrue(difficult.isChange());
    difficult.setNotChange();
    assertFalse(difficult.isChange());
    
  }

}
