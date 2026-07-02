package it.unibo.oop.smac.datatypes;

import it.unibo.oop.smac.database.model.StreetObserverNotValidException;

import java.util.Date;

import javax.management.InvalidAttributeValueException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Questa classe implementa l'interfaccia {@link ISighting}, ed ha il compito di raccogliere tutte
 * le informazioni generate dall'osservatore, quali la data dell'avvistamento, la targa dell'auto
 * avvistata e la sua velocita'. Questa classe è costruita utilizzando il pattern Builder.
 * 
 * @author Federico Bellini
 *
 */
public final class Sighting implements ISighting {

  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(Sighting.class);

  /**
   * Campi privati della classe.
   */
  private final IStreetObserver streetObserver;
  private final Date date;
  private final LicensePlate licensePlate;
  private final Float speed;

  /**
   * Costruttore privato della classe.
   * 
   * @param stObserver
   *          L'osservatore che ha compiuto l'avvistamento.
   * @param d
   *          La data dell'avvistamento.
   * @param plate
   *          La targa dell'auto avvistata.
   * @param s
   *          La velocita' rilevata.
   */
  private Sighting(final IStreetObserver stObserver, final Date d, final LicensePlate plate,
      final Float s) {
    this.streetObserver = stObserver;
    this.date = d;
    this.licensePlate = plate;
    this.speed = s;
  }

  /**
   * Restituisce l'{@link IStreetObserver} che ha generato le informazioni di questo avvistamento.
   * 
   * @return L'{@link IStreetObserver} autore dell'avvistamento.
   */
  @Override
  public IStreetObserver getStreetObserver() {
    StreetObserver out = null;
    try {
      out = new StreetObserver(this.streetObserver);
    } catch (StreetObserverNotValidException e) {
      LOGGER.error("Inner street observer is not valid", e);
    }
    return out;
  }

  /**
   * Restituisce la {@link Date} relativa al momento dell'avvistamento.
   * 
   * @return La {@link Date} relativa al momento dell'avvistamento.
   */
  @Override
  public Date getDate() {
    Date response = null;
    if (this.date != null) {
      response = new Date(this.date.getTime()); // defensive copy
    }
    return response;
  }

  /**
   * Restituisce la {@link LicensePlate} dell'auto avvistata.
   * 
   * @return La {@link LicensePlate} dell'auto avvistata.
   */
  @Override
  public LicensePlate getLicensePlate() {
    LicensePlate out = new LicensePlate();
    try {
      if (this.licensePlate != null) {
        out = new LicensePlate(this.licensePlate); // defensive copy
      }
    } catch (InvalidAttributeValueException e) {
      LOGGER.error("Invalid inner plate", e);
    }
    return out;
  }

  /**
   * Restituisce la velocita' dell'auto avvistata.
   * 
   * @return La velocita' dell'auto avvistata.
   */
  @Override
  public Float getSpeed() {
    return this.speed;
  }

  @Override
  public int hashCode() {
    final int prime = (1 << 3) - 1;
    int result = 1;
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    result = prime * result + ((licensePlate == null) ? 0 : licensePlate.hashCode());
    result = prime * result + ((speed == null) ? 0 : speed.hashCode());
    result = prime * result + ((streetObserver == null) ? 0 : streetObserver.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof Sighting) {
      return this.getDate().equals(((Sighting) obj).getDate())
          && this.getLicensePlate().equals(((Sighting) obj).getLicensePlate())
          && this.getSpeed().equals(((Sighting) obj).getSpeed())
          && this.getStreetObserver().equals(((Sighting) obj).getStreetObserver());
    }
    return false;
  }

  /**
   * Classe statica Builder per la classe Sighting. Questa classe permette di creare un oggetto
   * della classe Sighting, utilizzando il pattern Builder.
   * 
   * @author Federico Bellini
   */
  public static class Builder {
    private IStreetObserver stObserver;
    private Date d;
    private LicensePlate plate;
    private Float s;

    /**
     * Costruisce l'oggetto Sighting con l'{@link IStreetObserver} passato come paramentro.
     * 
     * @param streetObserver
     *          L'IStreetObserver da settare.
     * @return Il Builder stesso.
     * @throws InvalidAttributeValueException
     *           Nel caso in cui lo streetObserver passato non sia valido.
     */
    public Builder streetObserver(final IStreetObserver streetObserver)
        throws InvalidAttributeValueException {
      if (streetObserver == null) {
        throw new InvalidAttributeValueException();
      }
      this.stObserver = streetObserver;
      return this;
    }

    /**
     * Costruisce l'oggetto Sighting con la data' passata come paramentro.
     * 
     * @param date
     *          La data da settare.
     * @return Il Builder stesso.
     * @throws InvalidAttributeValueException
     *           Nel caso in cui la data non sia valida, oppure successiva alla data attuale.
     */
    public Builder date(final Date date) throws InvalidAttributeValueException {
      if (date == null || date.after(new Date())) {
        throw new InvalidAttributeValueException();
      }
      this.d = new Date(date.getTime());
      return this;
    }

    /**
     * Costruisce l'oggetto Sighting con la LicensePlate passata come paramentro.
     * 
     * @param licensePlate
     *          La LicensePlate' da settare.
     * @return Il Builder stesso.
     * @throws InvalidAttributeValueException
     *           Nel caso in cui la licensePlate non sia valida.
     */
    public Builder licensePlate(final LicensePlate licensePlate)
        throws InvalidAttributeValueException {
      if (licensePlate == null) {
        throw new InvalidAttributeValueException();
      }
      this.plate = licensePlate;
      return this;
    }

    /**
     * Costruisce l'oggetto Sighting con la velocita' passata come paramentro.
     * 
     * @param speed
     *          La velocita' da settare.
     * @return Il Builder stesso.
     * @throws InvalidAttributeValueException
     *           Nel caso in cui la velocita' non sia valida(nulla o negativa).
     */
    public Builder speed(final Float speed) throws InvalidAttributeValueException {
      if (speed == null || speed < 0) {
        throw new InvalidAttributeValueException();
      }
      this.s = speed;
      return this;
    }

    /**
     * Metodo utilizzato per creare un oggetto della classe Sighting con gli attributi appena
     * settati.
     * 
     * @return L'oggetto della classe Sighting appena creato.
     */
    public Sighting build() {
      return new Sighting(this.stObserver, this.d, this.plate, this.s);
    }
  }

}
