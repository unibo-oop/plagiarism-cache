package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import it.unibo.oop.smac.datatypes.Coordinates;

import javax.management.InvalidAttributeValueException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test per la corretta creazione delle {@link Coordinates}.
 * 
 * @author Federico Bellini
 */
public class CoordinatesTest {

  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CoordinatesTest.class);

  /**
   * Estremi di validità per le coordinate. Una coordinata che supera questi limiti è inconsistente.
   */
  private static final Float MAX_COORDINATE = 180f;
  private static final Float MIN_COORDINATE = -180f;

  /**
   * Controllo sulla creazione corretta delle Coordinates.
   */
  @Test
  public void testCreations() {
    try {
      new Coordinates(null, 4f);
      fail();
    } catch (InvalidAttributeValueException e) {
      LOGGER.info("Test 1 success");
    }

    try {
      new Coordinates(4f, null);
      fail();
    } catch (InvalidAttributeValueException e) {
      LOGGER.info("Test 2 success");
    }

    try {
      new Coordinates(null, -1f);
      fail();
    } catch (InvalidAttributeValueException e) {
      LOGGER.info("Test 3 success");
    }

    try {
      new Coordinates(MIN_COORDINATE - 1, 4f);
      fail();
    } catch (InvalidAttributeValueException e) {
      LOGGER.info("Test 4 success");
    }

    try {
      new Coordinates(4f, 360f);
      fail();
    } catch (InvalidAttributeValueException e) {
      LOGGER.info("Test 5 success");
    }

    try {
      new Coordinates(MAX_COORDINATE, MIN_COORDINATE);
    } catch (InvalidAttributeValueException e) {
      fail();
    }

    Coordinates c = null;
    try {
      c = new Coordinates(4f, 8f);
      assertEquals(c, new Coordinates(4f, 8f));
    } catch (InvalidAttributeValueException e) {
      fail();
    }

  }
}
