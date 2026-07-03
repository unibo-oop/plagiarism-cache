package adminpackage.prices;

import adminpackage.template.PriceOperation;

public class PricePerson extends PriceOperation {

    public PricePerson(String descrizione) {
        super(descrizione);
    }

    @Override
    public String[] getElements() {
        String[] ciao = { "Adulto", "Bambino", "Schiavo" };
        return ciao;
    }

}
