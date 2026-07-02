package model.shop;

import model.statistic.Statistics;

public interface MysteryBox {

    /**
     * Create the prize of the mystery box, choosing between four different options.
     * @param stats the game statistics. 
     * @return the string with the prize message. 
     */
    String createPrize(Statistics stats); 

    /**
     * 
     * @return the price of the box.
     */
    int getPrice(); 

}
