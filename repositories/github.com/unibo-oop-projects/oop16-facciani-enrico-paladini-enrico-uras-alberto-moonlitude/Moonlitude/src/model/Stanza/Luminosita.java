package model.Stanza;

import java.io.Serializable;

public enum Luminosita implements Serializable {
    SPENTA("Spenta", 0),
    BASSA("Bassa", 1),
    MEDIA("Media", 2),
    ALTA("Alta", 3),
    ALTISSIMA("Altissima", 5);
    
    private static final long serialVersionUID = -8979983670560730802L;
    private final String nome;
    private final Integer moltiplicatoreConsumo;
    
    private Luminosita(final String nome, final Integer moltiplicatoreConsumo) {
        this.nome = nome;
        this.moltiplicatoreConsumo = moltiplicatoreConsumo;
    }
    /**
     * Getter method for the name of the luminosity
     * @return the string value of the name of the luminosity
     */
    public String getNome() {
        return this.nome;
    }
    /**
     * Get wasting multiplier
     * @return an integer value representative of the wasting per hour of the generator energy
     */
    public Integer getMoltiplicatoreConsumo() {
        return this.moltiplicatoreConsumo;
    }
    
    public String toString() {
        return "L'illuminazione: " + this.nome + " consuma: " + this.moltiplicatoreConsumo;
    }

}
