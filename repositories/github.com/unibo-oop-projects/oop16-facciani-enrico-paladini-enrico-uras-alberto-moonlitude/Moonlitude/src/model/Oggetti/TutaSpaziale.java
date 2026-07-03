package model.Oggetti;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import model.RangeRandom;

public enum TutaSpaziale implements Oggetto, Serializable {
    TUTA("Tuta", Bonus.TUTA, 0, 1),
    TUTA_RINFORZATA("Tuta Rinforzata", Bonus.TUTA_RINFORZATA, 10, 2);
    
    private static final long serialVersionUID = -8979983411567730811L;
    private final String nome;
    private final Bonus bonus;
    private final Integer probabilita;
    private final Integer grado;
    
    private TutaSpaziale(final String nome, final Bonus bonus, final Integer probabilita, final Integer grado) {
        this.nome = nome;
        this.bonus = bonus;
        this.probabilita = probabilita;
        this.grado = grado;
    }

    @Override
    public String getNome() {
        return this.nome;
    }
    /**
     * Getter method for the bonus
     * @return the bonus of the suit
     */
    public Bonus getBonus() {
        return this.bonus;
    }
    
    public String toString() {
        return this.nome;
    }
    /**
     * Getter method for the probability that has the item to be found
     * @return the probability
     */
    public Integer getProbabilita() {
        return probabilita;
    }
    /**
     * method to get if the item will be found given a percentage increase
     * @return a map with objects found
     */
    public static Map<TutaSpaziale, Integer> verificati() {
        Map<TutaSpaziale, Integer> oggettiOttenuti = new HashMap<>();
        int probabilita = 0;
        for (TutaSpaziale tuta : TutaSpaziale.values()) {
            probabilita = tuta.getProbabilita();
            if (RangeRandom.getSuccesso(probabilita) > 0 && tuta.getGrado() > Equipaggiamento.getLog().getTuta().getGrado()) { //Si possono trovare solo tute migliori
                oggettiOttenuti.put(tuta, 1);
            }
        }
        return oggettiOttenuti; 
    }
    /**
     * Getter method to get item rank
     * @return the integer value of the rank
     */
    public Integer getGrado() {
        return grado;
    }

}
