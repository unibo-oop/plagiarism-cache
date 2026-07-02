package it.unibo.oop.smac.database;

import it.unibo.oop.smac.datatypes.ISighting;
import it.unibo.oop.smac.datatypes.IStreetObserver;
import it.unibo.oop.smac.datatypes.LicensePlate;
import it.unibo.oop.smac.datatypes.Sighting;

import java.util.Date;

import javax.management.InvalidAttributeValueException;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Classe che implementa un'{@link ISighting} da salvare nel database.
 * 
 * @author Francesco Capponi
 * @author Federico Bellini
 */
@DatabaseTable(tableName = "Sighting")
public class SightingRow implements ISighting {

  /**
   * Campo contenente l'ID dell'avvistamento.
   */
  @DatabaseField(generatedId = true)
  private int id;

  /**
   * Campo contenente l'osservatore che ha compiuto l'avvistamento.
   */
  @DatabaseField(canBeNull = false, foreign = true)
  private final StreetObserverRow streetObserverRow;

  /**
   * Campo contenente la data di avvistamento.
   */
  @DatabaseField(canBeNull = true, dataType = DataType.DATE_LONG)
  private final Date date;

  /**
   * Campo contenente la targa del mezzo rilevata.
   */
  @DatabaseField(canBeNull = true, dataType = DataType.SERIALIZABLE)
  private final LicensePlate licensePlate;

  /**
   * Campo contenente la velocita' rilevata.
   */
  @DatabaseField(canBeNull = true)
  private final Float speed;

  /**
   * Costruttore di default reimplementato per il corretto funzionamento delle librerie di database.
   * 
   * @throws InvalidAttributeValueException
   *           Questo caso non può presentarsi.
   */
  SightingRow() throws InvalidAttributeValueException {
    this(new Sighting.Builder().build(), new StreetObserverRow());
  }

  /**
   * Costruttore pubblico per la classe.
   * 
   * @param sighting
   *          L'{@link ISighting} rilevato dall'osservatore.
   * @param stObserverRow
   *          L'osservatore che ha compiuto l'avvistamento.
   */
  public SightingRow(final ISighting sighting, final StreetObserverRow stObserverRow) {
    this.streetObserverRow = stObserverRow;
    this.date = sighting.getDate();
    this.licensePlate = sighting.getLicensePlate();
    this.speed = sighting.getSpeed();
  }

  @Override
  public IStreetObserver getStreetObserver() {
    return streetObserverRow;
  }

  @Override
  public Date getDate() {
    return new Date(date.getTime());
  }

  @Override
  public LicensePlate getLicensePlate() {
    return licensePlate;
  }

  @Override
  public Float getSpeed() {
    return speed;
  }

  @Override
  public String toString() {
    return "SightingRow [id=" + id + ", streetObserverRow=" + streetObserverRow + ", date=" + date
        + ", licensePlate=" + licensePlate + ", speed=" + speed + "]";
  }

  @Override
  public int hashCode() {
    final int prime = (1 << 3) - 1;
    int result = 1;
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    result = prime * result + id;
    result = prime * result + ((licensePlate == null) ? 0 : licensePlate.hashCode());
    result = prime * result + ((speed == null) ? 0 : speed.hashCode());
    result = prime * result + ((streetObserverRow == null) ? 0 : streetObserverRow.hashCode());
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
    final SightingRow other = (SightingRow) obj;
    if (date == null || other.date == null) {
      return false;
    } else if (!date.equals(other.date)) {
      return false;
    }

    if (id != other.id) {
      return false;
    }

    if (licensePlate == null || other.licensePlate != null) {
      return false;
    } else if (!licensePlate.equals(other.licensePlate)) {
      return false;
    }

    if (speed == null || other.speed != null) {
      return false;
    } else if (!speed.equals(other.speed)) {
      return false;
    }
    if (streetObserverRow == null || other.streetObserverRow != null) {
      return false;
    } else if (!streetObserverRow.equals(other.streetObserverRow)) {
      return false;
    }
    return true;
  }

}