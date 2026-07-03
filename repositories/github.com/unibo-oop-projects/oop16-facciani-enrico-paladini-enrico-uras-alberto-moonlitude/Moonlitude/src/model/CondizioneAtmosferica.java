package model;

import java.io.Serializable;

import model.Stanza.StatoAcqua;

public enum CondizioneAtmosferica implements Serializable {
    PIOGGIA("Pioggia", "Inizia a piovere", 26, StatoAcqua.POTABILE, 11),
    PIOGGIA_ACIDA("Pioggia_Acida", "Inizia la pioggia acida", 5, StatoAcqua.ACIDA, 0),
    ACQUAZZONE("Acquazzone", "Inizia a piovere forte", 11, StatoAcqua.POTABILE, 15),
    TEMPESTA_SABBIA("Tempesta_Sabbia", "Tempesta di sabbia", 10, StatoAcqua.INSABBIATA, 0),
    CALMO("Calmo", "Le condizioni atmosferiche sono stabili", 48, StatoAcqua.POTABILE, 7);
    
    private static final long serialVersionUID = -8985023480567730812L;
    private final String nome;
    private final String descrizione;
    private final Integer probabilita;
    private final StatoAcqua statoAcqua;
    private final Integer aumentoAcqua;
    
    private CondizioneAtmosferica(final String nome, final String descrizione, final Integer probabilita, final StatoAcqua statoAcqua, final Integer aumentoAcqua) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.probabilita = probabilita;
        this.statoAcqua = statoAcqua;
        this.aumentoAcqua = aumentoAcqua;
    }
    /**
     * Getter method for the name of the atmospheric conditions
     * @return the string value of the name of the atmospheric conditions
     */
    public String getNome() {
        return this.nome;
    }
    /**
     * Getter method for the percentage of the atmospheric conditions
     * @return the integer value of the atmospheric conditions
     */
    public Integer getProbabilita() {
        return this.probabilita;
    }
    /**
     * Get percentages of each atmospheric condition
     * @return the array of integers of the percentages of each atmospheric condition
     */
    public static Integer[] getProbabilitaAssociate() {
        Integer[] probabilitaAssociate = new Integer[CondizioneAtmosferica.values().length];
        Integer i = 0;
        for (CondizioneAtmosferica cond : CondizioneAtmosferica.values()) {
            probabilitaAssociate[i] = cond.getProbabilita();
            i++;
        }
        return probabilitaAssociate;
    }
    /**
     * Getter method for the description of the atmospheric condition
     * @return the string value of the description of the atmospheric condition
     */
    public String getDescrizione() {
        return this.descrizione;
    }
    /**
     * Getter method for associated water state
     * @return the water state associated
     */
    public StatoAcqua getStatoAcqua() {
        return statoAcqua;
    }
    /**
     * Getter method for the amount of water increased per hour
     * @return the amount of water added in the filter per hour
     */
    public Integer getAumentoAcqua() {
        return aumentoAcqua;
    }

}
