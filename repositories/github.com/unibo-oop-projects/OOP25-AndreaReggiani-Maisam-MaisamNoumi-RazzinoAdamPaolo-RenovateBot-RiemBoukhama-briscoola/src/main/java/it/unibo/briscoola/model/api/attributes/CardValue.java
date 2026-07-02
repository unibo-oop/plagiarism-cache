package it.unibo.briscoola.model.api.attributes;

/**
 * in Briscola the cards are going from 1 to 7
 * after there are jack, horse, king
 * the value level are 2,4,5,6,7,jack,horse,king,tree,ace
 * card(valuePoints,valueHierarchy).
 * 
 * @author Andrea Reggiani
 */
public enum CardValue {

    /** 
     * Two - 0 points, power 1.
     */
    TWO(0, 1), 

    /** 
     * Four - 0 points, power 2.
     */
    FOUR(0, 2), 

    /** 
     * Five - 0 points, power 3. 
     */
    FIVE(0, 3), 

    /** 
     * Six - 0 points, power. 
     */
    SIX(0, 4), 

    /**
     * Seven - 0 points, power 5.
     */
    SEVEN(0, 5), 

    /** 
     * Jack - 2 points, power 6.
     */
    JACK(2, 6), 

    /** 
     * Horse - 3 points, power 7.
     */
    HORSE(3, 7), 

    /** 
     * King - 4 points, power 8.
     */
    KING(4, 8), 

    /** 
     * Three - 10 points, power 9.
     */
    THREE(10, 9), 

    /** 
     * Ace - 11 points, power 10. 
     */
    ACE(11, 10); 

    private final int pointCard;
    private final int powerCard;

    /**
     * Constructor for Cards.
     * 
     * @param pointCard the points assigned to the card value
     * @param powerCard the hierarchical power of the car (high beats low)
     */
    CardValue(final int pointCard, final int powerCard) {
        this.pointCard = pointCard;
        this.powerCard = powerCard;
    }

    /**
     * Return the point of the card.
     * 
     * @return the points value of the card
     */
    public int getPointCard() {
        return this.pointCard;
    }

    /**
     * Return the Hierarchy Value of the card.
     * 
     * @return the Power value opr valueHierarchy of the card
     */
    public int getPowerCard() {
        return this.powerCard;
    }
}
