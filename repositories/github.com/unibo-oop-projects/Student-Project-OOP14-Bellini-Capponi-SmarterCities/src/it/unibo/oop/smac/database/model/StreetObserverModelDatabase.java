package it.unibo.oop.smac.database.model;

import it.unibo.oop.smac.database.Connection;
import it.unibo.oop.smac.database.SightingRow;
import it.unibo.oop.smac.database.StreetObserverRow;
import it.unibo.oop.smac.datatypes.IInfoStreetObserver;
import it.unibo.oop.smac.datatypes.ISighting;
import it.unibo.oop.smac.datatypes.IStreetObserver;
import it.unibo.oop.smac.datatypes.InfoStreetObserver;
import it.unibo.oop.smac.datatypes.Sighting;
import it.unibo.oop.smac.model.IStreetObserverModel;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.j256.ormlite.dao.Dao;

/**
 * Implementazione dell'interfaccia del Model dell'applicazione. Questa classe si occupa di
 * aggiungere {@link IStreetObserver} e {@link ISighting} nel database, e di restituire le
 * informazioni in esso contenute. Questa classe utilizza il pattern Singleton.
 * 
 * @author Federico Bellini
 */
public class StreetObserverModelDatabase implements IStreetObserverModel {

  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(StreetObserverModelDatabase.class);

  /**
   * Instanza del Singleton
   */
  private static StreetObserverModelDatabase instance;

  /**
   * Restituisce un'istanza del model del database.
   * 
   * @return istanza del model database
   */
  public static StreetObserverModelDatabase getInstance() {
    synchronized (StreetObserverModelDatabase.class) {
      if (instance == null) {
        instance = new StreetObserverModelDatabase();
      }
      return instance;
    }
  }

  /**
   * Inserisce nel database un nuovo {@link IStreetObserver}. Questo metodo e' thread-safe.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} da inserire.
   */
  @Override
  public void addNewStreetObserver(final IStreetObserver streetObserver) {
    synchronized (StreetObserverModelDatabase.class) {
      if (!this.checkStreetObserverExists(streetObserver)) {
        final StreetObserverRow streetObserverRow = new StreetObserverRow(streetObserver);
        final Dao<StreetObserverRow, String> streetObserverDao = this.getStreetObserverDao();
        try {
          streetObserverDao.create(streetObserverRow);
        } catch (SQLException e) {
          LOGGER.error("The creation on database of the new SteetObserver {} is failed!",
              streetObserver);
        }
        try {
          System.out.println("Reading datas just added: "
              + streetObserverDao.queryForId(streetObserver.getId()));
        } catch (SQLException e) {
          LOGGER.error("Error reading from database of data just added ");
        }
      }
    }
  }

  /**
   * Inserisce nel database un nuovo {@link ISighting}.
   * 
   * @param sighting
   *          L'{@link ISighting} da inserire.
   * @throws StreetObserverNotValidException
   *           Se viene passato un sighting con streetObserver nullo.
   */
  @Override
  public void addSighting(final ISighting sighting) throws StreetObserverNotValidException {
    // controllo sullo streetObserver
    if (sighting.getStreetObserver() == null) {
      throw new StreetObserverNotValidException();
    }
    // se non è mai stato inserito prima, lo faccio ora
    if (!this.checkStreetObserverExists(sighting.getStreetObserver())) {
      this.addNewStreetObserver(sighting.getStreetObserver());
    }
    // aggiungo un nuovo sighting alla lista di sighting dello streetObserver
    try {
      final StreetObserverRow streetObserverRow = this.getStreetObserverRow(sighting
          .getStreetObserver());
      final SightingRow sightingRow = new SightingRow(sighting, streetObserverRow);
      streetObserverRow.addSightings(sightingRow);
    } catch (DatabaseNotFoundException e) {
      LOGGER.error("Errore del database nell'inserzione di un sighting", e);
    }
  }

  /**
   * Questo metodo controlla se l'{@link IStreetObserver} passato come argomento sia gia' presente
   * nel Model dell'appicazione.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} di cui verificare la presenza.
   * @return True se esiste, false altrimenti.
   */
  @Override
  public boolean checkStreetObserverExists(final IStreetObserver streetObserver) {
    final Dao<StreetObserverRow, String> streetObserverDao = this.getStreetObserverDao();
    try {
      return streetObserverDao.queryForId(streetObserver.getId()) != null;
    } catch (SQLException e) {
      return false;
    }
  }

  /**
   * Questo metodo restituisce lo {@link StreetObserverRow} corrispondente all'
   * {@link IStreetObserver} passato come argomento.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} da cercare.
   * @return Un oggetto {@link StreetObserverRow} corrispondente all' {@link IStreetObserver}
   *         passato.
   * 
   * @throws DatabaseNotFoundException
   *           Eccezione lanciata nel caso in cui l'{@link IStreetObserver} di cui si vogliono
   *           recuperare le informazioni non fosse presente nel Model dell'applicazione.
   */
  protected StreetObserverRow getStreetObserverRow(final IStreetObserver streetObserver)
      throws DatabaseNotFoundException {
    if (this.checkStreetObserverExists(streetObserver)) {
      final Dao<StreetObserverRow, String> streetObserverDao = this.getStreetObserverDao();
      try {
        return streetObserverDao.queryForId(streetObserver.getId());
      } catch (SQLException e) {
        LOGGER.error("Problems occured in database", e);
        return null;
      }
    } else {
      throw new DatabaseNotFoundException("The observer is not present");
    }
  }

