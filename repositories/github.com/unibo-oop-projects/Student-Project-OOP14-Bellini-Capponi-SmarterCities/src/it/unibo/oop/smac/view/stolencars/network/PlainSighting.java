package it.unibo.oop.smac.view.stolencars.network;

import it.unibo.oop.smac.datatypes.Coordinates;
import it.unibo.oop.smac.datatypes.Sighting;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe di scambio rete client-server, prima di essere tradotto in Sighting. Utilizzata poiché non
 * è possibile spedire attraverso la rete un messaggio contentente referenze ad database, quale
 * sarebbe un semplice {@link Sighting}. Implementa la classe Serializable per poter essere
 * serializzata e spedita via rete.
 * 
 * @author Francesco Capponi
 */
public class PlainSighting implements Serializable {

  private static final long serialVersionUID = -6098237635297318104L;

  /**
   * Coordinate dell'Observer che spedisce il Sighting.
   */
  // TODO non è meglio utilizzare l'interfaccia ICoordinates invece di Coordinates?
  private Coordinates coordinates;

  /**
   * Data-ora dell'avvistamento.
   */
  private Date date;

  /**
   * Targa della macchina avvistata.
   */
  private String licensePlate;

  /**
   * Velocità della macchina avvistata.
   */
  private Float speed;

  /**
   * Restituisce la data dell'avvistamento.
   * 
   * @return data d'avvistamento
   */
  public Date getDate() {
    return new Date(date.getTime());
  }

  /**
   * Imposta la data dell'avvistamento.
   * 
   * @param d
   *          La {@link Date} dell'avvistamento.
   */
  public void setDate(final Date d) {
    this.date = new Date(d.getTime());
  }

  /**
   * Restituisce la targa della macchina avvistata.
   * 
   * @return targa avvistata
   */
  public String getLicensePlate() {
    return licensePlate;
  }

  /**
   * Imposta la targa della macchina avvistata.
   * 
   * @param plate
   *          La targa dell'auto avvistata.
   */
  public void setLicensePlate(final String plate) {
    this.licensePlate = plate;
  }

  /**
   * Restituisce la velocità della macchina avvistata.
   * 
   * @return velocità della macchina
   */
  public Float getSpeed() {
    return speed;
  }

  /**
   * Imposta la velocità della macchina avvistata.
   * 
   * @param s
   *          La velocita' dell'auto avvistata.
   */
  public void setSpeed(final Float s) {
    this.speed = s;
  }

  /**
   * Restituisce le coordinate dello street observer che effettua l'avvistamento.
   * 
   * @return coordinate dello street observer
   */
  public Coordinates getCoordinates() {
    return coordinates;
  }

  /**
   * Imposta le coordinate dello street observer che effettua l'avvistamento.
   * 
   * @param c
   *          Le coordinate dello street observer.
   */
  public void setCoordinates(final Coordinates c) {
    this.coordinates = c;
  }

}