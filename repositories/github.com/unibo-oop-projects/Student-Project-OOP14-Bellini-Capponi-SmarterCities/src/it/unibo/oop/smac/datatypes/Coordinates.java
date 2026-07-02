package it.unibo.oop.smac.datatypes;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.management.InvalidAttributeValueException;

/**
 * Implementazione di una classe che gestisce le coordinate di un punto nello spazio.
 * 
 * @author Federico Bellini
 */
public class Coordinates implements ICoordinates, Serializable {

  private static final long serialVersionUID = 6127098657709069219L;

  /**
   * Estremi di validità per le coordinate. Una coordinata che supera questi limiti è inconsistente.
   */
  private static final Float MAX_COORDINATE = 180f;
  private static final Float MIN_COODRINATE = -180f;

  /**
   * La precisione dei decimali che si vuole nelle coordinate. Es. con DECIMAL_PRECISION settata a
   * 6, le coordinate avranno come lunghezza decimale massima 6 cifre, per + esempio 42.123456
   */
  private static final int DECIMAL_PRECISION = 6;

  private final Float latitude;
  private final Float longitude;

  /**
   * Costruttore che prende in ingresso la latitudine e la longitudine di un punto per crearne le
   * sue coordinate.
   * 
   * @param lat
   *          La latitudine del punto.
   * @param lng
   *          La longitudine del punto.
   * @throws InvalidAttributeValueException
   *           Questa eccezione viene lanciata quando le coordinate passate non sono valide.
   */
  public Coordinates(final Float lat, final Float lng) throws InvalidAttributeValueException {
    if (lat == null || lng == null || lat < MIN_COODRINATE || lat > MAX_COORDINATE
        || lng < MIN_COODRINATE || lng > MAX_COORDINATE) {
      throw new InvalidAttributeValueException();
    }
    this.latitude = lat;
    this.longitude = lng;
  }

  /**
   * Costruttore che prende in ingresso un'altro oggetto di tipo {@link ICoordinates} per creare un
   * nuovo oggetto posizionato nello stesso punto dell'oggetto passato.
   * 
   * @param coordinates
   *          Un oggetto {@link ICoordinates} di cui se ne vuole riprodurre un altro oggetto
   *          Coordinates avente la stessa posizione spaziale.
   * @throws InvalidAttributeValueException
   *           Questa eccezione viene lanciata quando le coordinate passate non sono valide.
   */
  public Coordinates(final ICoordinates coordinates) throws InvalidAttributeValueException {
    this(coordinates.getLatitude(), coordinates.getLongitude());
  }

  /**
   * Metodo privato utilizzato per troncare i numeri Float fino ad una certa cifra decimale,
   * stabilita dal campo DECIMAL_PRECISION.
   * 
   * @param number
   *          Il numero da modificare.
   * @return Il numero modificato.
   */
  private static Float decimalRound(final Float number) {
    BigDecimal bigDecimal = new BigDecimal(Float.toString(number));
    bigDecimal = bigDecimal.setScale(DECIMAL_PRECISION, BigDecimal.ROUND_HALF_UP);
    return bigDecimal.floatValue();
  }

  /**
   * Restituisce la latitudine del punto approssimata alla DECIMAL_PRECISION.
   * 
   * @return La latitudine del punto.
   */
  @Override
  public Float getLongitude() {
    return decimalRound(this.longitude);
  }

  /**
   * Restituisce la longitudine del punto approssimata alla DECIMAL_PRECISION.
   * 
   * @return La longitudine del punto.
   */
  @Override
  public Float getLatitude() {
    return decimalRound(this.latitude);
  }

  @Override
  public int hashCode() {
    final int prime = (1 << 3) - 1;
    int result = 1;
    result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
    result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
    return result;
  }

  // Due oggetti di tipo coordinata sono ritenuti uguali se hanno stessa latitudine e longitudine.
  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof ICoordinates) {
      return this.latitude.equals(((ICoordinates) obj).getLatitude())
          && this.longitude.equals(((ICoordinates) obj).getLongitude());
    }
    return false;
  }

  @Override
  public String toString() {
    return "[Latitude = " + latitude.toString() + ", Longitude = " + longitude.toString() + "]";
  }

}
