package migglione.model.impl;

import java.util.List;

/**
 * The class in which the methods of the Mosquito player are used.
 * Different from the user class as the user gets to choose autonomously.
 */
public final class Mosquito extends User {
    private boolean myTurn;
    private int consecWins;
    private int consecLosses;

    /**
     * Constructor for mosquito player.
     * *there will be more variables for checking the game state*
     * 
     * @param hand the hand of the player
     * @param turn which turn is at the first round
     */
    public Mosquito(final List<Card> hand, final boolean turn) {
        super(hand);
        myTurn = turn;
    }

    @Override
    public int playCard(final String attr, final Card playedCard) {
        final Card cardToPlay = playCard(attr);
        if (myTurn) {
            return super.playCard(bestAttr(cardToPlay), cardToPlay);
        }
        return super.playCard(attr, cardToPlay);
    }

    /**
     * Method to play a card, without an unused parameter.
     * if it's mosquito's turn, he'll decide what attribute to play on.
     * 
     * @param attr the attribute used by the User, only used if it's not mosquito's turn
     * @return the value of the played card in the best attribute, 10 is the max value for a card
     */
    private Card playCard(final String attr) {
        if (myTurn) {
            return playCardFirst();
        } else {
            return playCardSecond(attr);
        }
    }

    @Override
    public List<Card> getPile(final List<Card> pointsWon) {
        if (pointsWon.isEmpty()) {
            setMyTurn(false);
            consecWins = 0;
            consecLosses++;
            if (consecLosses >= 3) {
                setMyTurn(true);
                consecLosses = 0;
            }
            return super.getPile(pointsWon);
        }
        consecLosses = 0;
        consecWins += pointsWon.size() / 2;
        if (consecWins < 3) {
            setMyTurn(true);
        } else {
            setMyTurn(false);
            consecWins = 0;
        }
        return super.getPile(pointsWon);
    }

    /**
     * If it's mosquito's turn, he'll decide what attribute to play on.
     * An algorhythm to understand what could be best is used.
     * 
     * @return the value of the played card in the best attribute, 10 is the max value for a card
     */
    private Card playCardFirst() {
        int maxStat = 0;
        Card bestCard = new Card("placeholder", 0, 0, 0, 0, 0);
        for (final Card c : super.getHand()) {
            final String best = bestAttr(c);
            if (getAttr(best, c) > maxStat) {
                maxStat = getAttr(best, c);
                bestCard = c;
                chooseAttr(best);
            }
            if (maxStat == 10) {
                break;
            }
        }
        return bestCard;
    }

    /**
     * Method to understand what attribute is best for the card, used in playCardFirst.
     * 
     * @param playedCard the card to analyze
     * @return the name of the best attribute for the card, in case of tie the last with the best is chosen
     */
    private String bestAttr(final Card playedCard) {
        if (playedCard.getAttk() > playedCard.getDeff()) {
            if (playedCard.getAttk() > playedCard.getStrength()) {
                if (playedCard.getAttk() > playedCard.getIntelligence() && playedCard.getAttk() > playedCard.getStealth()) {
                    return "Attk";
                }
            } else if (playedCard.getStrength() > playedCard.getIntelligence()
                    && playedCard.getStrength() > playedCard.getStealth()) {
                return "Strength";
            }
        } else if (playedCard.getDeff() > playedCard.getStrength()) {
            if (playedCard.getDeff() > playedCard.getIntelligence()
                && playedCard.getDeff() > playedCard.getStealth()) {
                return "Deff";
            }
        } else if (playedCard.getStrength() > playedCard.getIntelligence()
            && playedCard.getStrength() > playedCard.getStealth()) {
            return "Strength";
        }
        if (playedCard.getIntelligence() > playedCard.getStealth()) {
            return "Intelligence";
        } else {
            return "Stealth";
        }

    }

    /**
     * If mosquito's second, he'll play a card that has the highest attribute.
     * 
     * @param attr the attribute used by the User
     * @return the value of the played card in the specified attribute
     */
    private Card playCardSecond(final String attr) {
        chooseAttr(attr);
        return maxStat(attr, new Card("placeholder", 0, 0, 0, 0, 0));
    }

    /**
     * To understand what card is best to be played, this is the algorhythm.
     * Might be optimized with "if i can't win, might use the worst"
     * 
     * @param attr the attribute used by the User
     * @param emptyCard the card that will be played and removed from hand
     * @return the value of the played card in the specified attribute
     */
    private Card maxStat(final String attr, final Card emptyCard) {
        Card bestCard = emptyCard;
        for (final Card c : super.getHand()) {
            if (getAttr(attr, c) >= getAttr(attr, bestCard)) {
                bestCard = c;
            }
        }
        return bestCard;
    }

    /**
     * To make Mosquito know if it's his turn or not.
     * public for test purposes
     * 
     * @param turn whose turn it is, true for mosquito, false for user
     */
    public void setMyTurn(final boolean turn) {
        this.myTurn = turn;
    }

    /**
     * To know what turn does the mosquito think it is.
     * 
     * @return if it's its turn or not
     */
    public boolean isMyTurn() {
        return myTurn;
    }
}
