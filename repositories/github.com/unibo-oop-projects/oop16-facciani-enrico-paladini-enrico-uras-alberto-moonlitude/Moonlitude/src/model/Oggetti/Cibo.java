package model.Oggetti;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Posizione;
import model.RangeRandom;
import model.Stanza.CommonPianta;

public enum Cibo implements Oggetto, Serializable {
    FAGIOLO("Fagioli", 1, CommonPianta.FAGIOLI, new LinkedList<>(Arrays.asList(Posizione.LAGO)), 55),
    CECE("Cece", 1, CommonPianta.CECI, new LinkedList<>(Arrays.asList(Posizione.LAGO)), 55),
    MELA("Mela", 5, CommonPianta.MELO, new LinkedList<>(Arrays.asList(Posizione.LAGO)), 55),
    PERA("Pera", 5, CommonPianta.PERO, new LinkedList<>(Arrays.asList(Posizione.LAGO)), 55),
    BANANA("Banana", 6, CommonPianta.BANANO, new LinkedList<>(Arrays.asList(Posizione.LAGO)), 55),
    NOCE("Noce", 2, CommonPianta.JUNGLANS, new LinkedList<>(Arrays.asList(Posizione.LAGO)), 55),
    NOCCIOLINA("Nocciolina", 1, CommonPianta.ARACHIDE, new LinkedList<>(Arrays.asList(Posizione.LAGO)), 55),
    ZUCCA("Zucca", 25, CommonPianta.ZUCCHE, new LinkedList<>(Arrays.asList(Posizione.LAGO)), 55),
    ZUCCHINA("Zucchina", 13, CommonPianta.ZUCCHINE, new LinkedList<>(Arrays.asList(Posizione.LAGO)), 55),
    MANDORLA("Mandorla", 2, CommonPianta.MANDORLO, new LinkedList<>(Arrays.asList(Posizione.LAGO)), 55),
    POMODORO("Pomodoro", 7, CommonPianta.POMODORI, new LinkedList<>(Arrays.asList(Posizione.LAGO)), 55);
    
    private static final long serialVersionUID = -8239983460567730812L;
    private final String nome;
    private final Integer diminuzioneFame;
    private final CommonPianta pianta;
    private final List<Posizione> lista;
    private final Integer probabilitaOraria;
    
    private Cibo(final String nome, final Integer diminuzioneFame, final CommonPianta pianta, final List<Posizione> lista, Integer probabilitaOraria) {
        this.nome = nome;
        this.diminuzioneFame = diminuzioneFame;
        this.pianta = pianta;
        this.lista = lista;
        this.probabilitaOraria = probabilitaOraria;
    }

    @Override
    public String getNome() {
        return nome;
    }

    /**
     * Getter method for the hunger decrease
     * @return the integer value of decrease
     */
    public Integer getDiminuzioneFame() {
        return diminuzioneFame;
    }
    
    @Override
    public String toString() {
        return "Nome: " + this.nome + " Diminuzione fame: " + this.diminuzioneFame;
    }
    
    /**
     * Getter method for the plant
     * @return CommonPianta connected with the food
     */
    public CommonPianta getPianta() {
        return this.pianta;
    }
    
    /**
     * Getter method places in which the object can be found
     * @return a list of Posizione in which the object is collectable
     */
    public List<Posizione> getLista() {
        return this.lista;
    }
    
    /**
     * Getter percentage in which the food can be found every hour of the exploration
     * @return the integer value of the percentage
     */
    public Integer getProbabilitaOraria() {
        return this.probabilitaOraria;
    }
    
    /**
     * Obtain the list of item that can be found in a position
     * @param pos position of the research
     * @return the list of item that can be found in that specific position
     */
    private static List<Cibo> getListaOggettiByPosizione(final Posizione pos) {
        List<Cibo> list = new LinkedList<>();
        for (Cibo mat : Cibo.values()) {
            if(mat.getLista().contains(pos)) {
                list.add(mat);
            }
        }
        return list;
    }
    /**
     * Get how many object will be found given a probability by hour and the hours of the exploration
     * @param probabilita probability per hour to find one of the object
     * @param ore hours of exploration
     * @return the amount of item found
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
     * Get a map of all food collected during an exploration in a given position for a given amount of time
     * @param pos position of the exploration
     * @param ore hours till the end of the exploration
     * @return A map that describes for each object the amount found
     */
    public static Map<Cibo, Integer> getOggettoByPosizione(final Posizione pos, final Integer ore) {
        List<Cibo> list = getListaOggettiByPosizione(pos);
        Map<Cibo, Integer> materialiOttenuti = new HashMap<>();
        list.stream().forEach(e -> {
            Integer quanto = getVerificatiAdOre(e.getProbabilitaOraria(), ore);
            if (quanto > 0) {
                materialiOttenuti.put(e, quanto);
            }
        });
        return materialiOttenuti;
    }
}
