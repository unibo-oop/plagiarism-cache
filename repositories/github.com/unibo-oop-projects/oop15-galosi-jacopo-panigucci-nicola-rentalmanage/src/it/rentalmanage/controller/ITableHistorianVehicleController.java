package it.rentalmanage.controller;

import it.rentalmanage.model.ICar;

/**
 * Created by nicolapanigucci on 03/05/16.
 */
public interface ITableHistorianVehicleController {

    /**
     * Permette di visualizzare lo storico dei clienti che hanno noleggiato 'car' in una tabella
     * @param car
     */
    void showHistorianVehicle(ICar car);
}
