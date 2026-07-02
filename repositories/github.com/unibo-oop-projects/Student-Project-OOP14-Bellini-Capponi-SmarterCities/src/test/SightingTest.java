package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import it.unibo.oop.smac.datatypes.Coordinates;
import it.unibo.oop.smac.datatypes.LicensePlate;
import it.unibo.oop.smac.datatypes.Sighting;
import it.unibo.oop.smac.datatypes.StreetObserver;

import java.util.Date;

import javax.management.InvalidAttributeValueException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test svolti sulla classe {@link Sighting}.
 * 
 * @author Federico Bellini
 */
public class SightingTest {

  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SightingTest.class);

  /**
   * Test svolti sulla creazione della classe {@link Sighting}.
   */
  @Test
  public void creationTest() {

    try {
      new Sighting.Builder().date(null);
      fail();
    } catch (InvalidAttributeValueException e) {
      LOGGER.info("Test 1 success");
    }

    try {
      new Sighting.Builder().licensePlate(null);
      fail();
    } catch (InvalidAttributeValueException e) {
      LOGGER.info("Test 2 success");
    }

    try {
      new Sighting.Builder().speed(null);
      fail();
    } catch (InvalidAttributeValueException e) {
      LOGGER.info("Test 3 success");
    }

    try {
      new Sighting.Builder().streetObserver(null);
      fail();
    } catch (InvalidAttributeValueException e) {
      LOGGER.info("Test 4 success");
    }

    try {
      // data futura, non ancora accaduto
      final Date d = new Date();
      d.setTime(d.getTime() + 360);
      new Sighting.Builder().date(d);
      fail();
    } catch (InvalidAttributeValueException e) {
      LOGGER.info("Test 6 success");
    }

    try {
      // velocità negativa
      new Sighting.Builder().speed(-1f);
      fail();
    } catch (InvalidAttributeValueException e) {
      LOGGER.info("Test 7 success");
    }

    try {
      final Sighting s = new Sighting.Builder().date(new Date()).speed(4f)
          .streetObserver(new StreetObserver(new Coordinates(4f, 4f)))
          .licensePlate(new LicensePlate()).build();
      assertEquals(
          s,
          new Sighting.Builder().date(new Date()).speed(4f)
              .streetObserver(new StreetObserver(new Coordinates(4f, 4f)))
              .licensePlate(new LicensePlate()).build());
    } catch (Exception e) {
      fail();
    }

  }

}
