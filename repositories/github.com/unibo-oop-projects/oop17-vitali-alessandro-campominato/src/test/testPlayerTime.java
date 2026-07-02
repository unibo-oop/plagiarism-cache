package test;

import static org.junit.Assert.*;

import org.junit.Test;

import record.PlayerTime;

public class testPlayerTime {

  @Test
  public void testGetTime() {
    PlayerTime playerTime = new PlayerTime("name", 10);
    assertEquals(10, playerTime.getTime());
  }

  @Test
  public void testGetName() {
    PlayerTime playerTime = new PlayerTime("name", 10);
    assertEquals("name", playerTime.getName());
  }

}
