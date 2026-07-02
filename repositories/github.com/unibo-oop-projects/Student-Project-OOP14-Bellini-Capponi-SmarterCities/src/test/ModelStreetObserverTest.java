package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import it.unibo.oop.smac.database.model.StreetObserverModelDatabase;
import it.unibo.oop.smac.database.model.StreetObserverNotValidException;
import it.unibo.oop.smac.datatypes.Coordinates;
import it.unibo.oop.smac.datatypes.IInfoStreetObserver;
import it.unibo.oop.smac.datatypes.IStreetObserver;
import it.unibo.oop.smac.datatypes.LicensePlate;
import it.unibo.oop.smac.datatypes.Sighting;
import it.unibo.oop.smac.datatypes.StreetObserver;
import it.unibo.oop.smac.model.IStreetObserverModel;
import it.unibo.oop.smac.simulator.client.LicensePlateGenerator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.management.InvalidAttributeValueException;

import org.junit.Test;

/**
 * Classe che controlla l'interfaccia esposta da {@link StreetObserverModelDatabase}.
 * 
 * @author Francesco Capponi
 * @author Federico Bellini
 */
public class ModelStreetObserverTest {

  /**
   * Indica il range di validità delle coordinate(+\- 180).
   */
  private static final int COORDINATES_RANGE = 180;

  private Coordinates generateCoordinates() throws InvalidAttributeValueException {
    final Random rand = new Random();
    return new Coordinates(rand.nextFloat() % COORDINATES_RANGE, -rand.nextFloat()
        % COORDINATES_RANGE);
  }

  /**
   * Controllo che il model riesca a creare uno streetObserver valido.
   * 
   * @throws InvalidAttributeValueException
   *           Questa evenienza non dovrebbe verificarsi.
   */
  @Test
  public void testAddNewStreetObserver() throws InvalidAttributeValueException {
    final IStreetObserverModel model = StreetObserverModelDatabase.getInstance();
    final StreetObserver streetObserver = new StreetObserver(this.generateCoordinates());
    model.addNewStreetObserver(streetObserver);
    assertTrue(model.checkStreetObserverExists(streetObserver));
  }

  /**
   * Controllo che il model NON riesca a creare uno streetObserver NON valido.
   * 
   * @exception StreetObserverNotValidException
   *              necessaria l'exception per il successo del test
   * @throws Exception
   *           l'esecuzione dovrebbe restituire un eccezione
   */
  @Test(expected = StreetObserverNotValidException.class)
  public void testAddNewStreetObserverFail() throws Exception {
    final IStreetObserverModel model = StreetObserverModelDatabase.getInstance();
    StreetObserver streetObserver = null;
    streetObserver = new StreetObserver((IStreetObserver) null);

    model.addNewStreetObserver(streetObserver);
  }

  /**
   * Controllo che il model riesca ad inserire un sighting valido.
   * 
   * @throws Exception
   *           l'esecuzione non dovrebbe restituire un eccezioni, se le restituisce qualcosa è
   *           andato storto e il test fallisce
   */
  @Test
  public void testAddSighting() throws Exception {
    final IStreetObserverModel model = StreetObserverModelDatabase.getInstance();

    final StreetObserver streetObserver = new StreetObserver(this.generateCoordinates());

    final LicensePlate licensePlate = LicensePlateGenerator.generate();

    final Sighting sighting = new Sighting.Builder().date(new Date())
        .streetObserver(streetObserver).speed(64f).licensePlate(licensePlate).build();

    model.addSighting(sighting);
    assertTrue(model.getStreetObserverInfo(streetObserver).getTotalNOfSight().get().equals(1));

  }

  /**
   * Controllo che il model NON riesca ad inserire un sighting con streetObserver non valido.
   * 
   * @throws Exception
   *           l'esecuzione dovrebbe restituire un eccezione poiché il {@link Sighting.Builder} non
   *           è stato creato correttamente
   */
  @Test(expected = Exception.class)
  public void testAddSightingFail() throws Exception {
    final IStreetObserverModel model = StreetObserverModelDatabase.getInstance();
    final StreetObserver streetObserver = new StreetObserver(this.generateCoordinates());
    model.addNewStreetObserver(streetObserver);

    model.addSighting(new Sighting.Builder().build());
    fail();
  }

  /**
   * Controllo che il model restituisca un InfoStreetObserver con dati coerenti a quelli nel
   * database.
   * 
   * @throws Exception
   *           l'esecuzione non dovrebbe restituire un eccezioni, se le restituisce qualcosa è
   *           andato storto e il test fallisce
   */
  @Test
  public void testInfoStreetObserver() throws Exception {
    final IStreetObserverModel model = StreetObserverModelDatabase.getInstance();

    final StreetObserver streetObserver = new StreetObserver(this.generateCoordinates());
    final LicensePlate licensePlate = LicensePlateGenerator.generate();

    // Ho bisogno delle date in cui verranno simulati i passaggi
    final Calendar lastHour = Calendar.getInstance();
    lastHour.add(Calendar.HOUR, -1);
    lastHour.add(Calendar.SECOND, 1);

    final Calendar today = Calendar.getInstance();
    today.set(Calendar.HOUR, 0);
    today.set(Calendar.MINUTE, 0);
    today.set(Calendar.SECOND, 0);
    today.set(Calendar.AM_PM, Calendar.AM);
    today.add(Calendar.SECOND, 1);

    final Calendar lastWeek = Calendar.getInstance();
    lastWeek.add(Calendar.DATE, -Calendar.DAY_OF_WEEK);
    lastWeek.add(Calendar.SECOND, 1);

    final Calendar lastMonth = Calendar.getInstance();
    lastMonth.add(Calendar.MONTH, -1);
    lastMonth.add(Calendar.SECOND, 1);

    final List<Calendar> dates = new ArrayList<Calendar>();
    dates.add(lastHour);
    dates.add(today);
    dates.add(lastWeek);
    dates.add(lastMonth);

    // aggiungo un po' di sighting per test, nella varie date possibili
    for (int i = 0; i < 8; i++) {
      for (final Calendar date : dates) {
        final Sighting sighting = new Sighting.Builder().date(date.getTime())
            .streetObserver(streetObserver).speed(64f).licensePlate(licensePlate).build();
        model.addSighting(sighting);
      }
    }

    // prendo le info necessarie
    final IInfoStreetObserver infoStreetObserver = model.getStreetObserverInfo(streetObserver);

    // verifico che tutti i parametri siano stati impostati correttamente
    assertTrue(infoStreetObserver.getTotalNOfSight().get().equals(8 * dates.size()));
    assertTrue(infoStreetObserver.getAverageSpeedToday().get().equals(64f));
    assertTrue(infoStreetObserver.getAverageSpeedLastWeek().get().equals(64f));
    assertTrue(infoStreetObserver.getAverageSpeedLastMonth().get().equals(64f));
    assertTrue(infoStreetObserver.getnOfSightLastHour().get().equals(8 * dates.size() / 4 * 1));
    assertTrue(infoStreetObserver.getnOfSightToday().get().equals(8 * dates.size() / 4 * 2));
    assertTrue(infoStreetObserver.getnOfSightLastWeek().get().equals(8 * dates.size() / 4 * 3));
    assertTrue(infoStreetObserver.getnOfSightLastMonth().get().equals(8 * dates.size() / 4 * 4));
    assertTrue(infoStreetObserver.getMaxSpeedToday().get().equals(64f));

  }
}
