package it.unibo.apice.oop.machikoro.model;

/**
 * Enumeratore per identificare gli effetti delle carte obiettivo.
 */
public enum AimEffect {
    /**
     * Stazione Ferroviaria, poter tirare due dadi.
     */
    TRAIN_STATION,
    /**
     * Centro Commerciale, aumenta la rendita delle carte Market e Ristorante.
     */
    SHOPPING_MALL,
    /**
     * Luna Park, permette di ritirare nel caso in cui si faccia doppio.
     */
    AMUSEMENT_PARK,
    /**
     * Torre Radio, consente di ritirare i dadi se non si è contenti del
     * risultato.
     */
    RADIO_TOWER
}
