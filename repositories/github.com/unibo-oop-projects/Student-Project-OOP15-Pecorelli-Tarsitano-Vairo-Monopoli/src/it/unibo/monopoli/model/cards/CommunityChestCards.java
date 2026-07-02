package it.unibo.monopoli.model.cards;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.ToBePaid;
import it.unibo.monopoli.model.table.Start;

/**
 * This is the enum of all the Community Chest's {@link Card}.
 *
 */
public enum CommunityChestCards {

    /**
     * CARD 1.
     */
    CARD1("RECEIVE DOCTOR'S BILL, PAY $50", 17),
    /**
     * CARD 2.
     */
    CARD2("PAY SCHOOL FEES OF OUR CHILDREN: $50", 18),
    /**
     * CARD 3.
     */
    CARD3("INHERITED $100 FROM A UNCLE", 19, new ToBePaid(100)),
    /**
     * CARD 4.
     */
    CARD4("GET OUT FREE OF JAIL. YOU CAN KEEP THIS CARD AND USE IT WHEN YOU WANT TO", 20),
    /**
     * CARD 5.
     */
    CARD5("PAY HOSPITAL'S BILL. PAY $100", 21),
    /**
     * CARD 6.
     */
    CARD6("FOR THE SALE OF A STOCK OF PRODUCTS YOU OBTAIN $50", 22, new ToBePaid(50)),
    /**
     * CARD 7.
     */
    CARD7("GO DIRECTLY TO JAIL WITHOUT PASSING FROM 'GO'", 23),
    /**
     * CARD 8.
     */
    CARD8("PAY CONTRIBUTIONS TO IMPROVE THE ROADS. YOU HAVE TO PAY $40 FOR EACH HOME AND $115 FOR EACH HOTEL THAT YOU OWN", 24),
    /**
     * CARD 9.
     */
    CARD9("IS YOUR BIRTHDAY. EACH PLAYER GIVES YOU $10", 25),
    /**
     * CARD 10.
     */
    CARD10("GO TO BOX 'GO' AND TAKE $" + Start.getMuchToPick(), 26),
    /**
     * CARD 11.
     */
    CARD11("GET $25 FOR YOUR ADVICE", 27, new ToBePaid(25)),
    /**
     * CARD 12.
     */
    CARD12("BANK RECOGNIZES AN ERROR IN YOUR STATEMENT. COLLECT $200", 28, new ToBePaid(200)),
    /**
     * CARD 13.
     */
    CARD13("YOU WON THE SECOND PRIZE IN A BEAUTY CONTEST. COLLECT $10", 29, new ToBePaid(10)),
    /**
     * CARD 14.
     */
    CARD14("MATURANO THE COUPONS OF YOUR ACTITONS. COLLECT $100", 30, new ToBePaid(100)),
    /**
     * CARD 15.
     */
    CARD15("FEE INCOME WAS REFUNDED. GET $20", 31, new ToBePaid(20)),
    /**
     * CARD 16.
     */
    CARD16("MATURANO INTEREST ON YOUR LIFE INSURANCE: COLLECT $100", 32, new ToBePaid(100));

    private final String description;
    private final int cardId;
    private final Optional<List<Action>> actions;

    CommunityChestCards(final String description, final int id, final Action... actions) {
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
