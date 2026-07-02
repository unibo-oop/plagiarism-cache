package test;

import it.unibo.oop.smac.database.model.StreetObserverNotValidException;
import it.unibo.oop.smac.datatypes.Coordinates;
import it.unibo.oop.smac.datatypes.IStreetObserver;
import it.unibo.oop.smac.datatypes.StreetObserver;

import javax.management.InvalidAttributeValueException;

import org.junit.Test;

/**
 * Esecuzione di test per verificare il corretto funzionamento della classe {@link StreetObserver}.
 * 
 * @author Federico Bellini
 */
public class StreetObserverTest {

  /**
   * Controllo sulla creazione corretta degli streetObserver.
   * 
   * @throws StreetObserverNotValidException
   *           exception necessaria per successo del test
   */
  @Test(expected = StreetObserverNotValidException.class)
  public void testCreations() throws StreetObserverNotValidException {
    new StreetObserver((IStreetObserver) null);
  }

  /**
   * Controllo sulla creazione corretta degli streetObserver.
   * 
   * @throws InvalidAttributeValueException
   *           exception necessaria per successo del test
   */
  @Test(expected = InvalidAttributeValueException.class)
  public void testCreations2() throws InvalidAttributeValueException {
    new StreetObserver((Coordinates) null);
  }
}
