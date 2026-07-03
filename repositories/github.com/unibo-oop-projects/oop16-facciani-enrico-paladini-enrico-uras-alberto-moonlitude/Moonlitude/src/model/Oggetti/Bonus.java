package model.Oggetti;
/**
 * Bonus associated with the equipment
 */
public enum Bonus {
    TUTA("Tuta base", Equipaggiamento.OSSIGENO, 0),
    MATERIALI_BASSO("Bonus Materiali 20%", Equipaggiamento.RISORSE, 20),
    MATERIALI_MEDIO("Bonus Materiali 40%", Equipaggiamento.RISORSE, 40),
    TEMPO("+1 ora bonus di Esplorazione", Equipaggiamento.TEMPO, 1),
    TUTA_RINFORZATA("Bonus spreco ossigeno -10%", Equipaggiamento.OSSIGENO, 1);
    
    private final String descrizione;
    private final String focusEffetto;
    private final Integer bonus;
    
    private Bonus(final String descrizione, final String focusEffetto, final Integer bonus) {
        this.descrizione = descrizione;
        this.focusEffetto = focusEffetto;
        this.bonus = bonus;
        
    }
    /**
     * Getter method for description
     * @return the value of description
     */
    public String getDescrizione() {
        return this.descrizione;
    }
    /**
     * Getter method for the value bonus
     * @return the integer value of the bonus
     */
    public Integer getBonus() {
        return this.bonus;
    }
    /**
     * Getter method for the effect
     * @return the string representative of the target
     */
    public String getFocusEffetto() {
        return this.focusEffetto;
    }
    
    public String toString() {
        return "Effetto: " + this.descrizione;
    }

}
