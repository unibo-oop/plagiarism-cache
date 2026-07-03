package model.Astronauta;

import java.io.Serializable;

public class Esperienza implements Serializable {
    private static final long serialVersionUID = -8976023480561120812L;
    private static final Integer MIN_LIV = 1;
    private static final Integer MAX_LIV = 10;
    private static final Integer EXP_PER_LIV = 200;
    private static final Integer MIN_EXP = 0;
    
    private Integer livello = MIN_LIV;
    private Integer esperienza = MIN_EXP;
    
    private static class LazyHolder {
        private static final Esperienza SINGLETON = new Esperienza();
    }
    
    private Esperienza() {
    }
    
    // Creo il SINGLETON alla prima chiamata
    public static Esperienza getLog() {
        return LazyHolder.SINGLETON;
    }
    /**
     * Increase crafting experience
     * @param aumento amount of the increase
     */
    public void aumentaEsperienza (final Integer aumento) {
        if (this.livello < MAX_LIV) {
            esperienza += aumento;
            fixEsperienza();
        }
    }
    /**
     * Fix level and experience
     */
    private void fixEsperienza() {
        if (this.livello < MAX_LIV && esperienza >= EXP_PER_LIV) {
            esperienza -= EXP_PER_LIV;
            livello ++;
            if (livello == MAX_LIV) {
                esperienza = EXP_PER_LIV;
            }
        }
    }
    /**
     * Getter of the percentage permanent increase of the crafting
     * @return the integer value of the percentage
     */
    public Integer getAumentoPercentualeCraft() {
        return this.livello - 1;
    }
    
    public String toString() {
        return "Livello: " + this.livello + " al prossimo livello: " + (EXP_PER_LIV - this.esperienza);
    }
    /**
     * Getter of the crafting level
     * @return the integer value of the level
     */
    public Integer getLivello() {
        return this.livello;
    }
    /**
     * Getter of the current crafting level experience
     * @return the integer value of the experience
     */
    public Integer getEsperienza() {
        return this.esperienza;
    }
    /**
     * Setter method for the level
     * @param livello level
     */
    public void setLivello(final Integer livello) {
        this.livello = livello;
    }
    /**
     * Setter method for the experience
     * @param esperienza experience
     */
    public void setEsperienza(final Integer esperienza) {
        this.esperienza = esperienza;
    }
}
