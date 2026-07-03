package it.rentalmanage.controller;

import it.rentalmanage.model.ICar;

/**
 * Created by utente on 09/03/2016.
 */
public interface IAddCarController {

    /**
     * Scrive un veicolo su file
     * @param car
     */
    void writeCar(ICar car);
}
