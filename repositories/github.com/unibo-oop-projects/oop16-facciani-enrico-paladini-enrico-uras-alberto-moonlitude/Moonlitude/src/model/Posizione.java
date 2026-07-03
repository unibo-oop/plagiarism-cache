package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Oggetti.Cibo;
import model.Oggetti.CommonEquipaggiamento;
import model.Oggetti.Equipaggiamento;
import model.Oggetti.Materiale;
import model.Oggetti.Oggetto;
import model.Oggetti.TutaSpaziale;

public enum Posizione implements Serializable {
    BASE("Base spaziale", true),
    ACCAMPAMENTO("Accampamento", false),
    BOSCO("Bosco", false),
    LAGO("Lago", false);
    
    private static final long serialVersionUID = -8985043480567730812L;
    private final String nome;
    private final Boolean disponibile;
    
    private Posizione(final String nome, final Boolean disponibile) {
        this.nome = nome;
        this.disponibile = disponibile;
    }
    
    public String toString() {
        return this.nome;
    }
    /**
     * Get whether or not the position has been initially discovered
     * @return whether or not the position has been initially discovered
     */
    public Boolean getDisponibile() {
        return this.disponibile;
    }
    /**
     * Getter of all position not initially discovered
     * @return a list of all position not initially discovered
     */
    public static List<Posizione> getPosizioniNonDisponibili() {
        List<Posizione> posizioniNonDisponibili = new LinkedList<>();
        for (Posizione pos : Posizione.values()) {
            if (pos.getDisponibile().equals(false)) {
                posizioniNonDisponibili.add(pos);
            }
        }
        return posizioniNonDisponibili;
    }
    /**
     * Explore a position
     * @param posizione Position to be explored
     * @param ore hours of the exploration
     * @param astronauta astronaut that explores
     * @return the map of all collected items-amounts
     */
    public static Map<Oggetto, Integer> esplorazione(final Posizione posizione, final Integer ore, final Boolean astronauta) {
        Integer tempo = ore;
        
        if (astronauta.equals(true)) {
            tempo += Equipaggiamento.getLog().getTempo();
        }
        
        Map<Oggetto, Integer> oggettiOttenuti = new HashMap<>();
        if (posizione.getDisponibile().equals(false)) {
            oggettiOttenuti.putAll(Materiale.getOggettoByPosizione(posizione, ore));
            oggettiOttenuti.putAll(Cibo.getOggettoByPosizione(posizione, ore));
        }
        if (astronauta.equals(true)) {
            for (Oggetto obj : oggettiOttenuti.keySet()) {
                oggettiOttenuti.compute(obj, (k,v) -> v = v + (v*Equipaggiamento.getLog().getRisorse())/RangeRandom.MAX_PROB);
            }
        }
        oggettiOttenuti.putAll(CommonEquipaggiamento.verificati());
        oggettiOttenuti.putAll(TutaSpaziale.verificati());
        
        return oggettiOttenuti;
    }
}
