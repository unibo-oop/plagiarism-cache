package it.unibo.monopoli.controller;

/**
 * This contains all possible actions of the game.
 *
 */
public enum Actions {
    /**
     * Action for throw dices.
     */
    ROLL_DICES("ROLL DICES"),
    /**
     * Action for end turn of actual {@link Player}.
     */
    END_OF_TURN("END OF TURN"),
    /**
     * Action for buy an {@link Ownership}.
     */
    BUY("BUY"),
    /**
     * Action for sell an {@link Ownership}.
     */
    SELL("SELL"),
    /**
     * Action for buy house or hotel.
     */
    BUILD("BUILD"),
    /**
     * Action for sell a building.
     */
    SELL_BUILDING("SELL BUILDING"),
    /**
     * Action for buy mortgage an {@link Ownership}..
     */
    MORTGAGE("MORTGAGE"),
    /**
     * Action for revoke mortgage from an {@link Ownership}..
     */
    REVOKE_MORTGAGE("REVOKE MORTGAGE"),
    /**
     * Action for end the game.
     */
    END_OF_THE_GAME("END OF THE GAME");

    private final String text;

    Actions(final String text) {
        this.text = text;
    }

    /**
     * This is a getter of text of Enumeration.
     * 
     * @return -the string of text.
     */
    public String getText() {
        return this.text;
    }

}
