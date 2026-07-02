package it.unibo.oop.smac.datatypes;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.management.InvalidAttributeValueException;

/**
 * Classe che implementa la struttura della targa di un mezzo.
 * 
 * @author Federico Bellini
 */
public class LicensePlate implements Serializable {

  private static final long serialVersionUID = -194344929770325193L;

  /**
   * contenitore della valore della targa
   */
  private final String plate;

  /**
   * Costruttore che crea una nuova LicensePlate, con la targa di test "AA000AA". (necessario per
   * funzionamento database)
   */
  public LicensePlate() {
    this.plate = "AA000AA";
  }

  /**
   * Costruttore che crea una nuova LicensePlate, copia di quella passata come parametro.
   * 
   * @param licensePlate
   *          La LicensePlate di cui creare una copia.
   * @throws InvalidAttributeValueException
   *           scatenato quando la targa non è valida
   */
  public LicensePlate(final LicensePlate licensePlate) throws InvalidAttributeValueException {
    this(licensePlate.getPlate());
  }

  /**
   * Coostruttore che crea una nuova LicensePlate impostando la stringa passata come parametro come
   * targa del mezzo.
   * 
   * @param licensePlate
   *          La targa che si vuole impostare.
   * @throws InvalidAttributeValueException
   *           scatenato quando la targa non è valida
   */
  public LicensePlate(final String licensePlate) throws InvalidAttributeValueException {
    if (!Pattern.matches("[A-Z]{2}[0-9]{3}[A-Z]{2}", licensePlate)) {
      throw new InvalidAttributeValueException();
    }
    this.plate = licensePlate;
  }

  /**
   * Restituisce una stringa contenente la targa del mezzo.
   * 
   * @return La targa del mezzo.
   */
  public String getPlate() {
    return this.plate;
  }

  @Override
  public int hashCode() {
    final int prime = (1 << 3) - 1;
    int result = 1;
    result = prime * result + ((plate == null) ? 0 : plate.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof LicensePlate) {
      return this.getPlate().equals(((LicensePlate) obj).getPlate());
    }
    return false;
  }

  @Override
  public String toString() {
    return this.getPlate();
  }

}
