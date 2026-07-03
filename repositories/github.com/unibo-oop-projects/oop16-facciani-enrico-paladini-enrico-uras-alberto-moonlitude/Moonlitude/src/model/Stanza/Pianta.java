package model.Stanza;

import java.io.Serializable;

public class Pianta implements Serializable{
    
    private static final long serialVersionUID = -8979980010567950812L;
    private final CommonPianta commonPianta;
    private Integer orePiantata = 0;
    private Integer oreSenzaAcqua = 0;
    private Integer oreSottoLuxGiusta = 0;
    private StatoPianta stato = StatoPianta.VIVA;
    private Boolean annaffiata = false;
    
    public Pianta(final CommonPianta commonPianta) {
        this.commonPianta = commonPianta;
    }
    public Pianta(final CommonPianta commonPianta,final Integer orePiantata, final Integer oreSenzaAcqua, final Integer oreSottoLuxGiusta, final StatoPianta stato, final Boolean annaffiata) {
        this.commonPianta = commonPianta;
        this.orePiantata = orePiantata;
        this.oreSenzaAcqua = oreSenzaAcqua;
        this.oreSottoLuxGiusta = oreSottoLuxGiusta;
        this.stato = stato;
        this.annaffiata = annaffiata;
    }
    /**
     * Getter of the hours without water
     * @return the integer value of the hours passed
     */
    public Integer getOreSenzaAcqua() {
        return this.oreSenzaAcqua;
    }
    /**
     * Getter of the hours passed by the time the plant has been planted
     * @return the integer value of the hours passed
     */
    public Integer getOrePiantata() {
        return this.orePiantata;
    }
    /**
     * Getter of the plant associated
     * @return the CommonPianta associated
     */
    public CommonPianta getCommonPianta() {
        return this.commonPianta;
    }
    /**
     * Increase the hour since when the plant has been planted
     * @param incremento amount of time passed
     */
    public void incrementaOre(Integer incremento) {
        this.orePiantata += incremento;
        if (commonPianta.getLux().getNome().equals(Generatore.getLog().getLuminosita().getNome())) {
            oreSottoLuxGiusta += incremento;
        }
        if (!this.annaffiata && this.orePiantata > this.commonPianta.getOreAcqua()) {
            this.oreSenzaAcqua = this.orePiantata - this.commonPianta.getOreAcqua();
        }
        fixStato();
    }
    /**
     * Method to fiz plant status by hours and conditions
     */
    private void fixStato() {
        if (this.oreSenzaAcqua >= this.commonPianta.getOreMorte()) {
            this.stato = StatoPianta.MORTA;
        }
        else if (!this.stato.equals(StatoPianta.MORTA) && this.orePiantata >= this.commonPianta.getOreMarcitura()) {
            this.stato = StatoPianta.MARCIA;
        }
        else if (!this.stato.equals(StatoPianta.MORTA) && !this.stato.equals(StatoPianta.MARCIA) && this.orePiantata >= this.commonPianta.getOreMaturazione() - oreSottoLuxGiusta && this.annaffiata.equals(true)) {
            this.stato = StatoPianta.MATURA;
        }
    }
    /**
     * Getter of plant status
     * @return the status of the plant
     */
    public StatoPianta getStato() {
        return this.stato;
    }
    /**
     * Waters the plant using the water collected in the Filter
     * @return 1 = SUCCESS, 0 = Plant already watered, -1 = Plant cannot be watered, -2 = Not enough water
     */
    public Integer annaffia() {
        if (!annaffiata) {
            if (getOreDaAnnaffiare() == 0) {
                this.oreSenzaAcqua = 0;
                if (Filtratore.getLog().annaffia().equals(true)) {
                    this.annaffiata = true;
                    fixStato();
                    return 1;
                } else {
                    return -2; //Non c'è abbastanza acqua
                }
            } else {
                return -1; //Non puoi annaffiare questa pianta
            }
        } else {
            return 0; //La pianta è già stata annaffiata
        }
    }
    /**
     * Get whether or not the plant has been watered
     * @return whether or not the plant has been watered
     */
    public Boolean getAnnaffiata() {
        return this.annaffiata;
    }
    /**
     * Get how many hours till the plant needs to be watered
     * @return the integer amount of hours
     */
    public Integer getOreDaAnnaffiare() {
        if (this.annaffiata.equals(true)) {
            return -1;
        }
        
        Integer ore = this.commonPianta.getOreAcqua() - this.orePiantata;
        if (ore < 0) {
            ore = 0;
        }
        return ore;
    }
    
    public String toString() {
        Integer oreMaturazione = (commonPianta.getOreMaturazione() - this.orePiantata) - this.oreSottoLuxGiusta;
        Integer oreMarcitura = commonPianta.getOreMarcitura() - this.orePiantata;
        Integer annaffiatura = this.commonPianta.getOreAcqua() - this.orePiantata;
        if (oreMaturazione < 0 || this.stato.equals(StatoPianta.MORTA)) {
            oreMaturazione = 0;
        }
        if (oreMarcitura < 0 || this.stato.equals(StatoPianta.MORTA)) {
            oreMarcitura = 0;
        }
        if (annaffiatura < 0 || this.stato.equals(StatoPianta.MORTA) || this.stato.equals(StatoPianta.MARCIA)) {
            annaffiatura = 0;
        }
        return this.commonPianta.getNome() + " Stato: " + this.stato.getStato() + " Ore Maturazione: " + oreMaturazione + " Ore Marcitura: " + oreMarcitura + " Da annaffiare fra: " + annaffiatura;
    }

}
