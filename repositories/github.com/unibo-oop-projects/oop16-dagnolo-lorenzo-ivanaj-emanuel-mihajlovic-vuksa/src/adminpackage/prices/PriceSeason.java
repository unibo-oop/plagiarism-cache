package adminpackage.prices;

import adminpackage.template.PriceOperation;

public class PriceSeason extends PriceOperation {

    public PriceSeason(String descrizione) {
        super(descrizione);
    }

    @Override
    public String[] getElements() {
        String[] ciao = { "Estate", "Primavera", "Autunno", "Inverno" };
        return ciao;
    }
}
