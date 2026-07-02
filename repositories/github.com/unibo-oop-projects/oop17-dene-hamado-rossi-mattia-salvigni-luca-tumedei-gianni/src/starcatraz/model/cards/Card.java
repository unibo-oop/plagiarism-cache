package starcatraz.model.cards;

/**
 * Represents a Starcatraz Card.
 */
public interface Card {

    /**
     * @return the Card Type
     */
    CardType getType();

    /**
     * @return the Card Color
     */
    CardColor getColor();

    /**
     * @return the Card value
     */
    int getValue();

    int hashCode();

    boolean equals(Object obj);
}
