package model.Oggetti;

import java.util.Map;
import java.util.TreeMap;

import model.BaseSpaziale;

public abstract class Contenitore<E extends Oggetto>{
    private static final Integer MAX = 100;
    private Integer spazio = 0;
    private Map<E,Integer> mappaOggetti = new TreeMap<>();
    private static final Integer ESPANSIONE = 10;
    /**
     * Increases container space
     * @param aumento amount of increase
     */
    protected void aumentaSpazio(final Integer aumento) {
        this.spazio += aumento;
    }
    
    /**
     * Increases container space
     * @return whether or not it is possible to increase the slots
     */
    protected Boolean aumentaSpazio() {
        if (BaseSpaziale.getLog().controllaOggetto(Materiale.MATTONE, ESPANSIONE)) {
            BaseSpaziale.getLog().rimuoviQuantitativoOggetto(Materiale.MATTONE, ESPANSIONE);
            this.spazio ++;
            return true;
        }
        return false;
    }
    /**
     * Getter method for the slots of object of the container
     * @return the list of slots of object of the container
     */
    public Map<E,Integer> getOggetti() {
        return new TreeMap<>(this.mappaOggetti);
    }
    
    /**
     * Get the amount of a given object collected in the container
     * @param oggetto object that has to be analyzed
     * @return the integer value of the amount of the given object currently inside the container
     */
    public Integer getQuantitaOggetto(final E oggetto){
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
    public Integer getMassimoInseribile(final E oggetto) {
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
    public Boolean inserimentoPossibile(final E oggetto) {
        if (this.mappaOggetti.containsKey(oggetto)) {
            return true;
        } else if (this.mappaOggetti.size() < this.spazio) {
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
    public Integer aggiungiOggetto(final E oggetto, final Integer quanti) {
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
    public void rimuoviOggetto(final E oggetto, final Integer quanti) {
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
    public void setMappaOggetti(final Map<E,Integer> oggetti) {
        this.mappaOggetti = oggetti;
    }
    
    /**
     * Getter method for Container space
     * @return the integer value
     */
    public Integer getSpazio() {
        return this.spazio;
    }
    /**
     * Setter method for Container space
     * @param spazio space to be saved
     */
    public void setSpazio(final Integer spazio) {
        this.spazio = spazio;
    }
    
    /**
     * Getter method for max insertable of an item
     * @return the integer value
     */
    public static Integer getMax() {
        return MAX;
    }
    
    @Override
    public String toString() {        
        return mappaOggetti.toString();
    }
}
