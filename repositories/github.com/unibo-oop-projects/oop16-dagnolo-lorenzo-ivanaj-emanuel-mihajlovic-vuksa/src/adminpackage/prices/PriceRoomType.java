package adminpackage.prices;

import adminpackage.template.PriceOperation;

public class PriceRoomType extends PriceOperation{

    public PriceRoomType(String descrizione) {
        super(descrizione);
    }

    public String[] getElements() {
        String[] ciao = { "Matrimoniale", "Singola", "Doppia" };
        return ciao;
    }
}