  /**
   * Questo metodo restituisce la lista di {@link ISighting} avvistati dall'osservatore richiesto.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} di cui si vogliono conoscere gli avvistamenti.
   * @return La lista di {@link ISighting} rilevati dall'{@link IStreetObserver}.
   * @throws DatabaseNotFoundException
   *           Eccezione lanciata nel caso in cui l'{@link IStreetObserver} di cui si vogliono
   *           recuperare le informazioni non fosse presente nel Model dell'applicazione.
   */
  protected List<ISighting> getStreetObserverSightings(final IStreetObserver streetObserver)
      throws DatabaseNotFoundException {
    final StreetObserverRow streetObserverRow = this.getStreetObserverRow(streetObserver);
    final List<SightingRow> sightingRowList = streetObserverRow.getSightingsList();
    final List<ISighting> out = new LinkedList<>(); // defensive copy

    sightingRowList.forEach((elem) -> {
      try {
        out.add(new Sighting.Builder().streetObserver(elem.getStreetObserver())
            .date(elem.getDate()).licensePlate(elem.getLicensePlate()).speed(elem.getSpeed())
            .build());
      } catch (Exception e) {
        LOGGER.error("Creation of defensive copy failed", e);
      }
    });

    return out;
  }

  /**
   * Questo metodo raccoglie i dati su di un {@link IStreetObserver}, e li organizza restituendo al
   * chiamante un {@link IInfoStreetObserver} contenente le informazioni sull'osservstore richiesto.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} di cui si vogliono recuperare le informazioni.
   * @return Un oggetto del tipo {@link IInfoStreetObserver} contenente le informazioni sull'
   *         {@link IStreetObserver} richiesto.
   * 
   * @throws DatabaseNotFoundException
   *           Eccezione lanciata nel caso in cui l'{@link IStreetObserver} di cui si vogliono
   *           recuperare le informazioni non fosse presente nel database.
   */
  @Override
  public IInfoStreetObserver getStreetObserverInfo(final IStreetObserver streetObserver)
      throws DatabaseNotFoundException {
    if (!this.checkStreetObserverExists(streetObserver)) {
      throw new DatabaseNotFoundException("The street observer " + streetObserver
          + "does not exist! ");
    }

    // imposto i vari intervalli di tempo di cui dobbiamo calcolare le statistiche
    final Calendar lastHour = Calendar.getInstance();
    lastHour.add(Calendar.HOUR, -1);
    final Calendar today = Calendar.getInstance();
    today.set(Calendar.HOUR, 0);
    today.set(Calendar.MINUTE, 0);
    today.set(Calendar.SECOND, 0);
    today.set(Calendar.AM_PM, Calendar.AM);
    final Calendar lastWeek = Calendar.getInstance();
    lastWeek.add(Calendar.DATE, -Calendar.DAY_OF_WEEK);
    final Calendar lastMonth = Calendar.getInstance();
    lastMonth.add(Calendar.MONTH, -1);

    // counters
    int sightLastHour = 0;
    int sightToday = 0;
    int sightLastWeek = 0;
    int sightLastMonth = 0;
    float totalSpeedToday = 0;
    float totalSpeedLastWeek = 0;
    float totalSpeedLastMonth = 0;
    float maxSpeedToday = 0;
    final int hoursInDay = (int) TimeUnit.DAYS.toHours(1);

    int[] carInHour = new int[hoursInDay];
    // aumento i counters
    final List<ISighting> sightingRowList = this.getStreetObserverSightings(streetObserver);
    for (final ISighting s : sightingRowList) {
      final Date date = s.getDate();
      if (date.after(lastMonth.getTime())) {
        sightLastMonth++;
        totalSpeedLastMonth += s.getSpeed();
      }
      if (date.after(lastWeek.getTime())) {
        sightLastWeek++;
        totalSpeedLastWeek += s.getSpeed();
      }
      if (date.after(today.getTime())) {
        sightToday++;
        totalSpeedToday += s.getSpeed();
        if (s.getSpeed() > maxSpeedToday) {
          maxSpeedToday = s.getSpeed();
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        carInHour[calendar.get(Calendar.HOUR)]++;
      }
      if (date.after(lastHour.getTime())) {
        sightLastHour++;
      }
    }

    // trovo il max car rate
    float maxCarRate = 0;
    int maxCarRateHour = 0;
    for (int i = 0; i < carInHour.length; i++) {
      if (carInHour[i] > carInHour[maxCarRateHour]) {
        maxCarRateHour = i;
      }
    }
    maxCarRate = carInHour[maxCarRateHour];

    // costruzione dell'oggetto InfoStreetObserver contenente tutte le
    // informazioni ricavate
    return new InfoStreetObserver.Builder().streetObserver(streetObserver)
        .totalNOfSight(sightingRowList.size()).nOfSightLastHour(sightLastHour)
        .nOfSightToday(sightToday).nOfSightLastWeek(sightLastWeek)
        .nOfSightLastMonth(sightLastMonth).averageSpeedToday(totalSpeedToday / sightToday)
        .averageSpeedLastWeek(totalSpeedLastWeek / sightLastWeek)
        .averageSpeedLastMonth(totalSpeedLastMonth / sightLastMonth).maxSpeedToday(maxSpeedToday)
        .maxCarRateToday(maxCarRate).build();
  }

  /**
   * Restituisce il Dao<> degli streetObserver della classe Connection.
   * 
   * @return Il Dao<> richiesto.
   */
  private Dao<StreetObserverRow, String> getStreetObserverDao() {
    try {
      return Connection.getInstance().getStreetObserverDao();
    } catch (SQLException e) {
      LOGGER.error("Fatal error: streetObserver can not be created in the database ", e);
      return null;
    }
  }

}
