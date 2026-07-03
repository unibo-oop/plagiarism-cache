package model.Stanza;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

import model.BaseSpaziale;
import model.Posizione;
import model.Oggetti.Materiale;

public class Radar implements Stanza, Serializable{
    private static final long serialVersionUID = -8979983480567739992L;
    Map<Posizione,Boolean> mappaMondo;
    
    private static class LazyHolder {
        private static final Radar SINGLETON = new Radar();
    }
    
    private Radar() {
        initMappaMondo();
    }
    
 // Creo il SINGLETON alla prima chiamata
    public static Radar getLog() {
        return LazyHolder.SINGLETON;
    }
    /**
     * Initialize the positions
     */
    private void initMappaMondo() {
        mappaMondo = new TreeMap<>();
        Posizione.getPosizioniNonDisponibili().stream().forEach(el -> {
           mappaMondo.put(el, false); 
        });
        mappaMondo.compute(Posizione.BOSCO, (k,v) -> v = true);
    }
    /**
     * Send a drone to discover a new position
     * @return whether or not is discovers a new Position
     */
    public Integer lanciaDrone() {
        if (BaseSpaziale.getLog().controllaOggetto(Materiale.DRONE, 1)) {
            List<Posizione> lista = new LinkedList<>();
            mappaMondo.entrySet().stream().filter(f -> f.getValue().equals(false)).map(m -> m.getKey()).forEach(el -> {
                lista.add(el);
            });
            if (lista.size() > 0) {
                Random r = new Random();
                Posizione scoperta = lista.get(r.nextInt(lista.size()));
                mappaMondo.compute(scoperta, (k,v) -> v = true);
                BaseSpaziale.getLog().rimuoviQuantitativoOggetto(Materiale.DRONE, 1);
                return 1;
            }
            return 0;
        }
        return -1;
    }
    /**
     * Get all discovered-undiscovered places
     * @param scoperti Boolean to split between discovered and undiscovered
     * @return the list of all places
     */
    public List<Posizione> getPosti(final Boolean scoperti) {
        return mappaMondo.entrySet().stream().filter(f -> f.getValue().equals(scoperti)).map(m -> m.getKey()).collect(Collectors.toList());
    }

    /**
     * Getter method for the worldmap
     * @return a map describing the world map
     */
    public Map<Posizione,Boolean> getMappaMondo() {
        Map<Posizione,Boolean> returnMap = new TreeMap<>();
        returnMap.putAll(this.mappaMondo);
        return returnMap;
    }
    
    public void setMappaMondo(final Map<Posizione,Boolean> mappaMondo) {
        this.mappaMondo.clear();
        this.mappaMondo.putAll(mappaMondo);
    }

}
