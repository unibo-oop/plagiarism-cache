package it.rentalmanage.controller;

import java.util.List;

/**
 * Created by nicolapanigucci on 01/05/16.
 */
public interface IExpiring {

    /**
     *
     * @return lista di stringhe con le scadenze di un veicolo
     */
    List<String> checkExpiring();

    /**
     * metodo utilizzato per verificare la presenza della data tra la lista delle scadenze
     * @return stringa "Insurance"
     */
    String getTextInsurance();

    /**
     * metodo utilizzato per verificare la presenza della data tra la lista delle scadenze
     * @return stringa "Car Tax"
     */
    String getTextCarTax();

    /**
     * metodo utilizzato per verificare la presenza della data tra la lista delle scadenze
     * @return "MOT Test"
     */
    String getTextMOTTest();
}
