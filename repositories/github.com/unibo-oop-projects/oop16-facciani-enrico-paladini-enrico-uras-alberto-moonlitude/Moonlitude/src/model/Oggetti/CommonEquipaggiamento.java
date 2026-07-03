package model.Oggetti;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import model.RangeRandom;

public enum CommonEquipaggiamento implements Oggetto, Serializable {
    STIVALI("Stivali", Bonus.TEMPO, 10),
    PICCONE("Piccone", Bonus.MATERIALI_BASSO, 12),
    PICCONE_FERRO("Piccone di ferro", Bonus.MATERIALI_MEDIO, 5);
    
    private static final long serialVersionUID = -8923983484567630812L;
    private final String nome;
    private final Bonus bonus;
    private final Integer probabilita;
    
    private CommonEquipaggiamento(final String nome, final Bonus bonus, final Integer probabilita) {
        this.nome = nome;
        this.bonus = bonus;
        this.probabilita = probabilita;
    }

    @Override
    public String getNome() {
        return this.nome;
    }
    /**
     * Getter method for the bonus
     * @return the bonus given by the object
     */
    public Bonus getBonus() {
        return this.bonus;
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
    public static Map<CommonEquipaggiamento, Integer> verificati() {
        Map<CommonEquipaggiamento, Integer> oggettiOttenuti = new HashMap<>();
        int probabilita = 0;
        for (CommonEquipaggiamento equip : CommonEquipaggiamento.values()) {
            probabilita = equip.getProbabilita();
            if (RangeRandom.getSuccesso(probabilita) > 0) {
                oggettiOttenuti.put(equip, 1);
            }
        }
        return oggettiOttenuti; 
    }

}
