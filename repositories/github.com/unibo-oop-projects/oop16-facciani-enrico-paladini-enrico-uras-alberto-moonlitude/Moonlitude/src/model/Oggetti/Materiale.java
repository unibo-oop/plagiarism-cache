package model.Oggetti;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Posizione;
import model.RangeRandom;

public enum Materiale implements Oggetto, Serializable {
    FERRO("Ferro", new LinkedList<>(Arrays.asList(Posizione.LAGO)), 65),
    ALLUMINIO("Alluminio", new LinkedList<>(Arrays.asList(Posizione.BOSCO)), 60),
    LITIO("Litio", new LinkedList<>(Arrays.asList(Posizione.LAGO)), 15),
    TRANSISTOR("Transistor", new LinkedList<>(Arrays.asList(Posizione.ACCAMPAMENTO)), 25),
    GHISA("Ghisa", new LinkedList<>(Arrays.asList(Posizione.BOSCO)), 25),
    CHEROSENE("Cherosene", new LinkedList<>(Arrays.asList(Posizione.ACCAMPAMENTO)), 45),
    
    DRONE("Drone", new LinkedList<>(Arrays.asList(Posizione.ACCAMPAMENTO, Posizione.LAGO, Posizione.BOSCO)), 20),
    MATTONE("Mattone", new LinkedList<>(Arrays.asList(Posizione.BOSCO)), 70),
    BATTERIA("Batteria", new LinkedList<>(Arrays.asList(Posizione.ACCAMPAMENTO)), 40),
    CONTENITORE_VUOTO("Contenitore vuoto", new LinkedList<>(Arrays.asList(Posizione.LAGO)), 20),
    SFERA_ENERGIA("Sfera di energia", new LinkedList<>(Arrays.asList(Posizione.ACCAMPAMENTO)), 50),
    
    COMPUTER("Computer", new LinkedList<>(), 0),
    GENERATORE("Generatore", new LinkedList<>(), 0),
    MOTORE_PRINCIPALE("Motore principale", new LinkedList<>(), 0),
    PROPULSORE("Propulsore", new LinkedList<>(), 0);
    
    private static final long serialVersionUID = -8979983484447730812L;
    private final String nome;
    private final List<Posizione> lista;
    private final Integer probabilitaOraria;
    
    private Materiale(final String nome, final List<Posizione> lista, final Integer probabilitaOraria) {
        this.nome = nome;
        this.lista = lista;
        this.probabilitaOraria = probabilitaOraria;
    }

    @Override
    public String getNome() {
        return nome;
    }
    
    public String toString() {
        return "Nome: " + this.nome;
    }
    /**
     * Get all positions in which the material can be found
     * @return a list of positions in which the material can be found
     */
    public List<Posizione> getLista() {
        return this.lista;
    }
    /**
     * Get Percentage of the probability of the item to be found in an hour
     * @return the integer value of the probability
     */
    public Integer getProbabilitaOraria() {
        return this.probabilitaOraria;
    }
    /**
     * Get all the materials that can be found in a given position
     * @param pos position to be analyzed
     * @return a list of all materials that can be found
     */
    private static List<Materiale> getListaOggettiByPosizione(final Posizione pos) {
        List<Materiale> list = new LinkedList<>();
        for (Materiale mat : Materiale.values()) {
            if(mat.getLista().contains(pos)) {
                list.add(mat);
            }
        }
        return list;
    }
    /**
     * Get how many items will be gather given the percentage per hour and the hours
     * @param probabilita probabilities that an item will be found in an hour
     * @param ore hours of the research
     * @return the integer value of the items found
     */
    private static Integer getVerificatiAdOre(final Integer probabilita, final Integer ore) {
        Integer quanto = 0;
        for (int i = 0; i < ore; i++) {
            if (RangeRandom.getSuccesso(probabilita) == 1) {
                quanto ++;
            }
        }
        return quanto;
    }
    /**
     * Get a collection map of all the items found given a position and the hours of the exploration
     * @param pos position to be analyzed
     * @param ore hours of the exploration
     * @return the map of all the materials-amounts
     */
    public static Map<Materiale, Integer> getOggettoByPosizione(final Posizione pos, final Integer ore) {
        List<Materiale> list = getListaOggettiByPosizione(pos);
        Map<Materiale, Integer> materialiOttenuti = new HashMap<>();
        list.stream().forEach(e -> {
            Integer quanto = getVerificatiAdOre(e.getProbabilitaOraria(), ore);
            if (quanto > 0) {
                materialiOttenuti.put(e, quanto);
            }
        });
        return materialiOttenuti;
    }
    /**
     * Get a collection map of all the items found having a percentage increase
     * @param aumentoProbabilita percentage increase
     * @param ore hours of the exploration
     * @return the map of all the materials-amounts
     */
    public static Map<Materiale, Integer> getOggettiConAumentoProbabilita(final Integer aumentoProbabilita, final Integer ore) {
        List<Materiale> list = new LinkedList<Materiale>(Arrays.asList(Materiale.values()));
        Map<Materiale, Integer> materialiOttenuti = new HashMap<>();
        list.stream().forEach(e -> {
            Integer quanto = getVerificatiAdOre(e.getProbabilitaOraria() + aumentoProbabilita, ore);
            if (quanto > 0) {
                materialiOttenuti.put(e, quanto);
            }
        });
        return materialiOttenuti;
    }
}
