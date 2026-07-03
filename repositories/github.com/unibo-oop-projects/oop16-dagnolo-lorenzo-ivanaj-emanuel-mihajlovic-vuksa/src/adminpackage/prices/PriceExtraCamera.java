package adminpackage.prices;

import adminpackage.template.PriceOperation;

public class PriceExtraCamera extends PriceOperation {

    public PriceExtraCamera(String descrizione) {
        super(descrizione);
    }

    @Override
    public String[] getElements() {
        String[] ciao = { "ciao", "boh" };
        return ciao;
    }

}
