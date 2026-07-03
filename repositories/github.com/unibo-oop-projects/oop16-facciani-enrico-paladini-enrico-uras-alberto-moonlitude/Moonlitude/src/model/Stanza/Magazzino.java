package model.Stanza;

import java.io.Serializable;

import model.Oggetti.Contenitore;
import model.Oggetti.Materiale;

public class Magazzino extends Contenitore<Materiale> implements Stanza, Serializable {
    private static final long serialVersionUID = -8979981120567870863L;
    public static final Integer ESPANSIONE = 10;
    private static class LazyHolder {
        private static final Magazzino SINGLETON = new Magazzino(4);
    }
    
    // Creo il SINGLETON alla prima chiamata
    public static Magazzino getLog() {
        return LazyHolder.SINGLETON;
    }
    
    private Magazzino(final Integer spazio) {
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