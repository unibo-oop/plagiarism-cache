package it.unibo.oop.smac.database.model;

import it.unibo.oop.smac.database.Connection;
import it.unibo.oop.smac.database.StolenCarRow;
import it.unibo.oop.smac.datatypes.IStolenCar;
import it.unibo.oop.smac.datatypes.LicensePlate;
import it.unibo.oop.smac.datatypes.StolenCar;
import it.unibo.oop.smac.model.IStolenCarModel;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.management.InvalidAttributeValueException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

/**
 * Estensione del Model dell'applicazione, con aggiunta dei metodi necessari alla gestione delle
 * auto rubate. Questa classe utilizza il pattern Singleton.
 * 
 * @author Francesco Capponi
 */
public class StolenCarModelDatabase extends StreetObserverModelDatabase implements IStolenCarModel {

  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(StolenCarModelDatabase.class);

  /**
   * Instanza del Singleton
   */
  private static StolenCarModelDatabase instance;

  /**
   * Restituisce un'istanza del model del database.
   * 
   * @return istanza del model database
   */
  public static StolenCarModelDatabase getInstance() {
    synchronized (StolenCarModelDatabase.class) {
      if (instance != null) {
        return instance;
      }
      instance = new StolenCarModelDatabase();
      return instance;
    }
  }

  /**
   * Restituisce la lista delle {@link IStolenCar} contenute nel database. Se si verificano delle
   * eccezioni, restituisce una lista vuota.
   * 
   * @return La lista di {@link IStolenCar} richiesta.
   */
  @Override
  public List<IStolenCar> getStolenCarsInfoList() {
    // raccolgo le informazioni
    List<StolenCarRow> stolenCars;
    try {
      Dao<StolenCarRow, Integer> stolenCarDao;
      stolenCarDao = Connection.getInstance().getStolenCarDao();
      stolenCars = stolenCarDao.queryForAll();
    } catch (SQLException e) {
      LOGGER.error("Error fetching Stolen cars list ", e);
      return new LinkedList<IStolenCar>();
    }

    // elaboro le info ricavate per restituire la lista di IStolenCars
    final LinkedList<IStolenCar> out = new LinkedList<>();
    stolenCars.forEach((elem) -> {
      try {
        out.add(new StolenCar.Builder().licensePlate(elem.getLicensePlate())
            .insertionDate(elem.getInsertionDate()).build());
      } catch (InvalidAttributeValueException e) {
        LOGGER.error("Error creating output list ", e);
      }
    });
    return out;
  }

  /**
   * Controlla se la macchina con una determinata {@link LicensePlate}, è nell'elenco delle macchine
   * rubate.
   * 
   * @param licensePlate
   *          La {@link LicensePlate} da controllare.
   * @return True se la macchina è stata rubata, false altrimenti.
   */
  @Override
  public Boolean checkStolenPlate(final LicensePlate licensePlate) {
    Dao<StolenCarRow, Integer> stolenCarDao = null;
    try {
      stolenCarDao = Connection.getInstance().getStolenCarDao();
    } catch (SQLException e) {
      LOGGER.error("Database query error ", e);
      return false;
    }

    final QueryBuilder<StolenCarRow, Integer> statementBuilder = stolenCarDao.queryBuilder();
    boolean exist = false;
    try {
      statementBuilder.where().eq(StolenCarRow.LICENSEPLATE_FIELD_NAME, licensePlate.toString());
      exist = stolenCarDao.query(statementBuilder.prepare()).isEmpty();
    } catch (SQLException e) {
      LOGGER.error("Database query error ", e);
    }
    return !exist;
  }

  /**
   * Aggiungere una nuova {@link IStolenCar} a quelle già presenti nel database.
   * 
   * @param stolenCar
   *          L'{@link StolenCar} da aggiungere.
   */
  @Override
  public void addNewStolenCar(final StolenCar stolenCar) {
    synchronized (StolenCarModelDatabase.class) {
      if (!this.checkStolenPlate(stolenCar.getLicensePlate())) {
        final StolenCarRow stolenCarRow = new StolenCarRow(stolenCar);
        Dao<StolenCarRow, Integer> stolenCarDao = null;
        try {
          stolenCarDao = Connection.getInstance().getStolenCarDao();
          stolenCarDao.createIfNotExists(stolenCarRow);
        } catch (SQLException e) {
          LOGGER.error("The creation on database of the new StolenCar is failed!", stolenCarRow, e);
        }
      }
    }
  }

}