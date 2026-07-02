package it.unibo.oop.smac.model;

import it.unibo.oop.smac.datatypes.IStolenCar;
import it.unibo.oop.smac.datatypes.LicensePlate;
import it.unibo.oop.smac.datatypes.StolenCar;

import java.util.List;

/**
 * Interfaccia del Model dell'applicazione, con aggiunta dei metodi necessari alla gestione delle
 * auto rubate.
 * 
 * @author Francesco Capponi
 */
public interface IStolenCarModel extends IStreetObserverModel {

  /**
   * Restituisce la lista delle {@link IStolenCar} contenute in memoria.
   * 
   * @return La lista di {@link IStolenCar} richiesta.
   */
  List<IStolenCar> getStolenCarsInfoList();

  /**
   * Controlla se la macchina con una determinata {@link LicensePlate}, è nell'elenco delle macchine
   * rubate.
   * 
   * @param licensePlate
   *          La {@link LicensePlate} da controllare.
   * @return True se la macchina è stata rubata, false altrimenti.
   */
  Boolean checkStolenPlate(LicensePlate licensePlate);

  /**
   * Aggiungere una nuova {@link IStolenCar} a quelle già presenti nel Model.
   * 
   * @param stolenCar
   *          L'{@link StolenCar} da aggiungere.
   */
  void addNewStolenCar(StolenCar stolenCar);

}