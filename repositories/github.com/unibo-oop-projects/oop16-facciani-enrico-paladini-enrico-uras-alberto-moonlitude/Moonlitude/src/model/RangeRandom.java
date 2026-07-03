package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RangeRandom<X> {
    public static Integer MAX_PROB = 100;
    public static Integer MIN_PROB = 0;
    private final X[] valori;
    private final Integer[] probabilita;
    private final List<X> listaProbabilita = new LinkedList<>();
    Random r = new Random();
    
    public RangeRandom(final X[] valori, final Integer[] probabilita) {
        this.valori = valori;
        this.probabilita = probabilita;
        initVettoreProbabilita();
    }
    /**
     * Getter method for the objects of the Range
     * @return the array of the Objects
     */
    public X[] getValori() {
        return this.valori;
    }
    /**
     * Getter method for the associated probabilities
     * @return the array of integer of the percentages
     */
    public Integer[] getProbabilita() {
        return this.probabilita;
    }
    /**
     * Initialize the list of the probability
     */
    private void initVettoreProbabilita() {
        for (Integer i = 0; i < valori.length; i++) {
            for (Integer j = 0; j < probabilita[i]; j++) {
                listaProbabilita.add(valori[i]);
            }
        }
    }
    /**
     * Getter method of the list of Objects of the probability
     * @return the list of objects
     */
    public List<X> getListaProbabilita() {
        return new LinkedList<X>(listaProbabilita);
    }
    /**
     * Get a random object into the list of objects
     * @return an object of the probability object list
     */
    public X getARandom() {
        return listaProbabilita.get(r.nextInt(100));
    }
    /**
     * Get whether or not given a probability it happens
     * @param probabilita probability of success
     * @return 1 = SUCCESS, 0 = FAIL
     */
    public static Integer getSuccesso(final Integer probabilita) {
        RangeRandom<Integer> rr = new RangeRandom<>(new Integer[] {1, 0}, new Integer[] {probabilita, RangeRandom.MAX_PROB - probabilita});
        return rr.getARandom();
    }
    
    

}
