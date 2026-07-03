package model.Oggetti;

import java.util.HashSet;
import java.util.Set;

public enum CommonCraft {
    COMPUTER("Computer", new Oggetto[] {Materiale.LITIO, Materiale.TRANSISTOR, Materiale.BATTERIA}, new Integer[] {1, 2, 4}, 50),
    GENERATORE("Generatore", new Oggetto[] {Materiale.FERRO, Materiale.BATTERIA}, new Integer[] {20, 2}, 50),
    MOTORE_PRINCIPALE("Motore Principale", new Oggetto[] {Materiale.FERRO, Materiale.GHISA}, new Integer[] {18, 12}, 50),
    PROPULSORE("Propulsore", new Oggetto[] {Materiale.CHEROSENE , Materiale.ALLUMINIO}, new Integer[] {18, 12}, 50);
    
    private final String nome;
    private final Oggetto[] oggettiRichiesti;
    private final Integer[] numeroOggetti;
    private final Integer percentualeSuccesso;
    
    
    private CommonCraft(final String nome, final Oggetto[] oggettiRichiesti, final Integer[] numeroOggetti, final Integer percentualeSuccesso) {
        this.nome = nome;
        this.oggettiRichiesti = oggettiRichiesti;
        this.numeroOggetti = numeroOggetti;
        this.percentualeSuccesso = percentualeSuccesso;
    }
    /**
     * Getter method for the objects required to craft
     * @return an array of required objects
     */
    public Oggetto[] getOggettiRichiesti() {
        Oggetto[] returnOggettiRichiesti = new Oggetto[this.oggettiRichiesti.length];
        for (int i = 0; i < returnOggettiRichiesti.length; i++) {
            returnOggettiRichiesti[i] = this.oggettiRichiesti[i];
        }
        return returnOggettiRichiesti;
    }
    /**
     * Getter method for the objects required to craft
     * @return a set of required objects
     */
    public Set<Oggetto> getOggettiRichiestiComeSet() {
        Set<Oggetto> returnSet = new HashSet<>();
        for(int i = 0; i < this.oggettiRichiesti.length; i++) {
            returnSet.add(this.oggettiRichiesti[i]);
        }
        return returnSet;
    }
    /**
     * Get whether or not the object is craftable with the given materials
     * @param materiali materials to be analyzed
     * @return whether or not the materials given set owns all the needed materials
     */
    public Boolean getSeCraftabileConMateriali(final Set<Materiale> materiali) {
        for (int i = 0; i < this.oggettiRichiesti.length; i++) {
            if (!materiali.contains(this.oggettiRichiesti[i])) {
                return false;
            }
        }
        return true;
    }
    /**
     * Getter method for an array of integers representative of the amounts needed for each object
     * @return the array of integers representative of the amount needed for each object
     */
    public Integer[] getNumeroOggetti() {
        Integer[] numeroOggetti = new Integer[this.numeroOggetti.length];
        for (int i = 0; i < numeroOggetti.length; i++) {
            numeroOggetti[i] = this.numeroOggetti[i];
        }
        return numeroOggetti;
    }
    /**
     * Getter method for the string name
     * @return the string value of the name of the item
     */
    public String getNome() {
        return this.nome;
    }

    public String toString() {
        Integer pos = 0;
        String returnString = this.nome + " = ";
        while(pos < numeroOggetti.length) {
            returnString = returnString.concat(numeroOggetti[pos] + " " + oggettiRichiesti[pos].getNome() + " + ");
            pos ++;
        }
        returnString = returnString.substring(0, returnString.length() -3);
        return returnString;
    }
    /**
     * Getter of the success'percentage
     * @return the percentage of success
     */
    public Integer getPercentualeSuccesso() {
        return this.percentualeSuccesso;
    }
    /**
     * Get all possible craftable items that can be built with a specific item
     * @param materiale item of the research
     * @return the set of all compatible crafting items
     */
    public static Set<CommonCraft> getCraftConMateriale(final Materiale materiale) {
        Set<CommonCraft> returnSet = new HashSet<>();
        for (CommonCraft cf : CommonCraft.values()) {
            if (cf.getOggettiRichiestiComeSet().contains(materiale)) {
                returnSet.add(cf);
            }
        }
        return returnSet;
    }
    /**
     * Get a representation of a CommonCraft
     * @param common common to be analyzed
     * @return the string value
     */
    public static String rappresenta(final CommonCraft common) {
        String returnString = "";
        for(int i = 0; i < common.getOggettiRichiesti().length; i++) {
            returnString = returnString + common.getOggettiRichiesti()[i] + " x" + common.getNumeroOggetti()[i] + " - ";
        }
        returnString = returnString.substring(0, returnString.length() - 3);
        return returnString;
    }
}
