package model.Stanza;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import model.BaseSpaziale;
import model.RangeRandom;
import model.Astronauta.Astronauta;
import model.Astronauta.Esperienza;
import model.Oggetti.CommonCraft;
import model.Oggetti.Materiale;

public class Laboratorio implements Stanza, Serializable {
    private static final long serialVersionUID = -8979983487654730992L;
    private Set<Materiale> oggettiStudiati = new HashSet<>();
    private Set<CommonCraft> disponibiliDaCraftare = new HashSet<>();
    
    private static class LazyHolder {
        private static final Laboratorio SINGLETON = new Laboratorio();
    }
    
    private Laboratorio() {
        
    }
    
    // Creo il SINGLETON alla prima chiamata
    public static Laboratorio getLog() {
        return LazyHolder.SINGLETON;
    }
    /**
     * Research a material
     * @param materiale material to be researched
     */
    public void ricerca(final Materiale materiale) {
        Set<CommonCraft> perMateriale = new HashSet<>();
        if (!oggettiStudiati.contains(materiale)) {
            this.oggettiStudiati.add(materiale);
            perMateriale.addAll(CommonCraft.getCraftConMateriale(materiale));
            perMateriale.stream().filter(f -> f.getSeCraftabileConMateriali(this.oggettiStudiati).equals(true)).forEach(e -> {
                this.disponibiliDaCraftare.add(e);
            });
        }
    }
    /**
     * Getter method for materials available to be researched
     * @return the set of materials
     */
    public Set<Materiale> getMaterialiNonStudiati() {
        Set<Materiale> oggettiNonStudiati = new HashSet<>();
        for (Materiale mat : Materiale.values()) {
            if (!oggettiStudiati.contains(mat)) {
                oggettiNonStudiati.add(mat);
            }
        }
        return oggettiNonStudiati;
    }
    
    /**
     * Getter method for craftable items
     * @return the set of craftable items
     */
    public Set<CommonCraft> getDisponibiliDaCraftare() {
        return new HashSet<>(this.disponibiliDaCraftare);
    }
    
    /**
     * Getter method for objects studied
     * @return the set of materials
     */
    public Set<Materiale> getOggettiStudiati() {
        return new HashSet<>(this.oggettiStudiati);
    }
    
    /**
     * Try to craft an item
     * @param craftingItem item to be crafted
     * @param astronauta astronaut that will craft
     * @return 1 = SUCCESS, 0 = FAIL, -1 = NO ITEMS
     */
    public Integer craft(final CommonCraft craftingItem, final Astronauta astronauta) {
        Boolean controllo = true;
        for (int i = 0; i < craftingItem.getOggettiRichiesti().length; i++) {
            if (!BaseSpaziale.getLog().controllaOggetto(craftingItem.getOggettiRichiesti()[i], craftingItem.getNumeroOggetti()[i])) {
                controllo = false;
            }
        }
        
        
        for (Materiale materiale : Materiale.values()) {
            if (materiale.getNome().equals(craftingItem.getNome())) {
                if (Magazzino.getLog().getMassimoInseribile(materiale) <= 0) {
                    controllo = false;
                }
            }
        }
            
        if (controllo == true) {
            for (int i = 0; i < craftingItem.getOggettiRichiesti().length; i++) {
                BaseSpaziale.getLog().rimuoviQuantitativoOggetto(craftingItem.getOggettiRichiesti()[i], craftingItem.getNumeroOggetti()[i]);
            }
            for (Materiale materiale : Materiale.values()) {
                if (materiale.getNome().equals(craftingItem.getNome())) {
                    Integer percentualeDiSuccesso = craftingItem.getPercentualeSuccesso() + Esperienza.getLog().getAumentoPercentualeCraft();
                    if (percentualeDiSuccesso >= RangeRandom.MAX_PROB) {
                        percentualeDiSuccesso = RangeRandom.MAX_PROB;
                    } else if (percentualeDiSuccesso <= RangeRandom.MIN_PROB) {
                        percentualeDiSuccesso = RangeRandom.MIN_PROB;
                    }
                    if (RangeRandom.getSuccesso(percentualeDiSuccesso) == 1) { //se il crafting ha successo
                        Esperienza.getLog().aumentaEsperienza(RangeRandom.MAX_PROB - percentualeDiSuccesso);
                        BaseSpaziale.getLog().aggiungiQuantitativoOggetto(materiale, 1);
                        return 1;
                    } 
                    return 0; //Se non ha successo
                }
            }
        }
        return -1; //Se non ci sono oggetti
    }
}
