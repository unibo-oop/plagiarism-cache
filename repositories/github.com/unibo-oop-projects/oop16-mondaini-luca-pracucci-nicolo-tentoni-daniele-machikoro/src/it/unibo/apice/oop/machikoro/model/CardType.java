package it.unibo.apice.oop.machikoro.model;

/**
 * Enumeratore per identificare i vari tipi di carte da gioco.
 */
public enum CardType {
    /**
     * Identifica le carte di tipo Agricoltura, come il campo di grano.
     */
    AGRICOLTURE,
    /**
     * Identifica le carte di tipo Primario, come la Foresta.
     */
    PRIMARY,
    /**
     * Identifica le carte di tipo Fattoria, come la Fattoria.
     */
    FARM,
    /**
     * Identifica le carte di tipo Negozio, come il Panettiere.
     */
    SHOP,
    /**
     * Identifica le carte di tipo Fabbrica, come il mobilificio.
     */
    FACTORY,
    /**
     * Identifica le carte di tipo Mercato, come il Negozio Ortofrutticolo.
     */
    MARKET,
    /**
     * Identifica le carte di tipo Ristorante, come la caffetteria.
     */
    RESTAURANT,
    /**
     * Questo identificatore è usato nell'implementazione degli effetti, per
     * dire che l'effetto non dipende da nessun'altra istanza di qualche carta.
     */
    NOTHING
}