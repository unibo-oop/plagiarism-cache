package model;

/**
 * Enumeration used by DataChartImpl for the choice.
 * 
 * @author Nico Nize
 *
 */

public enum DatiDaVisualizzareEnum {
    
    TEMPOLAVORO(1, "Tempo di lavoro"),
    ENTRATE(2, "Entrate Giornaliere");
    
    private final int index;
    private final String itemName;
    
    DatiDaVisualizzareEnum(final int index, final String itemName){
        this.index = index;
        this.itemName = itemName;
    }

    public int getIndex() {
        return index;
    }

    public String getItemName() {
        return itemName;
    }
    
}
