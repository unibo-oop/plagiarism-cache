package it.rentalmanage.controller;

/**
 * Created by utente on 04/03/2016.
 */
public interface IPersonController {

    /**
     * Aggiorna i dati di una persona e li salva nei file
     */
    void updatePerson(String surname, String name, String address, String telephone);

    /**
     * Elimina la persona dai file
     */
    void deletePerson();
}
