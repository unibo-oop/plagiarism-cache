package it.rentalmanage.controller;

import it.rentalmanage.model.IPerson;

/**
 * Created by nicolapanigucci on 03/05/16.
 */
public interface ITableHistorianPersonController {

    /**
     * Permette di visualizzare lo storico delle auto noleggiate da 'person' in una tabella
     * @param person
     */
    void showHistorianPerson(IPerson person);
}
