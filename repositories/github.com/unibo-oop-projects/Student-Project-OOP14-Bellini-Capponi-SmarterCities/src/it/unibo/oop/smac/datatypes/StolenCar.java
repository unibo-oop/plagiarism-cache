package it.unibo.oop.smac.datatypes;

import java.util.Date;

import javax.management.InvalidAttributeValueException;

/**
 * Implementazione concreta di un'{@link IStolenCar}. Questa classe e' implementata utilizzando il
 * pattern Builder.
 * 
 * @author Francesco Capponi
 */
public class StolenCar implements IStolenCar {

  /**
   * Attributo contente la targa della macchina rubata.
   */
  private final LicensePlate licensePlate;

  /**
   * Attributo contenente la data del furto della macchina.
   */
  private final Date insertionDate;

  /**
   * Costruttore della classe che permette l'inizializzazione i suoi campi.
   * 
   * @param plate
   *          La {@link LicensePlate} della macchina.
   * @param d
   *          La {@link Date} del furto della macchina.
   * @throws InvalidAttributeValueException
   *           Viene generata se il valore della targa non è conforme alle specifiche (es. AA000AA).
   */
  public StolenCar(final LicensePlate plate, final Date d) throws InvalidAttributeValueException {
    this.licensePlate = new LicensePlate(plate);
    this.insertionDate = new Date(d.getTime());
  }

  /**
   * Restituisce la targa della macchina rubata.
   * 
   * @return Targa della macchina rubata.
   */
  @Override
  public LicensePlate getLicensePlate() {
    return licensePlate;
  }

  /**
   * Restituisce la data del furto della macchina.
   * 
   * @return Data del furto.
   */
  @Override
  public Date getInsertionDate() {
    return new Date(insertionDate.getTime());
  }

  /**
   * Classe statica Builder per la classe StolenCar. Questa classe permette di creare un oggetto
   * della classe StolenCar, utilizzando il pattern Builder.
   */
  public static class Builder {

    private LicensePlate plate;
    private Date insDate;

    /**
     * Costruisce l'oggetto StolenCar con la LicensePlate passata come paramentro.
     * 
     * @param licensePlate
     *          La LicensePlate' da settare.
     * @return Il Builder stesso.
     */
    public Builder licensePlate(final LicensePlate licensePlate) {
      this.plate = licensePlate;
      return this;
    }

    /**
     * Costruisce l'oggetto StolenCar con la targa passata come paramentro.
     * 
     * @param licensePlate
     *          La LicensePlate' da settare.
     * @return Il Builder stesso.
     * @throws InvalidAttributeValueException
     *           restituita quando la targa non ha il formato giusto, es. AA000AA
     */
    public Builder licensePlate(final String licensePlate) throws InvalidAttributeValueException {
      this.licensePlate(new LicensePlate(licensePlate));
      return this;
    }

    /**
     * Costruisce l'oggetto StolenCar con la data' passata come paramentro.
     * 
     * @param insertionDate
     *          La data da settare.
     * @return Il Builder stesso.
     */
    public Builder insertionDate(final Date insertionDate) {
      this.insDate = new Date(insertionDate.getTime());
      return this;
    }

    /**
     * Costruisce l'oggetto StolenCar con la data' corrente.
     * 
     * @return Il Builder stesso.
     */
    public Builder insertionDateNow() {
      this.insertionDate(new Date());
      return this;
    }

    /**
     * Metodo utilizzato per creare un oggetto della classe Sighting con gli attributi appena
     * settati.
     * 
     * @return L'oggetto della classe Sighting appena creato.
     * @throws InvalidAttributeValueException
     *           Eccezione lanciata quando la targa inserita non e' valida.
     * @exception IllegalArgumentException
     *              Lanciata quando viene invocato il metodo build senza aver settato tutti i campi
     *              con un valore.
     */
    public StolenCar build() throws InvalidAttributeValueException {
      if (this.plate == null || this.insDate == null) {
        throw new IllegalArgumentException("You must complete all fields before building! ");
      }
      return new StolenCar(this.plate, this.insDate);
    }
  }

}
