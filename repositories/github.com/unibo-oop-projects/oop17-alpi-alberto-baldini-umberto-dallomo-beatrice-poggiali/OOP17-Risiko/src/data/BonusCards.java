package data;

/**
 * Enum of bonus cards' data with getter methods.
 */
public enum BonusCards {

    /**
     * types of cards and the number of their copies.
     * cannone.
     */
    CANNON(14, "Cannone"),
    /**
     * fante.
     */
    INFANTRYMAN(14, "Fante"),
    /**
     * caveliere.
     */
    KNIGHT(14, "Cavaliere"),
    /**
     * jolly.
     */
    JOLLY(2, "Jolly");

    /**
     * number of kinds of cards.
     */
    public static final int TYPEOFCARDS = 4;
    //number of bonus armies for type of combo
    /**
     * number of bonus armies in a jolly combo.
     */
    public static final int BONUSJOLLY = 12;
    /**
     * number of bonus armies in a combo with different cards.
     */
    public static final int BONUSDIFF = 10;
    /**
     * number of bonus armies in a combo with 3 copies of a card.
     */
    public static final int BONUSSAME = 8; //tournament rules
    private int nCopies;
    private String name;

    BonusCards(final int nCopies, final String name) {
        this.nCopies = nCopies;
        this.name = name;
    }

    /**
     * 
     * @return number of a card's copies.
     */
    public int getNcards() {
        return this.nCopies;
    }

    /**
     * 
     * @return the name of a card as a string.
     */
    public String getName() {
        return this.name;
    }
}
