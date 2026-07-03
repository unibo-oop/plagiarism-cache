package model.Stanza;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import model.BaseSpaziale;
import model.Oggetti.Materiale;

public class Giardino implements Stanza, Serializable {
    private static final long serialVersionUID = -8979980987567730812L;
    private static final Integer ESPANSIONE = 10;
    private Integer slots;
    List<Pianta> piante = new LinkedList<>();
    
    private static class LazyHolder {
        private static final Giardino SINGLETON = new Giardino(6);
    }
    
    private Giardino(final Integer slots) {
        this.slots = slots;
    }
    
    // Creo il SINGLETON alla prima chiamata
    public static Giardino getLog() {
        return LazyHolder.SINGLETON;
    }
    /**
     * Getter of garden's space
     * @return the space of the garden
     */
    public Integer getSlots() {
        return this.slots;
    }
    /**
     * Increase garden space
     * @return whether or not it is possible to increase the slots
     */
    public Boolean aggiungiSlots() {
        if (BaseSpaziale.getLog().controllaOggetto(Materiale.MATTONE, ESPANSIONE)) {
            BaseSpaziale.getLog().rimuoviQuantitativoOggetto(Materiale.MATTONE, ESPANSIONE);
            this.slots ++;
            return true;
        }
        return false;
    }
    /**
     * Method to increase slots without using any item.
     * Used for the "Upload Game"
     */
    public void aggiungiSlots(final Integer dimensione) {
        this.slots += dimensione;
    }
    /**
     * Pass some hours
     * @param ore hours that passed
     */
    public void passanoOre(final Integer ore) {
        piante.forEach(el -> {
            el.incrementaOre(ore);
        });
    }
    /**
     * Add a plant into the garden
     * @param pianta plant to be added
     */
    public void aggiungiPianta(final Pianta pianta) {
        if (piante.size() < slots) {
            piante.add(pianta);
        }
    }
    /**
     * Gather if possible a plant
     * @param slot slot to be gathered
     * @return true = plant gathered, false = plant not gathered
     */
    public Boolean prendiPianta(final Integer slot) {
        if (slot <= slots && slot >= 0) {
            Pianta tmpPianta = piante.get(slot);
            if (tmpPianta.getStato().equals(StatoPianta.MORTA) || tmpPianta.getStato().equals(StatoPianta.MATURA) || tmpPianta.getStato().equals(StatoPianta.MARCIA)) {
                piante.remove(piante.get(slot));
                BaseSpaziale.getLog().aggiungiQuantitativoOggetto(tmpPianta.getCommonPianta().getCibo(), tmpPianta.getCommonPianta().getProdotti());
                return true;
            }
        }
        return false;
    }
    /**
     * Water a plant
     * @param slot slot to be watered
     * @return 1 = SUCCESS, 0 = Plant already watered, -1 = Plant cannot be watered, -2 = Not enough water
     */
    public Integer annaffiaPianta(final Integer slot) {
        return this.piante.get(slot).annaffia();
    }
    /**
     * Getter of all the plants of the garden
     * @return a list of planted plants
     */
    public List<Pianta> getPiante() {
        return new LinkedList<Pianta> (piante);
    }
    /**
     * Setter method for the list of plants
     * @param piante list of plants
     */
    public void setPiante(final List<Pianta> piante) {
        this.piante.clear();
        this.piante.addAll(piante);
    }
    
    public String toString() {
        return "Nel giardino ci sono:\n" + this.piante.stream().map(m -> m.toString()).collect(Collectors.joining("\n", "[ ", " ]"));
    }

}
