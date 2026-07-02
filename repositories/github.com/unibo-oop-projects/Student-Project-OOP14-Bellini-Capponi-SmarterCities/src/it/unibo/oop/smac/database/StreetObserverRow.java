package it.unibo.oop.smac.database;

import it.unibo.oop.smac.datatypes.Coordinates;
import it.unibo.oop.smac.datatypes.ICoordinates;
import it.unibo.oop.smac.datatypes.IStreetObserver;
import it.unibo.oop.smac.datatypes.StreetObserver;

import java.util.ArrayList;
import java.util.List;

import javax.management.InvalidAttributeValueException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Classe che implementa un'{@link IStreetObserver} da salvare nel database.
 * 
 * @author Francesco Capponi
 * @author Federico Bellini
 */
@DatabaseTable(tableName = "StreetObserver")
public class StreetObserverRow implements IStreetObserver {

  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(StreetObserverRow.class);

  /**
   * Campo contenente l'ID dell'osservatore.
   */
  @DatabaseField(id = true, canBeNull = false)
  private final String id;

  /**
   * Campo contenente le coordinate dell'osservatore.
   */
  @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
  private final Coordinates coordinates;

  /**
   * Campo contenente tutti gli avvistamenti compiuti da questo osservatore.
   */
  @ForeignCollectionField(eager = false)
  private ForeignCollection<SightingRow> sightings;

  /**
   * Costruttore di default reimplementato per il corretto funzionamento delle librerie di database.
   * 
   * @throws InvalidAttributeValueException
   *           Questo caso non si può presentare mai.
   */
  public StreetObserverRow() throws InvalidAttributeValueException {
    this(new StreetObserver(new Coordinates(0f, 0f)));
  }

  /**
   * Costruttore che prende in ingresso un'{@link IStreetObserver} di cui salvarne una copia.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} da farne una copia.
   */
  public StreetObserverRow(final IStreetObserver streetObserver) {
    this.coordinates = (Coordinates) streetObserver.getCoordinates();
    this.id = streetObserver.getId();
  }

  /**
   * Metodo pubblico che aggiunge un avvistamento alla lista di avvistamenti compiuti da questo
   * osservatore.
   * 
   * @param sighting
   *          L'avvistamento da aggiungere alla lista.
   */
  public void addSightings(final SightingRow sighting) {
    this.sightings.add(sighting);
    System.out.println("Just added new sighting: " + sighting);
  }

  /**
   * Metodo che restituisce una lista contenente tutti gli avvistamenti compiuti da questo
   * osservatore.
   * 
   * @return Una lista contenente tutti gli avvistamenti compiuti da questo osservatore.
   */
  public List<SightingRow> getSightingsList() {
    return new ArrayList<SightingRow>(this.sightings); // defensive copy
  }

  /**
   * Restituisce le coordinate dove è posizionato l'osservatore.
   * 
   * @return Le {@link ICoordinates} relative alla posizione dell'osservatore.
   */
  @Override
  public ICoordinates getCoordinates() {
    Coordinates out = null;
    try {
      out = new Coordinates(this.coordinates); // defensive copy
    } catch (InvalidAttributeValueException e) {
      LOGGER.error("Invalid inner coordinates ", e);
    }
    return out;
  }

  /**
   * Restituisce la latitudine dell'osservatore.
   * 
   * @return La latitudine dell'osservatore.
   */
  @Override
  public Float getLatitude() {
    return this.coordinates.getLatitude();
  }

  /**
   * Restituisce la longitudine dell'osservatore.
   * 
   * @return La longitudine dell'osservatore.
   */
  @Override
  public Float getLongitude() {
    return this.coordinates.getLongitude();
  }

  /**
   * Restituisce una stringa contenente un identificatore univoco per l'osservatore.
   * 
   * @return L'identificatore univoco dell'osservatore.
   */
  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String toString() {
    return "StreetObserverRow = " + "[ID : " + this.id + "; " + this.coordinates + "; "
        + this.sightings + "]";
  }

  @Override
  public int hashCode() {
    final int prime = (1 << 3) - 1;
    int result = 1;
    result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((sightings == null) ? 0 : sightings.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final StreetObserverRow other = (StreetObserverRow) obj;
    if (coordinates == null || other.coordinates != null) {
      return false;
    } else if (!coordinates.equals(other.coordinates)) {
      return false;
    }

    if (id == null || other.id != null) {
      return false;
    } else if (!id.equals(other.id)) {
      return false;
    }

    if (sightings == null || other.sightings != null) {
      return false;
    } else if (!sightings.equals(other.sightings)) {
      return false;
    }
    return true;
  }

}