package model.Stanza;

import java.io.Serializable;

public enum StatoPianta implements Serializable{
    VIVA("Viva"),
    MATURA("Matura"),
    MARCIA("Marcia"),
    MORTA("Morta");
    
    private static final long serialVersionUID = -8998765480567730812L;
    private final String stato;
    
    private StatoPianta(final String stato) {
        this.stato = stato;
    }
    /**
     * Getter method for plant status
     * @return the string value of the plant status
     */
    public String getStato() {
        return this.stato;
    }

}
