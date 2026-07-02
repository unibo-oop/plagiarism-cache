package it.unibo.burraco.model.cards;

/**
 * Utility class to manage point values and numerical conversions
 * for cards based on Burraco rules.
 */
public final class CardPoint {

    private static final int POINTS_JOLLY = 30;
    private static final int POINTS_TWO = 20;
    private static final int POINTS_ACE = 15;
    private static final int POINTS_HIGH_CARDS = 10;
    private static final int POINTS_LOW_CARDS = 5;

    /**
     * Default constructor.
     */
    public CardPoint() {
        // Empty constructor.
    }

    /**
     * Calculates the score value of a specific card.
     *
     * @param card the card to evaluate
     * @return the points assigned to the card
     */
    public int getCardPoints(final Card card) {
        return switch (card.getValue()) {
            case JOLLY -> POINTS_JOLLY;
            case TWO -> POINTS_TWO;
            case ACE -> POINTS_ACE;
            case KING, QUEEN, JACK, TEN, NINE, EIGHT -> POINTS_HIGH_CARDS;
            case SEVEN, SIX, FIVE, FOUR, THREE -> POINTS_LOW_CARDS;
        };
    }

    /**
     * Converts a card to its numerical rank (1 = Ace, 13 = King).
     * Delegates directly to {@link CardValue#getNumericalValue()}.
     *
     * @param card the card to convert
     * @return an integer from 1 (Ace) to 13 (King)
     * @throws IllegalArgumentException if the card is a Jolly
     */
    public int toInt(final Card card) {
        if (card.getValue().isJolly()) {
            throw new IllegalArgumentException(
                "Cannot convert JOLLY to int: " + card.getValue());
        }
        return card.getValue().getNumericalValue();
    }
}
