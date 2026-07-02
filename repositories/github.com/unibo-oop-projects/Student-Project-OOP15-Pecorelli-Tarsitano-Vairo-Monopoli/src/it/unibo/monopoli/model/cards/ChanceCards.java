package it.unibo.monopoli.model.cards;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.MoveUpTo;
import it.unibo.monopoli.model.actions.ToBePaid;
import it.unibo.monopoli.model.table.Start;

/**
 * This is the enum of all the Chance's {@link Card}.
 *
 */
public enum ChanceCards {

    /**
     * CARD 1.
     */
    CARD1("YOU WILL BE PAID $50 FOR YOUR WORK", 1, new ToBePaid(50)),
    /**
     * CARD 2.
     */
    CARD2("GO TO PARK LANE: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), 2),
    /**
     * CARD 3.
     */
    CARD3("ADVANCE TO THE NEAREST STATION: IF IT'S FREE, YOU CAN BUY IT; IF IT IS OWNED BY ANOTHER PLAYER, PAY HIM TWICE THE PRICE THAT MATTER",
            3, MoveUpTo.theNearestStation()),
    /**
     * CARD 4.
     */
    CARD4("GET OUT FREE OF JAIL. YOU CAN KEEP THIS CARD AND USE IT WHEN YOU WANT TO", 4),
    /**
     * CARD 5.
     */
    CARD5("FINE FOR SPEEDING. PAY $20", 5),
    /**
     * CARD 6.
     */
    CARD6("THE BANK WILL YOU PAY A BONUS OF $50", 6, new ToBePaid(50)),
    /**
     * CARD 7.
     */
    CARD7("GO DIRECTLY TO JAIL WITHOUT PASSING FROM 'GO'", 7),
    /**
     * CARD 8.
     */
    CARD8("PERFORM MAINTENANCE WORK ON ALL OUR BUILDINGS. YOU HAVE TO PAY $25 FOR EACH HOME AND $100 FOR EACH HOTEL THAT YOU OWN", 8),
    /**
     * CARD 9.
     */
    CARD9("YOU HAVE BEEN PROMOTED TO THE PRESIDENCY OF THE BOARD OF DIRECTORS. YOU HAVE TO PAY 50 TO ANY PLAYER", 9),
    /**
     * CARD 10.
     */
    CARD10("GO TO BOX 'GO' AND TAKE $" + Start.getMuchToPick(), 10),
    /**
     * CARD 11.
     */
    CARD11("GO TO THE FENCHURCH ST. STATION: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), 11),
    /**
     * CARD 12.
     */
    CARD12("ADVANCE TO THE NEAREST STATION: IF IT'S FREE, YOU CAN BUY IT; IF IT IS OWNED BY ANOTHER PLAYER, PAY HIM TWICE THE PRICE THAT MATTER",
            12, MoveUpTo.theNearestStation()),
    /**
     * CARD 13.
     */
    CARD13("GO TO THE TRAFALGAR SQUARE: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), 13),
    /**
     * CARD 14.
     */
    CARD14("GO TO THE WHITEHALL: IF PASS FROM 'GO', TAKE $" + Start.getMuchToPick(), 14),
    /**
     * CARD 15.
     */
    CARD15("ADVANCE TO THE NEAREST STATION: IF IT'S FREE, YOU CAN BUY IT; IF IT IS OWNED BY ANOTHER PLAYER, PAY THE OWNER 10 TIMES THE NUMBER RELEASED WITH DICES",
            15, MoveUpTo.theNearestStation()),
    /**
     * CARD 16 .
     */
    CARD16("MATURANO THE COUPONS OF YOUR REAL ESTATE FUNDS: COLLECT $150", 16, new ToBePaid(150));

    private final String description;
    private final int cardId;
    private final Optional<List<Action>> actions;

    ChanceCards(final String description, final int id, final Action... actions) {
        this.description = description;
        this.cardId = id;
        this.actions = Optional.of(Arrays.asList(actions));
    }

    /**
     * Returns {@link Card}'s ID.
     * 
     * @return {@link Card}'s ID
     */
    public int getID() {
        return this.cardId;
    }

    /**
     * Returns {@link Card}'s description.
     * 
     * @return {@link Card}'s description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns an {@link Optional} {@link List} of {@link Action}s that the {@link Card} have to do.
     * 
     * @return a {@link Optional} {@link List} of {@link Card}'s {@link Action}s
     */
    public Optional<List<Action>> getActions() {
        return this.actions;
    }
}
