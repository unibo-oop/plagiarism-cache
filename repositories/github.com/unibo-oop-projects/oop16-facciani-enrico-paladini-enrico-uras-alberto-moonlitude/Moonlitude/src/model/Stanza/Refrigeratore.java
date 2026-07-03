package model.Stanza;

import java.io.Serializable;

import model.Oggetti.Cibo;
import model.Oggetti.Contenitore;

public class Refrigeratore extends Contenitore<Cibo> implements Stanza, Serializable {
    private static final long serialVersionUID = -8973334480567736811L;
    public static final Integer ESPANSIONE = 10;
    
    private static class LazyHolder {
        private static final Refrigeratore SINGLETON = new Refrigeratore(4);
    }
    
    // Creo il SINGLETON alla prima chiamata
    public static Refrigeratore getLog() {
        return LazyHolder.SINGLETON;
    }
    
    private Refrigeratore(final Integer spazio) {
        super.aumentaSpazio(4);
    }
    /**
     * Method to increase storage space
     * @return whether or not it is possible to increase the slots
     */
    public Boolean aumentaSpazio() {
        return super.aumentaSpazio();
    }
    
    /**
     * Method to increase store space without using any item.
     * Used for the "Upload Game"
     */
    public void aumentaSpazio(final Integer dimensione) {
        super.aumentaSpazio(dimensione);
    }
}