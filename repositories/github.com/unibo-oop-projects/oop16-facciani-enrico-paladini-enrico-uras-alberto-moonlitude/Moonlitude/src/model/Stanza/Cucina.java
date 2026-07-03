package model.Stanza;

import model.Astronauta.Astronauta;
import model.Oggetti.Cibo;

public class Cucina implements Stanza {
    
    private static class LazyHolder {
        private static final Cucina SINGLETON = new Cucina();
    }
    
    private Cucina() {
        
    }
    
    // Creo il SINGLETON alla prima chiamata
    public static Cucina getLog() {
        return LazyHolder.SINGLETON;
    }
    /**
     * Eat : given an astronaut and given a food
     * @param astronauta astronaut that will eat
     * @param cibo food that will be eaten
     * @param quanto quantity to be eaten
     */
    public void mangia(final Astronauta astronauta, final Cibo cibo, final Integer quanto) {
        Integer diminuzione = cibo.getDiminuzioneFame() * quanto * 2;
        astronauta.getParametri().modificaFame(diminuzione);
        Refrigeratore.getLog().rimuoviOggetto(cibo, quanto);
    }
}
