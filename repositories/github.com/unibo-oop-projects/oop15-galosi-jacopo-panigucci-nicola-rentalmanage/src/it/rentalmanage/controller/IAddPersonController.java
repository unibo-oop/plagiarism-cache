package it.rentalmanage.controller;

import it.rentalmanage.model.IPerson;

/**
 * Created by utente on 04/03/2016.
 */
public interface IAddPersonController {

    /**
     * Scrive una persona su file
     * @param person
     */
    void writePerson(IPerson person);

}
