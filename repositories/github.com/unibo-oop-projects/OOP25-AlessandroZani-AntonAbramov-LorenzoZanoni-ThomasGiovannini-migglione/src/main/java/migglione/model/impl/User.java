package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;

import migglione.model.api.Player;

/**
 * The class in which the methods of the user player are used.
 * The user gets to choose autonomously what card to play and what attribute to use.
 */
public class User implements Player {
    private final List<Card> hand = new ArrayList<>();
    private String chosenAttr;
    private final String name;
    private final PointsPile pile = new PointsPile();
    private Card lastPlayed;

    /**
     * Constructor for the Mosquito player(or anonymous).
     * 
     * @param startHand the hand of the player at the start of the match
     */
    public User(final List<Card> startHand) {
        hand.addAll(startHand);
        this.name = "Player";
    }

    /**
     * Constructor for the User player with a specified name.
     * 
     * @param startHand the hand of the player at the start of the match
     * @param name the name of the player
     */
    public User(final List<Card> startHand, final String name) {
        hand.addAll(startHand);
        this.name = name;
    }

    /**
     * Method used for playing a specified card.
     * Needs the Integer representing the card in the Cards.java class,
     * then removes the card from the player's hand.
     * Subclasses that override this method must:
     * call super.playCard(attr, playedCard) to preserve this behavior
     * 
     * @param attr the attribute decided to determine who wins
     * @param playedCard the played card who's going to be removed from the hand
     * @return the value of card's attribute
     */
    @Override
    public int playCard(final String attr, final Card playedCard) {
        lastPlayed = playedCard;
        hand.remove(playedCard);
        return getAttr(attr, playedCard);
    }

    @Override
    public final List<Card> getHand() {
        return List.copyOf(hand);
    }

    @Override
    public final void drawCard(final Card drawnCard) {
        if (hand.size() < 3) {
            hand.addLast(drawnCard);
        }
    }

    @Override
    public final void chooseAttr(final String attr) {
        chosenAttr = attr;
    }

    @Override
    public final String getAttr() {
        return chosenAttr;
    }

    /**
     * A method to understand what attributes is being searched.
     * 
     * @param attr used to know what attribute to use
     * @param playedCard the card we want to know the value of
     * @return the value of the specified card's attribute
     */
    protected int getAttr(final String attr, final Card playedCard) {
        switch (attr) {
            case "Attk":
                return playedCard.getAttk();
            case "Deff":
                return playedCard.getDeff();
            case "Strength":
                return playedCard.getStrength();
            case "Intelligence":
                return playedCard.getIntelligence();
            case "Stealth":
                return playedCard.getStealth();
            default:
                throw new IllegalArgumentException("Invalid attribute: " + attr);
        }
    }

    /**
     * Method to get the points of the player.
     * Subclasses that override this method must:
     * call super.getPile(pointsWon) to preserve this behavior
     * 
     * @param pointsWon the cards to add to pile
     * @return the pile of points won
     */
    @Override
    public List<Card> getPile(final List<Card> pointsWon) {
        for (final Card point : pointsWon) {
            pile.addPile(point);
        }
        return pile.getPile();
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Card getPlayedCard() {
        return lastPlayed;
    }
}
