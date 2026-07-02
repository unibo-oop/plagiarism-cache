package it.unibo.oop.smac.datatypes;

import it.unibo.oop.smac.database.model.StreetObserverNotValidException;

import javax.management.InvalidAttributeValueException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementazione concreta di un'{@link IStreetObserver}.
 * 
 * @author Federico Bellini
 */
public class StreetObserver implements IStreetObserver {

  /**
   * Logger della classe.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(StreetObserver.class);

  /**
   * Le coordinate dell'osservatore.
   */
  private final ICoordinates coordinates;

  /**
   * Costruttore che prende in ingresso un'{@link IStreetObserver} e ne crea una copia.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} riprodurre.
   * @throws StreetObserverNotValidException
   *           Se viene passato uno {@link IStreetObserver} nullo.
   */
  public StreetObserver(final IStreetObserver streetObserver)
      throws StreetObserverNotValidException {
    if (streetObserver == null) {
      throw new StreetObserverNotValidException();
    }
    this.coordinates = streetObserver.getCoordinates();
  }

  /**
   * Costruttore che crea un nuovo osservatore in base alle coordinate fornite.
   * 
   * @param c
   *          Le coordinate di posizione dell'osservatore.
   * @throws InvalidAttributeValueException
   *           Se le coordinate passate non sono valide.
   */
  public StreetObserver(final ICoordinates c) throws InvalidAttributeValueException {
    if (c == null) {
      throw new InvalidAttributeValueException();
    }
    this.coordinates = c;
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
      out = new Coordinates(this.coordinates);
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
    return this.getLatitude() + "." + this.getLongitude();
  }

  @Override
  public int hashCode() {
    final int prime = (1 << 3) - 1;
    int result = 1;
    result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
    return result;
  }

  // Due IStreetObserver vengono ritenuti identici se hanno le stesse
  // coordinate.
  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof IStreetObserver) {
      return this.coordinates.equals(((IStreetObserver) obj).getCoordinates());
    }
    return false;
  }

  @Override
  public String toString() {
    return "StreetObserver in : " + coordinates;
  }

}
