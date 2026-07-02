package it.unibo.oop.smac.database;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Classe che permette la connessione al database, e la creazione delle tabelle.
 * 
 * @author Francesco Capponi
 */
public final class Connection {

  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(Connection.class);

  /**
   * Indirizzo del database.
   */
  private static final String DATABASE_URL = "jdbc:h2:mem:account";

  /**
   * Attributo che contiene l'istanza del singleton.
   */
  private static Connection instance;

  /**
   * tabelle gestite
   */
  private Dao<SightingRow, Integer> sightingDao;
  private Dao<StolenCarRow, Integer> stolenCarDao;
  private Dao<StreetObserverRow, String> streetObserverDao;

  /**
   * Costruttore che inizializza la connessione.
   * 
   * @throws SQLException
   *           se il link di connessione è errato, potrebbe fallire
   */
  private Connection() throws SQLException {
    final JdbcConnectionSource connectionSource = new JdbcConnectionSource(DATABASE_URL);
    setupDatabase(connectionSource);
    LOGGER.info("Connection succeed");
  }

  /**
   * Implementazione del pattern Singleton.
   * 
   * @return istanza della connessione
   * @throws SQLException
   *           potrebbe fallire nell'inizializzazione della connessione
   */
  public static Connection getInstance() throws SQLException {
    synchronized (Connection.class) {
      if (instance == null) {
        instance = new Connection();
      }
      return instance;
    }
  }

  /**
   * Creazione delle tabelle.
   * 
   * @param connectionSource
   *          connessione su cui craere le tabelle
   * @throws SQLException
   *           potrebbero risultare errori di connessione o creazione delle tabelle
   */
  private void setupDatabase(final ConnectionSource connectionSource) throws SQLException {

    this.sightingDao = DaoManager.createDao(connectionSource, SightingRow.class);
    this.stolenCarDao = DaoManager.createDao(connectionSource, StolenCarRow.class);
    this.streetObserverDao = DaoManager.createDao(connectionSource, StreetObserverRow.class);

    TableUtils.createTable(connectionSource, SightingRow.class);
    TableUtils.createTable(connectionSource, StolenCarRow.class);
    TableUtils.createTable(connectionSource, StreetObserverRow.class);
  }

  /**
   * Restituisce un'istanza della tabella dei sighting.
   * 
   * @return tabella dei sighting
   */
  public Dao<SightingRow, Integer> getSightingDao() {
    return sightingDao;
  }

  /**
   * Restituisce un'istanza della tabella delle stolen car.
   * 
   * @return tabella delle stolen car
   */
  public Dao<StolenCarRow, Integer> getStolenCarDao() {
    return stolenCarDao;
  }

  /**
   * Restituisce un'istanza della tabella degli street observer.
   * 
   * @return tabella degli street observer
   */
  public Dao<StreetObserverRow, String> getStreetObserverDao() {
    return streetObserverDao;
  }

}
