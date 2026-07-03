package model.Oggetti;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Equipaggiamento implements Serializable{
    public static final String OSSIGENO = "Ossigeno";
    public static final String RISORSE = "Risorse";
    public static final String TEMPO = "Tempo";
    
    private static final long serialVersionUID = -8979983430567735812L;
    private TutaSpaziale tuta = TutaSpaziale.TUTA;
    private CommonEquipaggiamento equipaggio = CommonEquipaggiamento.PICCONE;
    
    private List<CommonEquipaggiamento> oggettiEquipaggiabili = new LinkedList<>();
    
    private Integer bonusOssigeno = 0;
    private Integer bonusRisorse = 0;
    private Integer bonusTempo = 0;
    
    private static class LazyHolder {
        private static final Equipaggiamento SINGLETON = new Equipaggiamento();
    }
    
    private Equipaggiamento() {
        setBonus();
    }
    
    // Creo il SINGLETON alla prima chiamata
    public static Equipaggiamento getLog() {
        return LazyHolder.SINGLETON;
    }
    
    public CommonEquipaggiamento getEquipaggio() {
        return this.equipaggio;
    }
    
    /**
     * Getter of the equipped suit
     * @return the equipped suit
     */
    public TutaSpaziale getTuta() {
        return this.tuta;
    }
    /**
     * Change suit
     * @param tuta suit to be changed
     */
    public void cambioTuta(final TutaSpaziale tuta) {
        if(tuta.getBonus().getBonus() > this.tuta.getBonus().getBonus()) {
            this.tuta = tuta;
            setBonus();
        }
    }
    /**
     * Get whether or not an object is present
     * @param oggetto object to be analyzed
     * @return whether or not is present
     */
    public Boolean presenzaOggetto(final Oggetto oggetto) {
        if (oggetto instanceof TutaSpaziale) {
            if (oggetto.getNome().equals(tuta.getNome())) {
                return true;
            } else {
                return false;
            }
        } else if (oggetto instanceof CommonEquipaggiamento) {
            if (this.equipaggio.getNome().equals(oggetto.getNome())) {
                return true;
            }
        }
        return false;
    }
    /**
     * Equip an object
     * @param equipaggiamento object to be equipped
     */
    public void aggiuntaEquipaggiamento(final CommonEquipaggiamento equipaggiamento) {
        inserisciInListaOggettiEquipaggiabili(this.equipaggio);
        this.oggettiEquipaggiabili.remove(equipaggiamento);
        this.equipaggio = equipaggiamento;
        this.setBonus();
    }
    /**
     * Method to insert an equipment in the stock
     * @param equipaggiamento equipment to be added
     */
    public void inserisciInListaOggettiEquipaggiabili (final CommonEquipaggiamento equipaggiamento){
        if(!this.oggettiEquipaggiabili.contains(equipaggiamento)) {
            this.oggettiEquipaggiabili.add(equipaggiamento);
        }
        
    }
    /**
     * Method to insert a list of equipments in the stock
     * @param equipaggiamento list of equipments to be added
     */
    public void inserisciInListaOggettiEquipaggiabili (final List<CommonEquipaggiamento> equipaggiamento){
        this.oggettiEquipaggiabili.addAll(equipaggiamento);
    }
    /**
     * Getter method for the stock items
     * @return the list of stocked equipments
     */
    public List<CommonEquipaggiamento> getOggettiEquipaggiabili () {
        return new LinkedList<CommonEquipaggiamento> (this.oggettiEquipaggiabili);
    }
    
    public String toString() {
        return "Tuta: " + this.tuta + "\nStrumento: " + this.equipaggio;
    }
    /**
     * Set all the bonuses
     */
    private void setBonus() {
        this.bonusOssigeno = getBonusOssigeno();
        this.bonusRisorse = getBonusRisorseOTempo(Equipaggiamento.RISORSE);
        this.bonusTempo = getBonusRisorseOTempo(Equipaggiamento.TEMPO);
    }
    /**
     * Get the new oxygen bonus
     * @return the integer value of the oxygen bonus
     */
    public Integer getBonusOssigeno() {
        return this.tuta.getBonus().getBonus();
    }
    /**
     * Get new Time or Resources bonuses
     * @param ricerca Bonus to be analyzed
     * @return the integer value of the bonus
     */
    public Integer getBonusRisorseOTempo(final String ricerca) {
        if (ricerca.equals(Equipaggiamento.TEMPO) || ricerca.equals(Equipaggiamento.RISORSE)) {
            if (this.equipaggio.getBonus().getFocusEffetto().equals(ricerca)) {
                return this.equipaggio.getBonus().getBonus();
            }
        }
        return 0;
    }
    /**
     * Getter of the current oxygen bonus
     * @return the integer value of the bonus
     */
    public Integer getOssigeno() {
        return this.bonusOssigeno;
    }
    /**
     * Getter of the current resource bonus
     * @return the integer value of the bonus
     */
    public Integer getRisorse() {
        return this.bonusRisorse;
    }
    /**
     * Getter of the current Time bonus
     * @return the integer value of the bonus
     */
    public Integer getTempo() {
        return this.bonusTempo;
    }
    
}
