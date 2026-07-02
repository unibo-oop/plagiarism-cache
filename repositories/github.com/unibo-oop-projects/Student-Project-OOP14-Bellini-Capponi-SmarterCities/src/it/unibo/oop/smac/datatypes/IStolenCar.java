package it.unibo.oop.smac.datatypes;

import java.util.Date;

/**
 * Interfaccia per la gestione delle informazioni su di una macchina rubata.
 * 
 * @author Francesco Capponi
 */
public interface IStolenCar {

  /**
   * Restituisce la targa della macchina rubata.
   * 
   * @return targa della macchina rubata
   */
  LicensePlate getLicensePlate();

  /**
   * Restituisce la data del furto della macchina.
   * 
   * @return data del furto
   */
  Date getInsertionDate();
}
