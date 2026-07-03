package model.Stanza;

import java.io.Serializable;

import model.BaseSpaziale;
import model.Oggetti.Materiale;

public class Generatore implements Stanza, Serializable {
    private static final long serialVersionUID = -8799983484567703812L;
    private static final Double MAX = 100.0;
    private static final Double MIN = 0.0;
    private static final Double REDUCTION = 0.5;
    private Luminosita lux = Luminosita.SPENTA;
    
    private Double carica = MAX;
    
    private static class LazyHolder {
        private static final Generatore SINGLETON = new Generatore();
    }
    
    private Generatore() {
    }
    
    // Creo il SINGLETON alla prima chiamata
    public static Generatore getLog() {
        return LazyHolder.SINGLETON;
    }
    /**
     * Reduce the charge of the generator
     */
    public void riduciCarica() {
        this.carica -= lux.getMoltiplicatoreConsumo() * REDUCTION;
        if (this.carica <= MIN) {
            this.carica = MIN;
            lux = Luminosita.SPENTA;
        }
    }
    /**
     * Recharges the generator for a given amount
     * @return the boolean value whether or not the recharging is possible
     */
    public Boolean riCarica() {
        if (Magazzino.getLog().getQuantitaOggetto(Materiale.BATTERIA) > 0 && this.carica < MAX) {
            this.carica += 20;
            BaseSpaziale.getLog().rimuoviQuantitativoOggetto(Materiale.BATTERIA, 1);
            if (this.carica > MAX) {
                this.carica = MAX;
            }
            return true;
        }
        return false;
    }
    
    
    /**
     * Setter of the current luminosity
     * @param lux luminosity to be setted
     */
    public void setLuminosita(final Luminosita lux) {
        this.lux = lux;
    }
    /**
     * Getter method for the luminosity
     * @return the current luminosity in the base station
     */
    public Luminosita getLuminosita() {
        return this.lux;
    }
    /**
     * Getter method for generator charge
     * @return the charge
     */
    public Double getCarica() {
        return this.carica;
    }
    /**
     * Setter method for generator charge
     * @param carica charge
     */
    public void setCarica(final Double carica) {
        this.carica = carica;
    }
    public String toString() {
        return "Attualmente la luce del generatore e': " + this.lux.getNome() + " ha rimasto: " + this.carica + "/" + MAX;
    }

}
