package model.Stanza;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import model.BaseSpaziale;
import model.CondizioneAtmosferica;
import model.Astronauta.Astronauta;
import model.Oggetti.Materiale;

public class Filtratore implements Stanza, Serializable {
    private static final long serialVersionUID = -8979983411967730182L;
    private static final Integer ESPANSIONE = 1;
    private static final Integer ANNAFFIA = 5;
    private List<ContenitoreAcqua> contenitori = new LinkedList<>();
    private Integer acquaRaccolta = 0;
    
    private static class LazyHolder {
        private static final Filtratore SINGLETON = new Filtratore();
    }
    
    private Filtratore() {
        contenitori.add(new ContenitoreAcqua());
    }
    
    // Creo il SINGLETON alla prima chiamata
    public static Filtratore getLog() {
        return LazyHolder.SINGLETON;
    }
    /**
     * Getter of all water containers
     * @return a list of water containers
     */
    public List<ContenitoreAcqua> getContenitori() {
        return this.contenitori;
    }
    /**
     * Change all the containers status
     * @param condizioneAtmosferica Atmospheric condition that changes the status
     */
    public void cambiaStati(final CondizioneAtmosferica condizioneAtmosferica) {
        this.contenitori.forEach(e-> {
            if (e.getStato().getNome().equals(StatoAcqua.POTABILE.getNome())) { //switch the stated of the stored water in the filter just if the water was potable
                e.cambiaStatoAcqua(condizioneAtmosferica.getStatoAcqua());
            }
        });
    }
    /**
     * Method to fill the containers
     * @param ore hours passed
     * @param condizione Atmospheric condition that determinates the increase
     */
    public void passanoOre(final Integer ore, final CondizioneAtmosferica condizione) {
        this.contenitori.forEach(e -> {
            e.aggiungAcqua(ore * condizione.getAumentoAcqua());
        });
    }
    /**
     * Add a container
     */
    public void aggiungiContenitore() {
        if (BaseSpaziale.getLog().controllaOggetto(Materiale.CONTENITORE_VUOTO, ESPANSIONE)) {
            BaseSpaziale.getLog().rimuoviQuantitativoOggetto(Materiale.CONTENITORE_VUOTO, ESPANSIONE);
            this.contenitori.add(new ContenitoreAcqua());
        }
    }
    /**
     * Getter of drinkable water
     * @return drinkable water
     */
    public Integer getAcquaRaccolta() {
        return acquaRaccolta;
    }
    /**
     * Add some water to drinkable water
     * @param aggiunta Quantity to be added
     */
    public void aggiungiAcquaRaccolta(Integer aggiunta) {
        this.acquaRaccolta += aggiunta;
    }
    /**
     * Method to drink
     * @param astronauta that drinks the water
     * @param quanto quantity to be drunk
     */
    public void Bevi(final Astronauta astronauta, final Integer quanto) {
        if (quanto > this.acquaRaccolta) {
            System.out.println("Non c'e' abbastanza acqua");
        } else if (quanto < 0) {
            System.out.println("errore");
        } else {
            astronauta.getParametri().modificaSete(quanto);
            this.acquaRaccolta -= quanto;
        }
    }
    /**
     * Method used to lower the water in order to water a plant
     * @return whether or not it is possible to water a plant
     */
    public Boolean annaffia() {
        if(acquaRaccolta >= ANNAFFIA) {
            this.acquaRaccolta -= ANNAFFIA;
            return true;
        }
        return false;
    }
    /**
     * Setter method for gathered water
     * @param acqua
     */
    public void setAcquaRaccolta(final Integer acqua){
        this.acquaRaccolta = acqua;
    }
    /**
     * Setter method for the list of containers
     * @param contenitori list of containers
     */
    public void setListaContenitori(final List<ContenitoreAcqua> contenitori) {
        this.contenitori.clear();
        this.contenitori.addAll(contenitori);
    }
}
