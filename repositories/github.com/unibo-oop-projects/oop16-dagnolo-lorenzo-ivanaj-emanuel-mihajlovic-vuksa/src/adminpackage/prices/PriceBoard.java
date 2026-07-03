package adminpackage.prices;

import adminpackage.template.PriceOperation;

public class PriceBoard extends PriceOperation{

    public PriceBoard(String descrizione) {
        super(descrizione);
    }

    @Override
    public String[] getElements() {
        String[] ciao = { "Pensione completa", "Mezza pensione", "Colazione" };
        return ciao;
    }
    public static void main(String[] args){
        new PriceBoard("Prezzo pensione");
    }

}
