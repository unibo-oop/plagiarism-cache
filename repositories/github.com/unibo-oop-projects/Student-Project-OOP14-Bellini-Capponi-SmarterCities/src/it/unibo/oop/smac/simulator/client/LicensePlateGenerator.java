package it.unibo.oop.smac.simulator.client;

import it.unibo.oop.smac.datatypes.LicensePlate;
import it.unibo.oop.smac.utils.RandomStringGenerator;

import javax.management.InvalidAttributeValueException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe che genera una nuova targa, utilizzata a fini di test.
 *
 * @author Francesco Capponi
 */
public final class LicensePlateGenerator {

  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(LicensePlateGenerator.class);

  /**
   * Costruttore privato per non rendere istanziabile questa classe di utility
   */
  private LicensePlateGenerator() {

  }

  /**
   * funzione statica che implementa la generazione della targa.
   * 
   * @return targa generata
   */
  public static LicensePlate generate() {
    LicensePlate licensePlate = null;
    final String licensePlateString = RandomStringGenerator.generateRandomString(2,
        RandomStringGenerator.Mode.ALPHA)
        + RandomStringGenerator.generateRandomString(3, RandomStringGenerator.Mode.NUMERIC)
        + RandomStringGenerator.generateRandomString(2, RandomStringGenerator.Mode.ALPHA);
    try {
      licensePlate = new LicensePlate(licensePlateString);
    } catch (InvalidAttributeValueException e) {
      // NON può succedere poiché la targa è generata secondo lo standard
      // richiesto
      LOGGER.error("Il formato generato non è adeguato ({})", licensePlateString, e);
    }
    return licensePlate;

  }
}
