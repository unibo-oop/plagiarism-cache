package model.Oggetti;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Zaino implements Serializable{
    private static final long serialVersionUID = -8971983488567740812L;
    
    private static final Integer MAX = 100;
    private static final Integer SPAZIO = 5;
    Map<Oggetto,Integer> mappaOggetti = new HashMap<>();
    
    
    private static class LazyHolder {
        private static final Zaino SINGLETON = new Zaino();
    }
    
    // Creo il SINGLETON alla prima chiamata
    public static Zaino getLog() {
        return LazyHolder.SINGLETON;
    }
    
    private Zaino() {
    }
    
    /**
     * Getter method for the slots of object of the container
     * @return the list of slots of object of the container
     */
    public Map<Oggetto,Integer> getOggetti() {
        return new HashMap<>(this.mappaOggetti);
    }
    
    /**
     * Get the amount of a given object collected in the container
     * @param oggetto object that has to be analyzed
     * @return the integer value of the amount of the given object currently inside the container
     */
    public Integer getQuantitaOggetto(final Oggetto oggetto){
        if (mappaOggetti.containsKey(oggetto)) {
            return this.mappaOggetti.get(oggetto);
        }
        return 0;
    }
    
    /**
     * Get the maximum insertable amount of an object
     * @param oggetto object to be inserted
     * @return the amount of the given object that can be inserted
     */
    public Integer getMassimoInseribile(final Oggetto oggetto) {
        return MAX - this.getQuantitaOggetto(oggetto);
    }
    /**
     * Method to determinate the amount to be computed into the map value
     * @param quantoPresente amount of the object
     * @param quanti amount to be inserted
     * @return the amount to be computed
     */
    private Integer fixAggiuntaNuovoQuanto(final Integer quantoPresente, final Integer quanti) {
        Integer nuovoQuanto = quantoPresente + quanti;
        if (nuovoQuanto > MAX) {
            nuovoQuanto = MAX;
        }
        return nuovoQuanto;
    }
    
    /**
     * Method to determinate the amount to be computed into the map value
     * @param quantoPresente amount of the object
     * @param quanti amount to be inserted
     * @return the amount to be computed
     */
    private Integer fixRimozioneNuovoQuanto(final Integer quantoPresente, final Integer quanti) {
        Integer nuovoQuanto = quantoPresente - quanti;
        if (nuovoQuanto < 0) {
            nuovoQuanto = 0;
        }
        return nuovoQuanto;
    }
    /**
     * Method to determinate whether or not there is enough space to insert an item
     * @param oggetto object to be inserted
     * @return the boolean value
     */
    public Boolean inserimentoPossibile(final Oggetto oggetto) {
        if (this.mappaOggetti.containsKey(oggetto)) {
            return true;
        } else if (this.mappaOggetti.size() < SPAZIO) {
            return true;
        }
        return false;
    }
    /**
     * Add a given object
     * @param oggetto object to be added
     * @param quanti amount of the object that has to be added
     * @return the amount of item not inserted
     */
    public Integer aggiungiOggetto(final Oggetto oggetto, final Integer quanti) {
        Integer rimanenza = getMassimoInseribile(oggetto) - quanti;
        
        Integer nuovoQuanto = fixAggiuntaNuovoQuanto(this.getQuantitaOggetto(oggetto), quanti);
        if (inserimentoPossibile(oggetto)) {
            if(mappaOggetti.containsKey(oggetto)) {
                mappaOggetti.compute(oggetto, (k,v) -> v = nuovoQuanto);
            } else {
                mappaOggetti.put(oggetto, nuovoQuanto);
            }
        } else {
            rimanenza = MAX +1;
        }
        return rimanenza;
    }
    /**
     * Remove a given object
     * @param oggetto object to be removed
     * @param quanti amount of the object to be removed
     */
    public void rimuoviOggetto(final Oggetto oggetto, final Integer quanti) {
        if(this.getQuantitaOggetto(oggetto) > 0) {
            Integer nuovoQuanto = fixRimozioneNuovoQuanto(this.getQuantitaOggetto(oggetto), quanti);
            if(nuovoQuanto > 0) {
                mappaOggetti.compute(oggetto, (k,v) -> v = nuovoQuanto);
            } else {
                mappaOggetti.remove(oggetto);
            }
        }
    }
    
    /**
     * Setter method for the items
     * @param oggetti list of slots
     */
    public void setMappaOggetti(final Map<Oggetto,Integer> oggetti) {
        this.mappaOggetti = oggetti;
    }
    

    /**
     * Exchange one item and its amount with one other item
     * @param oggettoDaRicevere object to be collected
     * @param quanto amount of the object to be collected
     * @param oggettoDaButtare object to be discarded
     */
    public void scambio(final Oggetto oggettoDaRicevere, final Integer quanto, final Oggetto oggettoDaButtare) {
        this.rimuoviOggetto(oggettoDaButtare, getQuantitaOggetto(oggettoDaButtare));
        this.aggiungiOggetto(oggettoDaRicevere, quanto);
    }
    
    @Override
    public String toString() {        
        return mappaOggetti.toString();
    }
}
