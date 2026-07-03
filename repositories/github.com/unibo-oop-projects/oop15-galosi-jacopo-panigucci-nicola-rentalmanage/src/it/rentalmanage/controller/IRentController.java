package it.rentalmanage.controller;

import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IPerson;

import java.util.Date;

/**
 * Created by nicolapanigucci on 09/04/16.
 */
public interface IRentController {

    /**
     * Registra il noleggio su 'IPerson' e su 'ICar'
     * @param iCar
     * @param iPerson
     * @param start
     * @param end
     */
    void rentCar(ICar iCar, IPerson iPerson, Date start, Date end);
}
