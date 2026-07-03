package model.Stanza;

import java.io.Serializable;

public class ContenitoreAcqua implements Serializable{
    private static final long serialVersionUID = -8979983480511120812L;
    private static final Integer MIN_ACQUA = 0;
    private static final Integer DIMENSIONE = 150;
    private Integer riempimento = MIN_ACQUA;
    private StatoAcqua stato = StatoAcqua.POTABILE;
    
    public ContenitoreAcqua() {
    }
    
    public ContenitoreAcqua(final Integer riempimento, final StatoAcqua stato) {
        this.riempimento = riempimento;
        this.stato = stato;
    }
    /**
     * Get the water
     * @return the amount of collected water
     */
    public Integer spostaAcqua() {
        Integer tmpRiempimento = this.riempimento;
        this.riempimento = MIN_ACQUA;
        
        if (stato.toString().equals(StatoAcqua.POTABILE.toString())) {
            return tmpRiempimento;
        } else if (stato.toString().equals(StatoAcqua.INSABBIATA.toString())) {
            this.stato = StatoAcqua.POTABILE;
            return tmpRiempimento / 2 ;
        } else {
            this.stato = StatoAcqua.POTABILE;
            return MIN_ACQUA;
        }
    }
    /**
     * Add the water into the container
     * @param aggiunta amount of water to be added
     */
    public void aggiungAcqua(final Integer aggiunta) {
        this.riempimento += aggiunta;
        if (this.riempimento > DIMENSIONE) {
            this.riempimento = DIMENSIONE;
        }
    }
    /**
     * Change water status
     * @param stato status of the water
     */
    public void cambiaStatoAcqua(final StatoAcqua stato) {
        this.stato = stato;
    }
    /**
     * Getter method for water state
     * @return water current state
     */
    public StatoAcqua getStato() {
        return this.stato;
    }
    /**
     * Getter method for the water
     * @return the amount of water stored
     */
    public Integer getRiempimento() {
        return this.riempimento;
    }
    
    public String toString() {
        return "L'acqua contenuta e': " + this.riempimento + "/" + DIMENSIONE + " ed e': " + this.stato.getNome();
    }
}
