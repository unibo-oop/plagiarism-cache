package test;

import static org.junit.Assert.*;

import org.junit.Test;

import record.TableRecord;

public class testTableRecord {

  @Test
  public void testAddRecord() {
    TableRecord tableRecord = new TableRecord("Easy");
    tableRecord.writeDefaultRecord();
    tableRecord.addRecord("name", 1);
    assertEquals("name", tableRecord.getListRecord()[0].getName());
    assertEquals(1, tableRecord.getListRecord()[0].getTime());
    tableRecord.writeDefaultRecord();
  }

  @Test
  public void testIsRecord() {
    TableRecord tableRecord = new TableRecord("Easy");
    tableRecord.writeDefaultRecord();
    assertTrue(tableRecord.isRecord(1));
    tableRecord.writeDefaultRecord();
  }

  @Test
  public void testGetListRecord() {
    TableRecord tableRecord = new TableRecord("Easy");
    assertEquals(10, tableRecord.getListRecord().length);
  }

  @Test
  public void testGetTableDifficult() {
    TableRecord tableRecord = new TableRecord("Easy");
    assertEquals("Easy", tableRecord.getTableDifficult());
  }

  @Test
  public void testWriteDefaultRecord() {
    TableRecord tableRecord = new TableRecord("Easy");
    tableRecord.writeDefaultRecord();
    assertEquals("Vuoto", tableRecord.getListRecord()[0].getName());
  }

}
