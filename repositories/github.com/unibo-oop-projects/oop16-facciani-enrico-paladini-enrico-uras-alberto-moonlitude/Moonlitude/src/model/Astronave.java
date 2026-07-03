package model;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.io.Serializable;

import model.Oggetti.Materiale;
import model.Oggetti.Oggetto;
import model.Stanza.Magazzino;

public class Astronave implements Serializable {
    private static final long serialVersionUID = -8985023480526620812L;

    private static final Integer SCUDI_NECESSARI = 3600; //amount of shield needed to leave the planet safely
    
    private static final Map<Materiale,Integer> pezziAstronave = new TreeMap<>();
    private static final Map<Materiale,Integer> probabilitaDistrutto = new TreeMap<>();
    private Map<Materiale,Integer> pezziDanneggiati = new TreeMap<>();
    private Integer scudi;
    
    private static class LazyHolder {
        private static final Astronave SINGLETON = new Astronave();
    }
    
    private Astronave() {
        initAstronave();
    }
    
    // Creo il SINGLETON alla prima chiamata
    public static Astronave getLog() {
        return LazyHolder.SINGLETON;
    }
    /**
     * Initialize the maps of:
     *          Possible damaged materials
     *          Associated percentages
     */
    private void initMappe() {
        Random r = new Random();
        scudi = SCUDI_NECESSARI - (r.nextInt(50)+50)*10;
        pezziAstronave.put(Materiale.COMPUTER, 1);
        probabilitaDistrutto.put(Materiale.COMPUTER, 100);
        
        pezziAstronave.put(Materiale.GENERATORE, 1);
        probabilitaDistrutto.put(Materiale.GENERATORE, 90);
        
        pezziAstronave.put(Materiale.PROPULSORE, 1);
        probabilitaDistrutto.put(Materiale.PROPULSORE, 90);
        
        pezziAstronave.put(Materiale.MOTORE_PRINCIPALE, 2);
        probabilitaDistrutto.put(Materiale.MOTORE_PRINCIPALE, 80);
    }
    /**
     * Initialize the broken materials of the spaceship
     */
    private void initAstronave() {
        initMappe();
        pezziAstronave.entrySet().stream().forEach(e -> {
            Integer quanto = randomDistruzione(e.getKey());
            if (quanto > 0) {
                pezziDanneggiati.put(e.getKey(), quanto);
            }
        });
    }
    /**
     * Random counts the amount of damaged components of a kind
     * @param oggetto object to be searched
     * @return the amount of damaged object
     */
    private Integer randomDistruzione(final Oggetto oggetto) {
        Integer numeroDistrutti = 0;
        for (int i = 0; i < pezziAstronave.get(oggetto); i++) {
            if (RangeRandom.getSuccesso(probabilitaDistrutto.get(oggetto)).equals(1)) {
                numeroDistrutti++;
            }
        }
        return numeroDistrutti;
    }
    /**
     * Shows the broken components
     * @return a string value representative of broken pieces
     */
    public String mostraPezziRotti() {
        return pezziDanneggiati.entrySet().stream().map(m -> m.getKey().getNome() + " x" + m.getValue()).collect(Collectors.joining(", ", "[ ", " ]"));
    }
    /**+
     * Getter method for the shield value
     * @return the integer value of the shield
     */
    public Integer getScudo() {
        return this.scudi;
    }
    /**
     * Recharge the shield
     * @param materiale material to be used to recharge the shield
     * @param quanto amount of the material to be used
     * @return the boolean value whether or not the recharging is possible
     */
    public Boolean ricaricaScudi(final Materiale materiale, final Integer quanto) {
        if (materiale.getNome().equals("Sfera di energia") && BaseSpaziale.getLog().getQuantitativoOggetto(Materiale.SFERA_ENERGIA) >= 1 && scudi < SCUDI_NECESSARI) {
            scudi += 100*quanto;
            if (scudi > SCUDI_NECESSARI) {
                scudi = SCUDI_NECESSARI;
            }
            BaseSpaziale.getLog().rimuoviQuantitativoOggetto(Materiale.SFERA_ENERGIA, quanto);
            return true;
        }
        return false;
    }
    /**
     * Method to repair a component of the spaceship
     * @param materiale material to be repaired
     * @return the boolean value whether or not the repairing worked
     */
    public Boolean ripara(final Materiale materiale) {
        Integer inMagazzino = Magazzino.getLog().getQuantitaOggetto(materiale);
        if (inMagazzino >= this.pezziDanneggiati.get(materiale)) {
            BaseSpaziale.getLog().rimuoviQuantitativoOggetto(materiale, this.pezziDanneggiati.get(materiale));
            this.pezziDanneggiati.remove(materiale);
            return true;
        }
        return false;
    }
    /**
     * Getter method for damaged pieces
     * @return the map of the pieces
     */
    public Map<Materiale,Integer> getPezziDanneggiati() {
        Map<Materiale,Integer> returnMap = new TreeMap<>();
        returnMap.putAll(this.pezziDanneggiati);
        return returnMap;
    }
    /**
     * Setter method for damaged materials
     * @param pezzi map of damagedMaterial - Quantity
     */
    public void setPezziDanneggiati (final Map<Materiale,Integer> pezzi) {
        this.pezziDanneggiati.clear();
        this.pezziDanneggiati.putAll(pezzi);
    }
    /**
     * Setter method for the shield
     * @param scudo shield
     */
    public void setScudo (final Integer scudo) {
        this.scudi = scudo;
    }
    public String toString() {
        return this.mostraPezziRotti() + "\n" + "Scudi: " + this.scudi;
    }
}
