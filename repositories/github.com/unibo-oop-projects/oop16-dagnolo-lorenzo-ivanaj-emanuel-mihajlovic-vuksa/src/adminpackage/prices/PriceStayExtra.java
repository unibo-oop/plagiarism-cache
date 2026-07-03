package adminpackage.prices;

import adminpackage.template.PriceOperation;

/**
 * 
 * emanu.
 *
 */
public class PriceStayExtra extends PriceOperation {
    /**
     * 
     * @param descrizione
     */
    public PriceStayExtra(String descrizione) {
        super(descrizione);
    }

    @Override
    public String[] getElements() {
        String[] ciao = { "Parcheggio", "Colazione a buffet", "Palestra", "Yacuzzi" };
        return ciao;
    }
}
